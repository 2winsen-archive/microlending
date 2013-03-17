package lv.vitalijs.shakels.microlending.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
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
	
	@Transient
	private Long dueDateMillis;

	private Date creationDate;
	
	@Transient
	private Long creationDateMillis;
	
	private Boolean extended;

	@Version
	private Integer version;

	public Loan() {
	}

	public Loan(Loan loan) {
		this(loan.amount, loan.term, loan.ipAddress, loan.interest,
				loan.returnAmount, loan.dueDate, loan.creationDate);
	}

	public Loan(BigDecimal amount, Integer term, String ipAddress,
			BigDecimal interest, BigDecimal returnAmount, Date dueDate,
			Date creationDate) {
		super();
		this.amount = amount;
		this.term = term;
		this.ipAddress = ipAddress;
		this.interest = interest;
		this.returnAmount = returnAmount;
		if (this.dueDate != null) {
			this.dueDate = (Date) dueDate.clone();
		}
		if (this.creationDate != null) {
			this.creationDate = (Date) creationDate.clone();
		}
	}

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
		if (dueDate != null) {
			dueDateMillis = dueDate.getTime();
		}
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		if (creationDate != null) {
			creationDateMillis = creationDate.getTime();
		}		
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

	public Boolean getExtended() {
		return extended;
	}

	public void setExtended(Boolean extended) {
		this.extended = extended;
	}

	public Long getDueDateMillis() {
		return dueDateMillis;
	}

	public void setDueDateMillis(Long dueDateMillis) {
		this.dueDateMillis = dueDateMillis;
	}

	public Long getCreationDateMillis() {
		return creationDateMillis;
	}

	public void setCreationDateMillis(Long creationDateMillis) {
		this.creationDateMillis = creationDateMillis;
	}

}
