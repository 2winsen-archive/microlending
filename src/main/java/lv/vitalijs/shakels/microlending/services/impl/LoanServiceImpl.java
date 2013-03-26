package lv.vitalijs.shakels.microlending.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lv.vitalijs.shakels.microlending.constants.MicrolandingConstants;
import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.LoanService;

import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanServiceImpl implements LoanService {

	private static BigDecimal INTEREST_MULTIPLICATION_FACTOR = new BigDecimal("1.5");

	private LoanRepository loanRepository;

	@Autowired
	public void setLoanRepository(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}

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
		loan.setDueDate(loan.getDueDate().plusDays(DateTimeConstants.DAYS_PER_WEEK));
		loan.setTerm(loan.getTerm() + DateTimeConstants.DAYS_PER_WEEK);
		updateInterest(loan, INTEREST_MULTIPLICATION_FACTOR);
		loan.setExtended(true);
		loanRepository.saveLoan(loan);
		includeDatesInMillis(loan);
		return loan;
	}

	private BigDecimal calculateReturnAmount(final Loan loan) {
		BigDecimal result = loan.getAmount().multiply(loan.getInterest()).add(loan.getAmount());
		return result.setScale(MicrolandingConstants.NUM_DECIMALS, RoundingMode.HALF_UP);
	}

	private DateTime calculateDueDate(final Loan loan) {
		return loan.getCreationDate().plusDays(loan.getTerm());
	}

	private BigDecimal calculateInterest(final Loan loan) {
		return MicrolandingConstants.INTEREST.multiply(new BigDecimal(loan.getTerm()));
	}

	private Loan includeDatesInMillis(Loan loan) {
		loan.setDueDateMillis(loan.getDueDate().getMillis());
		loan.setCreationDateMillis(loan.getCreationDate().getMillis());
		return loan;
	}

	private Loan updateInterest(Loan loan, BigDecimal interestFactor) {
		loan.setInterest(loan.getInterest().multiply(interestFactor));
		loan.setReturnAmount(calculateReturnAmount(loan));
		return loan;
	}

}
