<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="admin.home" bundle="${localeBundle}" var="pageLoginTitle" scope="page"/>
<t:page title="${pageLoginTitle}">
    <jsp:body>
        <main>
            <!--Header start-->
            <header>
                <!-- Header Start -->
                <div class="header-area header-sticky">
                    <div class="main-header ">
                        <div class="container">
                            <div class="row align-items-center">
                                <!-- logo -->
                                <div class="col-xl-2 col-lg-2">
                                    <div class="logo">
                                        <a href="secured-admin.command"><img class="logo-image" src="assets/img/logo/logo.png"
                                                                             alt=""></a>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6">
                                    <!-- main-menu -->
                                    <div class="main-menu f-right d-none d-lg-block">
                                        <nav>
                                            <ul id="navigation">
                                                <li><a href="secured-admin.command">
                                                    <fmt:message key="users" bundle="${localeBundle}"/>
                                                </a></li>
                                                <li><a href="secured-admin-rooms.command">
                                                    <fmt:message key="rooms" bundle="${localeBundle}"/>
                                                </a></li>
                                                <li><a href="logout.command">
                                                    <fmt:message key="log.out" bundle="${localeBundle}"/>
                                                </a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                                <div class="col-xl-2 col-lg-2">
                                    <div class="d-none d-lg-block">
                                        <div class="nice-select">
                                            <span class="current">${"uk_UA" eq (sessionScope.locale) ? "Uk" : "En"}</span>
                                            <ul class="list">
                                                <li data-value="1" class="option selected focus"><a
                                                        href="locale.command?locale=en_US">En</a></li>
                                                <li data-value="2" class="option"><a href="locale.command?locale=uk_UA">Uk</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <!-- Mobile Menu -->
                                <div class="col-12">
                                    <div class="mobile_menu d-block d-lg-none"></div>
                                    <div class="language-selector d-block d-lg-none">
                                        <div class="nice-select">
                                            <span class="current">${"uk_UA" eq (sessionScope.locale) ? "Uk" : "En"}</span>
                                            <ul class="list">
                                                <li data-value="1" class="option selected focus"><a
                                                        href="locale.command?locale=en_US">En</a></li>
                                                <li data-value="2" class="option"><a href="locale.command?locale=uk_UA">Uk</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Header End -->
            </header>
            <!--Header end-->

            <!-- slider Area Start-->
            <div class="slider-area">
                <div class="single-slider hero-overly slider-height2 d-flex align-items-center"
                     data-background="assets/img/hero/contact_hero.jpg">
                    <div class="container">
                        <div class="row ">
                            <div class="col-md-11 offset-xl-1 offset-lg-1 offset-md-1">
                                <div class="hero-caption">
                                    <span>
                                        <fmt:message key="rooms.page.title" bundle="${localeBundle}"/>
                                    </span>
                                    <h2><fmt:message key="rooms" bundle="${localeBundle}"/></h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- slider Area End-->

            <!--Users table -->
            <div class="whole-wrap">
                <div class="container box_1170">
                    <div class="section-top-border">
                        <h3 class="mb-30">Image upload</h3>
                        <div class="progress-table-wrap">
                            <form method="post" action="uploadImage" enctype="multipart/form-data">
                                <input type="hidden" name="room-id" value="1">
                                <input type="file" name="file">
                                <input type="submit"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</t:page>
