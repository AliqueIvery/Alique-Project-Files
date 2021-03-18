package com.revature.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.exceptions.PersonAlreadyExistException;
import com.revature.exceptions.PersonNotFoundException;
import com.revature.utils.HibernateUtil;

public class PersonHibernate implements PersonDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Person p = s.get(Person.class, id);
		s.close();
		return p;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String query = "FROM Person";
		Query<Person> q = s.createQuery(query,Person.class);
		List<Person> personList = q.getResultList();
		Set<Person> personSet = new HashSet<>();
		personSet.addAll(personList);
		s.close();
		return personSet;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
	}

	@Override
	public Person add(Person p) throws PersonAlreadyExistException {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return p;
	}

	
	@Override
	public Person getByUsername(String username) throws PersonNotFoundException {
		// TODO Auto-generated method stub
		Session s = hu.getSession();
		String hql = "FROM Person where username = :username";
		Query<Person> q = s.createQuery(hql , Person.class);
		q.setParameter("username", username);
		List<Person> persons = q.getResultList();
		s.close();
		return persons.get(0);
	}

}
