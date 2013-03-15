package lv.vitalijs.shakels.microlending.services.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import lv.vitalijs.shakels.microlending.bo.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.LoanService;
import lv.vitalijs.shakels.microlending.utils.MicrolandingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public void processLoan(Loan loan) {
		loan.setInterest(calculateInterest(loan));
		loan.setReturnAmount(calculateReturnAmount(loan));
		loan.setDueDate(calculateDueDate(loan));
		loanRepository.saveLoan(loan);
	}
	
	private BigDecimal calculateReturnAmount(Loan loan) {
		return loan.getAmount().multiply(loan.getInterest()).add(loan.getAmount());
	}
	
	private Date calculateDueDate(Loan loan) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(loan.getCreationDate());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + loan.getTerm());
		return calendar.getTime();
	}
	
	private BigDecimal calculateInterest(Loan loan) {
		return MicrolandingUtils.INTEREST.multiply(new BigDecimal(loan.getTerm()));
	}

}
