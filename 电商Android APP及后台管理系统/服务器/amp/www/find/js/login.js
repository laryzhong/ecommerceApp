$(document).ready(function () {
    var username = $("#username");
    var password = $("#password");
    var notnull = 0;

    username.bind('input', function () {
        if (username.val().trim() != "") {
            username.siblings("i").css("display", "block");
        }
        if (username.val() != "" && password.val() != "") {
            $("#loginBtn").addClass("btn-active");
        } else {
            $("#loginBtn").removeClass("btn-active");
        }
    });
    password.bind('input', function () {
        if (username.val() != "" && password.val() != "") {
            $("#loginBtn").addClass("btn-active");
        } else {
            $("#loginBtn").removeClass("btn-active");
        }
    });
    username.siblings("i").click(function () {
        username.val("");
        username.siblings("i").css("display", "none");
    })
    $("#loginBtn").click(function () {
        if (username.val() == "lqs" && password.val() == "lqs") {
            window.location.href = "../MJD/shangping.html";
        }
    })
})