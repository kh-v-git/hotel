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
                                        <a href="secured-admin.command"><img class="logo-image"
                                                                             src="assets/img/logo/logo.png"
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
                    <div class="col-lg-4">
                        <div class="blog_right_sidebar">
                            <aside class="single_sidebar_widget search_widget">
                                <form action="secured-admin-rooms.command" method="post">
                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <input type="text" name="search-number" class="form-control"
                                                   placeholder='Search Number'
                                            <c:if test="${not empty searchNumber}">
                                                    value="${searchNumber}"
                                            </c:if>
                                                   onfocus="this.placeholder = ''"
                                                   onblur="this.placeholder = 'Search Number'">
                                            <div class="input-group-append">
                                                <button  class="btn" type="button"><i class="ti-search"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <button class="button rounded-0 primary-bg text-white w-100 btn_1 boxed-btn"
                                            type="submit">Search
                                    </button>
                                </form>
                            </aside>
                        </div>
                    </div>
                    <div class="section-top-border">
                        <h3 class="mb-30">
                            <h2><fmt:message key="rooms" bundle="${localeBundle}"/></h2>

                        </h3>
                        <!--Add new room button start -->
                        <div class="button-group-area">
                            <a href="room-add-new-page-admin.command" class="genric-btn info">Add room</a>
                        </div>
                        <!--Add new room button end -->
                        <div class="progress-table-wrap">
                            <div class="progress-table">
                                <div class="table-head">
                                    <div class="visit">ID</div>
                                    <div class="visit">Number</div>
                                    <div class="visit">Adults</div>
                                    <div class="visit">Children</div>
                                    <div class="visit">Price</div>
                                    <div class="visit">Bed Size</div>
                                </div>
                                <c:forEach items="${roomList}" var="room">
                                    <div class="table-row">
                                        <div class="visit">${room.roomID}</div>
                                        <div class="visit"><a
                                                href="room-edit-page-admin.command?room-id=${room.roomID}">${room.number}</a>
                                        </div>
                                        <div class="visit">${room.adultCapacity}</div>
                                        <div class="visit">${room.childrenCapacity}</div>
                                        <div class="visit">${room.price}</div>
                                        <div class="visit">${room.bedSize}</div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <nav class="blog-pagination justify-content-center d-flex">
                            <ul class="pagination">

                                <c:forEach begin="1" end="${numOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active">
                                                <a href="secured-admin-rooms.command?page=${i}&search-number=${searchNumber}"
                                                   class="page-link ">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a href="secured-admin-rooms.command?page=${i}&search-number=${searchNumber}"
                                                   class="page-link ">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <!--Users table -->
        </main>
    </jsp:body>
</t:page>