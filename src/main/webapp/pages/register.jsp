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
                                        <fmt:message key="register.page.title" bundle="${localeBundle}"/>
                                    </span>
                                    <h2><fmt:message key="register" bundle="${localeBundle}"/></h2>
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
                            <form action="register.command" method="post">

                                <!--First Name start-->
                                <div class="mt-10">
                                    <input type="text" name="user-first-name" placeholder="First Name"
                                           pattern="^[A-Za-z\u0400-\u04ff]{1,32}$"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = 'First Name'"
                                           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have max 32 letters' : '');"
                                           required
                                           class="single-input">
                                </div>
                                <!--First Name end-->

                                <!--Last Name start-->
                                <div class="mt-10">
                                    <input type="text" name="user-last-name" placeholder="Last Name"
                                           pattern="^[A-Za-z\u0400-\u04ff]{1,32}$"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = 'Last Name'"
                                           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have max 32 letters' : '');"
                                           required
                                           class="single-input">
                                </div>
                                <!--Last Name end-->

                                <!--Phone start-->
                                <div class="mt-10">
                                    <input type="text" name="user-phone" placeholder="380959956781"
                                           pattern="^[0-9]{12}$"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = '380959956781'"
                                           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have 12 digits' : '');"
                                           required
                                           class="single-input">
                                </div>
                                <!--Phone end-->

                                <!--Email start-->
                                <div class="mt-10">
                                    <input type="text" name="user-email" placeholder="Email"
                                           pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'" required
                                           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must be valid email' : '');"
                                           class="single-input">
                                </div>
                                <!--Email end-->

                                <!--Pass one start-->
                                <div class="mt-10">
                                    <input type="password" name="user-password" id="password"
                                           required
                                           pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
                                           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 8 characters, capital, number' : '');
                        if(this.checkValidity()) form.user_repeat_password.pattern = this.value;"
                                           placeholder="Password min 8 characters, capital, number"
                                           class="single-input">

                                </div>
                                <!--Pass one end-->

                                <!--Pass two start-->
                                <div class="mt-10">
                                    <input type="password" name="user-password-repeat" id="user_repeat_password"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'"
                                           required
                                           pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
                                           onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');
                        if(this.checkValidity()) form.user_repeat_password.pattern = this.value;"
                                           placeholder="Repeat password"
                                           class="single-input">
                                </div>
                                <!--Pass two end-->

                                <!--About start-->
                                <div class="mt-10">
                                    <input type="text" name="user-about" placeholder="About"
                                           pattern="^[A-Za-z\u0400-\u04ff]{1,32}$"
                                           onfocus="this.placeholder = ''" onblur="this.placeholder = 'About yourself'"
                                           class="single-input">
                                </div>
                                <!--About end-->

                                <button class="btn btn-lg btn-primary btn-block" type="submit">
                                    <fmt:message key="register" bundle="${localeBundle}"/>
                                </button>
                                <a href="login.command">
                                    <fmt:message key="login" bundle="${localeBundle}"/>
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