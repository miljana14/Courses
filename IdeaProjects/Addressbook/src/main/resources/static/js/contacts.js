$(document).ready(function(){
    (function(){
        $.ajax({
            url: 'http://localhost:8080/contacts',
            type: 'GET',
            success: function(response){
              $.each(response.contacts, (i, contact) => {


                let deleteButton = '<button ' +
                                        'id=' +
                                        '\"' + 'btn_delete_' + contact.id + '\"'+
                                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                                        '>&times</button>';

                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + contact.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' +
                                            contact.id +
                                            '</button>';

                let tr_id = 'tr_' + contact.id;
                let contactRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_picture\">' + contact.picture + '</td>' +
                          '<td class=\"td_name\">' + contact.name + '</td>' +
                          '<td class=\"td_address\">' + contact.address + '</td>' +
                          '<td>' + deleteButton + '</td>' +
                          '</tr>';
                $('#contactTable tbody').append(contactRow);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    })();

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/contacts.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});

