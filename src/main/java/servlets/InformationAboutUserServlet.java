package servlets;

import ado.AccountAdo;
import adoImpl.AccountAdoImpl;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "informationAboutUserServlet")
public class InformationAboutUserServlet extends HttpServlet {
    AccountAdo ado = new AccountAdoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String card = request.getParameter("card");
        User user = ado.getUserByCard(card);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("informationAboutUser.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("informationAboutUser.jsp");
        dispatcher.forward(request, response);
    }
}
