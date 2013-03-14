package lv.vitalijs.shakels.microlending.bo;

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
	
	@Version
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
