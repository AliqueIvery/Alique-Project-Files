package com.revature.services;

import com.revature.beans.Person;
import com.revature.exceptions.PersonAlreadyExistException;
import com.revature.exceptions.PersonNotFoundException;

public interface PersonService {
	public Integer addPerson(Person p) throws PersonAlreadyExistException;
	public Person getPersonById(Integer id) throws PersonNotFoundException;
	public Person getPersonByUsername(String string) throws PersonNotFoundException;
	public void updatePerson(Person p);
	public void deletePerson(Person p);
}
