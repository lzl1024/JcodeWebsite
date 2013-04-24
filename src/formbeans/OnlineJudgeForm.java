package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class OnlineJudgeForm extends FormBean {
	private int	   id = -1;
	private String code;
	private String submit;
	
	public int	  getId() { return id; }
	public String getCode()  { return code; }
	public String getSubmit() { return submit;}
	
	public void setId(String d) { id = Integer.parseInt(d); }
	public void setCode(String s)   { code = s; }
	public void setSubmit(String s) { submit = s; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (code == null || code.length() == 0) {
			errors.add("Please Enter Code");
		}
		return errors;
	}
	

}