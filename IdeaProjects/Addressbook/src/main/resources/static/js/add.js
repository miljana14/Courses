$(document).ready(function() {
    $("#add_new_contact").submit(function(evt) {
        evt.preventDefault();

        // PREPARE FORM DATA
        let formData = {
            picture : $("#picture").val(),
            name :  $("#name").val(),
            address: $("#address").val()
        }

        $.ajax({
            url: 'http://localhost:8080/contacts/add',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            cache: false,
            success: function (response) {
                let contact = response.contacts[0];
                let contactString = "{ picture: " + contact.picture + ", name: " + contact.name +
                                            ", address: " + contact.address + " }"
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong>' + response.message + '</strong> Contact\'s Info = ' + contactString;
                                    '</div>'
                $("#response").append(successAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong>' + response.message + '</strong>' + ' ,Error: ' + message.error +
                                '</div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            }
        });
    });

    function resetUploadForm(){
        $("#picture").val("");
        $("#name").val("");
        $("#address").val("");
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/add.html"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/customers.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});