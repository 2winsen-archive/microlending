package lv.vitalijs.shakels.microlending.repositories;

import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;

import org.hibernate.HibernateException;

public interface LoanRepository {

	void saveLoan(final Loan loan) throws HibernateException;
	
	List<Loan> getLoansByIP(final String ip) throws HibernateException;
	
	List<Loan> getAllLoans() throws HibernateException;
	
	Loan getLoanbyId(Long id) throws HibernateException;
}
