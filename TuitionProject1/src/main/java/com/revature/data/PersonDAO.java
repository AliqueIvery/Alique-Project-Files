package com.revature.data;

import com.revature.beans.Person;
import com.revature.exceptions.PersonAlreadyExistException;
import com.revature.exceptions.PersonNotFoundException;

public interface PersonDAO extends GenericDAO<Person> {
	public Person add(Person p) throws PersonAlreadyExistException;
	public Person getByUsername(String username) throws PersonNotFoundException;
	
}
