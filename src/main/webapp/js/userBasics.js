/**
 * 
 */

window.onload = () => {
	// document.getElementById("userinfo").addEventListener("click", getUserInfo);
	//submitRequest();
	getUserRequest();
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
				if (key === "password" || key === 'isEmployed')
					continue;


				let row = document.createElement("tr");
				let dataDescription = document.createElement("td");
				let data = document.createElement("td");


				dataDescription.innerText = key.charAt(0).toUpperCase() + key.slice(1);
				if (key.toLowerCase() === "title" || key.toLowerCase() === "firstname" || key.toLowerCase() === "lastname" || key.toLowerCase() === "address")
					data.innerText = obj[key].charAt(0).toUpperCase() + obj[key].slice(1);
				else
					data.innerText = obj[key];

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

function submitRequest(){
	document.getElementById("infodiv").innerHTML="";


	let newform = document.createElement("form");
	newform.action = "../request";
	newform.method = "POST";
	newform.style = "font-family:Courier New";
	
	newform.append("Reason: ");
	let reason = document.createElement("input");
	reason.type="text";
	reason.name="reason";
	newform.append(reason);
	newform.append(document.createElement("br"));

	newform.append("Amount: ");
	let amount = document.createElement("input");
	amount.type="number";
	amount.name="amount";
	newform.append(amount);
	newform.append(document.createElement("br"));

	let newinput = document.createElement("input");
	newinput.type="submit";
	newinput.value="Submit";
	newform.append(newinput);
	
	document.getElementById("infodiv").append(newform);
	//let xhr = new XMLHttpRequest();
}

function getUserRequest(){
	event.preventDefault;
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange=()=>{
		if (xhr.status==200 && xhr.readyState==4){
			let body=document.getElementById("infodiv");
			let table = document.createElement("table");
			table.className="table table-bordered table-striped";
			let tableheader = document.createElement("thead");
			let rowOne = document.createElement("tr");
			let colNum = document.createElement("th");
			colNum.scope="col";
			colNum.innerHTML="Request ID";
			let colOne = document.createElement("th");
			colOne.scope="col";
			colOne.innerHTML="Reason";
			let colTwo = document.createElement("th");
			colTwo.scope="col";
			colTwo.innerHTML="Amount";
			let colThree = document.createElement("th");
			colThree.scope="col";
			colThree.innerHTML="Status";
			rowOne.append(colNum, colOne, colTwo, colThree);
			tableheader.append(rowOne);
			table.append(tableheader);

			let obj = JSON.parse(xhr.responseText);
			console.log(obj);
			obj.forEach(element => {
				let row = document.createElement("tr");
				let id = document.createElement("th");
				id.scope="row";
				id.innerHTML=element.id;

				let reason = document.createElement("td");
				reason.innerHTML=element.reason;
				let amount = document.createElement("td");
				amount.innerHTML=element.requestAmount;
				let status=document.createElement("td");
				status.innerHTML=element.approvalStatus;

				row.append(id, reason, amount, status);
				table.append(row);
			});

			body.append(table);
		}
	};

	xhr.open("GET", "http://localhost:8090/Project1_ERS/user");
	xhr.send();
}
