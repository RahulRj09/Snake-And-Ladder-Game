function login() {
    $("#my-form").submit(function (e) {
        let emailId = $("#email").val();
        let password = $("#password").val();
        localStorage.setItem("emailId", emailId);
        $.get("/login?emailId=" + emailId + "password=" + password, function (data) {
            if (data === true) {
                window.location.replace("/SnakeAndLadderGame/home");
            }
        });
    });
}