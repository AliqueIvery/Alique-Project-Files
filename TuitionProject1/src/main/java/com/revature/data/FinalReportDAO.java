package com.revature.data;

import com.revature.beans.FinalReport;

public interface FinalReportDAO extends GenericDAO <FinalReport> {
	public FinalReport add(FinalReport report);
}
