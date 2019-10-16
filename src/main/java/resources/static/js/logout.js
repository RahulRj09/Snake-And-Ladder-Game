const modalForLogout = document.getElementById("logoutModal");
$.emailId = localStorage.getItem("emailId");
$('#logout').click(function () {
    $.get("/logout?emailId=" + $.emailId, function (data) {
        if (data === "false") {
            window.location.replace("/SnakeAndLadderGame/login");
        } else if (data === "true") {
            modalForLogout.style.display = "block";
        }
    });
});
$('#save').click(function () {
    window.location.replace("/SnakeAndLadderGame/login");
})

$('#exit').click(function () {
    $.ajax({
        url: '/logout',
        type: 'DELETE',
        success: function (response) {
            window.location.replace(response);
        }
    });
})