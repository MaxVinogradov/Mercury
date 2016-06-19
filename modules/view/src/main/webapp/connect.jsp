<%@ page import="nc.sumy.edu.webapp.integration.IntegrationImpl" %>
<%@ page import="nc.sumy.edu.webcontainer.common.integration.SocialNetworks" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="jsp-parts/header-part.jsp"/>
    <title>Connect</title>
    <link href="lib/waves.js/waves.min.css" rel="stylesheet">
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
                        <div class="row panel-body">
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <a href="connect_vk.jsp">
                                    <img src="images/vk.svg" alt="vk.com" class="waves-image img-responsive">
                                </a>
                            </div>
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <a href="
                                    <%= new IntegrationImpl().getAuthorisationUrlForNetwork(SocialNetworks.FACEBOOK) %>
                                ">
                                    <img src="images/facebook.svg" alt="facebook.com" class="waves-image img-responsive">
                                </a>
                            </div>
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <a href="
                                    <%= new IntegrationImpl().getAuthorisationUrlForNetwork(SocialNetworks.TWITTER) %>
                                ">
                                    <img src="images/twitter.svg" alt="twitter.com" class="waves-image img-responsive ">
                                </a>
                            </div>
                        </div>
                        <% Object successFlag = request.getParameter("success");
                            if(successFlag != null) { if(!successFlag.equals("false")) { %>

                            <h4 class="text-success text-center"><strong>Success!</strong> Connection to network was successful!</h4>

                        <% } else { %>

                            <h4 class="text-danger text-center"><strong>Ooops!</strong> Connection to network failed!</h4>

                        <% } }%>
                    </div>
                </div>
            </div>

            <div id="particles"></div>
        </section>
    </div>
    <jsp:include page="jsp-parts/script-part.jsp"/>
    <script type="text/javascript" src="js/menu.js"></script>
    <script type="text/javascript" src="lib/waves.js/waves.min.js"></script>
</body>
</html>
