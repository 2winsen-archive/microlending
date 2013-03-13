package lv.vitalijs.shakels.microlending.rest.params;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonServerData {
	
	private BigDecimal interest;
	
	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

}
