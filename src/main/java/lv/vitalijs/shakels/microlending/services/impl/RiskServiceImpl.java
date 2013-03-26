package lv.vitalijs.shakels.microlending.services.impl;

import java.util.ArrayList;
import java.util.List;

import lv.vitalijs.shakels.microlending.constants.MicrolandingConstants;
import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.repositories.LoanRepository;
import lv.vitalijs.shakels.microlending.services.RiskService;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RiskServiceImpl implements RiskService {

	private static final int RISK_TIME_MIN_LIMIT = 0;
	private static final int RISK_TIME_MAX_LIMIT = 7;

	/**
	 * Is 2 not 3 because 3rd loan is one that is not yet persisted
	 */
	private static final int RISK_MAX_SAME_IP = 2;

	private static final Logger logger = LoggerFactory.getLogger(RiskServiceImpl.class);

	private LoanRepository loanRepository;

	@Autowired
	public void setLoanRepository(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}

	@Override
	public boolean isHighRisk(final Loan loan) {
		return (isLoanMadeFromMidnightTillSeven(loan) && isLoanWithMaximumAmount(loan)) || is3rdLoanFromSameIP(loan);
	}

	private boolean isLoanMadeFromMidnightTillSeven(final Loan loan) {
		int hour = loan.getCreationDate().getHourOfDay();
		return hour >= RISK_TIME_MIN_LIMIT && hour <= RISK_TIME_MAX_LIMIT;
	}

	private boolean isLoanWithMaximumAmount(final Loan loan) {
		return loan.getAmount().equals(MicrolandingConstants.MAX_LOAN_AMOUT);
	}

	@Transactional
	private boolean is3rdLoanFromSameIP(final Loan loan) {
		List<Loan> result = new ArrayList<Loan>();
		if (loan.getIpAddress().equals(MicrolandingConstants.UNKNOWN_IP_ADDRESS)) {
			return false;
		}
		try {
			result = loanRepository.getLoansByIP(loan.getIpAddress());
		} catch (HibernateException e) {
			logger.error(e.getMessage());
		}
		return result.size() > RISK_MAX_SAME_IP;
	}

}
