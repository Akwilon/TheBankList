package web.servlets;

import bean.Account;
import bean.User;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import dao.impl.AccountDao;
import dao.impl.UserDao;
import service.AllAcountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/summ-accounts")
public class SummAcountSerlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/Parametres.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer value = AllAcountService.getSumAllAccount();
        req.setAttribute("value", value);
        doGet(req, resp);
    }
}
