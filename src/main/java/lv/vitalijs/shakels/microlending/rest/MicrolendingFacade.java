package lv.vitalijs.shakels.microlending.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lv.vitalijs.shakels.microlending.rest.params.JsonLoan;
import lv.vitalijs.shakels.microlending.rest.params.JsonServerData;
import lv.vitalijs.shakels.microlending.services.LoanService;
import lv.vitalijs.shakels.microlending.services.RiskService;
import lv.vitalijs.shakels.microlending.utils.MicrolandingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class MicrolendingFacade {

	@Autowired
	private RiskService riskService;
	
	@Autowired
	private LoanService loanService;
	
	@GET
	@Path("/getServerConstants")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonServerData getServerConstants() {
		JsonServerData data = new JsonServerData();
		data.setInterest(MicrolandingUtils.INTEREST);
		return data;
	}
	
	@POST
	@Path("/takeLoan")
	@Consumes(MediaType.APPLICATION_JSON)
	public void takeLoan(JsonLoan loan) {
		String result = null;
		if (!riskService.isHighRisk()) {
			loanService.saveLoan(null);
		} else {
			result = "HIGH RISK";
		}
	}
	
//	@GET
//	@Path("/extendLoan")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String extendLoan() {
//		return "ExtendLoan";
//	}
	
//	@GET
//	@Path("/getLoansHistory")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String getLoansHistory() {
//		return "LoansHistory";
//	}
	
}

