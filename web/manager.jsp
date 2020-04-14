<%-- 
    Document   : manager.jsp
    Created on : Feb 22, 2020, 9:54:27 AM
    Author     : dhtha
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"  media="screen">
        <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

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
            <c:if test="${requestScope.CATEGORY_LIST == null || requestScope.DRINK_LIST==null}">
                <c:redirect url="adminSearchDrink"></c:redirect>
            </c:if>
            <div class="site-section mt-5">
                <div class="container">
                    <div class="row mb-5">
                        <div class="col-12 section-title text-center mb-5">
                            <h2 class="d-block">Manager Drink</h2>
                            <h3 class="text-success">${requestScope.MESSAGE}</h3>
                        </div>

                    </div>
                    <div class="row mb-5">
                        <div class="col-12 section-subtitle text-center mb-5">
                            <h2 class="d-block">Search Drink</h2>
                        </div>
                        <form action="adminSearchDrink" class="form-group col-12">
                            <div class="text-center row">
                                <div class="col-6 section-subtitle text-center mb-5">
                                    <label class="">Name: </label>
                                    <input type="text" name="nameSearch" value="${requestScope.NAME}" class="form-control border mr-0"/>
                                </div>
                                <div class="col-6 section-subtitle text-center mb-5">
                                    <label>Category: </label>
                                    <select name="categoryIDSearch" class="form-control">
                                        <c:if test="${empty requestScope.CATEGORY_ID}">
                                            <option value="" selected="">--All Type--</option>
                                        </c:if>
                                        <c:if test="${not empty requestScope.CATEGORY_ID}">
                                            <option value="">--All Type--</option>
                                        </c:if>
                                        <c:forEach var="category" items="${requestScope.CATEGORY_LIST}">
                                            <c:if test="${requestScope.CATEGORY_ID != category.ID}">
                                                <option value="${category.ID}">${category.name}</option>
                                            </c:if>
                                            <c:if test="${requestScope.CATEGORY_ID == category.ID}">
                                                <option value="${category.ID}" selected="">${category.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-6 section-subtitle text-center mb-5">
                                    <label>Price:</label>
                                    <div data-role="rangeslider">
                                        <input id="priceMin" type="text" class="form-group" value="${requestScope.PRICE_FROM}" style="width: 100px;"/>
                                        <input type="range" name="priceFrom" class="form-group slider-price range" step="50" value="${requestScope.PRICE_FROM}" min="0" max="1000" onchange="priceMin.value = value">
                                        <input id="priceMax" type="text"  value="${requestScope.PRICE_TO}" class="form-group" style="width: 100px;"/>
                                        <input type="range" class="form-group slider-price range" step="50" name="priceTo" id="price-max" value="${requestScope.PRICE_TO}" min="0" max="1000" onchange="priceMax.value = value">
                                    </div>
                                </div>
                                <div class="col-4 section-subtitle text-center mb-5">
                                    <label>Status: </label>
                                    <select name="status" class="form-control">
                                        <option value="">--All--</option>
                                        <c:if test="${requestScope.STATUS eq 'Active'}">
                                            <option value="Active" selected="">Active</option>
                                            <option value="Inactive">Inactive</option>
                                        </c:if>
                                        <c:if test="${requestScope.STATUS eq 'Inactive'}">
                                            <option value="Active" >Active</option>
                                            <option value="Inactive" selected="">Inactive</option>
                                        </c:if>
                                        <c:if test="${empty requestScope.STATUS}">
                                            <option value="Active" >Active</option>
                                            <option value="Inactive">Inactive</option>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="col-2 ">
                                    <label> </label>
                                    <input type="submit" value="Search" class="btn btn-info" style="margin-top: 40px;"/>
                                </div>
                            </div>  
                    </div>
                    </form>
                </div>
                <c:if test="${empty requestScope.DRINK_LIST}">
                    <h3 class="text-center text-danger">Not found</h3>
                </c:if>  
                <form name="formDeleteDrinks"action="deleteDrink" method="POST" class="">
                    <div class="col-12 text-center clearfix">
                        <input type="hidden" name="action" value="DeleteDrinks"/>
                        <input type="button" value="Delete Select Drinks" class="btn btn-danger" style="margin-bottom: 20px;" 
                               onclick="
                                       if (confirm('Do you want to delete selected drinks?')) {
                                           document.forms['formDeleteDrinks'].submit();
                                       }
                               "/>
                    </div>
                    <div class="row">
                        <c:if test="${not empty requestScope.DRINK_LIST}">
                            <c:forEach var="drink" items="${requestScope.DRINK_LIST}" varStatus="count">
                                <div class="col-lg-4 mb-5 col-md-6">
                                    <div class="wine_v_1 text-center pb-4">
                                        <c:url var="view" value="viewDrink">
                                            <c:param name="ID" value="${drink.ID}"></c:param>
                                        </c:url>
                                        <a href="${view}" class="thumbnail d-block mb-4"><img src="${drink.image}" alt="Image" class="img-fluid"></a>
                                        <div class="wine">
                                            <h3 class="heading-2"><a href="#">${drink.name}</a></h3>
                                            <span class="price d-block">Price: $${drink.price}</span>
                                            <span class="price d-block">Quantity: ${drink.quantity}</span>
                                            <span class="price d-block">CreateDate: ${drink.createDate}</span>
                                            <span class="price d-block">Category: ${drink.category.name}</span>
                                            <c:if test="${drink.status eq 'Active'}">
                                                <span class="price d-block text-success">Status: ${drink.status}</span>
                                            </c:if>
                                            <c:if test="${drink.status != 'Active'}">
                                                <span class="price d-block text-danger">Status: ${drink.status}</span>
                                            </c:if>
                                            <div class="rating">
                                                <span class="icon-star"></span>
                                                <span class="icon-star"></span>
                                                <span class="icon-star"></span>
                                                <span class="icon-star"></span>
                                                <span class="icon-star-o"></span>
                                            </div>
                                            <div class="text-center">
                                                <c:if test="${drink.status eq 'Inactive'}">
                                                    <c:url var="delete" value="deleteDrink">
                                                        <c:param name="ID" value="${drink.ID}"></c:param>
                                                    </c:url>
                                                   
                                                    <button  class="btn btn-danger" onclick="a = '${delete}';
                                                            if (confirm('Do you want to delete this Drink?')) {
                                                                window.location.href = a;
                                                            }" disabled="">Delete</button>
                                                </c:if>
                                                <c:if test="${drink.status eq 'Active'}">
                                                    <c:url var="delete" value="deleteDrink">
                                                        <c:param name="ID" value="${drink.ID}"></c:param>
                                                    </c:url>
                                                    <button  class="btn btn-danger" onclick="a = '${delete}';
                                                            if (confirm('Do you want to delete this Drink?')) {
                                                                window.location.href = a;
                                                            }" >Delete</button>
                                                </c:if>

                                                <c:url var="update" value="UpdateDrink">
                                                    <c:param name="ID" value="${drink.ID}"></c:param>
                                                    <c:param name="action" value="GetData"></c:param>
                                                </c:url>
                                                <!--                                                <form action="UpdateDrink" method="POST" style="display: none;">
                                                                                                    <input type="hidden" name="ID" value="${drink.ID}"/>
                                                                                                    <input type="hidden" name="action" value="GetData"/>
                                                                                                    <input type="submit" value="Update" class="btn btn-success"/>
                                                                                                </form>-->
                                                <!--<a href="" class="btn btn-info" onclick="">Update</a>-->
                                                <a href="${update}" class="btn btn-info">Update</a>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${drink.status eq 'Inactive'}">
                                        <input class="form-control" type="checkbox" name="drinkIDs" value="${drink.ID}" style="display: none;"/>
                                    </c:if>
                                    <c:if test="${drink.status eq 'Active'}">
                                        <input class="form-control" type="checkbox" name="drinkIDs" value="${drink.ID}"/>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:if>
                        <div class="col-12">
                            <ul class="pagination justify-content-center">
                                <c:forEach var="i" begin="1" end="${requestScope.NUMBER_PAGE}">
                                    <c:url var="pageLink" value="adminSearchDrink">
                                        <c:param name="nameSearch" value="${requestScope.NAME}"></c:param>
                                        <c:param name="categoryIDSearch" value="${requestScope.CATEGORY_ID}"></c:param>
                                        <c:param name="priceFrom" value="${requestScope.PRICE_FROM}"></c:param>
                                        <c:param name="priceTo" value="${requestScope.PRICE_TO}"></c:param>
                                        <c:param name="status" value="${requestScope.STATUS}"></c:param>
                                        <c:param name="page" value="${i}"></c:param>
                                    </c:url>
                                    <c:if test="${requestScope.PAGE == i}">
                                        <li class="page-item active">
                                            <a class="page-link" href="${pageLink}">${i}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${requestScope.PAGE != i}">
                                        <li class="page-item">
                                            <a class="page-link" href="${pageLink}">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>  
                        </div>
                    </div>
                </form>
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

        <div class="site-section bg-light">
            <div class="container">
                <div class="owl-carousel owl-slide-3 owl-slide">

                    <blockquote class="testimony">
                        <img src="images/person_1.jpg" alt="Image">
                        <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        <p class="small text-primary">&mdash; Collin Miller</p>
                    </blockquote>
                    <blockquote class="testimony">
                        <img src="images/person_2.jpg" alt="Image">
                        <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        <p class="small text-primary">&mdash; Harley Perkins</p>
                    </blockquote>
                    <blockquote class="testimony">
                        <img src="images/person_3.jpg" alt="Image">
                        <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        <p class="small text-primary">&mdash; Levi Morris</p>
                    </blockquote>
                    <blockquote class="testimony">
                        <img src="images/person_1.jpg" alt="Image">
                        <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero sapiente beatae, nemo quasi quo neque consequatur rem porro reprehenderit, a dignissimos unde ut enim fugiat deleniti quae placeat in cumque?&rdquo;</p>
                        <p class="small text-primary">&mdash; Allie Smith</p>
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