package lv.vitalijs.shakels.microlending.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.generators.TestDataGenerator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoanValidatorTest {

	private Loan testLoan;

	@Before
	public void setUp() {
		testLoan = TestDataGenerator.generateValidLoan();
	}

	@After
	public void tearDown() {
		testLoan = null;
	}

	@Test
	public void validLoan() {
		boolean isValid = LoanValidator.isValid(testLoan);
		assertTrue(isValid);
	}

	@Test
	public void invalidLoanIP() {
		testLoan.setIpAddress("invalid IP");
		boolean isValid = LoanValidator.isValid(testLoan);
		assertFalse(isValid);
	}

	@Test
	public void invalidLoanTerm() {
		testLoan.setTerm(999);
		boolean isValid = LoanValidator.isValid(testLoan);
		assertFalse(isValid);
	}

	@Test
	public void invalidLoanAmount() {
		testLoan.setAmount(new BigDecimal("999"));
		boolean isValid = LoanValidator.isValid(testLoan);
		assertFalse(isValid);
	}

}
