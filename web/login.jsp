<%-- 
    Document   : login
    Created on : Jan 7, 2020, 3:39:40 PM
    Author     : dhtha
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="877709833880-cnuba5h4ru7knsm61n8hqte6qa92iov4.apps.googleusercontent.com">
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
                            <form id="login-form" class="form" action="login" method="post">
                                <h3 class="text-danger text-center">${requestScope.MESSAGE}</h3>
                                <h3 class="text-center text-info">Login</h3>
                                <div class="form-group">
                                    <label for="email" class="text-info">Email:</label><br>
                                    <input type="email" name="email" id="username" class="form-control" required="" value="${requestScope.EMAIL}">
                                    <h3 class=" text-danger">${requestScope.USER_ERROR.emailErr}</h3>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control" required="">
                                    <h3 class=" text-danger">${requestScope.USER_ERROR.passwordErr}</h3>
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="action" class="btn btn-info btn-md" value="Login">
                                </div>
                            </form>
                            <!--                            <div id="login-link" class="text-center">
                                                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/HanaShop/loginGoogle&response_type=code&client_id=877709833880-cnuba5h4ru7knsm61n8hqte6qa92iov4.apps.googleusercontent.com&approval_prompt=force" class="btn btn-social btn-google">
                                                                <span class="fa fa-google"></span> Sign in with Google
                                                            </a>
                                                        </div>-->
                            <form id="formLogin" name="formLogin" action="loginGoogle" method="POST" style="display: none">
                                <input id="loginEmail" name="Email" type="text"/>
                              
                            </form>
                            <div class="g-signin2" data-onsuccess="onSignIn"></div>
                            <script>
                                function onSignIn(googleUser) {
                                    var profile = googleUser.getBasicProfile();
                                    var email = profile.getEmail();
                                    signOut();
                                    document.getElementById("loginEmail").value = email;
                                    document.getElementById("formLogin").submit();
                                }
                            </script>
                            <script>
                                function signOut() {
                                    var auth2 = gapi.auth2.getAuthInstance();
                                    auth2.signOut().then(function () {
                                        console.log('User signed out.');
                                    });
                                }
                            </script>
                            <div id="register-link" class="text-right">
                                <a href="registration.jsp" class="text-info">Register here</a>
                            </div>
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
</html>