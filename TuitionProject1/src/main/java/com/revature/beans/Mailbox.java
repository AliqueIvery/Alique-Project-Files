package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="\"TuitionRefund\".mailbox")
public class Mailbox {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mailbox_id")
	private Integer mailboxId;
	@Column(name="person_id")
	private Integer personId;
	@Column(name="sender_id")
	private Integer senderId;
	private String message;
	@Column(name="read_status")
	private Boolean readStatus;
	public Integer getMailboxId() {
		return mailboxId;
	}
	public void setMailboxId(Integer mailboxId) {
		this.mailboxId = mailboxId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mailboxId == null) ? 0 : mailboxId.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((readStatus == null) ? 0 : readStatus.hashCode());
		result = prime * result + ((senderId == null) ? 0 : senderId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mailbox other = (Mailbox) obj;
		if (mailboxId == null) {
			if (other.mailboxId != null)
				return false;
		} else if (!mailboxId.equals(other.mailboxId))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (readStatus == null) {
			if (other.readStatus != null)
				return false;
		} else if (!readStatus.equals(other.readStatus))
			return false;
		if (senderId == null) {
			if (other.senderId != null)
				return false;
		} else if (!senderId.equals(other.senderId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Mailbox [mailboxId=" + mailboxId + ", personId=" + personId + ", senderId=" + senderId + ", message="
				+ message + ", readStatus=" + readStatus + "]";
	}
	
	
}
