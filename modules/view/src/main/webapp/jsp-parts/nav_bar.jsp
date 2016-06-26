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
                <li><a href="${pageContext.request.contextPath}/create_post.jsp">Create post<span class="pull-right hidden-xs showopacity glyphicon glyphicon glyphicon-send menu-icon-size"></span ></a></li>
                <li><a href="${pageContext.request.contextPath}/app_posts.jsp">Your posts<span class="pull-right hidden-xs showopacity glyphicon glyphicon-paperclip menu-icon-size"></span ></a></li>
                <li class="disabled"><a href="#">Aggregation wall<span class="pull-right hidden-xs showopacity glyphicon glyphicon-folder-open menu-icon-size"></span"></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Settings <span class="caret"></span><span class="pull-right hidden-xs showopacity glyphicon glyphicon-cog menu-icon-size"></span></a>
                    <ul class="dropdown-menu forAnimate" role="menu">
                        <li><a href="${pageContext.request.contextPath}/connect.jsp">Add accounts</a></li>
                        <li><a href="#" class="disabled">Remove accounts</a></li>
                        <li><a href="#" class="disabled">Change your profile</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}" target="_blank">About<span class="pull-right hidden-xs showopacity glyphicon glyphicon-home menu-icon-size"></span></a></li>
                    <li>
                        <form action="Controller?action=log_out" method="POST" style="margin-bottom: 0;">
                            <a href="#"><button type="submit" class="btn btn-default log-out-btn"><span class="text-primary">Log out</span><span class="text-primary pull-right hidden-xs showopacity glyphicon glyphicon-log-out menu-icon-size"></span></button></a>
                        </form>
                    </li>
            </ul>
        </div>
    </div>
</nav>