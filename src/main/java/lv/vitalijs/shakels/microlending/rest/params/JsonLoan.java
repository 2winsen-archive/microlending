package lv.vitalijs.shakels.microlending.rest.params;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonLoan implements Cloneable {

	private BigDecimal amount;

	private Integer term;

	private BigDecimal interest;

	private Date dueDate;

	private Date creationDate;

	private String ipAddress;

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

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public JsonLoan clone() {
		JsonLoan clone = new JsonLoan();
		clone.amount = this.amount;
		clone.term = this.term;
		clone.interest = this.interest;
		clone.dueDate = (Date) this.dueDate.clone();
		clone.creationDate = (Date) this.creationDate.clone();
		clone.ipAddress = this.ipAddress;		
		return clone;
	}

}
