package com.revature.services;

import com.revature.beans.Mailbox;
import com.revature.exceptions.MailNotSentException;
import com.revature.exceptions.MailboxNotFoundException;

public interface MailboxService {
	public Integer addMailbox(Mailbox m) throws MailNotSentException;
	public Mailbox getMailboxById(Integer mailboxId) throws MailboxNotFoundException;
	public Mailbox getMailboxByEmployeeId(Integer employeeId) throws MailboxNotFoundException;
	public Mailbox getMailboxBySenderId(Integer senderId) throws MailboxNotFoundException;
	public void updateMailbox(Mailbox m);
	public void deleteMailBox(Mailbox m);
}
