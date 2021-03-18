package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Person;
import com.revature.exceptions.PersonAlreadyExistException;
import com.revature.exceptions.PersonNotFoundException;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

public class LoginDelegate implements FrontControllerDelegate {
	
	private PersonService perServ = new PersonServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("loginDelegate");
		String path =  (String) req.getAttribute("path");
		System.out.println(path);
		System.out.println(req.getMethod());
		if(path == null || path.equals("")){
			if ("POST".equals(req.getMethod())) {
				// register a user
				System.out.println("line 30 login delegate");
				Person p = (Person) om.readValue(req.getInputStream(), Person.class);
					try {
						p.setId(perServ.addPerson(p));
					} catch (PersonAlreadyExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (p.getId() == 0) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				} else {
					resp.getWriter().write(om.writeValueAsString(p));
					resp.setStatus(HttpServletResponse.SC_CREATED);
				}
			} else if ("GET".equals(req.getMethod())) {
				System.out.println("line 46 login delegate");
				Person p = (Person) req.getSession().getAttribute("person");
				System.out.println(p);
				resp.getWriter().write(om.writeValueAsString(p));
				resp.setStatus(200);
				System.out.println("line 50 login delegate");
			} else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else if (path.contains("login")) {
			if ("POST".equals(req.getMethod())) {
				System.out.println("line 49 LoginDelegate");
				logIn(req, resp);
			}
			else if ("DELETE".equals(req.getMethod())) {
				req.getSession().invalidate();
			}
			else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else {
			userWithId(req, resp, Integer.valueOf(path));
		}

	}
	private void userWithId(HttpServletRequest req, HttpServletResponse resp, Integer id) throws IOException {
		System.out.println("line 71 login delegate");
		switch (req.getMethod()) {
			case "GET":
			Person p = null;
			try {
				p = perServ.getPersonById(id);
			} catch (PersonNotFoundException e) {
				// TODO Auto-generated catch block
				resp.sendError(404, "No user found with that id.");
			}
				if (p != null) {
					resp.getWriter().write(om.writeValueAsString(p));
				} else {
					resp.sendError(404, "Person not found.");
				}
				break;
			case "PUT":
				String password = req.getParameter("pass");
				Person person = (Person) req.getSession().getAttribute("person");
				if (person != null) {
					person.setPassword(password);
					perServ.updatePerson(person);
				} else
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				break;
			case "DELETE":
				Person user = om.readValue(req.getInputStream(), Person.class);
				perServ.deletePerson(user);
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
		}
	}
	private void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username  = req.getParameter("user");
		String password = req.getParameter("pass");
		
		Person p = null;
		try {
			p = perServ.getPersonByUsername(username);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			resp.sendError(404, "No user found with that username throwing error.");
		}
		if (p != null) {
			if (p.getPassword().equals(password)) {
				req.getSession().setAttribute("person", p);
				resp.getWriter().write(om.writeValueAsString(p));
			} else {
				resp.sendError(404, "Incorrect password.");
			}
		} else {
			resp.sendError(404, "No user found with that username. line 112 in loginDelegate");
		}
	}

}

