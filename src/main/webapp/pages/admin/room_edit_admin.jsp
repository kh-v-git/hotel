<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="room.edit.admin" bundle="${localeBundle}" var="pageLoginTitle" scope="page"/>
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
                                        <fmt:message key="admin" bundle="${localeBundle}"/>
                                    </span>
                                    <h2><fmt:message key="room.edit.admin" bundle="${localeBundle}"/></h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- slider Area End-->

            <!--User edit Start -->
            <div class="whole-wrap">
                <div class="container box_1170">
                    <div class="section-top-border">
                        <div class="row">
                            <div class="col-lg-8 col-md-8">
                                <h3 class="mb-30">
                                    <fmt:message key="room" bundle="${localeBundle}"/>
                                        ${room.number}
                                    <fmt:message key="page" bundle="${localeBundle}"/>
                                </h3>
                                <form method="post">
                                    <!--Room number start-->
                                    <div class="mt-10">
                                        <label for="room-number">
                                            <fmt:message key="room.number" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="number" name="room-number" id="room-number" placeholder="Room Number"
                                               value="${room.number}"
                                               pattern="([0-9]*)"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'Room Number'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must be digit' : '');"
                                               class="single-input"
                                               required>
                                    </div>
                                    <!--Room number end-->

                                    <!--Adult capacity start-->
                                    <div class="mt-10">
                                        <label for="room-adult-capacity">
                                            <fmt:message key="adults.capacity" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="number" name="room-adult-capacity" id="room-adult-capacity" placeholder="Adult Capacity"
                                               value="${room.adultCapacity}"
                                               pattern="([0-9]*)"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'Adult Capacity'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must be digit' : '');"
                                               class="single-input"
                                               required>
                                    </div>
                                    <!--Adult capacity end-->

                                    <!--Children capacity start-->
                                    <div class="mt-10">
                                        <label for="room-children-capacity">
                                            <fmt:message key="children.capacity" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="number" name="room-children-capacity" id="room-children-capacity" placeholder="Children Capacity"
                                               value="${room.childrenCapacity}"
                                               pattern="([0-9]*)"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'Children Capacity'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must be digit' : '');"
                                               class="single-input"
                                               required>
                                    </div>
                                    <!--Children capacity end-->

                                    <!--Price start-->
                                    <div class="mt-10">
                                        <label for="room-price">
                                            <fmt:message key="room.price" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="number" name="room-price" id="room-price" placeholder="Price"
                                               value="${room.price}"
                                               pattern="^([0-9]{1,}\.?[0-9]{2})$"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'Price'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must be valid price' : '');"
                                               class="single-input"
                                               required>
                                    </div>
                                    <!--Price end-->

                                    <!-- Bed Size start-->
                                    <div class="input-group-icon mt-10">
                                        <div class="form-select">
                                            <label for="room-bed-size">
                                                <fmt:message key="bed.size" bundle="${localeBundle}"/>
                                            </label>
                                            <select name="room-bed-size" id="room-bed-size" required>
                                                <c:forEach items="${roomBedSizeList}" var="bedSize">
                                                    <c:choose>
                                                        <c:when test="${bedSize eq room.bedSize}">
                                                            <option value="${bedSize}" selected>
                                                                <c:out value="${bedSize}"/>
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${bedSize}">
                                                                <c:out value="${bedSize}"/>
                                                            </option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- Bed Size end-->


                                    <!--About start-->
                                    <div class="mt-10">
                                        <label for="room-about">
                                            <fmt:message key="about" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="text" name="room-about" id="room-about" placeholder="About"
                                               value="${room.about}"
                                               pattern="^(\p{L}+[\s]*)+$"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'About'"
                                               class="single-input"
                                               required>
                                    </div>
                                    <!--About end-->

                                    <button class="btn btn-lg btn-primary btn-block" type="submit"
                                            formaction="room-update-admin.command?room-id=${room.roomID}">
                                        <fmt:message key="update" bundle="${localeBundle}"/>
                                    </button>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit" formnovalidate
                                            formaction="room-delete-admin.command?room-id=${room.roomID}">
                                        <fmt:message key="delete" bundle="${localeBundle}"/>
                                    </button>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit"
                                            formaction="secured-admin-rooms.command" formnovalidate>
                                        <fmt:message key="cancel" bundle="${localeBundle}"/>
                                    </button>
                                </form>
                            </div>
                            <div class="col-lg-3 col-md-4 mt-sm-30">
                                <div class="single-element-widget">
                                    <h3 class="mb-30">
                                        <fmt:message key="room.image" bundle="${localeBundle}"/>
                                    </h3>
                                </div>
                                <div class="single-element-widget mt-30">
                                    <img src="downloadImage?room-id=${room.roomID}&image-caption=mainView" alt="Room image">
                                </div>
                                <div class="single-element-widget mt-30">
                                    <form method="post" action="uploadImage" enctype="multipart/form-data">
                                        <input type="hidden" name="room-id" value="${room.roomID}">
                                        <input type="hidden" name="image-caption" value="mainView">
                                        <input type="hidden" name="image-id" value="${roomImageId}">
                                        <input type="file" name="file" >
                                        <button type="submit" class="btn btn-lg btn-primary btn-block">
                                            <fmt:message key="image.upload" bundle="${localeBundle}"/>

                                        </button>

                                        <button class="btn btn-lg btn-primary btn-block" type="submit"
                                                formaction="room-image-delete-admin.command" formnovalidate>
                                            <fmt:message key="image.delete" bundle="${localeBundle}"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!--User edit End -->

        </main>
    </jsp:body>
</t:page>