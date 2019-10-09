$('#dice').click(function () {
    $('#position').empty();
    $.emailId = localStorage.getItem("emailId");
    console.log($.emailId);
    $.get("/getNumberOnDice?emailId=" + $.emailId, function (data) {
        $('#position').append(data.position);
    });
});