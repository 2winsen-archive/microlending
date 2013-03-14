package lv.vitalijs.shakels.microlending.validators;

import java.math.BigDecimal;

import lv.vitalijs.shakels.microlending.rest.params.JsonLoan;
import lv.vitalijs.shakels.microlending.utils.MicrolandingUtils;

public class LoanValidator {

	public static boolean valid(JsonLoan loan) {
		if (loan.getAmount().compareTo(MicrolandingUtils.MAX_LOAN_AMOUT) == 1 || loan.getAmount().compareTo(BigDecimal.ZERO) == -1) {
			return false;
		}
		if (loan.getTerm().compareTo(MicrolandingUtils.MAX_LOAN_TERM) == 1 || loan.getTerm().compareTo(0) == -1) {
			return false;
		}
		
		return false;
	}
}
