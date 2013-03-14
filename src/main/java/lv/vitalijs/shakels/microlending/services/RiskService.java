package lv.vitalijs.shakels.microlending.services;

import lv.vitalijs.shakels.microlending.bo.Loan;

public interface RiskService {
	
	boolean isHighRisk(Loan loan);

}
