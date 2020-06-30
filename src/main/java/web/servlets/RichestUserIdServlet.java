package web.servlets;

import bean.Account;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import dao.impl.AccountDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/richest-user-id")
public class RichestUserIdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/Parametres.jsp").forward(req, resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ID = 0;
        try {
            List<Account> accountList = new AccountDao().getAll();
            int maxValue = Integer.MIN_VALUE;
            for (Account account:accountList){
                 if (account.getAccount() > maxValue){
                     maxValue = account.getAccount();
                     ID = account.getUserID();
                 }
            }
            req.setAttribute("account", ID);
        } catch (ConnectionPoolException | ConnectionPoolNotInitializedException e) {
            e.printStackTrace();
        }
        doGet(req,resp);
    }
}