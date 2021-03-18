let mailbox = null;
let mailboxSection = document.getElementById("mailboxSection");
mailboxSetup();

async function mailboxSetup(){
	mailboxSection.innerHTML=`<button type="button" id="inbox">Inbox</button>
								<button type="button" id="newMessage">New Message</button>`;
	inbox.onclick = getMail;
	newMessage.onclick = createMessage;
	async function getMail(){
		url = baseUrl + "/mailbox/"+loggedUser.id;
		response = await fetch(url, {method:"GET"})
		console.log(response.status);
		if(response.status == 201){
			mailbox = await response.json();
			populateMailbox();
		}
	}
	async function createMessage(){
		mailboxSection.innerHTML = `<form id="add-mailbox">
        <label for="reciever">To:</label>
        <input type="text" id="reciever" placeholder="Employee ID" required />
        
        <label for="message">Message:</label>
        <input type="text" id="message" placeholder="Message" required />
        <button type="button" onclick="addMailbox" id="submit-add-mailbox" >Submit</button>
		</form>`;
		 let submitAddBtn = document.getElementById('submit-add-mailbox');
		submitAddBtn.onclick = addMailbox;
	}
	function populateMailbox(){
		console.log("line 23 mailbox js");
	console.log(mailbox);
	mailboxSection.innerHTML = `<button type="button" id="newMessage">New Message</button>`;
	if(mailbox.length > 0){
		console.log("line 27 mailbox js");
		let table = document.createElement('table');
		table.id = 'mailboxTable';
		table.innerHTML = `<tr>
		<th>From</th>
		<th>To</th>
		<th>Message</th>
		</tr>`;
			for(let mail of mailbox){
				if(mail.personId == loggedUser.id){
					let tr = document.createElement('tr');
					tr.innerHTML = `<td>${mailbox.senderId}</td>
							<td>${mailbox.personId}</td>
							<td>${mailbox.message}</td>
							`;
							let td = document.createElement('td');
							tr.appendChild(td);
							table.appendChild(tr);
			}
		}
		mailboxSection.appendChild(table);
	}else {
        mailboxSection.innerHTML = 'No mail are available.';
	}
	}
	
	async function addMailbox(){
		mailbox = {mailboxId:0,message:document.getElementById("message").value, personId:document.getElementById("reciever").value,senderId:loggedUser.id,readStatus:false};
		url = baseUrl + "/mailbox";
		response = await fetch(url,{method:"POST",body: JSON.stringify(mailbox)});
		console.log(response.status);
		if(response.status == 201){
			alert("Message sent.");
		}else{
			alert("Message failed to send");
		}
	}
}