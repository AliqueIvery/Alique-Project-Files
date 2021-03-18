package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="\"TuitionRefund\".claim")
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="claim_id")
	private Integer claimId;
	private Integer statusId;
	@Column(name="employee_id")
	private Integer employeeId;
	@Column(name ="firstname")
	private String firstName;
	@Column(name ="lastname")
	private String lastName;
	@Column(name ="typeofclaim")
	private String typeOfClaim;
	@Column(name="estimated_refund")
	private  Double estimatedRefund;
	@Column(name="approved_refund")
	private Double approvedRefund;
	@Column(name="cost_of_course")
	private Double costOfClass;
	@Column(name="date_applied")
	private String dateApplied;
	@Column(name="date_approved")
	private String dateApproved;
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTypeOfClaim() {
		return typeOfClaim;
	}
	public void setTypeOfClaim(String typeOfClaim) {
		this.typeOfClaim = typeOfClaim;
	}
	public Double getEstimatedRefund() {
		return estimatedRefund;
	}
	public void setEstimatedRefund(Double estimatedRefund) {
		this.estimatedRefund = estimatedRefund;
	}
	public Double getApprovedRefund() {
		return approvedRefund;
	}
	public void setApprovedRefund(Double approvedRefund) {
		this.approvedRefund = approvedRefund;
	}
	public Double getCostOfClass() {
		return costOfClass;
	}
	public void setCostOfClass(Double costOfClass) {
		this.costOfClass = costOfClass;
	}
	public String getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getDateApproved() {
		return dateApproved;
	}
	public void setDateApproved(String dateApproved) {
		this.dateApproved = dateApproved;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvedRefund == null) ? 0 : approvedRefund.hashCode());
		result = prime * result + ((claimId == null) ? 0 : claimId.hashCode());
		result = prime * result + ((costOfClass == null) ? 0 : costOfClass.hashCode());
		result = prime * result + ((dateApplied == null) ? 0 : dateApplied.hashCode());
		result = prime * result + ((dateApproved == null) ? 0 : dateApproved.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((estimatedRefund == null) ? 0 : estimatedRefund.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result + ((typeOfClaim == null) ? 0 : typeOfClaim.hashCode());
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
		Claim other = (Claim) obj;
		if (approvedRefund == null) {
			if (other.approvedRefund != null)
				return false;
		} else if (!approvedRefund.equals(other.approvedRefund))
			return false;
		if (claimId == null) {
			if (other.claimId != null)
				return false;
		} else if (!claimId.equals(other.claimId))
			return false;
		if (costOfClass == null) {
			if (other.costOfClass != null)
				return false;
		} else if (!costOfClass.equals(other.costOfClass))
			return false;
		if (dateApplied == null) {
			if (other.dateApplied != null)
				return false;
		} else if (!dateApplied.equals(other.dateApplied))
			return false;
		if (dateApproved == null) {
			if (other.dateApproved != null)
				return false;
		} else if (!dateApproved.equals(other.dateApproved))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (estimatedRefund == null) {
			if (other.estimatedRefund != null)
				return false;
		} else if (!estimatedRefund.equals(other.estimatedRefund))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		if (typeOfClaim == null) {
			if (other.typeOfClaim != null)
				return false;
		} else if (!typeOfClaim.equals(other.typeOfClaim))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", statusId=" + statusId + ", employeeId=" + employeeId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", typeOfClaim=" + typeOfClaim + ", estimatedRefund="
				+ estimatedRefund + ", approvedRefund=" + approvedRefund + ", costOfClass=" + costOfClass
				+ ", dateApplied=" + dateApplied + ", dateApproved=" + dateApproved + "]";
	}
	
	
	
	
}
