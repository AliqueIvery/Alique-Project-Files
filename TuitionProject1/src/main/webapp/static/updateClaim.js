updateClaim2();
async function updateClaim2(){
	getStatusbyId();
	if(status.benefitsCoordinatorsApproval && (status.departmentHeadApproval && status.directSupervisorApproval)){
		claim = {approvedRefund: claim.estimatedRefund,claimId: claim.claimId,costOfClass:claim.costOfClass,dateApplied: claim.dateApplied, dateApproved: new Date()+"",employeeId: claim.employeeId, estimatedRefund: claim.estimatedRefund,firstName: claim.firstName,lastName: claim.lastName,statusId:claim.statusId, typeOfClaim: claim.typeOfClaim};
		url = baseUrl+"/claim/"+claim.claimId;
		response = await fetch(url,{method:"PUT",body: JSON.stringify(claim)});
		console.log(response.status);
		if(response.status == 201){
			alert("Claim is fully approved.");
		}else{
			alert("something happened line 146 claim decision");
		}
	}
}