<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign up</title>
    <!-- Style links -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
    <link href="css/query.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/icon.ico" type="image/x-icon">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <section>
        <div class="container">
            
            <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">

                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <img class="img-responsive" src="images/logo-large.svg" alt="logo">
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title text-center">Sign up</div>
                    </div>
                    <div class="panel-body">
                        <form name="form" id="form" class="form-horizontal" enctype="multipart/form-data" method="POST">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="user" type="text" class="form-control" name="user" value="" placeholder="User" required minlength="5">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                <input id="mail" type="email" class="form-control" name="mail" value="" placeholder="Email" required>
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="password" type="password" class="form-control" name="password" placeholder="Password" required minlength="8">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="passwordConf" type="password" class="form-control" name="passwordConf" placeholder="Confirm password" required minlength="8">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                <input id="birthDate" type="date" class="form-control" name="birthDate" placeholder="Date of Birth" required>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 controls">
                                    <button type="submit" class="btn btn-primary pull-right" id='submitBtn'><i class="glyphicon glyphicon-log-in"></i>&nbsp;&nbsp;&nbsp;Log in</button>
                                    <button type="cancel" class="btn btn-danger pull-right" style="margin-right:15px;"><i class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;&nbsp;Cancel</button>
                                </div>
                            </div>
                            <p id="massage" class="text-center text-danger"></p>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="particles"></div>
    </section>

    <!-- Scripts -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="lib/particleground.js"></script>
    <script type="text/javascript" src="lib/validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="lib/validate/additional-methods.min.js"></script>
    <script type="text/javascript" src="js/form.js"></script>
    <script type="text/javascript" src="js/validator.js"></script>
</body>

</html>