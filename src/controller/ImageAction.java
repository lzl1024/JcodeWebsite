package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.ProfileDAO;

import org.genericdao.RollbackException;

import databeans.Profile;

/**
 * This action looks up the photo bean by "email" and then passes it
 * (via request attribute) to the ImageServlet.  See also the mapping
 * of /image in the web.xml file.
 * 
 * We need to use a servlet instead of a JSP for the "view" of the image
 * because we need to send back the image bytes and not HTML.
 */
public class ImageAction extends Action {
	private ProfileDAO profileDAO;

    public ImageAction(Model model) {
    	profileDAO = model.getProfileDAO();
	}

    public String getName() { return "image.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {

        	Profile p = profileDAO.read(request.getParameter("email"));
    		if (p != null) request.setAttribute("profile",p);
    		
    		// Note: "/image" is mapped (in the web.xml file) to the ImageServlet
    		// which looks at the "profile" request attribute and sends back the bytes.
    		// If there is no "profile" attribute, it sends back HTTP Error 404 (resource not found).
    		return "image";
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
