<%-- 
    Document   : create_drink
    Created on : Feb 21, 2020, 10:18:04 PM
    Author     : dhtha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Drink Page</title>
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
            <c:if test="${requestScope.CATEGORY_LIST == null }">
                <c:url var="createDrink" value="CreateDrink">
                    <c:param name="action" value="GetCategory"></c:param>
                </c:url>
                <c:redirect url="${createDrink}"></c:redirect>
            </c:if>
            <div class="container" style="margin-top: 100px" >
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="createDrink" method="post">
                                <h3 class="text-center text-info">Create Drink</h3>
                                <h3 class="text-center">${requestScope.DRINK.name}</h3>
                                <h3 class="text-center text-danger">${requestScope.MESSAGE}</h3>
                                <div class="form-group">
                                    <label class="form-check-label">Name: </label>
                                    <input type="text" name="name" value="${requestScope.DRINK.name}" class="form-control" required=""/>
                                    <h4 class="text-danger">${requestScope.DRINK_ERR.nameErr}</h4>
                                </div>
                                <div class="form-group">
                                    <label class="form-check-label">Image:  </label>
                                    <input type="file" name="image" value="" class="form-control-file" required=""/>
                                    <h4 class="text-danger">${requestScope.DRINK_ERR.imageErr}</h4>
                                </div><div class="form-group">
                                    <label class="form-check-label">Description: </label>
                                    <textarea class="form-control" name="description" rows="4" cols="50" required="">${requestScope.DRINK.description}</textarea>
                                    <h4 class="text-danger">${requestScope.DRINK_ERR.descriptionErr}</h4>
                                </div><div class="form-group">
                                    <label class="form-check-label">Price:  </label> <br>
                                    <input type="number" name="price" max="1000" min="0" value="${requestScope.DRINK.price}" class="form-control-sm" required=""/>
                                    <h4 class="text-danger">${requestScope.DRINK_ERR.priceErr}</h4>
                                </div><div class="form-group">
                                    <label class="form-check-label">Category: </label>
                                    <select name="categoryID" class="form-control-sm">
                                        <c:forEach var="category" items="${requestScope.CATEGORY_LIST}">
                                            <option value="${category.ID}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                    <h4 class="text-danger">${requestScope.DRINK_ERR.categoryErr}</h4>
                                </div><div class="form-group">
                                    <label class="form-check-label">Quantity:  </label><br>
                                    <input type="number" name="quantity" value="${requestScope.DRINK.quantity}" min="0" class="form-control-sm" required=""/>
                                    <h4 class="text-danger">${requestScope.DRINK_ERR.quantityErr}</h4>
                                </div>
                                <div class="form-group text-center">
                                    <input type="submit"  name="action" class="btn btn-info btn-md" value="Create Drink">
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
</html>