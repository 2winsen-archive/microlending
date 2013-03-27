package lv.vitalijs.shakels.microlending.validators;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import lv.vitalijs.shakels.microlending.constants.MicrolandingConstants;
import lv.vitalijs.shakels.microlending.entities.Loan;

public class LoanValidator {

	private static final String IP_ADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static boolean isValid(final Loan loan) {
		if (loan.getAmount() != null && isLoanAmountFromValidInterval(loan.getAmount())) {
			return false;
		}
		if (loan.getTerm() != null && isLoanTermFromValidInterval(loan.getTerm())) {
			return false;
		}
		Pattern pattern = Pattern.compile(IP_ADDRESS_PATTERN);
		if (loan.getIpAddress() != null && !pattern.matcher(loan.getIpAddress()).matches()) {
			return false;
		}
		return true;
	}

	private static boolean isLoanAmountFromValidInterval(final BigDecimal loanAmount) {
		return (loanAmount.compareTo(MicrolandingConstants.MAX_LOAN_AMOUT) == 1 || loanAmount
				.compareTo(MicrolandingConstants.MIN_LOAN_AMOUT) == -1);
	}

	private static boolean isLoanTermFromValidInterval(final Integer loanTerm) {
		return (loanTerm.compareTo(MicrolandingConstants.MAX_LOAN_TERM) == 1 || loanTerm
				.compareTo(MicrolandingConstants.MIN_LOAN_TERM) == -1);
	}
}
