package ctl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
@WebFilter(filterName="FrontController",urlPatterns={"/*"})
public class FrontController  implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(true);
       
UserBean bean= (UserBean) session.getAttribute("user");
if(bean!= null){
System.out.println(bean.getName());
}

        if (session.getAttribute("user") == null) {
        	System.out.println("in if");
            request.setAttribute("message",
                    "Your session has been expired. Please re-login.");
           /* String uri=request.getRequestURI();
            request.setAttribute("targetURI", uri);*/
            String file = request.getServletPath();
            
            if (file.equals("/LoginCtl")){
                chain.doFilter(request, response);
            }else{
          RequestDispatcher rd= request.getRequestDispatcher("/jsp/LoginView.jsp");
          rd.forward(request, response);}
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig conf) throws ServletException {
    }

}
