package com.revature.services;

import com.revature.beans.FinalReport;

public interface FinalReportService {
	public Integer addFinalReport(FinalReport f);
	public FinalReport getFinalReportById(Integer finalReportId);
	public void updateFinalReport(FinalReport f);
	public void deleteFinalReport(FinalReport f);
}
