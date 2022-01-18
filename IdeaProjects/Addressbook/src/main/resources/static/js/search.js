$(document).ready(function () {

    $("#search-form").submit(function (event) {

        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {
let search ={
            name :  $("#fieldKeyword").val()}

    $("#buttonSearch").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://localhost:8080/contacts/" + name,
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#buttonSearch").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#buttonSearch").prop("disabled", false);

        }
    });

}