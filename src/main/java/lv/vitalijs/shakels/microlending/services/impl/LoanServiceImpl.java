package lv.vitalijs.shakels.microlending.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lv.vitalijs.shakels.microlending.constants.MicrolandingConstants;
import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.LoanService;
import lv.vitalijs.shakels.microlending.utils.DateUtils;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanServiceImpl implements LoanService {

	private static BigDecimal INTEREST_MULTIPLICATION_FACTOR = new BigDecimal("1.5"); 
	
	@Autowired
	private LoanRepository loanRepository;

	@Transactional
	@Override
	public void processLoan(Loan loan) throws HibernateException {
		loan.setInterest(calculateInterest(loan));
		loan.setReturnAmount(calculateReturnAmount(loan));
		loan.setDueDate(calculateDueDate(loan));
		loanRepository.saveLoan(loan);
	}

	@Transactional
	@Override
	public List<Loan> getAllLoans() throws HibernateException {
		List<Loan> loans = loanRepository.getAllLoans();
		for (Loan loan : loans) {
			includeDatesInMillis(loan);
		}
		return loans;
	}

	@Transactional
	@Override
	public Loan extendLoan(Long id) throws HibernateException {
		Loan loan = loanRepository.getLoanbyId(id);
		loan.setDueDate(DateUtils.shiftDateByDays(loan.getDueDate(), DateUtils.DAYS_IN_A_WEEK));
		loan.setTerm(loan.getTerm() + DateUtils.DAYS_IN_A_WEEK);
		updateInterest(loan, INTEREST_MULTIPLICATION_FACTOR);
		loan.setExtended(true);
		loanRepository.saveLoan(loan);
		return loan;
	}

	private BigDecimal calculateReturnAmount(final Loan loan) {
		BigDecimal result = loan.getAmount().multiply(loan.getInterest()).add(loan.getAmount());
		return result.setScale(MicrolandingConstants.NUM_DECIMALS, RoundingMode.HALF_UP);
	}

	private Date calculateDueDate(final Loan loan) {
		return DateUtils.shiftDateByDays(loan.getCreationDate(), loan.getTerm());
	}

	private BigDecimal calculateInterest(final Loan loan) {
		return MicrolandingConstants.INTEREST.multiply(new BigDecimal(loan.getTerm()));
	}

	private Loan includeDatesInMillis(Loan loan) {
		loan.setDueDateMillis(loan.getDueDate().getTime());
		loan.setCreationDateMillis(loan.getCreationDate().getTime());
		return loan;
	}
	
	private Loan updateInterest(Loan loan, BigDecimal interestFactor) {
		loan.setInterest(loan.getInterest().multiply(interestFactor));
		loan.setReturnAmount(calculateReturnAmount(loan));
		return loan;
	}

}
