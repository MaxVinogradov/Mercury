<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create post</title>
    <jsp:include page="jsp-parts/header-part.jsp"/>
    <link href="css/posting.css" rel="stylesheet">
</head>

<body>
<jsp:include page="jsp-parts/nav_bar.jsp"/>
<div class="main">
    <div id="massageBox" class="mainBox col-md-offset-1 col-sm-offset-1 col-md-10 col-sm-10">

        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <img class="img-responsive" src="images/logo-large.svg" alt="logo" style="margin-top: 10px;">
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-title text-center">Create post</div>
            </div>
            <div class="panel-body">

                <div class="form-group">
                    <form name="form" id="form" class="form-horizontal" enctype="multipart/form-data" method="POST">
                        <textarea class="shadow-affect" rows="7" id="post"></textarea>
                        <div class="col-sm-12 controls text-center">
                            <button type="cancel" class="btn btn-danger" style="margin-right:15px;"><i class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;&nbsp;Cancel</button>
                            <button class="btn btn-primary" style="margin-right: 15px;"><i class="glyphicon glyphicon-share-alt"></i>&nbsp;&nbsp;&nbsp;Go to the wall</button>
                            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-envelope"></i>&nbsp;&nbsp;&nbsp;Post</button>
                        </div>
                    </form>
                </div>
                <div class="result-container container-fluid text-center">
                    <div class="row-fluid">
                        <h4 class="posting-result"></h4>
                    </div>
                    <table id="result-table" class="table table-striped table-hover">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<jsp:include page="jsp-parts/script-part.jsp"/>
<script type="text/javascript" src="js/menu.js"></script>
</body>

</html>