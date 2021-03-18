package com.revature.data;

import com.revature.beans.Claim;
import com.revature.exceptions.MoreThanOneClaimException;

public interface ClaimDAO extends GenericDAO<Claim> {
	public Claim add(Claim c) throws MoreThanOneClaimException;
	public Claim getClaimByEmployeeId(Integer id);
	public Claim getClaimByStatusId(Integer id);
}
