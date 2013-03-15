package lv.vitalijs.shakels.microlending.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lv.vitalijs.shakels.microlending.rest.params.JsonLoan;

@Entity
public class Loan {

	@Id
	@GeneratedValue
	private Long id;

	private BigDecimal amount;
	
	private Integer term;
	
	private String ipAddress;
	
	private BigDecimal interest;
	
	private BigDecimal returnAmount;

	private Date dueDate;

	private Date creationDate;
	

	public Loan() {
	}
	
	public Loan(JsonLoan jsonLoan) {
		this.amount = jsonLoan.getAmount();
		this.term = jsonLoan.getTerm();
		this.ipAddress = jsonLoan.getIpAddress();
	}
	
	@Version
	private Integer version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}

}
