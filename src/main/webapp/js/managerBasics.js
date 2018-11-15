/**
 * 
 */

window.onload = () => {
	// document.getElementById("userinfo").addEventListener("click", getUserInfo);
	document.getElementById("logout").addEventListener('click', logOut);
	//document.getElementById("home").onclick = addUnapprovedRequest;
	addUnapprovedRequest();
}

function logOut() {
	event.preventDefault;
	window.sessionStorage.clear();
}

function getUserInfo() {
	event.preventDefault;
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let obj = JSON.parse(xhr.responseText);
			let body = document.getElementById("infodiv");
			body.innerText = "";

			let table = document.createElement("table");
			table.className = "table-condensed col-sm-6";

			// let row = document.createElement("tr");
			// let dataDescription = document.createElement("td");
			// let data = document.createElement("td");

			for (var key in obj) {
				if (key==="password" || key==='isEmployed')
					continue;


				let row = document.createElement("tr");
				let dataDescription = document.createElement("td");
				let data = document.createElement("td");
				
				dataDescription.innerText=key.charAt(0).toUpperCase() + key.slice(1);
				data.innerText=obj[key];
				if (key==="title")
					data.innerText=obj[key].charAt(0).toUpperCase() + obj[key].slice(1);

				row.appendChild(dataDescription);
				row.appendChild(data);
				
				table.appendChild(row);
			}
			body.appendChild(table);
		}
	};

	xhr.open("GET", "../info", true);
	xhr.send();
}

function addUnapprovedRequest() {
	event.preventDefault;
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange=()=>{
		if (xhr.readyState==4 && xhr.status==200){
			let body=document.getElementById("infodiv");

			let newForm = document.createElement("form");
			newForm.action="../update";
			newForm.method="POST";
			newForm.style="font-family:Courier New";
			newForm.append("Request ID: ");
			let requestID=document.createElement("input");
			requestID.type="number";
			requestID.name="id";
			requestID.placeholder="Enter Request ID";

			let approve = document.createElement("input");
			approve.type="submit";
			approve.name="status";
			approve.value="Approve";
			let deny = document.createElement("input");
			deny.type="submit";
			deny.name="status";
			deny.value="Deny";
			newForm.append(requestID, document.createElement("br"),approve, deny);

			body.append(newForm);
			


			let table = document.createElement("table");
			table.className="table table-bordered table-striped";
			let tableheader = document.createElement("thead");
			let rowOne = document.createElement("tr");
			let colNum = document.createElement("th");
			colNum.scope="col";
			colNum.innerHTML="Request ID";
			let colOne = document.createElement("th");
			colOne.scope="col";
			colOne.innerHTML="User ID";
			let colTwo = document.createElement("th");
			colTwo.scope="col";
			colTwo.innerHTML="Reason";
			let colThree = document.createElement("th");
			colThree.scope="col";
			colThree.innerHTML="Amount";
			rowOne.append(colNum, colOne, colTwo, colThree);
			tableheader.append(rowOne);
			table.append(tableheader);

			obj = JSON.parse(xhr.responseText);
			obj.forEach(element => {
				let row = document.createElement("tr");
				let hd = document.createElement("th");
				hd.scope="row";
				hd.innerHTML=element.id;

				let id = document.createElement("td");
				id.innerHTML=element.employeeID;
				let reason = document.createElement("td");
				reason.innerHTML=element.reason;
				let amount=document.createElement("td");
				amount.innerHTML=element.requestAmount;

				row.append(hd, id, reason, amount);
				table.append(row);
			});
			body.append(table);
		}
	};


	xhr.open("get", "http://localhost:8090/Project1_ERS/manager");
	xhr.send();
}

function getAllUserInfo() {
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange= ()=>{
		if(xhr.status == 200 && xhr.readyState == 4){
			let obj = JSON.parse(xhr.responseText);
			let body = document.getElementById("infodiv");
			body.innerText = "";

			let table = document.createElement("table");
			table.className="table table-bordered table-striped";
			let tableheader = document.createElement("thead");
			let rowOne = document.createElement("tr");
			let colNum = document.createElement("th");
			colNum.scope="col";
			colNum.innerHTML="Employee ID";
			let colOne = document.createElement("th");
			colOne.scope="col";
			colOne.innerHTML="Name";
			let colTwo = document.createElement("th");
			colTwo.scope="col";
			colTwo.innerHTML="Username";
			let colThree = document.createElement("th");
			colThree.scope="col";
			colThree.innerHTML="Email";
			rowOne.append(colNum, colOne, colTwo, colThree);
			tableheader.append(rowOne);
			table.append(tableheader);

			console.log(obj);
			obj.forEach(element => {
				let row = document.createElement("tr");
				let hd = document.createElement("th");
				hd.scope="row";
				hd.innerHTML=element.id;

				let id = document.createElement("td");
				id.innerHTML=element.firstname+ " " +element.lastname;
				let reason = document.createElement("td");
				reason.innerHTML=element.address;
				let amount=document.createElement("td");
				amount.innerHTML=element.email;

				row.append(hd, id, reason, amount);
				table.append(row);
			});

			body.append(table);
		}
	}

	xhr.open("GET", "http://localhost:8090/Project1_ERS/allusers");
	xhr.send();
}