package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.Person;
import com.revature.data.DAOFactory;
import com.revature.data.PersonDAO;
import com.revature.exceptions.PersonAlreadyExistException;
import com.revature.exceptions.PersonNotFoundException;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	public PersonServiceImpl() {
		personDao = DAOFactory.getPersonDAO();
	}
	private Logger log = Logger.getLogger(PersonServiceImpl.class);
	@Override
	public Integer addPerson(Person p) throws PersonAlreadyExistException {
		// TODO Auto-generated method stub
		return personDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) throws PersonNotFoundException {
		// TODO Auto-generated method stub
		return personDao.getById(id);
	}

	@Override
	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		if(personDao.getById(p.getId()) != null) {
			personDao.update(p);
		}
		else {
			log.info("User tried to update a nonexistent user");
		}
	}

	@Override
	public void deletePerson(Person p) {
		// TODO Auto-generated method stub
		if(personDao.getById(p.getId()) != null) {
			personDao.delete(p);
		}
		else {
			log.info("User tried to delete a non existent user");
		}
	}

	@Override
	public Person getPersonByUsername(String string) throws PersonNotFoundException {
		// TODO Auto-generated method stub
		
		return personDao.getByUsername(string);
	}

}
