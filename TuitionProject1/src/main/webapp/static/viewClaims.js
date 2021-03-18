let claims = null;
getClaim();
async function getClaim() {
    let url = baseUrl + '/claim';
    console.log(url);
    let response = await fetch(url);
    console.log(response.status);
    if (response.status === 200) {
     claims = await response.json();
        console.log(claims);
        populateClaims();
    }
}

function populateClaims() {
    let claimSection = document.getElementById('claimSection');
    claimSection.innerHTML = '';
    if (claims.length > 0) {
        let table = document.createElement('table');
        table.id = 'claimTable';
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

                for (let claim of claims){
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
                    tr.appendChild(td)
                    table.appendChild(tr);
				}

				claimSection.appendChild(table);
	}else{
        alert("There are no claims to view");
    }
}
