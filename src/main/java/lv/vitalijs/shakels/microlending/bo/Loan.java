package lv.vitalijs.shakels.microlending.bo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lv.vitalijs.shakels.microlending.rest.params.JsonLoan;

@Entity
public class Loan extends JsonLoan {

	@Id
	@GeneratedValue
	private Long id;

	private BigDecimal interest;

	private Date dueDate;

	private Date creationDate;

	@Version
	private Integer version;
	
	public Loan() {
		super();
	}
	
	public Loan(JsonLoan jsonLoan) {
		super(jsonLoan);
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

}
