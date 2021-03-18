package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.FinalReport;
import com.revature.data.DAOFactory;
import com.revature.data.FinalReportDAO;

public class FinalReportServiceImpl implements FinalReportService {
	private FinalReportDAO finalReportDao;
	private Logger log = Logger.getLogger(FinalReportServiceImpl.class);
	public FinalReportServiceImpl() {
		finalReportDao = DAOFactory.getFinalReportDAO();
	}
	@Override
	public Integer addFinalReport(FinalReport f) {
		// TODO Auto-generated method stub
		return finalReportDao.add(f).getFinalReportId();
	}

	@Override
	public FinalReport getFinalReportById(Integer finalReportId) {
		// TODO Auto-generated method stub
		return finalReportDao.getById(finalReportId);
	}


	@Override
	public void updateFinalReport(FinalReport f) {
		// TODO Auto-generated method stub
		if(finalReportDao.getById(f.getFinalReportId()) != null) {
			finalReportDao.update(f);
		}
		else {
			log.info("User tried to update a non existent final report with id: "+ f.getFinalReportId());
		}
	}

	@Override
	public void deleteFinalReport(FinalReport f) {
		// TODO Auto-generated method stub
		if(finalReportDao.getById(f.getFinalReportId()) != null) {
			finalReportDao.delete(f);
		}
		else {
			log.info("User tried to delete a non existent final report with id:" + f.getFinalReportId());
		}
	}

}
