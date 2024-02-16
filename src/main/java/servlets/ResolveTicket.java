package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.hibernate.Session;
import org.hibernate.Transaction;

import helper.FactoryProvider;
import models.Ticket;

@WebServlet("/ResolveTicket")
public class ResolveTicket extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResolveTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = session.beginTransaction();
        String message = "";
        Ticket check = session.get(Ticket.class, id);
        if (check == null) {
            message = "Ticket Updating Failed - Ticket not found";
        } else {
            check.setStatus(status);
            session.merge(check);
            tx.commit();
            message = "Ticket Resolved successfully";
        }
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("resolvetickets.jsp");
        rd.forward(request, response);
        session.close();
    }
}
