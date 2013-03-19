package lv.vitalijs.shakels.microlending.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.stubs.LoanRepositoryStub;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoanServiceImplTest {

	private static LoanServiceImpl service;

	private static List<Loan> testLoans;

	@BeforeClass
	public static void setUpBeforeClass() {
		Calendar dueDateCal = Calendar.getInstance();
		dueDateCal.set(2013, 3, 19);
		
		Calendar creationDateCal = Calendar.getInstance();
		creationDateCal.set(2013, 3, 19);
		
		Loan testLoan1 = new Loan();
		testLoan1.setAmount(new BigDecimal("1.1"));
		testLoan1.setTerm(10);
		testLoan1.setCreationDate(creationDateCal.getTime());
		testLoan1.setDueDate(dueDateCal.getTime());
		testLoan1.setExtended(false);
		testLoan1.setInterest(new BigDecimal("1.1"));
		testLoan1.setIpAddress("1.1.1.1");
		testLoan1.setReturnAmount(new BigDecimal("1.1"));

		Loan testLoan2 = new Loan();
		testLoan2.setAmount(new BigDecimal("2.1"));
		testLoan2.setTerm(10);
		testLoan2.setCreationDate(creationDateCal.getTime());
		testLoan2.setDueDate(dueDateCal.getTime());
		testLoan2.setExtended(false);
		testLoan2.setInterest(new BigDecimal("2.1"));
		testLoan2.setIpAddress("2.2.2.2");
		testLoan2.setReturnAmount(new BigDecimal("2.1"));

		testLoans = new ArrayList<Loan>();
		testLoans.add(testLoan1);
		testLoans.add(testLoan2);

		service = new LoanServiceImpl();
	}

	@Before
	public void setUp() {
		service.setLoanRepository(new LoanRepositoryStub());
		service.processLoan(testLoans.get(0));
		service.processLoan(testLoans.get(1));
	}

	@After
	public void tearDown() {
		service.setLoanRepository(null);
	}

	@Test
	public void processLoanTest() {
		Assert.assertEquals("Interest", testLoans.get(0).getInterest(), new BigDecimal("0.10"));
		Assert.assertEquals("Return Amount", testLoans.get(0).getReturnAmount(), new BigDecimal("1.21"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(testLoans.get(0).getDueDate());
		Assert.assertEquals("Due Date DATE", cal.get(Calendar.DATE), 29);
		Assert.assertEquals("Due Date MONTH", cal.get(Calendar.MONTH), 3);
		Assert.assertEquals("Due Date YEAR", cal.get(Calendar.YEAR), 2013);
	}

	@Test
	public void getAllLoansTest() {
		List<Loan> loans = service.getAllLoans();
		Assert.assertEquals("Should be 2 loans", service.getAllLoans().size(), 2);
		Assert.assertNotNull("Due date in Millis", loans.get(0).getDueDateMillis());
		Assert.assertNotNull("Creation date in Millis", loans.get(0).getCreationDateMillis());
	}

	@Test
	public void extendLoanTest() {
		Loan loan = service.extendLoan(1L);
		Assert.assertEquals("Interest", loan.getInterest(), new BigDecimal("0.150"));
		Assert.assertEquals("Return Amount", loan.getReturnAmount(), new BigDecimal("1.27"));
		Assert.assertEquals("Term", loan.getTerm(), new Integer(17));
		Calendar cal = Calendar.getInstance();
		cal.setTime(loan.getDueDate());
		Assert.assertEquals("Due Date DATE", cal.get(Calendar.DATE), 6);
		Assert.assertEquals("Due Date MONTH", cal.get(Calendar.MONTH), 4);
		Assert.assertEquals("Due Date YEAR", cal.get(Calendar.YEAR), 2013);
		Assert.assertTrue("Extended", loan.getExtended());
		Assert.assertNotNull("Due date in Millis", loan.getDueDateMillis());
		Assert.assertNotNull("Creation date in Millis", loan.getCreationDateMillis());
	}

}
