package lv.vitalijs.shakels.microlending.services.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.LoanService;
import lv.vitalijs.shakels.microlending.utils.MicrolandingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public void processLoan(Loan loan) throws DataAccessException {
		loan.setInterest(calculateInterest(loan));
		loan.setReturnAmount(calculateReturnAmount(loan));
		loan.setDueDate(calculateDueDate(loan));
		loanRepository.saveLoan(loan);
	}
	
	@Override
	public List<Loan> getAllLoans() throws DataAccessException {
		List<Loan> loans = loanRepository.getAllLoans();
		for (Loan loan : loans) {
			includeDatesInMillis(loan);
		}
		return loans;
	}
	
	private BigDecimal calculateReturnAmount(final Loan loan) {
		return loan.getAmount().multiply(loan.getInterest()).add(loan.getAmount());
	}
	
	private Date calculateDueDate(final Loan loan) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(loan.getCreationDate());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + loan.getTerm());
		return calendar.getTime();
	}
	
	private BigDecimal calculateInterest(final Loan loan) {
		return MicrolandingUtils.INTEREST.multiply(new BigDecimal(loan.getTerm()));
	}
	
	private Loan includeDatesInMillis(Loan loan) {
		loan.setDueDateMillis(loan.getDueDate().getTime());
		loan.setCreationDateMillis(loan.getCreationDate().getTime());
		return loan;
	}

}
