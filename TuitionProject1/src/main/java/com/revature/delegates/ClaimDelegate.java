package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Claim;
import com.revature.beans.Person;
import com.revature.exceptions.ClaimNotFoundException;
import com.revature.exceptions.MoreThanOneClaimException;
import com.revature.services.ClaimService;
import com.revature.services.ClaimServiceImpl;

public class ClaimDelegate implements FrontControllerDelegate {
	private ClaimService cs = new ClaimServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = (String) req.getAttribute("path");
		
		if(path == null || path.equals("")){
			switch(req.getMethod()) {
			case "POST":
				Claim claim = (Claim) om.readValue(req.getInputStream(), Claim.class);
				try {
					claim.setClaimId(cs.addClaim(claim));
					System.out.println("line 31 claim delegate");
					resp.getWriter().write(om.writeValueAsString(claim));
					resp.setStatus(HttpServletResponse.SC_CREATED);
				} catch (MoreThanOneClaimException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("line 38 claim delegate");
				break;
			case "GET":
				resp.getWriter().write(
						om.writeValueAsString(cs.getAllClaims()));
				break;

			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
			}
		}else if(path.equals("employee")) {
			if(req.getMethod().equals("GET")){
				int id = Integer.valueOf(req.getParameter("employeeid"));
				Claim c = cs.getClaimByEmployeeId(id);
				if(c != null) {
					resp.getWriter().write(om.writeValueAsString(c));
				}
				else {
					resp.sendError(404, "Claim not found.");
				}
			}
		}else if(path.contains("status")) {
			if(req.getMethod().equals("GET")) {
				int id = Integer.valueOf(req.getParameter("statusId"));
				Claim c = cs.getClaimByStatusId(id);
				if(c != null) {
					resp.getWriter().write(om.writeValueAsString(c));
					resp.setStatus(201);
				}else {
					resp.sendError(404, "Claim not found.");
				}
			}
		}
		else {
			int claimId = Integer.valueOf(path);
			Claim c = null;
			switch(req.getMethod()) {
			case "GET":
				try {
					c = cs.getClaimById(claimId);
				} catch (ClaimNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(c != null) {
					resp.getWriter().write(om.writeValueAsString(c));
				}
				else {
					resp.sendError(404, "Claim not found.");
				}
				break;
			case "PUT":
					System.out.println("Line 91 claim delegate");
					c = om.readValue(req.getInputStream(), Claim.class);
					if(c != null) {
						System.out.println("line 94 claim delegate");
						cs.updateClaim(c);
						resp.getWriter().write(om.writeValueAsString(c));
						resp.setStatus(HttpServletResponse.SC_CREATED);
					}else {
						resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					}
				break;
			case "DELETE":
				if(isManagement((Person) req.getSession().getAttribute("person"))) {
					c = om.readValue(req.getInputStream(), Claim.class);
					cs.deleteClaim(c);
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
		if(p != null && ((p.getTypeOfUser().equals("Direct Supervisor") || p.getTypeOfUser().contentEquals("Department Head")) || p.getTypeOfUser().equals("Benco Supervisor"))) {
			return true;
		}
		return false;
	}

}
