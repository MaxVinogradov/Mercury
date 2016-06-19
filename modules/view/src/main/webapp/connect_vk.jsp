<%@ page import="nc.sumy.edu.webapp.integration.IntegrationImpl" %>
<%@ page import="nc.sumy.edu.webcontainer.common.integration.SocialNetworks" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vk connection</title>
    <jsp:include page="jsp-parts/header-part.jsp"/>
    <link href="css/form.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="jsp-parts/nav_bar.jsp"/>
    <div class="main">
        <section>
            <div class="container">

                <div id="loginbox" class="mainbox col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">

                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <img class="img-responsive" src="images/logo-large.svg" alt="logo">
                        </div>
                    </div>

                    <div class="row panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title text-center">Connect your social networks</div>
                        </div>
                        <div class="row panel-body text-center">
                            <h2>Create connection with Vk</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquam aliquid animi at
                                consequatur dicta doloremque eaque eos eum expedita illum molestias nam obcaecati,
                                placeat quos recusandae sapiente tempora vero voluptas.2</p>
                            <div class="row">
                                <a target="_blank" href="
                                    <%= new IntegrationImpl().getAuthorisationUrlForNetwork(SocialNetworks.VK) %>
                                "><button class="btn btn-primary btn-lg">Log in Vk</button></a>
                            </div>
                            <div class="row" style="margin-top: 25px;">
                                <div class="form-group">
                                    <form action="VkCodeProcessor" name="form" id="form" class="form-horizontal" enctype="multipart/form-data" method="POST">
                                        <div class="input-group-lg" >
                                            <input type="text" placeholder="Enter access token" style="width: 80%;"/>
                                        </div>
                                        <button type="cancel" class="btn btn-danger" style="margin-right:15px;"><i class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;&nbsp;Cancel</button>
                                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-oks"></i>&nbsp;&nbsp;&nbsp;Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="particles"></div>
        </section>
    </div>
    <jsp:include page="jsp-parts/script-part.jsp"/>
    <script type="text/javascript" src="js/menu.js"></script>
</body>
</html>
