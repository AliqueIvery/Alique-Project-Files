package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Claim;
import com.revature.data.ClaimDAO;
import com.revature.data.DAOFactory;
import com.revature.exceptions.ClaimNotFoundException;
import com.revature.exceptions.MoreThanOneClaimException;

public class ClaimServiceImpl implements ClaimService {
	private ClaimDAO claimDao;
	public ClaimServiceImpl() {
		claimDao = DAOFactory.getClaimDAO();
	}
	private Logger log = Logger.getLogger(ClaimServiceImpl.class);
	@Override
	public Integer addClaim(Claim claim) throws MoreThanOneClaimException {
		// TODO Auto-generated method stub
		return claimDao.add(claim).getClaimId();
	}

	@Override
	public Claim getClaimById(Integer id) throws ClaimNotFoundException {
		// TODO Auto-generated method stub
		return claimDao.getById(id);
	}


	@Override
	public void updateClaim(Claim claim) {
		// TODO Auto-generated method stub
		if(claimDao.getById(claim.getClaimId()) != null) {
			claimDao.update(claim);
		}
		else {
			log.info("User tried to update non existent claim with id: "+claim.getClaimId());
		}
	}

	@Override
	public void deleteClaim(Claim claim) {
		// TODO Auto-generated method stub
		if(claimDao.getById(claim.getClaimId()) != null) {
			claimDao.delete(claim);
		}
		else {
			log.info("User tried to delete claim with id: " + claim.getClaimId());
		}
	}

	@Override
	public Set<Claim> getAllClaims() {
		// TODO Auto-generated method stub
		return claimDao.getAll();
	}

	@Override
	public Claim getClaimByEmployeeId(Integer id) {
		// TODO Auto-generated method stub
		return claimDao.getClaimByEmployeeId(id);
	}

	@Override
	public Claim getClaimByStatusId(Integer id) {
		// TODO Auto-generated method stub
		return claimDao.getClaimByStatusId(id);
	}
}
