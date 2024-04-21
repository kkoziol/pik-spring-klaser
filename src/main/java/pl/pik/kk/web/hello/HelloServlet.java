package pl.pik.kk.web.hello;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().println("Hurra moj pierwszy serwlet z obsluga POST ---> "
                + request.getParameter("param1"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Hello doGET...");
        
        response.getOutputStream().println("Hurra moj pierwszy serwlet");

    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Hello init");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Hello destroy");
    }
    
}
