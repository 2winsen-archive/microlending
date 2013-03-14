package lv.vitalijs.shakels.microlending.services;

import lv.vitalijs.shakels.microlending.bo.Loan;

public interface LoanService {
	
	Loan processLoan(Loan loan); 

}
