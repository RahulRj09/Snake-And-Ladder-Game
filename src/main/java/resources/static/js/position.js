$('#dice').click(function () {
    $('#position').empty();
    $('#currentPositionEmailId').empty();
    $.emailId = localStorage.getItem("emailId");
    $.get("/getCurrentPosition?emailId=" + $.emailId, function (data) {
        if (data) {
            $('#currentPositionEmailId').append(data.details[0].emailId);
            $('#position').append(data.details[0].position);
        }
        else {
            $('#position').append(0);
        }
    });
});