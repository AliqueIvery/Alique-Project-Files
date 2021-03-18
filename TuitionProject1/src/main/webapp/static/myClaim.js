checkLogin();
let claim = null;
nav.innerHTML += `
                    <form>
                        <label for="claim_id">Claim ID: </label>
						<input id="claim_id" name="claim_id" type="number" />
						 <button type="button" id="loginBtn">Enter</button>
                    </form>`;
                    loginBtn.onclick = setup;
async function setup(){
let url = baseUrl +"/claim?";
url += document.getElementById("claim_id");
let response = await fetch(url, {method:'GET'});
if(response.status == 200){
    claim = await response.json();
    populateClaim();
}else{
    alert("Something went wrong.");
}

}

        function populateClaim() {
            let claimSection = document.getElementById('claimSection');
                let table = document.createElement('table');

                table.innerHTML = `
                    <tr>
                        <th>Claim ID</th>
                        <th>Status ID</th>
                        <th>Employee ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Claim Type</th>
                        <th>Estimated Refund</th>
                        <th>Approved Refund</th>
                        <th>Cost Of Class</th>
                        <th>Date Applied</th>
                        <th>Date Approved</th>
                    </tr>
                `;

                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td>${claim.claimId}</td>
                        <td>${claim.statusId}</td>
                        <td>${claim.employeeId}</td>
                        <td>${claim.firstName}</td>
                        <td>${claim.lastName}</td>
                        <td>${claim.typeOfClaim}</td>
                        <td>${claim.estimatedRefund}</td>
                        <td>${claim.approvedRefund}</td>
                        <td>${claim.costOfClass}</td>
                        <td>${claim.dateApplied}</td>
                        <td>${claim.dateApproved}</td>
                    `;
                    let td = document.createElement('td');
                    tr.appendChild(td);
                    table.appendChild(tr);

                claimSection.appendChild(table);
        }