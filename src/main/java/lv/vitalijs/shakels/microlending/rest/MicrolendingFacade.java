package lv.vitalijs.shakels.microlending.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lv.vitalijs.shakels.microlending.services.LoanService;
import lv.vitalijs.shakels.microlending.services.RiskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/rest")
public class MicrolendingFacade {

	@Autowired
	private RiskService riskService;
	
	@Autowired
	private LoanService loanService;
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Oh hai";
	}
	
	@GET
	@Path("/takeLoan")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String takeLoan() {
		String result = null;
		if (!riskService.isHighRisk()) {
			loanService.saveLoan(null);
		} else {
			result = "HIGH RISK";
		}
		return result;
	}
	
	@GET
	@Path("/extendLoan")
	@Produces(MediaType.APPLICATION_JSON)
	public String extendLoan() {
		return "ExtendLoan";
	}
	
	@GET
	@Path("/getLoansHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoansHistory() {
		return "LoansHistory";
	}
	
}

