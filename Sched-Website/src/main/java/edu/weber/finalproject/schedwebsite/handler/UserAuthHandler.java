
package edu.weber.finalproject.schedwebsite.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import edu.weber.finalproject.schedmanager.SessionManager;

/**
 * Intercepts all requests and ensures an authenticated user.
 * @author ryan
 */
public class UserAuthHandler extends HandlerInterceptorAdapter {
 
    private String allowedURLs;
  
    public void setAllowedURLs(String allowedURLs) {
        this.allowedURLs = allowedURLs;
    }
     
    /**
     * Authenticates the user object stored in session and redirects to the 
     * login page on failure.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                  HttpServletResponse response,
                  Object handler) throws Exception {
        
        //Check for allowed urls
        for (String url : allowedURLs.split(","))
        {
            if (request.getServletPath().equals(url)) {
                return true;
            }            
        }
        
        //Check for a user object in session, if null then redirect
        if (request.getSession().getAttribute(SessionManager.SessionKeys.currentUser) == null) {
            response.sendRedirect(request.getServletContext().getContextPath());
            return false;
        }
  
        return true;
    }
}
