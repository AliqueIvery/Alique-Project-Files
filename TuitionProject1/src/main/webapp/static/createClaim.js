baseUrl = "/TuitionProject1";
let claimSection = document.getElementById('claimSection');
claimSection.innerHTML = `<form id="add-claim-form">
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" placeholder="First Name" required />
        
        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" placeholder="Last Name" required />
        
        <label for="employee_id">Employee ID:</label>
        <input type="number" id="employee_id" placeholder="Employee ID" required />

		<label for="cost_of_class">Course Cost:</label>
		<input type="number" id="cost_of_class" placeholder="Cost of Course" required />
		
		<label for="claim_type">Claim Type:</label>
		<input type="text" id="claim_type" placeholder="Claim Type" required />
		
        <button type="button" onclick="addClaim" id="submit-add-claim-form" >Submit</button>
        </form>
        `;
	 let submitAddBtn = document.getElementById('submit-add-claim-form');
		submitAddBtn.onclick = addClaim;
		
	async function addClaim(){
		let status = {statusId: 0, benefitsCoordinatorsApproval: false,departmentHeadApproval:false,directSupervisorApproval:false,finalReportId:0};
		 url = baseUrl + '/status';
		console.log(url);
		 response = await fetch(url, {method:"POST", body:JSON.stringify(status)});
		if (response.status === 201) {
			status = await response.json();
		} else {
			console.log("line 33 create claim js");
			alert('Something went wrong.');
		}
		let finalReport = {attachment: null, benCoApproval: false, finalReportId:0,statusId: status.statusId};
		url = baseUrl+"/finalreport";
		response = await fetch(url,{method:"POST",body: JSON.stringify(finalReport)});
		if(response.status === 201){
			finalReport = await response.json();
			console.log(finalReport);
		}
		else{
			alert("Something went wrong.line 43 crwate claim js");
		}
		let claim = {firstName: document.getElementById('firstname').value,lastName: document.getElementById('lastname').value, costOfClass: document.getElementById('cost_of_class').value,typeOfClaim: document.getElementById('claim_type').value,claimId:0,estimatedRefund: getEstimatedRefund(), approvedRefund: 0, employeeId: loggedUser.id, dateApplied: new Date()+"", dateApproved: null, statusId: status.statusId}
		function  getEstimatedRefund(){
			console.log(document.getElementById("claim_type").value);
			switch(document.getElementById("claim_type").value){
				case "College":
					estimatedRefund = document.getElementById("cost_of_class").value *.8;
					if(estimatedRefund > 1000){
						estimatedRefund = 1000;
					}
					return estimatedRefund;
				case "Seminar":
					estimatedRefund = document.getElementById("cost_of_class").value* .6;
					if(estimatedRefund > 1000){
						estimatedRefund = 1000;
					}
					return estimatedRefund;
				case "Certificate Prep":
					estimatedRefund = document.getElementById("cost_of_class").value* .75;
					if(estimatedRefund > 1000){
						estimatedRefund = 1000;
					}
					return estimatedRefund;
				case "Certificate":
					estimatedRefund = document.getElementById("cost_of_class").value;
					if(estimatedRefund > 1000){
						estimatedRefund = 1000;
					}
					return estimatedRefund;
				case "Technical":
					estimatedRefund = document.getElementById("cost_of_class").value* .9;
					if(estimatedRefund > 1000){
						estimatedRefund = 1000;
					}
					return estimatedRefund;
				case "Other":
					estimatedRefund = document.getElementById("cost_of_class").value*  .3;
					if(estimatedRefund > 1000){
						estimatedRefund = 1000;
					}
					return estimatedRefund;
				default:
					alert("Please enter College,Seminar,Certification Prep,Certificate,Technical,or Other");
			}
		}
		url = baseUrl + '/claim';
	 response = await fetch(url, {method:'POST', body:JSON.stringify(claim)});
	 if(response.status === 201){
		claim = await response.json();
		}
		else{
			alert("Something went wrong.line 43 crwate claim js");
		}
	 console.log(claim);
    if (response.status === 201) {
		console.log("line 84 create claim js");

        alert('Added claim successfully.');
    } else {
        alert('Something went wrong.line 86');
	}
	console.log("line 106 create claim js");
	getClaimByID(claim.claimId);

}

async function getClaimByID(id){
	console.log("line 112 create claim js");
	let url = baseUrl + "/claim/"+ id;
	response = await fetch(url, {method:"GET"});
	if(response.status === 200){
		console.log("line 116 create claim js");
		let claims = await response.json();
		console.log("line 118 create claim js");
		console.log(claims);
		populateClaim(claims);
	}

}
function populateClaim(claim){
	console.log("line 124 create claim js");
	console.log(claim);
	claimSection.innerHTML = ``;
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