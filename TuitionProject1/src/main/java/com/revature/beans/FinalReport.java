package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="\"TuitionRefund\".finalreport")
public class FinalReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="finalreport_id")
	private Integer finalReportId;
	@Column(name="attachment")
	private String attachment;
	@Column(name="benco_apporval")
	private Boolean benCoApproval;
	@Column(name="statusid")
	private Integer statusId;
	public Integer getFinalReportId() {
		return finalReportId;
	}
	public void setFinalReportId(Integer finalReportId) {
		this.finalReportId = finalReportId;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Boolean getBenCoApproval() {
		return benCoApproval;
	}
	public void setBenCoApproval(Boolean benCoApproval) {
		this.benCoApproval = benCoApproval;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result + ((benCoApproval == null) ? 0 : benCoApproval.hashCode());
		result = prime * result + ((finalReportId == null) ? 0 : finalReportId.hashCode());
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
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
		FinalReport other = (FinalReport) obj;
		if (attachment == null) {
			if (other.attachment != null)
				return false;
		} else if (!attachment.equals(other.attachment))
			return false;
		if (benCoApproval == null) {
			if (other.benCoApproval != null)
				return false;
		} else if (!benCoApproval.equals(other.benCoApproval))
			return false;
		if (finalReportId == null) {
			if (other.finalReportId != null)
				return false;
		} else if (!finalReportId.equals(other.finalReportId))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FinalReport [finalReportId=" + finalReportId + ", attachment=" + attachment + ", benCoApproval="
				+ benCoApproval + ", statusId=" + statusId + "]";
	}
	
	
}
