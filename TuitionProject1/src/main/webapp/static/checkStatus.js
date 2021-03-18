let baseurl = "/TuitionRefund";
let statusSection = document.getElementById('statusSection');
let status = null;
let claim = null;
statusSection.innerHTML = `
                    <form id="add-claim-form">
                        <label for="claim_id">Claim ID: </label>
						<input id="claim_id" name="claim_id" type="number" />
						 <button type="button" onclick="setup" id="submit-add-claim-form" >Submit</button>
        </form>`;
let submitAddBtn = document.getElementById('submit-add-claim-form');
		submitAddBtn.onclick = setup;
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
		if(claim.employeeId == loggedUser.id){
			getStatusbyId();
		}else{
			alert("You entered a non existent claim.");
		}
	}else{
		alert("You entered a non existent claim.");
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
			statusSetup(status);
		}
		else{
			alert("Something went wrong.");
		}	
}

function statusSetup(status){
	console.log(status);
	if(status != null){
	statusSection.innerHTML = ``;
	let table = document.createElement('table');
        table.innerHTML = `
            <tr>
                <th>Status ID</th>
                <th>Final Report Id</th>
                <th>Direct Supervisor Approval</th>
                <th>Benco Coordinator Approval</th>
                <th>Department Head Approval</th>
            </tr>
        `;
		let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${status.statusId}</td>
                <td>${status.finalReportId}</td>
                <td>${status.directSupervisorApproval}</td>
				<td>${status.benefitsCoordinatorsApproval}</td>
				<td>${status.departmentHeadApproval}</td>
			`;
			let td = document.createElement('td');
			tr.appendChild(td);
			table.appendChild(tr);
			statusSection.appendChild(table);
	}else{
		alert("There is no claim with that status Id");
	}
}