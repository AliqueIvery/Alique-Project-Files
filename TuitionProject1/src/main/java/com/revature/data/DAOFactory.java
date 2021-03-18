package com.revature.data;

import com.revature.hibernate.ClaimHibernate;
import com.revature.hibernate.FinalReportHibernate;
import com.revature.hibernate.MailboxHibernate;
import com.revature.hibernate.PersonHibernate;
import com.revature.hibernate.StatusHibernate;

public class DAOFactory {
	public static PersonDAO getPersonDAO() {
		return new PersonHibernate();
	}
	public static ClaimDAO getClaimDAO(){
		return new ClaimHibernate();
	}
	public static StatusDAO getStatusDAO() {
		return new StatusHibernate();
	}
	public static MailboxDAO getMailboxDAO() {
		return new MailboxHibernate();
	}
	public static FinalReportDAO getFinalReportDAO() {
		return new FinalReportHibernate();
	}
}
