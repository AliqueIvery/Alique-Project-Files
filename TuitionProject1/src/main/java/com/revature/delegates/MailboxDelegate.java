package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Mailbox;
import com.revature.beans.Person;
import com.revature.exceptions.MailNotSentException;
import com.revature.exceptions.MailboxNotFoundException;
import com.revature.services.MailboxService;
import com.revature.services.MailboxServiceImpl;

public class MailboxDelegate implements FrontControllerDelegate {
	private MailboxService mb = new MailboxServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = (String) req.getAttribute("path");
		
		if(path == null || path.equals("")) {
			switch(req.getMethod()) {
			case "POST":
				Mailbox m = om.readValue(req.getInputStream(), Mailbox.class);
				try {
					m.setMailboxId(mb.addMailbox(m));
				} catch (MailNotSentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resp.getWriter().write(om.writeValueAsString(m));
				resp.setStatus(HttpServletResponse.SC_CREATED);
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
			}
		}
		else {
			int id = Integer.valueOf(path);
			Mailbox m = null;
			switch(req.getMethod()) {
			case "GET":
				try {
					m = mb.getMailboxByEmployeeId(id);
				} catch (MailboxNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(m != null) {
					resp.getWriter().write(om.writeValueAsString(m));
				}
				else {
					resp.sendError(404, "Mailbox not found.");
				}
				break;
			case "PUT":
				if(isManagement((Person) req.getSession().getAttribute("person"))) {
					m = om.readValue(req.getInputStream(), Mailbox.class);
					mb.updateMailbox(m);
					resp.getWriter().write(om.writeValueAsString(m));
				}
				else {
					resp.sendError(404, "Method not authorized for particular user.");
				}
				break;
			case "DELETE":
				if(isManagement((Person) req.getSession().getAttribute("person"))) {
					m = om.readValue(req.getInputStream(), Mailbox.class);
					mb.deleteMailBox(m);
				}
				else {
					resp.sendError(404, "Method not authorized for particular user.");
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
			}
		}
	}
	public boolean isManagement(Person p) {
		if(p != null && ((p.getTypeOfUser().equals("directSupervisor") || p.getTypeOfUser().contentEquals("departmentHead")) || p.getTypeOfUser().equals("benCo"))) {
			return true;
		}
		return false;
	}
}
