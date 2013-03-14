package lv.vitalijs.shakels.microlending.services.impl;

import java.util.Calendar;
import java.util.Date;

import lv.vitalijs.shakels.microlending.bo.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.RiskService;
import lv.vitalijs.shakels.microlending.utils.MicrolandingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskServiceImpl implements RiskService {
	
	private static final int RISK_TIME_MIN_LIMIT = 0;
	private static final int RISK_TIME_MAX_LIMIT = 7;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	public boolean isHighRisk(Loan loan) {
		return (isLoanMadeFromMidnightTillSeven(loan) && isLoanWithMaximumAmount(loan)) || is3rdLoanFromSameIP(loan);
	}
	
	private boolean isLoanMadeFromMidnightTillSeven(Loan loan) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(loan.getCreationDate());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);	
		return hours >= RISK_TIME_MIN_LIMIT && hours <= RISK_TIME_MAX_LIMIT;
	}
	
	private boolean isLoanWithMaximumAmount(Loan loan) {
		return loan.getAmount().equals(MicrolandingUtils.MAX_LOAN_AMOUT);
	}
	
	private boolean is3rdLoanFromSameIP(Loan loan) {
		return false;
	}

}
