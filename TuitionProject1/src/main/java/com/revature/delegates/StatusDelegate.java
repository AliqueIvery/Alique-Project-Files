package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.services.StatusService;
import com.revature.services.StatusServiceImpl;

public class StatusDelegate implements FrontControllerDelegate {
	private StatusService statServ = new StatusServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("status delegate line 21");
		String path = (String) req.getAttribute("path");
		System.out.println(path+"-line 23 status delegate");
		if(path == null) {
			if(req.getMethod().equals("POST")) {
				System.out.println("post in status line 25");
				Status s = (Status) om.readValue(req.getInputStream(), Status.class);
				System.out.println(s);
				if(s != null) {
					s.setStatusId(statServ.addStatus(s));
					resp.getWriter().write(om.writeValueAsString(s));
					resp.setStatus(HttpServletResponse.SC_CREATED);
				}
				else {
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
			}
		}
			else {
				System.out.println("line 39 status delegate");
				int status_id = Integer.valueOf(path);
				switch(req.getMethod()) {
				case "GET":
					System.out.println("status delegate line 43");
					Status s = statServ.getStatusById(status_id);
					if(s != null) {
						resp.getWriter().write(om.writeValueAsString(s));
						resp.setStatus(HttpServletResponse.SC_CREATED);
					}
					else {
						resp.sendError(404, "Status not found with specified id number.");
					}
					break;
				case "PUT":
					System.out.println("line 55 status delegate");
					
						Status st = (Status) om.readValue(req.getInputStream(), Status.class);
						if(st != null) {
						statServ.updateStatus(st);
						resp.getWriter().write(om.writeValueAsString(st));
						resp.setStatus(HttpServletResponse.SC_CREATED);
					}
					else {
						resp.sendError(404, "Unauthorized User.");
					}
					break;
				case "DELETE":
					Person ps = (Person) req.getSession().getAttribute("person");
					if(isManagement(ps)) {
						Status ss = (Status) om.readValue(req.getInputStream(), Status.class);
						if(ss != null) {
							statServ.deleteStatus(ss);
						}
					}
					else {
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
