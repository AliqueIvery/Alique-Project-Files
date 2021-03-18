package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Mailbox;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.exceptions.MailNotSentException;
import com.revature.exceptions.MailboxNotFoundException;
import com.revature.services.MailboxService;
import com.revature.services.MailboxServiceImpl;
import com.revature.services.StatusService;
import com.revature.services.StatusServiceImpl;

public class PersonDelegate implements FrontControllerDelegate {
	private MailboxService mailServ = new MailboxServiceImpl();
	private StatusService statServ = new StatusServiceImpl();
	
	private ObjectMapper om = new ObjectMapper();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = (String) req.getAttribute("path");
		if(path.equals("mailbox")) {
			Mailbox m = null;
			Person p = null;
			switch(req.getMethod()) {
			//get mailbox by employee id
			case "GET":
				p = (Person) req.getSession().getAttribute("person");
				try {
					m = mailServ.getMailboxByEmployeeId(p.getId());
				} catch (MailboxNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(m != null) {
					resp.getWriter().write(om.writeValueAsString(m));
				}
				else {
					
				}
				break;
				//create new email
			case "POST":
				m = (Mailbox) om.readValue(req.getInputStream(), Mailbox.class);
				if(m != null) {
					try {
						mailServ.addMailbox(m);
					} catch (MailNotSentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resp.getWriter().write(om.writeValueAsString(m));
				}
				else {
					
				}
				break;
			case "PUT":
				p = (Person) req.getSession().getAttribute("person");
				if(p != null) {
					try {
						mailServ.updateMailbox(mailServ.getMailboxByEmployeeId(p.getId()));
						resp.getWriter().write(om.writeValueAsString(mailServ.getMailboxByEmployeeId(p.getId())));
					} catch (MailboxNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
			}
		}
		else if(path.equals("status")){
			Status s = null;
			Person p = null;
			switch(req.getMethod()) {
			case "PUT":
				p = (Person) req.getSession().getAttribute("person");
				s = (Status) om.readValue(req.getInputStream(), Status.class);
				if(isManagement(p) && s != null) {
					statServ.updateStatus(s);
					resp.getWriter().write(om.writeValueAsString(s));
				}
				else {
					
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
			}
		}
		else {
			
		}
	}
	public boolean isManagement(Person p) {
		if(p != null && ((p.getTypeOfUser().equals("directSupervisor") || p.getTypeOfUser().contentEquals("departmentHead")) || p.getTypeOfUser().equals("benCo"))) {
			return true;
		}
		return false;
	}

}
