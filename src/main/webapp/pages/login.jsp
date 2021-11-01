<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale_data" var="localeBundle"/>

<fmt:message key="login" bundle="${localeBundle}" var="pageLoginTitle" scope="page"/>
<t:page title="${pageLoginTitle}">
    <jsp:body>
        <main>

            <!-- slider Area Start-->
            <div class="slider-area">
                <div class="single-slider hero-overly slider-height2 d-flex align-items-center"
                     data-background="assets/img/hero/contact_hero.jpg">
                    <div class="container">
                        <div class="row ">
                            <div class="col-md-11 offset-xl-1 offset-lg-1 offset-md-1">
                                <div class="hero-caption">
                                    <span>
                                        <fmt:message key="login.page.title" bundle="${localeBundle}"/>
                                    </span>
                                    <h2><fmt:message key="login" bundle="${localeBundle}"/></h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- slider Area End-->

            <!-- Login start-->
            <section class="room-area customar-padding fix">
                <div class="container container-fluid p-0">
                    <div class="row justify-content-center">
                        <div class="col-xl-8">
                            <form action="authenticate.command" method="post">
                                <div class="mt-10">
                                    <input type="email" name="user-email" placeholder="Email"
                                           pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'" required
                                           class="single-input">
                                </div>
                                <div class="mt-10">
                                    <input type="password" name="user-password" placeholder="Password"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'"
                                           required
                                           class="single-input">
                                </div>
                                <button class="btn btn-lg btn-primary btn-block" type="submit">
                                    <fmt:message key="sign.in" bundle="${localeBundle}"/>
                                </button>
                                <a href="register-user-page.command">
                                    <fmt:message key="register" bundle="${localeBundle}"/>
                                </a>
                                <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Login end-->

        </main>
    </jsp:body>
</t:page>