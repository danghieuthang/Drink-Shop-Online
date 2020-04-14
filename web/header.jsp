<%-- 
    Document   : header
    Created on : Feb 18, 2020, 9:06:04 PM
    Author     : dhtha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header-top">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-12 text-center">
                <a href="index.jsp" class="site-logo">
                    <img src="images/logo.png" alt="Image" class="img-fluid" width="">
                </a>
            </div>
            <!--            <button class="mx-auto d-inline-block d-lg-none site-menu-toggle js-menu-toggle text-black"><span
                                class="icon-menu h3"></span></button>-->
            <a href="#" class="mx-auto d-inline-block d-lg-none site-menu-toggle js-menu-toggle text-black"><span
                    class="icon-menu h3"></span></a>
        </div>
    </div>
    <div class="site-navbar py-2 js-sticky-header site-navbar-target d-none pl-0 d-lg-block" role="banner" id="collapsibleNavbar">
        <div class="container">
            <div class="d-flex align-items-center">
                <div class="mx-auto">
                    <nav class="site-navigation position-relative text-left" role="navigation">
                        <ul class="site-menu main-menu js-clone-nav mx-auto d-none pl-0 d-lg-block border-none">
                            <li class="active"><a href="index.jsp" class="nav-link text-left">Home</a></li>
                            <li><a href="about.html" class="nav-link text-left">About</a></li>
                            <li><a href="shop.jsp" class="nav-link text-left">Wines</a></li>
                            <li><a href="searchDrink" class="nav-link text-left">Shop</a></li>
                            <li><a href="contact.html" class="nav-link text-left">Contact</a></li>
                                <c:if test="${sessionScope.USER.roleID eq 'Admin'}">
                                <li><a href="adminSearchDrink" class="nav-link text-left">Manager</a></li>
                                </c:if>
                                <c:if test="${empty sessionScope.USER}">
                                <li><a href="login.jsp" class="nav-link text-left">Login</a></li>
                                </c:if>
                                <c:if test="${sessionScope.USER.roleID eq 'Admin'}">
                                    <c:url var="create" value="CreateDrink">
                                        <c:param name="action" value="GetCategory"></c:param>
                                    </c:url>
                                <li><a href="${create}" class="nav-link text-left">Create Drink</a></li>
                                </c:if>
                                <c:if test="${not empty sessionScope.USER}">
                                <li class="nav-item dropdown">
                                    <a href="" class="nav-link text-left dropdown-toggle" id="navbardrop" data-toggle="dropdown">Welcome ${sessionScope.USER.name}</a>
                                    <div class="dropdown-menu">
                                        <c:if test="${sessionScope.USER.roleID != 'Admin'}">
                                            <a class="dropdown-item" href="viewCart">View Cart</a>
                                            <a class="dropdown-item" href="viewHistory">Shopping History</a>
                                        </c:if>
                                        <c:if test="${sessionScope.USER.roleID == 'Admin'}">
                                        </c:if>
                                        <a class="dropdown-item" href="logout">Logout</a>
                                    </div>
                                </li>
                            </c:if>

                        </ul>                                                                                                                                                                                                                                                                                         
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>