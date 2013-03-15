package lv.vitalijs.shakels.microlending.rest.params;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonResponse {
	
	public static final String PAGE_MYLOANS = "myLoans.html";
	public static final String PAGE_REJECT = "reject.html";

	private String page;

	private String error;

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

}
