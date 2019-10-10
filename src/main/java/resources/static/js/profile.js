$(window).on("load", GetAllProperties);

function GetAllProperties() {
    $.emailId = localStorage.getItem("emailId");
    $.get("/getProfile?emailId=" + $.emailId, function (data, status) {
        if (data) {
            $('h2').append(data.Profile[0].name);
            $('#name').append(data.Profile[0].name);
            $('#email').append(data.Profile[0].email);
            $('#win').append(data.Profile[0].win);
            $('#loss').append(data.Profile[0].loss);
            $('#total').append(data.Profile[0].total);
        } else {
            $('h2').append("User not found")
        }
    });
}
