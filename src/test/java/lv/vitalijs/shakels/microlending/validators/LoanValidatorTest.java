package lv.vitalijs.shakels.microlending.validators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoanValidatorTest {

	private static List<Loan> testLoans;

	@BeforeClass
	public static void setUpBeforeClass() {
		Calendar dueDateCal = Calendar.getInstance();
		dueDateCal.set(2013, 3, 19);

		Calendar creationDateCal = Calendar.getInstance();
		creationDateCal.set(2013, 3, 19);

		Loan testLoan1 = new Loan();
		testLoan1.setAmount(new BigDecimal("10"));
		testLoan1.setTerm(10);
//		testLoan1.setCreationDate(creationDateCal.getTime());
//		testLoan1.setDueDate(dueDateCal.getTime());
		testLoan1.setExtended(false);
		testLoan1.setInterest(new BigDecimal("1.1"));
		testLoan1.setIpAddress("1.1.1.1");
		testLoan1.setReturnAmount(new BigDecimal("1.1"));

		Loan testLoan2 = new Loan();
		testLoan2.setAmount(new BigDecimal("10"));
		testLoan2.setTerm(10);
//		testLoan2.setCreationDate(creationDateCal.getTime());
//		testLoan2.setDueDate(dueDateCal.getTime());
		testLoan2.setExtended(false);
		testLoan2.setInterest(new BigDecimal("2.1"));
		testLoan2.setIpAddress("2.2.2");
		testLoan2.setReturnAmount(new BigDecimal("2.1"));

		Loan testLoan3 = new Loan();
		testLoan3.setAmount(new BigDecimal("10"));
		testLoan3.setTerm(50);
//		testLoan3.setCreationDate(creationDateCal.getTime());
//		testLoan3.setDueDate(dueDateCal.getTime());
		testLoan3.setExtended(false);
		testLoan3.setInterest(new BigDecimal("2.1"));
		testLoan3.setIpAddress("2.2.2.2");
		testLoan3.setReturnAmount(new BigDecimal("2.1"));
		
		Loan testLoan4 = new Loan();
		testLoan4.setAmount(new BigDecimal("600"));
		testLoan4.setTerm(50);
//		testLoan4.setCreationDate(creationDateCal.getTime());
//		testLoan4.setDueDate(dueDateCal.getTime());
		testLoan4.setExtended(false);
		testLoan4.setInterest(new BigDecimal("2.1"));
		testLoan4.setIpAddress("2.2.2.2");
		testLoan4.setReturnAmount(new BigDecimal("2.1"));

		testLoans = new ArrayList<Loan>();
		testLoans.add(testLoan1);
		testLoans.add(testLoan2);
		testLoans.add(testLoan3);
		testLoans.add(testLoan4);
	}

	@Test
	public void isValid() {
		Assert.assertTrue("Valid", LoanValidator.isValid(testLoans.get(0)));
		Assert.assertTrue("Invalid ip", !LoanValidator.isValid(testLoans.get(1)));
		Assert.assertTrue("Invalid term", !LoanValidator.isValid(testLoans.get(2)));
		Assert.assertTrue("Invalid amount", !LoanValidator.isValid(testLoans.get(3)));
	}

}
