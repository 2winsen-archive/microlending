package lv.vitalijs.shakels.microlending.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.rest.params.JsonResponse;
import lv.vitalijs.shakels.microlending.rest.params.JsonServerData;
import lv.vitalijs.shakels.microlending.services.LoanService;
import lv.vitalijs.shakels.microlending.services.RiskService;
import lv.vitalijs.shakels.microlending.utils.MicrolandingUtils;
import lv.vitalijs.shakels.microlending.validators.LoanValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class MicrolendingFacade {

	private static final Logger logger = LoggerFactory
			.getLogger(MicrolendingFacade.class);

	@Autowired
	private RiskService riskService;

	@Autowired
	private LoanService loanService;

	@GET
	@Path("/getServerConstants")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonServerData getServerConstants() {
		logger.debug("getServerConstants - called successfully");
		JsonServerData data = new JsonServerData();
		data.setInterest(MicrolandingUtils.INTEREST);
		data.setMaxLoanAmount(MicrolandingUtils.MAX_LOAN_AMOUT);
		data.setMinLoanAmount(MicrolandingUtils.MIN_LOAN_AMOUT);
		data.setMaxLoanTerm(MicrolandingUtils.MAX_LOAN_TERM);
		data.setMinLoanTerm(MicrolandingUtils.MIN_LOAN_TERM);
		return data;
	}

	@POST
	@Path("/takeLoan")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse takeLoan(Loan paramLoan) {
		logger.debug("takeLoan - called successfully");
		JsonResponse response = new JsonResponse();
		if (LoanValidator.isValid(paramLoan)) {
			Loan loan = new Loan(paramLoan);
			loan.setCreationDate(new Date());
			if (!riskService.isHighRisk(loan)) {
				try {
					loanService.processLoan(loan);
					response.setPage(JsonResponse.PAGE_MYLOANS);
				} catch (DataAccessException e) {
					logger.error(e.getMessage());
					response.setError("Server side error has occured, please try again later.");
				}
			} else {
				response.setPage(JsonResponse.PAGE_REJECT);
			}
		}
		return response;
	}

	@GET
	@Path("/getAllLoans")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse getAllLoans() {
		logger.debug("takeLoan - called successfully");
		JsonResponse response = new JsonResponse();
		try {
			response.setResults(loanService.getAllLoans());
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			response.setError("Server side error has occured, please try again later.");
		}
		return response;
	}

	// @GET
	// @Path("/extendLoan")
	// @Produces(MediaType.APPLICATION_JSON)
	// public String extendLoan() {
	// return "ExtendLoan";
	// }

}
