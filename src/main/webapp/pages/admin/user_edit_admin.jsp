<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="admin.user.edit" bundle="${localeBundle}" var="pageLoginTitle" scope="page"/>
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
                                    <h2><fmt:message key="admin.user.edit" bundle="${localeBundle}"/></h2>
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
                                    <fmt:message key="user" bundle="${localeBundle}"/>
                                ${user.firstName}
                                    <fmt:message key="page" bundle="${localeBundle}"/>
                                   </h3>
                                <form method="post">
                                    <!--First Name start-->
                                    <div class="mt-10">
                                        <label for="user-firs-name">
                                            <fmt:message key="user.first.name" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="text" name="user-first-name" id="user-firs-name" placeholder="First Name"
                                               value="${user.firstName}"
                                               pattern="^[A-Za-z\u0400-\u04ff]{1,32}$"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'First Name'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have max 32 letters' : '');"
                                               class="single-input">
                                    </div>
                                    <!--First Name end-->

                                    <!--Last Name start-->
                                    <div class="mt-10">
                                        <label for="user-last-name">
                                            <fmt:message key="user.last.name" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="text" name="user-last-name" id="user-last-name" placeholder="Last Name"
                                               value="${user.lastName}"
                                               pattern="^[A-Za-z\u0400-\u04ff]{1,32}$"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'Last Name'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have max 32 letters' : '');"
                                               class="single-input">
                                    </div>
                                    <!--Last Name end-->

                                    <!--Phone start-->
                                    <div class="mt-10">
                                        <label for="user-phone">
                                            <fmt:message key="user.phone" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="text" name="user-phone" id="user-phone" placeholder="380959956781"
                                               value="${user.phone}"
                                               pattern="^[0-9]{12}$"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = '380959956781'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have 12 digits' : '');"
                                               class="single-input">
                                    </div>
                                    <!--Phone end-->

                                    <!--Email start-->
                                    <div class="mt-10">
                                        <label for="user-email">
                                            <fmt:message key="user.email" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="text" name="user-email" id="user-email" placeholder="Email"
                                               value="${user.email}"
                                               pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'"
                                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must be valid email' : '');"
                                               class="single-input">
                                    </div>
                                    <!--Email end-->

                                    <!--User role start-->
                                    <div class="input-group-icon mt-10">
                                        <label for="user-role">
                                            <fmt:message key="user.role" bundle="${localeBundle}"/>
                                        </label>
                                        <div class="form-select">
                                            <select name="user-role" id="user-role" required>
                                                <c:forEach items="${userRoleList}" var="role">
                                                    <c:choose>
                                                        <c:when test="${role eq user.role}">
                                                            <option value="${role}" selected>
                                                                <c:out value="${role}"/>
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${role}">
                                                                <c:out value="${role}"/>
                                                            </option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <!--User status start-->
                                    <div class="input-group-icon mt-10">
                                        <div class="form-select" id="default-select">
                                            <label for="user-status">
                                                <fmt:message key="user.status" bundle="${localeBundle}"/>
                                            </label>
                                            <select name="user-status" id="user-status" required>
                                                <c:forEach items="${userStatusList}" var="status">
                                                    <c:choose>
                                                        <c:when test="${status eq user.status}">
                                                            <option value="${status}" selected>
                                                                <c:out value="${status}"/>
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${status}">
                                                                <c:out value="${status}"/>
                                                            </option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <!--User status end-->

                                    <!--About start-->
                                    <div class="mt-10">
                                        <label for="user-about">
                                            <fmt:message key="user.about" bundle="${localeBundle}"/>
                                        </label>
                                        <input type="text" name="user-about" id="user-about" placeholder="About"
                                               value="${user.about}"
                                               pattern="^(\p{L}+[\s]*)+$"
                                               onfocus="this.placeholder = ''" onblur="this.placeholder = 'About yourself'"
                                               class="single-input">
                                    </div>
                                    <!--About end-->

                                    <button class="btn btn-lg btn-primary btn-block" type="submit"
                                            formaction="user-update-admin.command?user-id=${user.userID}">
                                        <fmt:message key="update" bundle="${localeBundle}"/>
                                    </button>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit" formnovalidate
                                            formaction="user-admin-delete.command?user-id=${user.userID}">
                                        <fmt:message key="delete" bundle="${localeBundle}"/>
                                    </button>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit" formnovalidate
                                            formaction="secured-admin.command">
                                        <fmt:message key="cancel" bundle="${localeBundle}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--User edit End -->

        </main>
    </jsp:body>
</t:page>