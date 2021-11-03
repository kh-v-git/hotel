<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="main.page.title" bundle="${localeBundle}" var="pageRoomTitle" scope="page"/>
<t:page title="${pageRoomTitle}">

    <jsp:body>
        <main>
            <header>
            <!-- Header Start -->
            <div class="header-area header-sticky">
                <div class="main-header ">
                    <div class="container">
                        <div class="row align-items-center">
                            <!-- logo -->
                            <div class="col-xl-2 col-lg-2">
                                <div class="logo">
                                    <a href="index.command"><img class="logo-image" src="assets/img/logo/logo.png" alt=""></a>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6">
                                <!-- main-menu -->
                                <div class="main-menu f-right d-none d-lg-block">
                                    <nav>
                                        <ul id="navigation">
                                            <li><a href="index.command">
                                                <fmt:message key="home" bundle="${localeBundle}"/>
                                            </a></li>
                                            <li><a href="">
                                                <fmt:message key="rooms" bundle="${localeBundle}"/>
                                            </a>
                                                <ul class="submenu">
                                                    <c:forEach items="${roomBedSizeList}" var="roomBedSize">
                                                    <li>
                                                        <a href="rooms-view.command?bed-size=${roomBedSize}">${roomBedSize}</a>
                                                        </c:forEach>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li><a href="login.command">
                                                <fmt:message key="login" bundle="${localeBundle}"/>
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
                                            <li data-value="2" class="option"><a href="locale.command?locale=uk_UA">Uk</a></li>
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
                                            <li data-value="2" class="option"><a href="locale.command?locale=uk_UA">Uk</a></li>
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
            <!-- slider Area Start-->
            <div class="slider-area">
                <!-- Mobile Menu -->
                <div class="slider-active dot-style">
                    <div class="single-slider  hero-overly slider-height d-flex align-items-center"
                         data-background="assets/img/hero/h1_hero.jpg">
                        <div class="container">
                            <div class="row justify-content-center text-center">
                                <div class="col-xl-9">
                                    <div class="h1-slider-caption">
                                        <h1 data-animation="fadeInUp" data-delay=".4s">
                                            <fmt:message key="hotel.slider.one.index" bundle="${localeBundle}"/>
                                        </h1>
                                        <h3 data-animation="fadeInDown" data-delay=".4s">
                                            <fmt:message key="hotel.description.index" bundle="${localeBundle}"/>
                                        </h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="single-slider  hero-overly slider-height d-flex align-items-center"
                         data-background="assets/img/hero/h1_hero.jpg">
                        <div class="container">
                            <div class="row justify-content-center text-center">
                                <div class="col-xl-9">
                                    <div class="h1-slider-caption">
                                        <h1 data-animation="fadeInUp" data-delay=".4s">
                                            <fmt:message key="hotel.slider.two.index" bundle="${localeBundle}"/>
                                        </h1>
                                        <h3 data-animation="fadeInDown" data-delay=".4s">
                                            <fmt:message key="hotel.description.index" bundle="${localeBundle}"/>
                                        </h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- slider Area End-->

            <!-- Booking Room Start-->
            <div class="booking-area">
                <div class="container">
                    <div class="row ">
                        <div class="col-12">
                            <form action="#">
                                <div class="booking-wrap d-flex justify-content-between align-items-center">

                                    <!-- select in date -->
                                    <div class="single-select-box mb-30">
                                        <!-- select out date -->
                                        <div class="boking-tittle">
                                            <span>
                                                <fmt:message key="check.in.date" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="boking-datepicker">
                                            <input name="arrival-date" id="datepicker1" placeholder="10/12/2020"/>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <!-- select out date -->
                                        <div class="boking-tittle">
                                            <span>
                                                <fmt:message key="check.out.date" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="boking-datepicker">
                                            <input name="departure-date" id="datepicker2" placeholder="12/12/2020"/>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>
                                                <fmt:message key="adults.capacity" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="select-this">
                                            <div class="select-itms">
                                                <select name="adults-capacity-request" id="select1">
                                                    <c:forEach varStatus="loop" begin="1" end="${maxAdults}" step="1">
                                                        <option value="${loop.count}">${loop.count}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>
                                                <fmt:message key="children.capacity" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="select-this">
                                            <div class="select-itms">
                                                <select name="children-capacity-request" id="select2">
                                                    <c:forEach varStatus="loop" begin="1" end="${maxChildren}" step="1">
                                                        <option value="${loop.count}">${loop.count}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>
                                               <fmt:message key="bed.size" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="select-this">
                                            <form action="#">
                                                <div class="select-itms">
                                                    <select name="bed-size-request" id="select3" required>
                                                        <c:forEach items="${roomBedSizeList}" var="roomBedSize">
                                                            <option value="bed-size-order">${roomBedSize}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box pt-45 mb-30">
                                        <a href="#" class="btn select-btn">
                                            <fmt:message key="request.now" bundle="${localeBundle}"/>
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Booking Room End-->

            <!-- Room Start -->
            <section class="room-area customar-padding fix">
                <div class="container container-fluid p-0">
                    <div class="row justify-content-center">
                        <div class="col-xl-8">
                            <!--font-back-tittle  -->
                            <div class="font-back-tittle mb-45">
                                <div class="archivment-front">
                                    <h3>
                                        <fmt:message key="our.rooms" bundle="${localeBundle}"/>
                                    </h3>
                                </div>
                                <h3 class="archivment-back">Our Rooms</h3>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach items="${roomViewList}" var="roomSorted">
                            <div class="col-xl-4 col-lg-6 col-md-6">
                                <!-- Single Room -->
                                <div class="single-room mb-50">
                                    <div class="room-img">
                                        <a href="rooms-view.command?bed-size=${roomSorted.bedSize}"><img
                                                src="assets/img/rooms/room1.jpg" alt=""></a>
                                    </div>
                                    <div class="room-caption">
                                        <h3>
                                            <a href="rooms-view.command?bed-size=${roomSorted.bedSize}">${roomSorted.bedSize}</a>
                                        </h3>
                                        <h5>
                                            <fmt:message key="adults.capacity" bundle="${localeBundle}"/>: 1
                                            <fmt:message key="to" bundle="${localeBundle}"/> ${roomSorted.maxAdults}
                                        </h5>
                                        <h5>
                                            <fmt:message key="children.capacity" bundle="${localeBundle}"/>: 0
                                            <fmt:message key="to" bundle="${localeBundle}"/> ${roomSorted.maxChildren}
                                        </h5>
                                        <div class="per-night">
                                        <span>$${roomSorted.minPrice}<span> /
                                             <fmt:message key="night" bundle="${localeBundle}"/>
                                            </span>
                                            <fmt:message key="to" bundle="${localeBundle}"/>
                                        </span>
                                            <span>$${roomSorted.maxPrice}<span> /
                                                <fmt:message key="night" bundle="${localeBundle}"/>
                                            </span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            <!-- Room End -->

            <!-- Gallery img Start-->
            <div class="gallery-area fix">
                <div class="container-fluid p-0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="gallery-active owl-carousel">
                                <div class="gallery-img">
                                    <a href="#"><img src="assets/img/gallery/gallery1.jpg" alt=""></a>
                                </div>
                                <div class="gallery-img">
                                    <a href="#"><img src="assets/img/gallery/gallery2.jpg" alt=""></a>
                                </div>
                                <div class="gallery-img">
                                    <a href="#"><img src="assets/img/gallery/gallery3.jpg" alt=""></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Gallery img End-->
        </main>
    </jsp:body>
</t:page>

