<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="rooms.page.title" bundle="${localeBundle}" var="pageRoomsTitle"/>
<t:page title="${pageRoomsTitle}">
    <jsp:body>
        <main>

            <!-- slider Area Start-->
            <div class="slider-area">
                <div class="single-slider hero-overly slider-height2 d-flex align-items-center"
                     data-background="assets/img/hero/aboutpage_hero.jpg">
                    <div class="container">
                        <div class="row ">
                            <div class="col-md-11 offset-xl-1 offset-lg-1 offset-md-1">
                                <div class="hero-caption">
                                    <span>
                                        <fmt:message key="about.room" bundle="${localeBundle}"/>
                                    </span>
                                    <h2>
                                        <fmt:message key="room" bundle="${localeBundle}"/> № ${pageRoom.number}
                                    </h2>
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
                            <form action="#" method="post">
                                <div class="booking-wrap d-flex justify-content-between align-items-center">

                                    <!-- select arrival date -->
                                    <div class="single-select-box mb-30">
                                        <!-- select arival date -->
                                        <div class="boking-tittle">
                                            <span>
                                                <fmt:message key="check.in.date" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="boking-datepicker">
                                            <input name="arrival-date" id="datepicker1" placeholder="10/12/2020"/>
                                        </div>
                                    </div>

                                    <!-- select departure date -->
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

                                    <!-- select adults capacity-->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>
                                                <fmt:message key="adults.capacity" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="select-this">
                                            <div class="select-itms">
                                                <select name="adults-capacity-request" id="select1" >
                                                        <option value="${pageRoom.adultCapacity}">${pageRoom.adultCapacity}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- select children capacity-->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>
                                                <fmt:message key="children.capacity" bundle="${localeBundle}"/>:
                                            </span>
                                        </div>
                                        <div class="select-this">
                                            <div class="select-itms">
                                                <select name="children-capacity-request" id="select2">
                                                    <option value="${pageRoom.childrenCapacity}">${pageRoom.childrenCapacity}</option>
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
                                                        <option value="${pageRoom.bedSize}">${pageRoom.bedSize}</option>
                                                    </select>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box pt-45 mb-30">
                                        <a href="#" class="btn select-btn">
                                            <fmt:message key="book.now" bundle="${localeBundle}"/>
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Booking Room End-->

            <!-- One Room Description Start-->
            <section class="make-customer-area customar-padding fix">
                <div class="container-fluid p-0">
                    <div class="row">
                        <div class="col-xl-5 col-lg-6">
                            <div class="customer-img mb-120">
                                <img src="assets/img/customer/customar1.png" class="customar-img1" alt="">
                                <img src="assets/img/customer/customar2.png" class="customar-img2" alt="">
                                <div class="service-experience heartbeat">
                                    <h3>
                                        <fmt:message key="room.price"
                                                     bundle="${localeBundle}"/>: ${pageRoom.price}
                                    </h3>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-4">
                            <div class="customer-caption">
                                <span>
                                    <fmt:message key="room" bundle="${localeBundle}"/> № ${pageRoom.number}
                                </span>
                                <h2>
                                    <fmt:message key="bed.size" bundle="${localeBundle}"/>: ${pageRoom.bedSize}
                                </h2>
                                <div class="caption-details">
                                    <p class="pera-dtails">${pageRoom.about}</p>
                                    <p>
                                        <fmt:message key="adults.capacity"
                                                     bundle="${localeBundle}"/>: ${pageRoom.adultCapacity}
                                        <br>
                                        <fmt:message key="children.capacity"
                                                     bundle="${localeBundle}"/>: ${pageRoom.childrenCapacity}
                                    </p>
                                    <div class="caption-details">
                                        <a href="rooms-view.command?bed-size=${pageRoom.bedSize}" class="btn more-btn1">Back to Rooms<i class="ti-angle-right"></i> </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
            <!-- One Room Description End-->

            <!-- Gallery img Start-->
            <div class="gallery-area g-padding fix">
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