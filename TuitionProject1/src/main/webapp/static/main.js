let baseUrl = '/TuitionProject1';
let nav = document.getElementById("navBar");
let loggedUser = null;
checkLogin();
setNav();
        function setNav() {
            nav.innerHTML = 
                    `<a href="index.html"><strong>Tuition Reimbursement</strong></a>
                    `;
                    //if user is management, display option to make a decision
            if (loggedUser == null) {
                console.log("user is null");
               nav.innerHTML += `
                    <form>
                        <label for="user">Username: </label>
                        <input id="user" name="user" type="text" />
                        <label for="pass"> Password: </label>
                        <input id="pass" name="pass" type="password" />
                        <button type="button" id="loginBtn">Log In</button>
                    </form>
                `;
            }else if(loggedUser.typeOfUser == "Staff"){
                console.log("user is staff");
                 nav.innerHTML += `
                    <a href="myClaim.html"My Claim</a>
                    <a href="mailbox.html">Mailbox</a>
                    <a href="checkStatus.html">Check Status</a>
                    <a href = "createClaim.html">Create Claim</a>
                    <span>
                        <button type="button" id="loginBtn">Log Out</button>
                    </span>`
            }
            else{
                console.log("user is management");
                nav.innerHTML+=`<a href="index.html"><strong>Tuition Reimbursement</strong></a>
                    <a href="viewClaims.html">View Claim</a>
                    <a href="mailbox.html">Mailbox</a>
                    <a href="checkStatus.html">Check Status</a>
                    <a href="claimDecision.html">Make Decision</a>
                    <button type="button" id="loginBtn">Log Out</button>
                    </span>`;
            }

            let loginBtn = document.getElementById('loginBtn');
            if (loggedUser != null && loginBtn != null) loginBtn.onclick = logout;
            else (loginBtn)?loginBtn.onclick = login: "";
        }
        async function login() {
            // http://localhost:8080/users?user=sierra&pass=pass
            let url = baseUrl+'/user/login?';
            url += 'user=' + document.getElementById('user').value + '&';
            url += 'pass=' + document.getElementById('pass').value;
            console.log(url);
            let user = {
            		user: document.getElementById('user').value,
            		pass: document.getElementById('pass').value
            };
            console.log(user);
            //line 67 passes a null value
            let response = await fetch(url, {method: 'POST', body: JSON.stringify(user)});
            console.log(response);
            switch (response.status) {
                case 200: // successful
                    loggedUser = await response.json();
                    console.log(loggedUser);
                    setNav();
                    break;
                case 400: // incorrect password
                    alert('Incorrect password, try again.');
                    document.getElementById('pass').value = '';
                    break;
                case 404: // user not found
                    alert('That user does not exist. line 73 index.html');
                    document.getElementById('user').value = '';
                    document.getElementById('pass').value = '';
                    break;
                default: // other error
                    alert('Something went wrong.');
                    break;
            }
        }

async function logout() {
    let url = baseUrl + '/user/login';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200){
        alert('Something went wrong.');
    }
    loggedUser = null;
    setNav();
}

async function checkLogin() {
    let url = baseUrl+'/user';
    let response = await fetch(url);
    console.log(response);
    if (response.status === 200){
        loggedUser = await response.json();
        console.log(response);
    }
    setNav();
}