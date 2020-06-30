package web.servlets;

import bean.Account;
import org.apache.log4j.Logger;
import service.AllAcountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/show-all-accounts")
public class AcountServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AcountServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> res = AllAcountService.getAllaccounts();
        if (res != null){
            req.setAttribute("accounts",res);
            getServletContext().getRequestDispatcher("/WEB-INF/view/AllAcc.jsp").forward(req,resp);
        } else {
            LOGGER.fatal("Account list in " + AcountServlet.class +  " is null" );
        }
    }
}
