package servlets;

import ado.AccountAdo;
import ado.UserAdo;
import adoImpl.AccountAdoImpl;
import adoImpl.UserAdoImpl;
import entity.Account;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="addUserAccount")
public class UserAccountServlet extends HttpServlet {
    AccountAdo adoAc = new AccountAdoImpl();
    UserAdo adoUs = new UserAdoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addAccount.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        String nameSurname = request.getParameter("nameSurname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String card = request.getParameter("card");
        String s = "";
        if (nameSurname != "" || phone != "" || email != "" || card != "") {
            Account account = new Account(card, 0, 0, 0);
            User user = new User(nameSurname, phone, email, account);
            adoAc.addAccount(account);
            adoUs.addUser(user);
            s = "Користувач доданий";
            request.setAttribute("s", s);
        } else {
            s = "Ви ввели не всі дані. Спробуйте ще раз.";
            request.setAttribute("s", s);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("addAccount.jsp");
        dispatcher.forward(request, response);
    }
}
