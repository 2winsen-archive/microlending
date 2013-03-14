package lv.vitalijs.shakels.microlending.services.impl;

import lv.vitalijs.shakels.microlending.bo.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public void processLoan(Loan loan) {
		// add calculation logic
	}

}
