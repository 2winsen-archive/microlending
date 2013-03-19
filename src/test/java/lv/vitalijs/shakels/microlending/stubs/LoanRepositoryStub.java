package lv.vitalijs.shakels.microlending.stubs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;

public class LoanRepositoryStub implements LoanRepository {

	private List<Loan> loans = new ArrayList<Loan>();
	
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public void saveLoan(Loan loan) throws HibernateException {
		loans.add(loan);
	}

	@Override
	public List<Loan> getLoansByIP(String ip) throws HibernateException {
		List<Loan> newloans = new ArrayList<Loan>();
		for (Loan loan : loans) {
			if (loan.getIpAddress().equals(ip)) {
				newloans.add(loan);
			}
		}
		return newloans;
	}

	@Override
	public List<Loan> getAllLoans() throws HibernateException {
		return loans;
	}

	@Override
	public Loan getLoanbyId(Long id) throws HibernateException {
		return loans.get(0);
	}

}
