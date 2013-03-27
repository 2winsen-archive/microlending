package lv.vitalijs.shakels.microlending.repositories.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.generators.TestDataGenerator;
import lv.vitalijs.shakels.microlending.spring.config.ApplicationContextTestConfig;
import lv.vitalijs.shakels.microlending.spring.config.PersistenceTestConfig;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextTestConfig.class, PersistenceTestConfig.class })
@Transactional
public class LoanRepositoryImplTest {

	private SessionFactory sessionFactory;
	private LoanRepositoryImpl repository;
	private static List<Loan> testLoans;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		repository = new LoanRepositoryImpl();
		repository.setSessionFactory(sessionFactory);
	}

	@Before
	public void setUp() {
		testLoans = TestDataGenerator.generateValidLoansList();
		repository.saveLoan(testLoans.get(0));
		repository.saveLoan(testLoans.get(1));
	}

	@After
	public void teadDown() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		testLoans = null;
	}

	@Test
	public void saveLoanTest() {
		
		List<Loan> loans = repository.getAllLoans();
		assertEquals(testLoans.get(0), loans.get(0));
	}

	@Test
	public void getLoansByIPTest() {
		List<Loan> loans = repository.getLoansByIP("144.30.30.30");
		assertEquals(loans.get(0), loans.get(0));
		assertEquals(loans.size(), 2);
	}

	@Test
	public void getAllLoans() {
		List<Loan> loans = repository.getAllLoans();
		assertEquals(loans.size(), 2);
	}

	@Test
	public void getLoanbyId() {
		Loan loan = repository.getLoanbyId(3L);
		Assert.assertEquals(loan, testLoans.get(0));
	}

}
