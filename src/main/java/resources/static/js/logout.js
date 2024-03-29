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

function saveTheGameCurrentPosition() {
    window.location.replace("/SnakeAndLadderGame/login");
}

function exitTheGameCurrentPosition() {
    $.ajax({
        url: '/logout',
        type: 'DELETE',
        success: function (response) {
            window.location.replace(response);
        }
    });
}

function replay() {
    window.location.replace("/SnakeAndLadderGame/home");
}

function exitTheGameAfterGettingTheWinner() {
    $.ajax({
        url: '/logout',
        type: 'DELETE',
        success: function (response) {
            window.location.replace(response);
        }
    });
}