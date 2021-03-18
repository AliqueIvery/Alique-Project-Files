package com.revature.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import com.revature.utils.HibernateUtil;

public class StatusHibernate implements StatusDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Set<Status> getAll() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "from Status";
		Query<Status> q = s.createQuery(hql , Status.class);
		List<Status> statusList = q.getResultList();
		Set<Status> statusSet = new HashSet<>();
		statusSet.addAll(statusList);
		s.close();
		return statusSet;
	}

	@Override
	public void update(Status t) {
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
	public void delete(Status t) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
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
	public Status add(Status s) {
		// TODO Auto-generated method stub
		System.out.println("line 71 status hibernate");
		Session ss = hu.getSession();
		Transaction tx = null;
		try {
			tx = ss.beginTransaction();
			ss.save(s);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}finally {
			ss.close();
		}
		return s;
	}

	@Override
	public Status getById(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("line 91 status hibernate");
		Session s = hu.getSession();
		String hql = "from Status where status_id = :id";
		Query<Status> q = s.createQuery(hql , Status.class);
		q.setParameter("id",id);
		List<Status> statuses = q.getResultList();
		Status stat = statuses.get(0);
		s.close();
		return stat;
	}

}
