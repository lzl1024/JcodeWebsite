package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databeans.Profile;

/**
 * This servlet is the "view" for images.  It looks at the "profile"
 * request attribute and sends it's image bytes to the client browser.
 * 
 * We need to use a servlet instead of a JSP for the "view" of the image
 * because we need to send back the image bits and not HTML.
 */
@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	Profile profile = (Profile) request.getAttribute("profile");

        if (profile == null) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	return;
        }
        
        response.setContentType(profile.getPictype());
        System.out.print(profile.getPictype());
        ServletOutputStream out = response.getOutputStream();
        out.write(profile.getBytes());
    }
}
