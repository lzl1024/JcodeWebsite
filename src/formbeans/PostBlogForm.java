package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class PostBlogForm extends FormBean {
	private String content;
	private String title;
	
	public String getContent()  { return content; }
	public String getTitle()    { return title; }
	
	public void setContent(String s)  { content = s;  }
	public void setTitle(String s)    {	title = s; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (title == null || title.length() == 0) {
			errors.add("Title should not be empty");
		}
		
		if (content == null || content.length() == 0) {
			errors.add("Content should not be empty");
		}
				
		return errors;
	}
}