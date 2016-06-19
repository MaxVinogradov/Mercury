<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Log in</title>
    <jsp:include page="jsp-parts/header-part.jsp"/>
    <link href="css/form.css" rel="stylesheet">
    <link href="css/query.css" rel="stylesheet">
</head>

<body>
    <section>
        <div class="container">
            
            <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">

                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <img class="wawes img-responsive" src="images/logo-large.svg" alt="logo">
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title text-center">Log in</div>
                    </div>
                    <div class="panel-body">
                        <form name="form" id="form" class="form-horizontal" enctype="multipart/form-data" method="POST">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="user" type="text" class="form-control" name="user" value="" placeholder="User">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 controls">
                                    <button type="submit" class="btn btn-primary pull-right"><i class="glyphicon glyphicon-log-in"></i>&nbsp;&nbsp;&nbsp;Log in</button>
                                    <button type="cancel" class="btn btn-danger pull-right" style="margin-right:15px;"><i class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;&nbsp;Cancel</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="particles"></div>
    </section>

    <!-- Scripts -->
    <jsp:include page="jsp-parts/script-part.jsp"/>
    <script type="text/javascript" src="lib/particleground.js"></script>
    <script type="text/javascript" src="js/form.js"></script>
</body>

</html>