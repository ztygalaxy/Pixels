<html>
<script src="js/jquery.min.js"></script>
<script>
    function myfunction() {
        $.ajax({
            url: "user/find",
            contentType: "application/json;charset=UTF-8",
            data: '{"name":"hehe","pwd":123456}',
            dataType: "json",
            type: "post",
            success: function (data) {
                alert(data);
                alert(data.name);
            }
        });
    }
</script>
<body>
<h2>Hello World!</h2>
<a href="user/find?name=aa&pwd=20">查询所有</a>
<button onclick="myfunction()">点我</button>
</body>
</html>
