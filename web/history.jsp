<%-- 
    Document   : history
    Created on : Feb 25, 2020, 1:01:09 PM
    Author     : dhtha
--%>

<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <div class="site-section  pb-0">
                <div class="container">
                    <div class="row mb-5 justify-content-center">
                        <div class="col-7 section-title text-center mb-5">
                            <h2 class="d-block">History</h2>
                        </div>
                    </div>

                    <div class="row mb-5">
                        <form action="viewHistory" class="form-group col-12">
                            <div class="text-center row">
                                <div class="col-4 section-subtitle text-center mb-5">
                                    <label class="">Name: </label>
                                    <input type="text" name="name" value="${requestScope.NAME_SEARCH}" class="form-control border mr-0"/>
                                </div>
                                <div class="col-3 section-subtitle text-center mb-5">
                                    <label>Date From: </label>
                                    <input type="date" class="form-control" name="dateFrom" value="${requestScope.DATE_FROM}"/>
                                </div>
                                 <div class="col-3 section-subtitle text-center mb-5">
                                    <label>Date To: </label>
                                    <input type="date" class="form-control" name="dateTo" value="${requestScope.DATE_TO}"/>
                                </div>
                                <div class="col-2 ">
                                    <label> </label>
                                    <input type="submit" value="Search" class="btn btn-info form-control-lg" style="margin-top: 40px;"/>
                                </div>
                            </div>
                        </form>
                        <h3 class="text-info text-center col-12">${requestScope.MESSAGE}</h3>
                        <c:if test="${empty requestScope.ORDER_LIST}">
                            <h3 class="text-danger text-center col-12">No drink in your history!</h3>
                        </c:if>
                        <c:if test="${not empty requestScope.ORDER_LIST}">

                            <div class="site-blocks-table">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="product-thumbnail">Image</th>
                                            <th class="product-name">Drink</th>
                                            <th class="product-price">Price</th>
                                            <th class="product-quantity">Quantity</th>
                                            <th class="product-total">Total</th>
                                            <th class="product-remove">Shopping Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="rows" items="${requestScope.ORDER_LIST}">
                                            <tr>
                                                <td class="product-thumbnail">
                                                    <img src="${rows.drink.image}" alt="Image" class="img-fluid">
                                                </td>
                                                <td class="product-name">
                                                    <h2 class="h5 cart-product-title text-black">${rows.drink.name}</h2>
                                                </td>
                                                <td>${rows.drink.price}</td>
                                                <td>
                                                    ${rows.quantity}
                                                </td>
                                                <td>$${rows.quantity * rows.drink.price}</td>                                      
                                                <td>                              
                                                    ${rows.transaction.createDate}                                           
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </c:if>
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