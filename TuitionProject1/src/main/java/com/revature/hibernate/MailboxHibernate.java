package com.revature.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Mailbox;
import com.revature.data.MailboxDAO;
import com.revature.exceptions.PersonNotFoundException;
import com.revature.utils.HibernateUtil;

public class MailboxHibernate implements MailboxDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Mailbox getById(Integer id) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM Mailbox where mailbox_id = :id";
		Query<Mailbox> q = s.createQuery(hql, Mailbox.class);
		q.setParameter("id", id);
		List<Mailbox> mailBoxes = q.getResultList();
		Mailbox m = mailBoxes.get(0);
		s.close();
		return m;
	}

	@Override
	public Set<Mailbox> getAll() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM Mailbox";
		Query<Mailbox> q = s.createQuery(hql , Mailbox.class );
		List<Mailbox> mailList = q.getResultList();
		Set<Mailbox> mailSet = new HashSet<>();
		mailSet.addAll(mailList);
		s.close();
		return mailSet;
	}

	@Override
	public void update(Mailbox t) {
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
	public void delete(Mailbox t) {
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
	public Mailbox add(Mailbox m) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(m);
			tx.commit();
		}catch(Exception e){
			if(tx != null) {
				tx.rollback();
			}
		}finally {
			s.close();
		}
		return m;
	}

	@Override
	public Mailbox getByEmployeeId(Integer id) throws PersonNotFoundException {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM \"TuitionRefund\".mailbox where person_id = :id";
		Query<Mailbox> q = s.createQuery(hql , Mailbox.class);
		q.setParameter("id", id);
		List<Mailbox> mailList = q.getResultList();
		Mailbox m = mailList.get(0);
		s.close();
		
		return m;
	}

	@Override
	public Mailbox getBySenderId(Integer id) throws PersonNotFoundException {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM \"TuitionRefund\".mailbox where sender_id = :id";
		Query<Mailbox> q = s.createQuery(hql , Mailbox.class);
		q.setParameter("id", id);
		List<Mailbox> mailList = q.getResultList();
		Mailbox m = mailList.get(0);
		s.close();
		return m;
	}

}
