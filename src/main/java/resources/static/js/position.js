$('#dice').click(function () {
    $('#position').empty();
    $('#currentPositionEmailId').empty();
    $.emailId = localStorage.getItem("emailId");
    $.get("/getCurrentPosition?emailId=" + $.emailId, function (data) {
        if (data) {
            $('#currentPositionEmailId').append(data.details[0].emailId);
            $('#position').append(data.details[0].position);
            if (data.details[0].position === 100) {
                $.get("/getWinner", function (data) {
                    console.log(data)
                    $('#winner').append("Winner "+ data);
                });
            }
        } else {
            $('#position').append(0);
        }
    });
});