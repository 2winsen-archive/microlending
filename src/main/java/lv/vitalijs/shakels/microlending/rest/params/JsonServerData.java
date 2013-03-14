package lv.vitalijs.shakels.microlending.rest.params;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonServerData {

	private BigDecimal interest;

	private BigDecimal maxLoanAmount;

	private int maxLoanTerm;

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(BigDecimal maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public int getMaxLoanTerm() {
		return maxLoanTerm;
	}

	public void setMaxLoanTerm(int maxLoanTerm) {
		this.maxLoanTerm = maxLoanTerm;
	}

}
