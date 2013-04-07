package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class OnlineJudgeForm extends FormBean {
	private int	   problemId = -1;
	private String code;
	private String result;
	private String switcher = "0";
	
	public int	  getProblemId() { return problemId; }
	public String getCode()  { return code; }
	public String getResult()  { return result; }
	public String getSwitcher()  { return switcher;}
	
	public void setProblemId(int d) { problemId = d; }
	public void setCode(String s)   { code = s;  }
	public void setResult(String s) {	result = s;                  }
	public void setSwitcher(String i)  { switcher = i;}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (code == null || code.length() == 0) {
			errors.add("Please Enter Code");
		}
		
		return errors;
	}
}