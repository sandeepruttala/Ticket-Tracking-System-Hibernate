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

@WebServlet("/DeleteTicket")
public class DeleteTicket extends HttpServlet{
    private static final long serialVersionUID = 1L;

	public DeleteTicket() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Ticket ticket = new Ticket();
        ticket.setId(id);
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = session.beginTransaction();
        String message = "";
        Ticket check = session.get(Ticket.class, id);

        if (check != null) {
            session.remove(check);
            tx.commit();
            message = "Ticket Deletion Successful";
        }
        else {
            message = "Ticket Deletion Failed - Ticket not found";
        }
        request.setAttribute("message", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("deleteticket.jsp");
        dispatcher.forward(request, response);

        session.close();
	}
}
