<link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">

<nav class="navbar sidebar" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/images/logo-large.svg" alt="logo"></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/create_post.jsp">Create post<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon glyphicon-send"></span></a></li>
                <li><a href="${pageContext.request.contextPath}/app_posts.jsp">Your posts<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-paperclip"></span></a></li>
                <li class="disabled"><a href="#">Aggregation wall<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-folder-open"></span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Settings <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
                    <ul class="dropdown-menu forAnimate" role="menu">
                        <li><a href="${pageContext.request.contextPath}/connect.jsp">Add accounts</a></li>
                        <li><a href="#">Remove accounts</a></li>
                        <li><a href="#">Change your profile</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}" target="_blank">About<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
                <li><a href="#">Log out<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div>
    </div>
</nav>