package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.Status;
import com.revature.data.DAOFactory;
import com.revature.data.StatusDAO;

public class StatusServiceImpl implements StatusService {
	private StatusDAO statusDao;
	public StatusServiceImpl() {
		statusDao = DAOFactory.getStatusDAO();
	}
	private Logger log = Logger.getLogger(StatusServiceImpl.class);
	@Override
	public Integer addStatus(Status s) {
		System.out.println("line 14 status service");
		// TODO Auto-generated method stub
		return statusDao.add(s).getStatusId();
	}

	@Override
	public Status getStatusById(Integer statusId) {
		// TODO Auto-generated method stub
		return statusDao.getById(statusId);
	}

	@Override
	public void updateStatus(Status s) {
		// TODO Auto-generated method stub
		if(statusDao.getById(s.getStatusId()) != null) {
			statusDao.update(s);
		}
		else {
			log.info("User tried to update a non existent user");
		}
	}

	@Override
	public void deleteStatus(Status s) {
		// TODO Auto-generated method stub
		if(statusDao.getById(s.getStatusId()) != null) {
			statusDao.delete(s);
		}
		else {
			log.info("User tried to delete a non existent user");
		}
	}

}
