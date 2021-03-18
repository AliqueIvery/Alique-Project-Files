package com.revature.data;

import com.revature.beans.Mailbox;
import com.revature.exceptions.PersonNotFoundException;

public interface MailboxDAO extends GenericDAO<Mailbox> {
	public Mailbox add(Mailbox m);
	public Mailbox getByEmployeeId(Integer id) throws PersonNotFoundException;
	public Mailbox getBySenderId(Integer id) throws PersonNotFoundException;
}	