var ClientAjaxRequest = function() {
	
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
				$("#danger-alert-body").html(data);
				setTimeout(() => {
					$("#danger-alert").addClass("hide");
				}, 5000);
			},
			error: function(data, statusText, xhr) {
				$("#danger-alert").removeClass("hide");
				$("#danger-alert-body").empty();
				$("#danger-alert-body").html(data);
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


var ClientFormValidation = function () {
	
	return {
		//main function to initiate the module
		init: function () {
		},
		updateModal : function(id, name, active) {
			$("#update-id").val(id);
			$("#update-name").val(name);
			$("#update-active").val(active);
			$("#updateCreateModal").modal();
		},
		deleteModal: function (id, name) {			
			$('#delete-id').val(id);
			$('#delete-name').text(name);
			$('#deleteCreateModal').modal();
		},
	};

}();

jQuery(document).ready(function() {
	ClientFormValidation.init();
	$("#client_menu").addClass("active");

	$("#createClient").click(function() {
		let formData = $("#createClientForm");
		ClientAjaxRequest.POST(formData);
	});
	
	$("#updateClient").click(function() {
		let formData = $("#updateClientForm");
		ClientAjaxRequest.POST(formData);
	});
	
	$("#deleteClient").click(function() {
		let formData = $("#deleteClientForm");
		ClientAjaxRequest.DELETE(formData);
	});

});