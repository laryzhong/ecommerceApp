$(document).ready(function () {
        function down() {
            this.indexBodyheight = $("body").height();
            var e = $("#download_banner");
            e.css("display", "none");
        }

        $("#download_openweb").click(function () {
            down();
        })

        function leftright() {
            var slideShow = $("#slider"), //获取最外层框架的名称
                ul = slideShow.find("ul"),
                showNumber = slideShow.find(".focus-btn span"),//获取按钮
                oneWidth = slideShow.find("ul li").eq(0).width(); //获取每个图片的宽度
            var timer = null; //定时器返回值，主要用于关闭定时器
            var iNow = 0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0

            showNumber.on("click", function () {  //为每个按钮绑定一个点击事件
                $(this).addClass("active").siblings().removeClass("active"); //按钮点击时为这个按钮添加高亮状态，并且将其他按钮高亮状态去掉
                var index = $(this).index(); //获取哪个按钮被点击，也就是找到被点击按钮的索引值
                iNow = index;
                ul.animate({
                    "left": -oneWidth * iNow, //注意此处用到left属性，所以ul的样式里面需要设置position: relative; 让ul左移N个图片大小的宽度，N根据被点击的按钮索引值iNOWx确定
                })
            });

            timer = setInterval(function () { //打开定时器
                iNow++;    //让图片的索引值次序加1，这样就可以实现顺序轮播图片
                if (iNow > showNumber.length - 1) { //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
                    iNow = 0;
                }
                showNumber.eq(iNow).trigger("click"); //模拟触发数字按钮的click
            }, 2000); //2000为轮播的时间
        }

        leftright();

        function updown() {
            function timer(opj) {
                $(opj).find('ul').animate({
                    marginTop: "-140px"
                }, 6000, function () {
                    $(this).css({
                        marginTop: "32px"
                    }).find("li:first").appendTo(this);
                })
            }

            for (var i = 0; i < 1000; i++) {
                timer(".notice_active");
            }
            var num = $(".notice_active").find('li').length;
            if (num > 1) {
                var time = setInterval(timer(".notice_active"), 3500);
                $('.gg_more a').mousemove(function () {
                    clearInterval(time);
                }).mouseout(function () {
                    time = setInterval(timer(".notice_active"), 3500);
                });
            }
        }

        updown();

        function overtime() {
            var date = new Date();
            var now = date.getTime();
            var str = "2018/12/12 00:00:00";
            var endDate = new Date(str);
            var end = endDate.getTime();
            var leftTime = end - now;
            var d, h, m, s;
            if (leftTime >= 0) {
                h = Math.floor(leftTime / 1000 / 60 / 60 % 24);
                m = Math.floor(leftTime / 1000 / 60 % 60);
                s = Math.floor(leftTime / 1000 % 60);
            }
            document.getElementById("t_h").innerHTML = h;
            document.getElementById("t_m").innerHTML = m;
            document.getElementById("t_s").innerHTML = s;
            setTimeout(overtime, 1000);
        }

        overtime();
    }
)