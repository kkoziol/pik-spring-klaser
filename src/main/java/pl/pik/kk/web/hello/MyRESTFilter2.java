package pl.pik.kk.web.hello;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "/MyRESTFilter2", 
//servletNames = { "HelloServlet" },
urlPatterns = { "/hello" })
//@Component
public class MyRESTFilter2 implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("--------------- filter 2 start ---------------");
        
        chain.doFilter(req, res);

        HttpServletRequest r =  (HttpServletRequest) req;
        HttpSession session = r.getSession(true);
        if(session != null){
            System.out.println("---- W sesji mam ----");
            Enumeration<String> attributeNames = session.getAttributeNames();
            while(attributeNames.hasMoreElements()) {
                String element = attributeNames.nextElement();
                System.out.print(element + "=");
                System.out.println(session.getAttribute(element));
            }

            session.putValue("ddd", "Kukuryku");
        }
        
        System.out.println("--------------- filter 2 end ---------------");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}