$(document).ready(function(){
    let contactId = 0;

    $(document).on("click", "#div_contact_table table button.btn_delete", function() {
        let btn_id = (event.srcElement.id);
        contactId = btn_id.split("_")[2];

        $("div.modal-body")
            .text("Do you want delete a contact with id = " + contactId + " ?");
        $("#model-delete-btn").css({"display": "inline"});
    });

    $(document).on("click", "#model-delete-btn", function() {
        $.ajax({
            url: 'http://localhost:8080/contacts/' + contactId,
            type: 'DELETE',
            success: function(response) {
                $("div.modal-body")
                    .text("Delete successfully a contact with id = " + contactId + "!");

                $("#model-delete-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                // delete the customer row on html page
                let row_id = "tr_" + contactId;
                $("#" + row_id).remove();
                $("#div_contact_updating").css({"display": "none"});
            },
            error: function(error){
                console.log(error);
                $("#div_contact_updating").css({"display": "none"});
                alert("Error -> " + error);
            }
        });
    });
});