package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.FinalReport;
import com.revature.beans.Person;
import com.revature.services.FinalReportService;
import com.revature.services.FinalReportServiceImpl;

public class FinalReportDelegate implements FrontControllerDelegate {
	private FinalReportService  fp = new FinalReportServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = (String) req.getAttribute("path");
		
		if(path == null || path.equals("")) {
			switch(req.getMethod()) {
			case "POST":
				FinalReport finalReport = (FinalReport) om.readValue(req.getInputStream(), FinalReport.class);
				finalReport.setFinalReportId(fp.addFinalReport(finalReport));
				resp.getWriter().write(om.writeValueAsString(finalReport));
				resp.setStatus(HttpServletResponse.SC_CREATED);
				break;
			default:
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				break;
			}
		}
		else {
			int finalReport_id = Integer.valueOf(path);
			FinalReport f = null;
			switch(req.getMethod()) {
			case "GET":
				f = fp.getFinalReportById(finalReport_id);
				if(f != null) {
					resp.getWriter().write(om.writeValueAsString(f));
				}
				else {
					resp.sendError(404, "Final Report not found.");
				}
				break;
			case "PUT":
				if(isManagement((Person) req.getSession().getAttribute("person"))){
					f = om.readValue(req.getInputStream(), FinalReport.class);
					fp.updateFinalReport(f);
					resp.getWriter().write(om.writeValueAsString(f));
				}
				else {
					resp.sendError(404, "Final Report not found.");
				}
				break;
			case "DELETE":
				if(isManagement((Person) req.getSession().getAttribute("person"))) {
					f = om.readValue(req.getInputStream(), FinalReport.class);
					fp.deleteFinalReport(f);
					
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
