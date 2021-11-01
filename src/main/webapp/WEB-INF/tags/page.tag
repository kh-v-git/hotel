<%@tag pageEncoding="UTF-8" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" required="true" type="java.lang.String" %>
<%@attribute name="skipHeader" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<!doctype html>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" type="image/png" href="assets/img/favicon/favicon.png">

    <!-- CSS here -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/css/gijgo.css">
    <link rel="stylesheet" href="assets/css/slicknav.css">
    <link rel="stylesheet" href="assets/css/animate.min.css">
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="assets/css/themify-icons.css">
    <link rel="stylesheet" href="assets/css/slick.css">
    <link rel="stylesheet" href="assets/css/nice-select.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/responsive.css">
    <jsp:invoke fragment="css"/>
    <title>${title}</title>
</head>

<body>
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

<c:if test="${!skipHeader}">
    <c:if test="${not empty errorPage}">
        <div class="genric-btn danger radius">
                ${errorPage}
        </div>
    </c:if>
    <c:if test="${not empty errorCommand}">
        <div class="genric-btn danger radius">
                ${errorCommand}
        </div>
    </c:if>
    <c:if test="${not empty statusCommand}">
        <div class="genric-btn danger radius">
                ${statusCommand}
        </div>
    </c:if>
    <c:if test="${not empty statusPage}">
        <div class="genric-btn danger radius">
                ${statusPage}
        </div>
    </c:if>
    <jsp:invoke fragment="header"/>
</c:if>
<jsp:doBody/>
<jsp:invoke fragment="footer"/>

<footer>
    <!-- Footer Start-->
    <div class="footer-area black-bg footer-padding">
        <div class="container">
            <div class="row d-flex justify-content-between">
                <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                    <div class="single-footer-caption mb-30">
                        <!-- logo -->
                        <div class="footer-logo">
                            <a href="index.html"><img src="assets/img/logo/logo2_footer.png" alt=""></a>
                        </div>
                        <div class="footer-pera">
                            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                                All rights reserved | This template is made with <i class="ti-heart"
                                                                                    aria-hidden="true"></i> by <a
                                        href="https://colorlib.com" target="_blank">Colorlib</a>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-lg-3 col-md-3 col-sm-5">
                    <div class="single-footer-caption mb-30">
                        <div class="footer-tittle">
                            <h4>
                                <fmt:message key="quick.links" bundle="${localeBundle}"/>
                            </h4>
                            <ul>
                                <li><a href="#">
                                    <fmt:message key="about" bundle="${localeBundle}"/>
                                    </a>
                                </li>
                                <li><a href="#">
                                    <fmt:message key="rooms" bundle="${localeBundle}"/>
                                    </a>
                                </li>
                                <li><a href="#">
                                    <fmt:message key="request.now" bundle="${localeBundle}"/>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3">
                    <div class="single-footer-caption mb-30">
                        <div class="footer-tittle">
                            <h4>
                                <fmt:message key="reservation.contacts" bundle="${localeBundle}"/>
                            </h4>
                            <ul>
                                <li><a href="tel:3455667889">Tel: 345 5667 889</a></li>
                                <li><a href="mailto:reservations@epamhotel.com">reservations@epamhotel.com</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-lg-3 col-md-4  col-sm-5">
                    <div class="single-footer-caption mb-30">
                        <div class="footer-tittle">
                            <h4>
                                <fmt:message key="our.location" bundle="${localeBundle}"/>
                            </h4>
                            <ul>
                                <li><a href="https://goo.gl/maps/5cLYvG3hdZteWWbF6">
                                    <fmt:message key="our.address" bundle="${localeBundle}"/>
                                   </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End-->
</footer>

<!-- JS here -->

<!-- All JS Custom Plugins Link Here here -->
<script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>

<!-- Jquery, Popper, Bootstrap -->
<script src="./assets/js/vendor/jquery-1.12.4.min.js"></script>
<script src="./assets/js/popper.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
<!-- Jquery Mobile Menu -->
<script src="./assets/js/jquery.slicknav.min.js"></script>

<!-- Jquery Slick , Owl-Carousel Plugins -->
<script src="./assets/js/owl.carousel.min.js"></script>
<script src="./assets/js/slick.min.js"></script>
<!-- Date Picker -->
<script src="./assets/js/gijgo.min.js"></script>
<!-- One Page, Animated-HeadLin -->
<script src="./assets/js/wow.min.js"></script>
<script src="./assets/js/animated.headline.js"></script>
<script src="./assets/js/jquery.magnific-popup.js"></script>

<!-- Scrollup, nice-select, sticky -->
<script src="./assets/js/jquery.scrollUp.min.js"></script>
<script src="./assets/js/jquery.nice-select.min.js"></script>
<script src="./assets/js/jquery.sticky.js"></script>

<!-- contact js -->
<script src="./assets/js/contact.js"></script>
<script src="./assets/js/jquery.form.js"></script>
<script src="./assets/js/jquery.validate.min.js"></script>
<script src="./assets/js/mail-script.js"></script>
<script src="./assets/js/jquery.ajaxchimp.min.js"></script>

<!-- Jquery Plugins, main Jquery -->
<script src="./assets/js/plugins.js"></script>
<script src="./assets/js/main.js"></script>

</body>
</html>
