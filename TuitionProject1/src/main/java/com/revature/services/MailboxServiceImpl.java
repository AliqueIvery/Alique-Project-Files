package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.Mailbox;
import com.revature.data.DAOFactory;
import com.revature.data.MailboxDAO;
import com.revature.exceptions.MailNotSentException;
import com.revature.exceptions.MailboxNotFoundException;
import com.revature.exceptions.PersonNotFoundException;

public class MailboxServiceImpl implements MailboxService {
	private MailboxDAO mailboxDao;
	private Logger log = Logger.getLogger(MailboxServiceImpl.class);
	public MailboxServiceImpl() {
		mailboxDao = DAOFactory.getMailboxDAO();
	}
	@Override
	public Integer addMailbox(Mailbox m) throws MailNotSentException {
		// TODO Auto-generated method stub
		return mailboxDao.add(m).getMailboxId();
	}

	@Override
	public Mailbox getMailboxById(Integer mailboxId) throws MailboxNotFoundException {
		// TODO Auto-generated method stub
		return mailboxDao.getById(mailboxId);
	}

	@Override
	public Mailbox getMailboxByEmployeeId(Integer employeeId) throws MailboxNotFoundException {
		// TODO Auto-generated method stub
		try {
			return mailboxDao.getByEmployeeId(employeeId);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Mailbox getMailboxBySenderId(Integer senderId) throws MailboxNotFoundException {
		// TODO Auto-generated method stub
		try {
			return mailboxDao.getBySenderId(senderId);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateMailbox(Mailbox m) {
		// TODO Auto-generated method stub
		if(mailboxDao.getById(m.getMailboxId()) != null) {
			mailboxDao.update(m);
		}
		else {
			log.info("User tried to update a non existent mailbox with id "+ m.getMailboxId());
		}
	}

	@Override
	public void deleteMailBox(Mailbox m) {
		// TODO Auto-generated method stub
		if(mailboxDao.getById(m.getMailboxId()) != null) {
			mailboxDao.update(m);
		}
		else {
			log.info("User tried to update a non existent mailbox with id: "+ m.getMailboxId());
		}
	}

}
