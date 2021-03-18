package com.revature.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Claim;
import com.revature.data.ClaimDAO;
import com.revature.exceptions.MoreThanOneClaimException;
import com.revature.utils.HibernateUtil;

public class ClaimHibernate implements ClaimDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public Claim getById(Integer id) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "From Claim where claim_id = :id";
		Query<Claim> q = s.createQuery(hql , Claim.class);
		q.setParameter("id", id);
		List<Claim> claimList = q.getResultList();
		Claim c = claimList.get(0);
		s.close();
		return c;
	}

	@Override
	public Set<Claim> getAll() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM Claim";
		Query<Claim> q = s.createQuery(hql , Claim.class);
		List<Claim> claimList = q.getResultList();
		Set<Claim> claimSet = new HashSet<>();
		claimSet.addAll(claimList);
		s.close();
		return claimSet;
	}

	@Override
	public void update(Claim t) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}finally {
			s.close();
		}
	}

	@Override
	public void delete(Claim t) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.getTransaction();
			s.delete(t);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}finally {
			s.close();
		}
	}

	@Override
	public Claim add(Claim c) throws MoreThanOneClaimException {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(c);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}finally {
			s.close();
		}
		return c;
	}
	public Claim getClaimByEmployeeId(Integer id) {
		Session s = hu.getSession();
		String hql = "FROM Claim where employee_id= :id";
		Query<Claim> q = s.createQuery(hql, Claim.class);
		q.setParameter("id", id);
		List<Claim> claimList = q.getResultList();
		Claim c = claimList.get(0);
		s.close();
		return c;
	}
	public Claim getClaimByStatusId(Integer id) {
		Session s = hu.getSession();
		String hql = "FROM Claim where statusId= :id";
		Query<Claim> q = s.createQuery(hql, Claim.class);
		q.setParameter("id", id);
		List<Claim> claimList = q.getResultList();
		Claim c = claimList.get(0);
		System.out.println("line 115 claim hibernate");
		s.close();
		return c;
	}
}
