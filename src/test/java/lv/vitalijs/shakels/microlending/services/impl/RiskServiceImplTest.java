package lv.vitalijs.shakels.microlending.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
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

public class RiskServiceImplTest {

	private Loan testLoan;
	private RiskServiceImpl service;
	private LoanRepository repositoryMock;

	@Before
	public void setUp() {
		testLoan = TestDataGenerator.generateValidLoan();
		service = new RiskServiceImpl();
		repositoryMock = mock(LoanRepository.class);
		service.setLoanRepository(repositoryMock);
	}

	@After
	public void tearDown() {
		testLoan = null;
		repositoryMock = null;
		service = null;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void normalRisk() {
		List<Loan> mockList = mock(List.class);
		when(mockList.size()).thenReturn(1);
		when(repositoryMock.getLoansByIP(anyString())).thenReturn(mockList);
		boolean isHighRisk = service.isHighRisk(testLoan);
		assertFalse(isHighRisk);

		verify(repositoryMock).getLoansByIP(anyString());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void highRiskSameIP() {
		List<Loan> mockList = mock(List.class);
		when(mockList.size()).thenReturn(3);
		when(repositoryMock.getLoansByIP(anyString())).thenReturn(mockList);
		boolean isHighRisk = service.isHighRisk(testLoan);
		assertTrue(isHighRisk);

		verify(repositoryMock).getLoansByIP(anyString());
	}

	@Test
	public void highRiskMaxAmountInNightHours() {
		testLoan.setAmount(new BigDecimal("200"));
		testLoan.setCreationDate(new DateTime(2005, 3, 26, 0, 10, 20, 0));
		boolean isHighRisk = service.isHighRisk(testLoan);
		assertTrue(isHighRisk);
	}

}
