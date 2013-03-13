package lv.vitalijs.shakels.microlending.repositories;

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
	
	
	public Loan saveLoan(Loan loan) {
		return null;
	}
	

}
