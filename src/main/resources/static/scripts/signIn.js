document.addEventListener("DOMContentLoaded", function(event) {
	const employeeIdEditElement = getEmployeeIdEditElement();
	employeeIdEditElement.focus();
    employeeIdEditElement.select();
    
});

//Build a method to validate input on form submission[1,2]
function validateForm() 
{

	//checking that the employee ID is numeric
	const employeeIdEditElement = getEmployeeIdEditElement();
    if (isNaN(Number(employeeIdEditElement.value)) || (Number(employeeIdEditElement.value) <= 0)) 
    {
		displayError("Please provide a valid employee ID.");

		employeeIdEditElement.focus();
		employeeIdEditElement.select();
		
		return false;
	}

	//checking that the employee ID is not blank
	const passwordEditElement = getPasswordEditElement();
    if ((passwordEditElement.value == null) || (passwordEditElement.value.trim() === "")) 
    {

		displayError("Please provide a valid password. It may not be blank.");

		passwordEditElement.focus();
		passwordEditElement.select();
		
		return false;
	}

	return true;
}
//Getters and setters
function getPasswordEditElement() {
	return document.getElementById("password");
}
function getEmployeeIdEditElement() {
	return document.getElementById("employeeId");
}
//End getters and setters