$(document).ready(function () {

        $("#p_210740260").find("i").click(function () {
            var select = $("#p_210740260").find("div.goods");
            if (select.hasClass("selected") == true) {
                select.removeClass("selected");
                $("#s_8888").find(".head_wrap").removeClass("selected");
            }
            else {
                select.addClass("selected");
                $("#s_8888").find(".head_wrap").addClass("selected");
            }
        })


        $("#p_16518710877").find("i").click(function () {
            var select = $("#p_16518710877").find("div.goods");
            if (select.hasClass("selected") == true) {
                select.removeClass("selected");
                $("#s_138580").find(".head_wrap").removeClass("selected");
                $("#fixBarBot ").addClass("selected")
            }
            else {
                select.addClass("selected");
                $("#s_138580").find(".head_wrap").addClass("selected");
                $("#fixBarBot ").addClass("selected");
            }
        })

        var sum = $("#totalPrice");
        var sum2 = $("#totalBackMoney");
        $(".buyJs").click(function () {
            sum.text("¥180.90");
            sum2.text("总额¥180.90 立减¥0.00");
        })

    }
)