function snake() {
    //sn存放蛇，dz存放食物，fx存放方向，n存放新蛇头
    var sn = [42,41], dz = 43, fx = 1, n, ctx = document.getElementById("can").getContext("2d"), score=-1;
    //清空上一次的结果
    ctx.clearRect(0,0,400,400);
    //画出一个方块
    function draw(t, c) {
        ctx.fillStyle = c;
        ctx.fillRect((t%20)*20+1,~~(t/20)*20+1,18,18);
    }
    //根据按键决定方向：-1(left),-20(down),1(right),20(up)
    document.onkeydown = function(e) {
        fx = sn[1] - sn[0] === (n = [ -1, -20, 1, 20 ][(e || event).keyCode - 37] || fx) ? fx : n
    };
    //自执行函数
    !function() {
        //新蛇头位置等于原蛇头位置加上当前移动方向
        n = sn[0] + fx;
        //新蛇
        sn.unshift(n);
        //判断游戏是否结束
        if (sn.indexOf(n, 1) > 0 || n<0||n>399 || fx === 1 && n % 20 === 0 || fx === -1 && n % 20 === 19){
            alert("游戏结束，本次分数为："+score);
            $.post("/enjoy/updateRange",{"score":score},function () {
                searchRange();
            });
            return;
        }
        //画出新蛇头
        draw(n, "Lime");
        //如果吃到食物
        if (n === dz) {
            while (sn.indexOf(dz = ~~(Math.random() * 400)) >= 0){}
            //画出新食物
            draw(dz, "Yellow");
            score++;
            $("#score").html(score);
        } else{
            //如果没有吃到食物
            draw(sn.pop(), "Black");
        }
        //设置速度
        setTimeout(arguments.callee, 130);
    }();
}
function searchRange() {
    var t = "";
    $.post("/enjoy/getRange",function (data) {
        $.each(data,function(index, element) {
            t += "<h3>"+element['name']+"&nbsp;&nbsp;&nbsp;"+element['score']+"</h3>";
        });
        $("#range").html(t);
    },'json');
}
$(function () {
    searchRange();
});