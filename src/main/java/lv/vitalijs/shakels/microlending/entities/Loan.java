package lv.vitalijs.shakels.microlending.entities;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.base.Objects;

@Entity
@NamedQueries({
		@NamedQuery(name = "loan.getLoansByIp", query = "select loan from Loan loan where loan.ipAddress = :ipAddress"),
		@NamedQuery(name = "loan.getAllLoans", query = "select loan from Loan loan") })
@XmlRootElement
public class Loan {

	@Id
	@GeneratedValue
	private Long id;

	private String businessKey = UUID.randomUUID().toString();

	private BigDecimal amount;

	private Integer term;

	private String ipAddress;

	@Column(precision = 8, scale = 6)
	private BigDecimal interest;

	private BigDecimal returnAmount;

	@Column
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime dueDate;

	@Transient
	private Long dueDateMillis;

	@Column
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime creationDate;

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

	public DateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
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
		return Objects.hashCode(businessKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Loan) {
			final Loan loan = (Loan) obj;
			return Objects.equal(businessKey, loan.businessKey);
		} else {
			return false;
		}
	}

}
