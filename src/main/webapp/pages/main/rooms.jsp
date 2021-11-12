<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="rooms.page.title" bundle="${localeBundle}" var="pageRoomTitle" scope="page"/>
<t:page title="${pageRoomTitle}">
    <jsp:body>
        <main>

            <!-- slider Area Start-->
            <div class="slider-area">
                <div class="single-slider hero-overly slider-height2 d-flex align-items-center"
                     data-background="assets/img/hero/roomspage_hero.jpg">
                    <div class="container">
                        <div class="row ">
                            <div class="col-md-11 offset-xl-1 offset-lg-1 offset-md-1">
                                <div class="hero-caption">
                                    <span>
                                        <fmt:message key="rooms" bundle="${localeBundle}"/>
                                    </span>
                                    <h2>Our Rooms</h2>
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
                            <form action="login.command" method="post">
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
                                                    <c:forEach varStatus="loop" begin="1" end="${maxAdultsCategory}"
                                                               step="1">
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
                                                    <c:forEach varStatus="loop" begin="1" end="${maxChildrenCategory}"
                                                               step="1">
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
                                                        <option value="bed-size-order">${bedCategory}</option>
                                                    </select>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box pt-45 mb-30">
                                        <a href="login.command" class="btn select-btn">
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
                                        <fmt:message key="our.rooms" bundle="${localeBundle}"/> : ${roomSorted.bedSize}
                                    </h3>
                                </div>
                                <h3 class="archivment-back">
                                    Our Rooms
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${roomSortedList}" var="roomSorted">
                        <div class="col-xl-4 col-lg-6 col-md-6">
                            <!-- Single Room -->
                            <div class="single-room mb-50">
                                <div class="room-img">
                                    <a href="room.command?room-id=${roomSorted.roomID}"><img
                                            src="assets/img/rooms/room1.jpg" alt=""></a>
                                </div>
                                <div class="room-caption">
                                    <h2><a href="room.command?room-id=${roomSorted.roomID}">№: ${roomSorted.number}</a>
                                    </h2>
                                    <h3><a>${roomSorted.bedSize}</a></h3>
                                    <h5>
                                        <fmt:message key="adults.capacity" bundle="${localeBundle}"/>: 1
                                        <fmt:message key="to" bundle="${localeBundle}"/> ${roomSorted.adultCapacity}
                                    </h5>
                                    <h5>
                                        <fmt:message key="children.capacity" bundle="${localeBundle}"/>: 0
                                        <fmt:message key="to" bundle="${localeBundle}"/> ${roomSorted.childrenCapacity}
                                    </h5>
                                    <div class="per-night">
                                        <span>$${roomSorted.price}<span> /
                                             <fmt:message key="night" bundle="${localeBundle}"/>
                                            </span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="row justify-content-center">
                    <div class="room-btn pt-70">
                        <a href="index.command" class="btn view-btn1">
                            <fmt:message key="go.home" bundle="${localeBundle}"/>
                              <i class="ti-angle-right"></i> </a>
                    </div>
                </div>
            </section>
            <!-- Room End -->

        </main>
    </jsp:body>
</t:page>