var ExpenseAjaxRequest = function() {
	
	var postReqHandler = function (form) {
		var reqURL = $(form).attr("action");
		
		$.ajax({
			type: "POST",
			url: reqURL,
			data: $(form).serialize(),
			beforeSend: function(request) {
			},
			success: function(data, statusText, xhr) {
				$("#success-alert").removeClass("hide");
				$("#success-alert-body").empty();
				$("#success-alert-body").html(data);
				setTimeout(() => {
					$("#success-alert").addClass("hide");
				}, 1000);
				location.reload(true);
			},
			failure: function(data, statusText, xhr) {
				$("#danger-alert").removeClass("hide");
				$("#danger-alert-body").empty();
				$("#danger-alert-body").html(data['responseText']);
				setTimeout(() => {
					$("#danger-alert").addClass("hide");
				}, 5000);
			},
			error: function(data, statusText, xhr) {
				$("#danger-alert").removeClass("hide");
				$("#danger-alert-body").empty();
				$("#danger-alert-body").html(data['responseText']);
				setTimeout(() => {
					$("#danger-alert").addClass("hide");
				}, 5000);
			}
		});        	
	}

	var deleteReqHandler = function (form) { 
		var reqURL = $(form).attr("action") + "/" + $('#delete-id').val();
		
		$.ajax({
			type: "DELETE",
			url: reqURL ,
			success: function(data, statusText, xhr) {
				$("#success-alert").removeClass("hide");
				$("#success-alert-body").empty();
				$("#success-alert-body").html(data);
				setTimeout(() => {
					$("#success-alert").addClass("hide");
				}, 1000);
				location.reload(true);				
			},
			failure: function(data, statusText, xhr) {
				$("#danger-alert").removeClass("hide");
				$("#danger-alert-body").empty();
				$("#danger-alert-body").html(data['responseText']);
				setTimeout(() => {
					$("#danger-alert").addClass("hide");
				}, 5000);
			},
			error: function(data, statusText, xhr) {
				$("#danger-alert").removeClass("hide");
				$("#danger-alert-body").empty();
				$("#danger-alert-body").html(data['responseText']);
				setTimeout(() => {
					$("#danger-alert").addClass("hide");
				}, 5000);
			}
		});
	}

	return {
		POST: function (form) {
			postReqHandler(form);
		},
		DELETE: function (form) {
			deleteReqHandler(form);
		}
	};

}();


var ExpenseFormValidation = function () {
	
	return {
		//main function to initiate the module
		init: function () {
		},
		updateModal : function(id, title, description, currency, amount, timesStampOfExpense, clientId) {
			$("#update-id").val(id);
			$("#update-title").val(title);
			$("#update-description").val(description);
			$("#update-currency").val(currency);
			$("#update-amount").val(amount);
			$("#update-timesStampOfExpense").val(timesStampOfExpense);
			//console.log(timesStampOfExpense);
			let now = new Date(timesStampOfExpense);
			let day = ("0" + now.getDate()).slice(-2);
		    let month = ("0" + (now.getMonth() + 1)).slice(-2);
		    today = now.getFullYear() + "-" + (month) + "-" + (day);
		    $('#update-timesStampOfExpense').val(today);
			$("#update-clientId").val(clientId);
			$("#expenseUpdateModal").modal();
		},
		deleteModal: function (id, name) {			
			$('#delete-id').val(id);
			$('#delete-title').text(name);
			$('#expenseDeleteModal').modal();
		},
	};

}();

jQuery(document).ready(function() {
	ExpenseFormValidation.init();
	$("#expense_menu").addClass("active");

	$("#createExpense").click(function() {
		let formData = $("#createExpenseForm");
		ExpenseAjaxRequest.POST(formData);
	});
	
	$("#updateExpense").click(function() {
		let formData = $("#updateExpenseForm");
		ExpenseAjaxRequest.POST(formData);
	});
	
	$("#deleteExpense").click(function() {
		let formData = $("#deleteExpenseForm");
		ExpenseAjaxRequest.DELETE(formData);
	});

});