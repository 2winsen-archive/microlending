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

public class RiskServiceImplTest {

	private static RiskServiceImpl service;

	private static List<Loan> testLoans;

	@BeforeClass
	public static void setUpBeforeClass() {
		Calendar dueDateCal = Calendar.getInstance();
		dueDateCal.set(2013, 3, 19);

		Calendar highCreationDateCal = Calendar.getInstance();
		highCreationDateCal.set(2013, 3, 19, 0, 12);

		Calendar lowCreationDateCal = Calendar.getInstance();
		lowCreationDateCal.set(2013, 3, 19, 12, 12);

		Loan testLoan1 = new Loan();
		testLoan1.setAmount(new BigDecimal("200"));
		testLoan1.setTerm(10);
//		testLoan1.setCreationDate(highCreationDateCal.getTime());
//		testLoan1.setDueDate(dueDateCal.getTime());
		testLoan1.setExtended(false);
		testLoan1.setInterest(new BigDecimal("1.1"));
		testLoan1.setIpAddress("1.1.1.1");
		testLoan1.setReturnAmount(new BigDecimal("200"));

		Loan testLoan2 = new Loan();
		testLoan2.setAmount(new BigDecimal("2.1"));
		testLoan2.setTerm(10);
//		testLoan2.setCreationDate(lowCreationDateCal.getTime());
//		testLoan2.setDueDate(dueDateCal.getTime());
		testLoan2.setExtended(false);
		testLoan2.setInterest(new BigDecimal("2.1"));
		testLoan2.setIpAddress("2.2.2.2");
		testLoan2.setReturnAmount(new BigDecimal("2.1"));

		Loan testLoan3 = new Loan();
		testLoan3.setAmount(new BigDecimal("2.1"));
		testLoan3.setTerm(10);
//		testLoan3.setCreationDate(lowCreationDateCal.getTime());
//		testLoan3.setDueDate(dueDateCal.getTime());
		testLoan3.setExtended(false);
		testLoan3.setInterest(new BigDecimal("2.1"));
		testLoan3.setIpAddress("3.3.3.3");
		testLoan3.setReturnAmount(new BigDecimal("2.1"));

		Loan testLoan4 = new Loan();
		testLoan4.setAmount(new BigDecimal("2.1"));
		testLoan4.setTerm(10);
//		testLoan4.setCreationDate(lowCreationDateCal.getTime());
//		testLoan4.setDueDate(dueDateCal.getTime());
		testLoan4.setExtended(false);
		testLoan4.setInterest(new BigDecimal("2.1"));
		testLoan4.setIpAddress("3.3.3.3");
		testLoan4.setReturnAmount(new BigDecimal("2.1"));

		Loan testLoan5 = new Loan();
		testLoan5.setAmount(new BigDecimal("2.1"));
		testLoan5.setTerm(10);
//		testLoan5.setCreationDate(lowCreationDateCal.getTime());
//		testLoan5.setDueDate(dueDateCal.getTime());
		testLoan5.setExtended(false);
		testLoan5.setInterest(new BigDecimal("2.1"));
		testLoan5.setIpAddress("3.3.3.3");
		testLoan5.setReturnAmount(new BigDecimal("2.1"));

		testLoans = new ArrayList<Loan>();
		testLoans.add(testLoan1);
		testLoans.add(testLoan2);
		testLoans.add(testLoan3);
		testLoans.add(testLoan4);
		testLoans.add(testLoan5);

		service = new RiskServiceImpl();
	}

	@Before
	public void setUp() {
		LoanRepositoryStub stub = new LoanRepositoryStub();
		stub.setLoans(testLoans);
		service.setLoanRepository(stub);
	}

	@After
	public void tearDown() {
		service.setLoanRepository(null);
	}

	@Test
	public void isHighRiskTest() {
		Assert.assertTrue("Risk is high", service.isHighRisk(testLoans.get(0)));
		Assert.assertTrue("Risk is low", !service.isHighRisk(testLoans.get(1)));

		Loan testLoan3 = new Loan();
		testLoan3.setIpAddress("2.2.2.2");
		Loan testLoan4 = new Loan();
		testLoan4.setIpAddress("2.2.2.2");
		testLoans.add(testLoan3);
		testLoans.add(testLoan4);
		Assert.assertTrue("Risk is high", service.isHighRisk(testLoans.get(2)));

	}

}
