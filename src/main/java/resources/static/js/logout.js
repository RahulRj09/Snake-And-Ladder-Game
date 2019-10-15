const modal = document.getElementById("logoutModal");
$('#logout').click(function () {
    $.emailId = localStorage.getItem("emailId");
    $.get("/logout?emailId=" + $.emailId, function (data) {
        if (data === "false") {
            window.location.replace("/SnakeAndLadderGame/login.html");
        } else if (data === "true") {
            modal.style.display = "block";
        }

    });
});
$('#save').click(function () {
    window.location.replace("/SnakeAndLadderGame/login.html");
})