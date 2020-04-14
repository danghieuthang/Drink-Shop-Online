<%-- 
    Document   : registration
    Created on : Jan 7, 2020, 2:38:11 PM
    Author     : dhtha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Cinzel:400,700|Montserrat:400,700|Roboto&display=swap" rel="stylesheet"> 
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/jquery.fancybox.min.css">
        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
        <link rel="stylesheet" href="css/aos.css">
        <link href="css/jquery.mb.YTPlayer.min.css" media="all" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
        <div class="site-wrap">
            <div class="site-mobile-menu site-navbar-target">
                <div class="site-mobile-menu-header">
                    <div class="site-mobile-menu-close mt-3">
                        <span class="icon-close2 js-menu-toggle"></span>
                    </div>
                </div>
                <div class="site-mobile-menu-body"></div>
            </div>
            <%@include file="header.jsp" %>
            <div class="container" style="margin-top: 100px" >
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="Registration" method="post">
                                <h3 class="text-center text-info">Registration</h3>
                                <div class="form-group">
                                    <label for="email" class="text-info">Email:</label><br>
                                    <input type="email" name="email"  class="form-control" required="" value="${requestScope.ACCOUNT.email}">
                                    <div class="bg-danger">
                                        ${requestScope.EMAIL_ERROR}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="text-info">Name:</label><br>
                                    <input type="text" name="name"  class="form-control" required="" value="${requestScope.ACCOUNT.name}">
                                    <div class="bg-danger">
                                        ${requestScope.NAME_ERROR}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password"  class="form-control" required="">
                                    <div class="bg-danger">
                                        ${requestScope.PASSWORD_ERROR}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="rePassword" class="text-info">RePassword:</label><br>
                                    <input type="password" name="rePassword"  class="form-control" required="">
                                    <div class="bg-danger">
                                        ${requestScope.REPASSWORD_ERROR}
                                    </div>
                                </div>
                                <div  class="text-center">
                                    <div class="form-group">
                                        <button type="submit" name="action" value="Registration" class="btn btn-info btn-md">Registration</button>
                                    </div>
                                </div>
                                <div class="bg-danger">
                                    ${requestScope.MESSAGE}
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="footer.jsp" %>
        </div>
        <!-- loader -->
        <div id="loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#ff5e15"/></svg></div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/bootstrap-datepicker.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.fancybox.min.js"></script>
        <script src="js/jquery.sticky.js"></script>
        <script src="js/jquery.mb.YTPlayer.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</body>
</html>
