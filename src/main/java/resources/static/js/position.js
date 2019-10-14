$('#dice').click(function () {
    $('#position').empty();
    $('#currentPositionEmailId').empty();
    $.emailId = localStorage.getItem("emailId");
    $.get("/getCurrentPosition?emailId=" + $.emailId, function (data) {
        if (data) {
            let position = data.details[0].position;
            let emailId = data.details[0].emailId;
            if (emailId === $.emailId) {
                $('#' + position).prepend('<img id="theImg" src="../static/images/snakes-and-ladders-board-game.jpg" />')
            }
            $('#currentPositionEmailId').append(emailId);
            $('#position').append(position);
            if (position === 100) {
                $.get("/getWinner", function (data) {
                    console.log(data)
                    $('#winner').append("Winner " + data);
                });
            }
        } else {
            $('#position').append(0);
        }
    });
});