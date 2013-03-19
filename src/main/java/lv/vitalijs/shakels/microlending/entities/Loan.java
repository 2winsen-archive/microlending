package lv.vitalijs.shakels.microlending.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
	@NamedQuery(name="loan.getLoansByIp", query="select loan from Loan loan where loan.ipAddress = :ipAddress"),
	@NamedQuery(name="loan.getAllLoans", query="select loan from Loan loan")
})
@XmlRootElement
public class Loan {

	@Id
	@GeneratedValue
	private Long id;
	
	private BigDecimal amount;

	private Integer term;

	private String ipAddress;

	@Column(precision=8, scale=6)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime
				* result
				+ ((creationDateMillis == null) ? 0 : creationDateMillis
						.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result
				+ ((dueDateMillis == null) ? 0 : dueDateMillis.hashCode());
		result = prime * result
				+ ((extended == null) ? 0 : extended.hashCode());
		result = prime * result
				+ ((interest == null) ? 0 : interest.hashCode());
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((returnAmount == null) ? 0 : returnAmount.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (creationDateMillis == null) {
			if (other.creationDateMillis != null)
				return false;
		} else if (!creationDateMillis.equals(other.creationDateMillis))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (dueDateMillis == null) {
			if (other.dueDateMillis != null)
				return false;
		} else if (!dueDateMillis.equals(other.dueDateMillis))
			return false;
		if (extended == null) {
			if (other.extended != null)
				return false;
		} else if (!extended.equals(other.extended))
			return false;
		if (interest == null) {
			if (other.interest != null)
				return false;
		} else if (!interest.equals(other.interest))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (returnAmount == null) {
			if (other.returnAmount != null)
				return false;
		} else if (!returnAmount.equals(other.returnAmount))
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}

}
