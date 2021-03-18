let claimSection = document.getElementById('claimSection');
let claim = null;
let status = null;
claimSection.innerHTML += `
	<form>
		<label for="claim_id">Claim ID: </label>
		<input id="claim_id" name="claim_id" type="text" />
			<button type="button" id="loginBtn1">Enter</button>
	</form>`;
	loginBtn1.onclick = setup;

function setup(){
	getClaimById();
}

async function getClaimById(){
	let id = document.getElementById("claim_id").value;
	console.log("line 112 create claim js");
	let url = baseUrl + "/claim/"+ id;
	response = await fetch(url, {method:"GET"});
	if(response.status === 200){
		console.log("line 116 create claim js");
		claim = await response.json();
		console.log("line 29 check status js");
		console.log(claim);
		populateClaim();
	}else{
		alert("You entered a non existent claim.");
	}
}
function populateClaim(){
	console.log("line 124 create claim js");
	console.log(claim);
	claimSection.innerHTML = ` <form>
		Do you approve this claim?
		<label for="claim_choice">Decision: </label>
		<input id="claim_choice" name="claim_choice" type="text" />
			<button type="button" id="loginBtn2">Enter</button>
	</form>`;
	let submitAddBtn = document.getElementById('loginBtn2');
		submitAddBtn.onclick = getStatusbyId;
	if(claim != null){
		console.log("line 129 create claim js");
		let table = document.createElement('table');
		table.id = 'claimTable';
		table.innerHTML = `<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Cost Of Course</th>
		<th>Claim Type</th>
		<th>Claim Id</th>
		<th>Employee Id</th>
		<th>Status Id</th>
		<th>Estimated Refund</th>
		<th>Approved Refund</th>
		<th>Date Applied</th>
		<th>Date Approved</th>
		</tr>`;
			let tr = document.createElement('tr');
			tr.innerHTML = `<td>${claim.firstName}</td>
							<td>${claim.lastName}</td>
							<td>${claim.costOfClass}</td>
							<td>${claim.typeOfClaim}</td>
							<td>${claim.claimId}</td>
							<td>${claim.employeeId}</td>
							<td>${claim.statusId}</td>
							<td>${claim.estimatedRefund}</td>
							<td>${claim.approvedRefund}</td>
							<td>${claim.dateApplied}</td>
							<td>${claim.dateApproved}</td>`;
							let td = document.createElement('td');
							tr.appendChild(td);
							table.appendChild(tr);
		claimSection.appendChild(table);
	}else {
        claimSection.innerHTML = 'No claims are available.';
	}
}
async function getStatusbyId(){
	 id = claim.statusId;
	  url = baseUrl+"/status/"+ id;
	 console.log(url);
	  response = await fetch(url , {method: 'GET'});
	 console.log("line 19 check status js");
	 console.log(response.status);
		if (response.status === 201){
			status = await response.json();
			let choice = document.getElementById('claim_choice').value;
			if(choice == 'yes'){
				console.log("line 91 claim decision js");
				updateClaim();
			}else{
				alert("no change were made.");
			}
		}
		else{
			alert("Something went wrong.");
		}
}

async function updateClaim(){
	url = baseUrl+"/status/"+status.statusId;
	console.log(url);
	if(loggedUser.typeOfUser == 'Benco Supervisor'){
		console.log("line 105 claim decision js");
		status = {statusId:status.statusId, finalReportId: status.finalReportId,directSupervisorApproval:status.directSupervisorApproval,departmentHeadApproval: status.departmentHeadApproval,benefitsCoordinatorsApproval:true};
		response = await fetch(url,{method:"PUT",body:JSON.stringify(status)});
		console.log(response.status);
		if(response.status == 201){
			alert("Update was a success.");
		}else{
			alert("something went wrong line 111 claim decision");
		}
	}else if(loggedUser.typeOfUser == 'Department Head'){
		status = {statusId:status.statusId, finalReportId: status.finalReportId,directSupervisorApproval:status.directSupervisorApproval,departmentHeadApproval: true,benefitsCoordinatorsApproval:status.benefitsCoordinatorsApproval};
		response = await fetch(url,{method:"PUT",body:JSON.stringify(status)});
		console.log(response.status);
		if(response.status == 201){
			alert("Update was a success.");
		}else{
			alert("something went wrong line 111 claim decision");
		}
	}else{
		status = {statusId:status.statusId, finalReportId: status.finalReportId,directSupervisorApproval:true ,departmentHeadApproval: status.departmentHeadApproval,benefitsCoordinatorsApproval:status.benefitsCoordinatorsApproval};
		response = await fetch(url,{method:"PUT",body:JSON.stringify(status)});
		console.log(response.status);
		if(response.status == 201){
			alert("Update was a success.");
		}else{
			alert("something went wrong line 111 claim decision");
		}
	}
	updateClaim2();
}

async function updateClaim2(){
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
