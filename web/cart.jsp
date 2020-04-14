<%-- 
    Document   : cart
    Created on : Feb 22, 2020, 9:42:50 PM
    Author     : dhtha
--%>

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
            <c:if test="${empty requestScope.TOTAL }">
                <c:redirect url="viewCart"></c:redirect>
            </c:if>
            <div class="site-section  pb-0">
                <div class="container">
                    <div class="row mb-5 justify-content-center">
                        <div class="col-7 section-title text-center mb-5">
                            <h2 class="d-block">Cart</h2>
                        </div>
                    </div>

                    <div class="row mb-5">
                        <h3 class="text-info text-center col-12">${requestScope.MESSAGE}</h3>
                        <c:if test="${empty sessionScope.CART}">
                            <h3 class="text-danger text-center col-12">No drink in your cart!</h3>
                        </c:if>
                        
                            <h3 class="text-danger text-center col-12">${requestScope.ERROR_MESSAGE}</h3>
                        <c:if test="${not empty sessionScope.CART}">
                            <form class="col-md-12" method="POST" action="UpdateCart">
                                <div class="site-blocks-table">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th class="product-thumbnail">Image</th>
                                                <th class="product-name">Drink</th>
                                                <th class="product-price">Price</th>
                                                <th class="product-quantity">Quantity</th>
                                                <th class="product-total">Total</th>
                                                <th class="product-remove">Remove</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="rows" items="${sessionScope.CART}">
                                                <tr>
                                                    <td class="product-thumbnail">
                                                        <img src="${rows.value.drink.image}" alt="Image" class="img-fluid">
                                                    </td>
                                                    <td class="product-name">
                                                        <h2 class="h5 cart-product-title text-black">${rows.value.drink.name}</h2>
                                                    </td>
                                                    <td>${rows.value.drink.price}</td>
                                                    <td>
                                                        <div class="input-group mb-3" style="max-width: 120px;">
                                                            <div class="input-group-prepend">
                                                                <button class="btn btn-outline-primary js-btn-minus" type="button">&minus;</button>
                                                            </div>
                                                            <input  type="hidden" name="ID" value="${rows.value.drink.ID}"/>

                                                            <input name="quantity_${rows.value.drink.ID}" type="text" class="form-control text-center border mr-0" value="${rows.value.quantity}" placeholder=""
                                                                   aria-label="Example text with button addon" aria-describedby="button-addon1" onchange="">
                                                            <div class="input-group-append">
                                                                <button class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>$${rows.value.totalPrice}</td>                                      
                                                    <td>                              
                                                        <c:url var="removeDrink" value="RemoveDrinkCart">
                                                            <c:param name="ID" value="${rows.value.drink.ID}"></c:param>
                                                        </c:url>
                                                        <button class="btn btn-primary height-auto btn-sm" onclick=" a = '${removeDrink}';
                                                                if(confirm('Do you want to delete this drink from cart?')){
                                                                    window.location.href=a;
                                                                }">X</button>
                                                        <!--<a class="btn btn-primary height-auto btn-sm" href="${removeDrink}">X</a>-->
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="row mb-5">
                                            <div class="col-md-6 mb-3 mb-md-0">
                                                <button type="submit" class="btn btn-primary btn-md btn-block">Update Cart</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="site-section pt-5 bg-light">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="row mb-5">
                                <div class="col-md-6">
                                    <button class="btn btn-outline-primary btn-md btn-block" onclick="window.location.href = 'searchDrink'">Continue Shopping</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label class="text-black h4" for="coupon">Coupon</label>
                                    <p>Enter your coupon code if you have one.</p>
                                </div>
                                <div class="col-md-8 mb-3 mb-md-0">
                                    <input type="text" class="form-control py-3" id="coupon" placeholder="Coupon Code">
                                </div>
                                <div class="col-md-4">
                                    <button class="btn btn-primary btn-md px-4">Apply Coupon</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 pl-5">
                            <div class="row justify-content-end">
                                <div class="col-md-7">
                                    <div class="row">
                                        <div class="col-md-12 text-right border-bottom mb-5">
                                            <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
                                        </div>
                                    </div>

                                    <div class="row mb-5">
                                        <div class="col-md-6">
                                            <span class="text-black">Total</span>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <strong class="text-black">$${requestScope.TOTAL}</strong>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <c:url var="checkout" value="CheckOut">
                                                <c:param name="action" value="GetDateCheckOut"></c:param>
                                            </c:url>
                                            <button class="btn btn-primary btn-lg btn-block" onclick="a='${checkout}';window.location.href=a;">Proceed To
                                                Checkout</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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