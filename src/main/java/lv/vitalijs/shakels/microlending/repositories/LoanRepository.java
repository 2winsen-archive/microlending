package lv.vitalijs.shakels.microlending.repositories;

import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoanRepository {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void saveLoan(final Loan loan) throws DataAccessException {
		hibernateTemplate.persist(loan);
	}

	@SuppressWarnings("unchecked")
	public List<Loan> getLoansByIP(final String ip) throws DataAccessException {
		List<Loan> loans = null;
		String query = "select loan from Loan loan where loan.ipAddress=?";
		Object queryParam = ip;
		loans = hibernateTemplate.find(query, queryParam);
		return loans;
	}
	
	@SuppressWarnings("unchecked")
	public List<Loan> getAllLoans() throws DataAccessException {
		List<Loan> loans = null;
		String query = "select loan from Loan loan";
		loans = hibernateTemplate.find(query);
		return loans;
	}
	
	public Loan getLoanbyId(Long id) throws DataAccessException {
		return (Loan) hibernateTemplate.get(Loan.class.getName(), id);
	}
	
	public void updateLoan(Loan loan) throws DataAccessException {
		hibernateTemplate.update(loan);
	}

}
