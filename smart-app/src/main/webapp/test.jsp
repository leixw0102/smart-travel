<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>演示：使用jquery.qrcode生成二维码</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css" />
    <style type="text/css">
        .demo{width:400px; margin:40px auto 0 auto; min-height:250px;}
        .demo p{line-height:30px}
        #code{margin-top:10px}
    </style>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript" src="jquery.qrcode.min.js"></script>
    <script type="text/javascript">
        $(function(){
            var str = "http://www.helloweba.com";
            $('#code').qrcode(str);

            $("#sub_btn").click(function(){
                $("#code").empty();
                var str = toUtf8($("#mytxt").val());

                $("#code").qrcode({
                    render: "table",
                    width: 200,
                    height:200,
                    text: str
                });
            });
        })
        function toUtf8(str) {
            var out, i, len, c;
            out = "";
            len = str.length;
            for(i = 0; i < len; i++) {
                c = str.charCodeAt(i);
                if ((c >= 0x0001) && (c <= 0x007F)) {
                    out += str.charAt(i);
                } else if (c > 0x07FF) {
                    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                    out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
                } else {
                    out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
                }
            }
            return out;
        }
    </script>
</head>

<body>
<div id="header">
    <div id="logo"><h1><a href="http://www.helloweba.com" title="返回helloweba首页">helloweba</a></h1></div>
</div>

<div id="main">
    <h2 class="top_title"><a href="http://www.helloweba.com/view-blog-226.html">使用jquery.qrcode生成二维码</a></h2>
    <div class="demo">
        <p>请输入内容然后提交生成二维码：</p>
        <p><input type="text" class="input" id="mytxt" value=""> <input type="button" id="sub_btn" value="提交"></p>
        <div id="code"></div>
    </div>

    <br/><div class="ad_76090"><script src="/js/ad_js/bd_76090.js" type="text/javascript"></script></div><br/>
</div>


<div id="footer">
    <p>Powered by helloweba.com  允许转载、修改和使用本站的DEMO，但请注明出处：<a href="http://www.helloweba.com">www.helloweba.com</a></p>
</div>
<p id="stat"><script type="text/javascript" src="/js/tongji.js"></script></p>
</body>
</html>