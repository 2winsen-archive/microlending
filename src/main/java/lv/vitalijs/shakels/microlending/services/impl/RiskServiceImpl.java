package lv.vitalijs.shakels.microlending.services.impl;

import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.RiskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskServiceImpl implements RiskService {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	public boolean isHighRisk() {
		return isLoanAfterMidnight() || is3rdLoanFromSameIP();
	}
	
	private boolean isLoanAfterMidnight() {
		return false;
	}
	
	private boolean is3rdLoanFromSameIP() {
		return false;
	}

}
