package formbeans;

import org.mybeans.form.FormBean;

public class SearchForm extends FormBean{
	private String keyword   = "";
	public String getKeyword()  		{return keyword;	}
	public void setKeyword(String k) 	{keyword = k;	}

}
