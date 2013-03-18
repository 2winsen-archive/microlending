package lv.vitalijs.shakels.microlending.rest.params;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lv.vitalijs.shakels.microlending.entities.Loan;

@XmlRootElement
public class JsonResponse {

	public static final String PAGE_MYLOANS = "myLoans.html";

	private String page;

	private String error;

	private List<Loan> results;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<Loan> getResults() {
		return results;
	}

	public void setResults(List<Loan> list) {
		this.results = list;
	}
	
	public void addToResults(Loan loan) {
		if (results == null) {
			results = new ArrayList<Loan>();
		}
		results.add(loan);
	}

}
