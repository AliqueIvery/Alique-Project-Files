package com.revature.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.FinalReport;
import com.revature.data.FinalReportDAO;
import com.revature.utils.HibernateUtil;

public class FinalReportHibernate implements FinalReportDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public FinalReport getById(Integer id) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM \"Tuition\".finalreport where finalreport_id = :id";
		Query<FinalReport> q = s.createQuery(hql , FinalReport.class);
		q.setParameter("id", id);
		List<FinalReport> finalReportList = q.getResultList();
		FinalReport fp = finalReportList.get(0);
		s.close();
		return fp;
	}

	@Override
	public Set<FinalReport> getAll() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM \"TuitionRefund\".finalreport";
		Query<FinalReport> q = s.createQuery(hql , FinalReport.class);
		List<FinalReport> fpList = q.getResultList();
		Set<FinalReport> fpSet = new HashSet<>();
		fpSet.addAll(fpList);
		s.close();
		return fpSet;
	}

	@Override
	public void update(FinalReport t) {
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
	public void delete(FinalReport t) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		}catch(Exception e) {
			if(tx!= null) {
				tx.rollback();
			}
		}finally {
			s.close();
		}
	}

	@Override
	public FinalReport add(FinalReport report) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(report);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		}finally {
			s.close();
		}
		return report;
	}

}
