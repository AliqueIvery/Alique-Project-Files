package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="\"TuitionRefund\".status")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="status_id")
	private Integer statusId;
	@Column(name ="direct_supervisor_approval")
	private Boolean directSupervisorApproval;
	@Column(name ="department_head_approval")
	private Boolean departmentHeadApproval;
	@Column(name ="benefits_coordinator_approval")
	private Boolean benefitsCoordinatorsApproval;
	@Column(name ="finalreport_id")
	private Integer finalReportId;
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Boolean getDirectSupervisorApproval() {
		return directSupervisorApproval;
	}
	public void setDirectSupervisorApproval(Boolean directSupervisorApproval) {
		this.directSupervisorApproval = directSupervisorApproval;
	}
	public Boolean getDepartmentHeadApproval() {
		return departmentHeadApproval;
	}
	public void setDepartmentHeadApproval(Boolean departmentHeadApproval) {
		this.departmentHeadApproval = departmentHeadApproval;
	}
	public Boolean getBenefitsCoordinatorsApproval() {
		return benefitsCoordinatorsApproval;
	}
	public void setBenefitsCoordinatorsApproval(Boolean benefitsCoordinatorsApproval) {
		this.benefitsCoordinatorsApproval = benefitsCoordinatorsApproval;
	}
	public Integer getFinalReportId() {
		return finalReportId;
	}
	public void setFinalReportId(Integer finalReportId) {
		this.finalReportId = finalReportId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((benefitsCoordinatorsApproval == null) ? 0 : benefitsCoordinatorsApproval.hashCode());
		result = prime * result + ((departmentHeadApproval == null) ? 0 : departmentHeadApproval.hashCode());
		result = prime * result + ((directSupervisorApproval == null) ? 0 : directSupervisorApproval.hashCode());
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
		Status other = (Status) obj;
		if (benefitsCoordinatorsApproval == null) {
			if (other.benefitsCoordinatorsApproval != null)
				return false;
		} else if (!benefitsCoordinatorsApproval.equals(other.benefitsCoordinatorsApproval))
			return false;
		if (departmentHeadApproval == null) {
			if (other.departmentHeadApproval != null)
				return false;
		} else if (!departmentHeadApproval.equals(other.departmentHeadApproval))
			return false;
		if (directSupervisorApproval == null) {
			if (other.directSupervisorApproval != null)
				return false;
		} else if (!directSupervisorApproval.equals(other.directSupervisorApproval))
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
		return "Status [statusId=" + statusId + ", directSupervisorApproval=" + directSupervisorApproval
				+ ", departmentHeadApproval=" + departmentHeadApproval + ", benefitsCoordinatorsApproval="
				+ benefitsCoordinatorsApproval + ", finalReportId=" + finalReportId + "]";
	}
}
