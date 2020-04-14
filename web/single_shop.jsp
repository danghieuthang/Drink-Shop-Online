<%-- 
    Document   : single_shop
    Created on : Feb 25, 2020, 8:37:08 PM
    Author     : dhtha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Wines &mdash; Website Template by Colorlib</title>
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
            <c:if test="${empty requestScope.DRINK}">
                <c:redirect url="searchDrink"></c:redirect>
            </c:if>
            <div class="site-section bg-primary py-5 page-title-wrap mt-5">
                <div class="container">
                    <h1>${requestScope.DRINK.name}</h4>
                </div>
            </div>
            <div class="site-section mt-5">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="owl-carousel hero-slide owl-style">
                                <img src="${requestScope.DRINK.image}" alt="Image" class="img-fluid">
                                <img src="${requestScope.DRINK.image}" alt="Image" class="img-fluid">
                                <img src="${requestScope.DRINK.image}" alt="Image" class="img-fluid">
                            </div>
                        </div>
                        <div class="col-lg-5 ml-auto">
                            <form action="addToCart" method="POST">
                                <input type="hidden" name="ID" value="${requestScope.DRINK.ID}"/>
                                <input type="hidden" name="from" value="single_shop" />
                                <h2 class="text-primary">Drink Details</h2>
                                <p>${requestScope.DRINK.description}</p>
                                <div class="mb-5">
                                    <div class="input-group mb-3" style="max-width: 200px;">
                                        <div class="input-group-prepend">
                                            <button class="btn btn-outline-primary js-btn-minus" type="button">&minus;</button>
                                        </div>
                                        <input name="quantity" type="number" class="form-control text-center border mr-0" value="1" placeholder=""
                                               aria-label="Example text with button addon" aria-describedby="button-addon1">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
                                        </div>
                                    </div>

                                </div>
                                <p>
                                    <button type="submit" class="buy-now btn btn-sm height-auto px-4 py-3 btn-primary">Add To Cart</button>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="hero-2" style="background-image: url('images/hero_2.jpg');">
                <div class="container">
                    <div class="row justify-content-center text-center align-items-center">
                        <div class="col-md-8">
                            <span class="sub-title">Welcome</span>
                            <h2>Wines For Everyone</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="site-section bg-primary">
                <div class="container">
                    <div class="owl-carousel owl-slide-3 owl-slide-3-white owl-slide">

                        <blockquote class="testimony">
                            <img src="images/person_1.jpg" alt="Image">
                            <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        </blockquote>
                        <blockquote class="testimony">
                            <img src="images/person_2.jpg" alt="Image">
                            <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        </blockquote>
                        <blockquote class="testimony">
                            <img src="images/person_3.jpg" alt="Image">
                            <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        </blockquote>
                        <blockquote class="testimony">
                            <img src="images/person_1.jpg" alt="Image">
                            <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        </blockquote>

                    </div>
                </div>
            </div>




            <div class="footer">
                <div class="container">

                    <div class="row">
                        <div class="col-12 text-center">
                            <div class="social-icons">
                                <a href="#"><span class="icon-facebook"></span></a>
                                <a href="#"><span class="icon-twitter"></span></a>
                                <a href="#"><span class="icon-youtube"></span></a>
                                <a href="#"><span class="icon-instagram"></span></a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="copyright">
                                <p>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart text-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank" >Colorlib</a>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                </p>



                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <!-- .site-wrap -->


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