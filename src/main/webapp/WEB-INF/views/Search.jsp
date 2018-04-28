<%--
  Created by IntelliJ IDEA.
  User: KitaharaHaruki
  Date: 2018/3/22
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>计价页面</title>
    <meta charset="UTF-8" />
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
</head>
<body>
    <div class="container">
        <div class="cos-xs-6 col-md-4">
        <form action="/DoSearch">

            <div class="form-group">
            <label>航线类型</label>
                <select class="bs-select form-control" data-live-search="true" name="linetype" id="site_id">
                    <option value="temp">请选择</option>
                    <option value="in">国内</option>
                    <option value="out">国际</option>
                </select>

            <label>起点</label>
                <select class="bs-select form-control"  data-live-search="true" name="start" id="">
                    <option value="temp">请选择</option>
                    <option value="C">中国其他省份</option>
                    <option value="CLW">中国乌鲁木齐兰州</option>
                    <option value="J">日本</option>
                    <option value="AM">美洲</option>
                    <option value="AC">澳新</option>
                    <option value="AME">米国</option>
                    <option value="R">俄罗斯</option>
                    <option value="D">迪拜</option>
                    <option value="S">新加坡</option>
                    <option value="W">中西亚</option>
                    <option value="N">内罗毕</option>
                </select>
                <label>终点</label>
                <select class="bs-select form-control" data-live-search="true" name="end">
                    <option value="temp">请选择</option>
                    <option value="C">中国其他省份</option>
                    <option value="CLW">中国乌鲁木齐兰州</option>
                    <option value="J">日本</option>
                    <option value="AM">美洲</option>
                    <option value="AME">米国</option>
                    <option value="AC">澳新</option>
                    <option value="R">俄罗斯</option>
                    <option value="D">迪拜</option>
                    <option value="S">新加坡</option>
                    <option value="W">中西亚</option>
                    <option value="N">内罗毕</option>
                </select>
            </div>
            <%--<div class="form-group">--%>
       <%----%>
            <%--</div>--%>

            <div class="form-group">
            <label>机舱</label>
            <select class="bs-select form-control" data-live-search="true" name="jicang">
            <option value="temp">请选择</option>
            <option value="T">头等舱</option>
            <option value="G">公务舱</option>
            <option value="M">明珠经济舱</option>
            <option value="J">经济舱</option>
            <option value="W">不占座婴儿</option>
            </select></div>


            <div class="form-group">
            <label>旅客</label>
            <select class="bs-select form-control" data-live-search="true" name="cusclass" >
            <option value="temp">请选择</option>
            <option value="A">成人</option>
                <option value="C">儿童</option>
            </select>
            </div>

            <div class="form-group">
            <label>优惠类型</label>
            <select class="bs-select form-control" data-live-search="true" name="vip">
            <option value="temp">请选择</option>
                <option value="W">无优惠</option>
            <option value="J">南航明珠金卡会员/天合联盟超级精英</option>
                <option value="S">南航明珠银卡会员/天合联盟精英</option>
                <option value="L">留学生/劳务/海员</option>

            </select>
            </div>

            <div class="form-group">
                <label>输入行李规格和重量</label>
                <input class="form-control" type="text" placeholder="格式为重量/长/宽/高 空格间隔行李" name="luggage">
            </div>
            <div class="form-group">
                <label>票价</label>
                <input   class="form-control" type="text" name="ticketvalue">
            </div>
            <button class="btn btn-primary">确认</button>

        </form>
        </div>
    </div>
</body>
</html>
