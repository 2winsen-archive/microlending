package lv.vitalijs.shakels.microlending.rest.params;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonServerData {

	private BigDecimal interest;

	private BigDecimal maxLoanAmount;

	private BigDecimal minLoanAmount;

	private int maxLoanTerm;

	private int minLoanTerm;

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

	public BigDecimal getMinLoanAmount() {
		return minLoanAmount;
	}

	public void setMinLoanAmount(BigDecimal minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}

	public int getMinLoanTerm() {
		return minLoanTerm;
	}

	public void setMinLoanTerm(int minLoanTerm) {
		this.minLoanTerm = minLoanTerm;
	}

}
