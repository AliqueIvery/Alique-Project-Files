package com.revature.services;

import com.revature.beans.Status;

public interface StatusService {
	public Integer addStatus(Status s);
	public Status getStatusById(Integer statusId);
	public void updateStatus(Status s);
	public void deleteStatus(Status s);
}
