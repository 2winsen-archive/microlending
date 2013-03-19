package lv.vitalijs.shakels.microlending.rest;

import java.util.Date;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lv.vitalijs.shakels.microlending.constants.MicrolandingConstants;
import lv.vitalijs.shakels.microlending.entities.Loan;
import lv.vitalijs.shakels.microlending.rest.params.JsonResponse;
import lv.vitalijs.shakels.microlending.rest.params.JsonServerData;
import lv.vitalijs.shakels.microlending.services.LoanService;
import lv.vitalijs.shakels.microlending.services.RiskService;
import lv.vitalijs.shakels.microlending.validators.LoanValidator;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class MicrolendingFacade implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory
			.getLogger(MicrolendingFacade.class);
	
	private ApplicationContext context; 

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
		data.setInterest(MicrolandingConstants.INTEREST);
		data.setMaxLoanAmount(MicrolandingConstants.MAX_LOAN_AMOUT);
		data.setMinLoanAmount(MicrolandingConstants.MIN_LOAN_AMOUT);
		data.setMaxLoanTerm(MicrolandingConstants.MAX_LOAN_TERM);
		data.setMinLoanTerm(MicrolandingConstants.MIN_LOAN_TERM);
		return data;
	}

	@POST
	@Path("/takeLoan")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse takeLoan(Loan loan) {
		logger.debug("takeLoan - called successfully");
		JsonResponse response = new JsonResponse();
		if (LoanValidator.isValid(loan)) {
			loan.setCreationDate(new Date());
			if (!riskService.isHighRisk(loan)) {
				try {
					loanService.processLoan(loan);
					response.setPage(JsonResponse.PAGE_MYLOANS);
				} catch (HibernateException e) {
					logger.error(e.getMessage());
					response.setError(context.getMessage("error.server.error", null, Locale.getDefault()));
				}
			} else {
				response.setError(context.getMessage("error.high.risk", null, Locale.getDefault()));
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
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			response.setError(context.getMessage("error.server.error", null, Locale.getDefault()));
		}
		return response;
	}

	@POST
	@Path("/extendLoan/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonResponse extendLoan(@PathParam("id") String id) {
		logger.debug("extendLoan - called successfully");
		JsonResponse response = new JsonResponse();
		try {
			response.addToResults(loanService.extendLoan(Long.parseLong(id)));
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			response.setError(context.getMessage("error.server.error", null, Locale.getDefault()));
		}
        return response;
    }

	@Override
	public void setApplicationContext(ApplicationContext paramApplicationContext)
			throws BeansException {
		this.context = paramApplicationContext;		
	}
	
}
