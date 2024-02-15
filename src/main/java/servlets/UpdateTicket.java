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

@WebServlet("/UpdateTicket")
public class UpdateTicket extends HttpServlet{
    private static final long serialVersionUID = 1L;

	public UpdateTicket() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String priority = request.getParameter("priority");

		Session session = FactoryProvider.getFactory().openSession();
		Transaction tx = session.beginTransaction();
        String message="";
        Ticket check = session.get(Ticket.class, id);
        if(check == null){
            message = "Ticket Updating Failed - Ticket not found";
        }
        else{
            check.setTitle(title);
            check.setDescription(description);
            check.setStatus(status);
            check.setPriority(priority);
            session.merge(check);
            tx.commit();
            message = "Ticket updated successfully";
        }
        request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("updateticket.jsp");
            rd.forward(request, response);
          session.close();
	}
}
