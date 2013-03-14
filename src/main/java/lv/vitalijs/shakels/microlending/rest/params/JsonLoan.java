package lv.vitalijs.shakels.microlending.rest.params;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonLoan {

	private BigDecimal amount;

	private Integer term;

	private String ipAddress;
	
	public JsonLoan() {
	}
	
	public JsonLoan(JsonLoan jsonLoan) {
		this(jsonLoan.amount, jsonLoan.term, jsonLoan.ipAddress);
	}

	public JsonLoan(BigDecimal amount, Integer term, String ipAddress) {
		super();
		this.amount = amount;
		this.term = term;
		this.ipAddress = ipAddress;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


}
