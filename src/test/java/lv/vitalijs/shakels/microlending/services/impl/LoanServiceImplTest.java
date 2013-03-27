package lv.vitalijs.shakels.microlending.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.generators.TestDataGenerator;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoanServiceImplTest {

	private Loan testLoan;
	private List<Loan> testLoans;
	private LoanServiceImpl service;
	private LoanRepository repositoryMock;

	@Before
	public void setUp() {
		testLoan = TestDataGenerator.generateValidLoan();
		testLoans = TestDataGenerator.generateValidLoansList();
		service = new LoanServiceImpl();
		repositoryMock = mock(LoanRepository.class);
		service.setLoanRepository(repositoryMock);
	}

	@After
	public void tearDown() {
		testLoan = null;
		testLoans = null;
		repositoryMock = null;
		service = null;
	}

	@Test
	public void processLoanTest() {
		service.processLoan(testLoan);
		assertEquals(testLoan.getInterest(), new BigDecimal("0.14"));
		assertEquals(testLoan.getReturnAmount(), new BigDecimal("114.00"));
		assertEquals(testLoan.getDueDate(), new DateTime(2005, 4, 9, 12, 10, 20, 0));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllLoansTest() {
		List<Loan> mockList = mock(List.class);
		when(mockList.size()).thenReturn(3);
		when(repositoryMock.getAllLoans()).thenReturn(testLoans);
		List<Loan> loans = service.getAllLoans();
		assertEquals(loans.size(), 10);
		assertEquals(loans.get(0).getDueDateMillis(), (Long) 1111831820000L);
		assertEquals(loans.get(0).getCreationDateMillis(), (Long) 1111831820000L);

		verify(repositoryMock).getAllLoans();
	}

	@Test
	public void extendLoanTest() {
		when(repositoryMock.getLoanbyId(anyLong())).thenReturn(testLoan);
		Loan loan = service.extendLoan(anyLong());
		assertEquals(loan.getInterest(), new BigDecimal("0.015"));
		assertEquals(loan.getTerm(), (Integer) 21);
		assertEquals(loan.getDueDate(), new DateTime(2005, 4, 2, 12, 10, 20, 0));
		assertEquals(loan.getCreationDate(), new DateTime(2005, 3, 26, 12, 10, 20, 0));

		verify(repositoryMock).getLoanbyId(anyLong());
	}

}
