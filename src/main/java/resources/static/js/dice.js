$('#dice').click(function () {
    $('#position').empty();
    $.emailId = localStorage.getItem("emailId");
    console.log($.emailId);
    $.get("/getCurrentPosition?emailId=" + $.emailId, function (data) {
        $('#position').append(data.position);
    });
});