let hideEmployeeSavedAlertTimer = undefined;

document.addEventListener("DOMContentLoaded", () => {
	document.getElementById("saveButton").addEventListener("click", saveActionClick);
	const employeeFirstNameEditElement = getEmployeeFirstNameEditElement();
	employeeFirstNameEditElement.focus();
	employeeFirstNameEditElement.select();
});

function completeSaveAction(callbackResponse) {
	if ((callbackResponse.data.redirectUrl != null) && (callbackResponse.data.redirectUrl !== "")) {
		window.location.replace(callbackResponse.data.redirectUrl);
		return;
	}

	if (callbackResponse.data == null) {
		return;
	}
	
	displayEmployeeSavedAlertModal();

	const employeeEmployeeIdElement = getEmployeeEmployeeIdElement();
	const employeeEmployeeIdRowElement = employeeEmployeeIdElement.closest("tr");
	if (employeeEmployeeIdRowElement.classList.contains("hidden")) {
		setEmployeeId(callbackResponse.data.id);
		employeeEmployeeIdElement.value = callbackResponse.data.employeeId;
		employeeEmployeeIdRowElement.classList.remove("hidden");
	}
}

function validateSave() {
	const firstNameEditElement = getEmployeeFirstNameEditElement();
	if (firstNameEditElement.value.trim() === "") {
		displayError("Please provide a valid employee first name.");
		firstNameEditElement.focus();
		firstNameEditElement.select();
		return false;
	}

	const lastNameEditElement = getEmployeeLastNameEditElement();
	if (lastNameEditElement.value.trim() === "") {
		displayError("Please provide a valid employee last name.");
		lastNameEditElement.focus();
		lastNameEditElement.select();
		return false;
	}

	const passwordEditElement = getEmployeePasswordEditElement();
	if (passwordEditElement.value.trim() === "") {
		displayError("Please provide a valid employee password.");
		passwordEditElement.focus();
		passwordEditElement.select();
		return false;
	}

	if (passwordEditElement.value !== getEmployeeConfirmPassword()) {
		displayError("Passwords do not match.");
		passwordEditElement.focus()
		passwordEditElement.select();
		return false;
	}

	const employeeTypeSelectElement = getEmployeeTypeSelectElement();
	if (!employeeTypeSelectElement.closest("tr").classList.contains("hidden")) {
		if (employeeTypeSelectElement.value <= 0) {
			displayError("Please provide a valid employee Type.");
			employeeTypeSelectElement.focus();
			return false;
		}
	}
	return true;
}

function saveActionClick(event) {
	if (!validateSave()) {
		return;
	}

	const saveActionElement = event.target;
	saveActionElement.disabled = true;

	const employeeId = getEmployeeId();
	const employeeIdIsDefined = (employeeId.trim() !== "");
	const saveActionUrl = ("/api/employee/" + (employeeIdIsDefined ? employeeId : ""));
	const saveEmployeeRequest = {
		id: employeeId,
		managerId: getEmployeeManagerId(),
		lastName: getEmployeeLastNameEditElement().value,
		password: getEmployeePasswordEditElement().value,
		firstName: getEmployeeFirstNameEditElement().value,
		classification: getEmployeeTypeSelectElement().value
	};

	if (employeeIdIsDefined) {
		ajaxPatch(saveActionUrl, saveEmployeeRequest, (callbackResponse) => {
			saveActionElement.disabled = false;

			if (isSuccessResponse(callbackResponse)) {
				completeSaveAction(callbackResponse);
			}
		});
	} else {
		ajaxPost(saveActionUrl, saveEmployeeRequest, (callbackResponse) => {
			saveActionElement.disabled = false;

			if (isSuccessResponse(callbackResponse)) {
				completeSaveAction(callbackResponse);
			}
		});
	}
}



function hideEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer){
		clearTimeout(hideEmployeeSavedAlertTimer);
	}
	getSavedAlertModalElement().style.display = "none";
}

function displayEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer){
		clearTimeout(hideEmployeeSavedAlertTimer);
	}
	const savedAlertModalElement = getSavedAlertModalElement();
	savedAlertModalElement.style.display = "none";
	savedAlertModalElement.style.display = "block";
	hideEmployeeSavedAlertTimer = setTimeout(hideEmployeeSavedAlertModal, 1200);
}

//Getters and setters
function getEmployeeId() {
	return document.getElementById("employeeId").value;
}

function getEmployeeManagerId() {
	return document.getElementById("employeeManagerId").value;
}

function getEmployeeEmployeeId() {
	return getEmployeeEmployeeIdElement().value;
}

function getEmployeeTypeSelectElement() {
	return document.getElementById("employeeType");
}

function getEmployeeFirstNameEditElement() {
	return document.getElementById("employeeFirstName");
}

function getEmployeeEmployeeIdElement() {
	return document.getElementById("employeeEmployeeId");
}

function getEmployeeLastNameEditElement() {
	return document.getElementById("employeeLastName");
}

function getSavedAlertModalElement() {
	return document.getElementById("employeeSavedAlertModal");
}

function getEmployeeConfirmPassword() {
	return document.getElementById("employeeConfirmPassword").value;
}

function setEmployeeId(employeeId) {
	document.getElementById("employeeId").value = employeeId;
}

function getEmployeePasswordEditElement() {
	return document.getElementById("employeePassword");
}