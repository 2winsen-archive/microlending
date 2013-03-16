package lv.vitalijs.shakels.microlending.services;

import lv.vitalijs.shakels.microlending.entities.Loan;

public interface RiskService {
	
	boolean isHighRisk(final Loan loan);

}
