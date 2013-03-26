package lv.vitalijs.shakels.microlending.repositories.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.spring.config.ApplicationContextTestConfig;
import lv.vitalijs.shakels.microlending.spring.config.PersistenceTestConfig;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
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

	@BeforeClass
	public static void setUpBeforeclass() {
		Calendar dueDateCal = Calendar.getInstance();
		dueDateCal.set(2013, 3, 19);

		Calendar creationDateCal = Calendar.getInstance();
		creationDateCal.set(2013, 3, 19);

		Loan testLoan1 = new Loan();
		testLoan1.setAmount(new BigDecimal("1.1"));
		testLoan1.setTerm(10);
//		testLoan1.setCreationDate(creationDateCal.getTime());
//		testLoan1.setDueDate(dueDateCal.getTime());
		testLoan1.setExtended(false);
		testLoan1.setInterest(new BigDecimal("1.1"));
		testLoan1.setIpAddress("1.1.1.1");
		testLoan1.setReturnAmount(new BigDecimal("1.1"));

		Loan testLoan2 = new Loan();
		testLoan2.setAmount(new BigDecimal("2.1"));
		testLoan2.setTerm(10);
//		testLoan2.setCreationDate(creationDateCal.getTime());
//		testLoan2.setDueDate(dueDateCal.getTime());
		testLoan2.setExtended(false);
		testLoan2.setInterest(new BigDecimal("2.1"));
		testLoan2.setIpAddress("2.2.2.2");
		testLoan2.setReturnAmount(new BigDecimal("2.1"));

		testLoans = new ArrayList<Loan>();
		testLoans.add(testLoan1);
		testLoans.add(testLoan2);
	}

	@Before
	public void setUp() {
		repository.saveLoan(testLoans.get(0));
		repository.saveLoan(testLoans.get(1));
	}

	@After
	public void teadDown() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

	@Test
	public void saveLoanTest() {
		repository = new LoanRepositoryImpl();
		repository.setSessionFactory(sessionFactory);
		List<Loan> loans = repository.getAllLoans();
		Assert.assertEquals("Created and persisted Laons should be equal", testLoans.get(0), loans.get(0));
	}

	@Test
	@Rollback(true)
	public void getLoansByIPTest() {
		Assert.assertEquals("Created and persisted Laons should be equal", testLoans.get(1),
				repository.getLoansByIP("2.2.2.2").get(0));
	}

	@Test
	public void getAllLoans() {
		Assert.assertEquals("Created and persisted Laons should be equal", testLoans.size(), repository.getAllLoans()
				.size());
	}

	@Test
	public void getLoanbyId() {
		Assert.assertEquals("Created and persisted Laons should be equal", testLoans.get(0), repository.getLoanbyId(3L));
	}

}
