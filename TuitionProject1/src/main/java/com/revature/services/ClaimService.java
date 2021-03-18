package com.revature.services;

import java.util.Set;

import com.revature.beans.Claim;
import com.revature.exceptions.ClaimNotFoundException;
import com.revature.exceptions.MoreThanOneClaimException;

public interface ClaimService {
	public Integer addClaim(Claim claim) throws MoreThanOneClaimException;
	public Set<Claim> getAllClaims();
	public Claim getClaimById(Integer id) throws ClaimNotFoundException;
	public void updateClaim(Claim claim);
	public void deleteClaim(Claim claim);
	public Claim getClaimByEmployeeId(Integer id);
	public Claim getClaimByStatusId(Integer id);
}
