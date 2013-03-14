package lv.vitalijs.shakels.microlending.repositories;

import java.util.ArrayList;
import java.util.List;

import lv.vitalijs.shakels.microlending.bo.Loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class LoanRepository {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void saveLoan(Loan loan) {
		hibernateTemplate.persist(loan);
	}

	@SuppressWarnings("unchecked")
	public List<Loan> getLoansByIP(String ip) {
		List<Loan> loans = new ArrayList<Loan>();
		String query = "select loan from Loan loan where loan.ipAddress=?";
		Object queryParam = ip;
		loans = hibernateTemplate.find(query, queryParam);
		return loans;
	}

}
