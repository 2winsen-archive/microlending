package lv.vitalijs.shakels.microlending.generators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import lv.vitalijs.shakels.microlending.entities.Loan;

public class TestDataGenerator {

	public static Loan generateValidLoan() {
		Loan testLoan = new Loan();
		testLoan.setAmount(new BigDecimal("100"));
		testLoan.setTerm(14);
		testLoan.setCreationDate(new DateTime(2005, 3, 26, 12, 10, 20, 0));
		testLoan.setDueDate(new DateTime(2005, 3, 26, 12, 10, 20, 0));
		testLoan.setExtended(false);
		testLoan.setInterest(new BigDecimal("0.01"));
		testLoan.setIpAddress("144.30.30.30");
		testLoan.setReturnAmount(new BigDecimal("200"));
		return testLoan;
	}
	
	public static List<Loan> generateValidLoansList() {
		List<Loan> testLoans = new ArrayList<Loan>();
		for (int i = 0; i < 10; i++) {
			testLoans.add(generateValidLoan());			
		}
		return testLoans;
	}

}
