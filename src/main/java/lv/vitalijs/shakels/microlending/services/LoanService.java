package lv.vitalijs.shakels.microlending.services;

import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;

import org.springframework.dao.DataAccessException;

public interface LoanService {
	
	void processLoan(Loan loan) throws DataAccessException;
	
	List<Loan> getAllLoans() throws DataAccessException;

}
