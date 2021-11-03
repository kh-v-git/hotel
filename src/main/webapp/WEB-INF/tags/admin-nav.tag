<%@tag pageEncoding="UTF-8" %>
<%@attribute name="css" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                                        <fmt:message key="admin.home" bundle="${localeBundle}"/>
                                    </a></li>
                                    <li><a href="#">
                                        <fmt:message key="users" bundle="${localeBundle}"/>
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