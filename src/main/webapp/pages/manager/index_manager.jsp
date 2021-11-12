<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="user.requests" bundle="${localeBundle}" var="pageLoginTitle" scope="page"/>
<t:page title="${pageLoginTitle}">
    <jsp:body>
        <main>
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
                                            <a href="secured-manager.command"><img class="logo-image"
                                                                                   src="assets/img/logo/logo.png"
                                                                                   alt=""></a>
                                        </div>
                                    </div>
                                    <div class="col-xl-6 col-lg-6">
                                        <!-- main-menu -->
                                        <div class="main-menu f-right d-none d-lg-block">
                                            <nav>
                                                <ul id="navigation">
                                                    <li><a href="secured-manager.command">
                                                        <fmt:message key="requests" bundle="${localeBundle}"/>
                                                    </a></li>
                                                    <li><a href="booking-page-manager.command">
                                                        <fmt:message key="booking" bundle="${localeBundle}"/>
                                                    </a></li>
                                                    <li><a href="#">
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
                                                    <li data-value="2" class="option"><a
                                                            href="locale.command?locale=uk_UA">Uk</a>
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
                                                    <li data-value="2" class="option"><a
                                                            href="locale.command?locale=uk_UA">Uk</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                <!--Header end-->

                <!-- slider Area Start-->
                <div class="slider-area">
                    <div class="single-slider hero-overly slider-height2 d-flex align-items-center"
                         data-background="assets/img/hero/roomspage_hero.jpg">
                        <div class="container">
                            <div class="row ">
                                <div class="col-md-11 offset-xl-1 offset-lg-1 offset-md-1">
                                    <div class="hero-caption">
                                    <span>
                                        <fmt:message key="user.requests" bundle="${localeBundle}"/>
                                    </span>
                                        <h2><fmt:message key="manager" bundle="${localeBundle}"/></h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- slider Area End-->

                <!--Users Request table -->
                <div class="whole-wrap">
                    <div class="container box_1170">
                        <div class="section-top-border">
                            <h3 class="mb-30">
                                <fmt:message key="requests" bundle="${localeBundle}"/>
                            </h3>
                            <div class="progress-table-wrap">
                                <div class="progress-table">
                                    <div class="table-head">
                                        <div class="serial">
                                            <fmt:message key="room" bundle="${localeBundle}"/>
                                        </div>
                                        <div class="visit">
                                            <fmt:message key="arrival" bundle="${localeBundle}"/>
                                        </div>
                                        <div class="visit">
                                            <fmt:message key="departure" bundle="${localeBundle}"/>
                                        </div>
                                        <div class="visit">
                                            <fmt:message key="status" bundle="${localeBundle}"/>
                                        </div>
                                        <div class="visit">
                                            <fmt:message key="adults.capacity" bundle="${localeBundle}"/>
                                        </div>
                                        <div class="visit">
                                            <fmt:message key="children.capacity" bundle="${localeBundle}"/>
                                        </div>
                                        <div class="visit">
                                            <fmt:message key="bed.size" bundle="${localeBundle}"/>
                                        </div>
                                        <div class="visit">
                                            <fmt:message key="edit" bundle="${localeBundle}"/>
                                        </div>
                                    </div>
                                    <c:forEach items="${requestList}" var="request">
                                        <div class="table-row">
                                            <div class="serial"><c:if
                                                    test="${ 0 ne request.roomID}">${request.roomID}</c:if></div>
                                            <div class="visit">${request.arrivalDay}</div>
                                            <div class="visit">${request.departureDay}</div>
                                            <div class="visit">${request.status}</div>
                                            <div class="visit">${request.adultCapacity}</div>
                                            <div class="visit">${request.childCapacity}</div>
                                            <div class="visit">${request.bedSize}</div>
                                            <div class="visit">

                                                <c:if test="${0 eq request.roomID}">
                                                    <ul>
                                                        <li>
                                                            <a class="genric-btn success small action-button"
                                                               href="#">Add Room</a>
                                                        </li>
                                                        <li>
                                                            <a class="genric-btn warning small action-button"
                                                               href="change-request-status-manager.command?user-id=${request.userID}&request-id=${request.userRequestID}&status=declined">Decline</a>
                                                        </li>
                                                    </ul>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Users Ordrs table -->

            </main>

        </main>
    </jsp:body>
</t:page>