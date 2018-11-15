/**
 * 
 */

window.onload = function(){
	
	document.getElementById("employee").addEventListener("click", () => {
		event.preventDefault;
		window.open("http://localhost:8090/Project1_ERS/html/user/userIndex.html", "_self");	
	});

	document.getElementById("manager").addEventListener("click", () => {
		event.preventDefault;
		console.log('click manager');
		window.open("http://localhost:8090/Project1_ERS/html/manager/managerIndex.html", "_self");
	});
}