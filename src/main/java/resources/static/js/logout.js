const modalForLogout = document.getElementById("logoutModal");
$.emailId = localStorage.getItem("emailId");

function logout() {
    $.get("/logout?emailId=" + $.emailId, function (data) {
        if (data === "false") {
            window.location.replace("/SnakeAndLadderGame/login");
        } else if (data === "true") {
            modalForLogout.style.display = "block";
        }
    });
}

function saveGameCurrentPosition() {
    window.location.replace("/SnakeAndLadderGame/login");
}

$('#exit').click(function () {
    $.ajax({
        url: '/logout',
        type: 'DELETE',
        success: function (response) {
            window.location.replace(response);
        }
    });
})

$('#replay').click(function () {
    window.location.replace("/SnakeAndLadderGame/home");
})

$('#exitAfterGameOver').click(function () {
    $.ajax({
        url: '/logout',
        type: 'DELETE',
        success: function (response) {
            window.location.replace(response);
        }
    });
})