$('#logout').click(function () {
    $.emailId = localStorage.getItem("emailId");
    $.get("/logout?emailId=" + $.emailId, function (data) {

    });
});
