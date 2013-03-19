package lv.vitalijs.shakels.microlending.repositories.impl;

import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LoanRepositoryImpl implements LoanRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveLoan(final Loan loan) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		if (loan.getId() == null) {
			session.persist(loan);
		} else {
			session.merge(loan);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Loan> getLoansByIP(final String ip) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("loan.getLoansByIp").setParameter(
				"ipAddress", ip);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Loan> getAllLoans() throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("loan.getAllLoans");
		return query.list();
	}

	public Loan getLoanbyId(Long id) throws HibernateException {
		Session session = sessionFactory.getCurrentSession();
		return (Loan) session.get(Loan.class, id);
	}

}
