<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="User Page | Main">

    <jsp:body>
        <main>

            <!-- slider Area Start-->
            <div class="slider-area ">
                <!-- Mobile Menu -->
                <div class="slider-active dot-style">
                    <div class="single-slider  hero-overly slider-height d-flex align-items-center"
                         data-background="assets/img/hero/h1_hero.jpg">
                        <div class="container">
                            <div class="row justify-content-center text-center">
                                <div class="col-xl-9">
                                    <div class="h1-slider-caption">
                                        <h1 data-animation="fadeInUp" data-delay=".4s">top hotel in the city</h1>
                                        <h3 data-animation="fadeInDown" data-delay=".4s">Hotel & Resourt</h3>
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
                                        <h1 data-animation="fadeInUp" data-delay=".4s">top hotel in the city</h1>
                                        <h3 data-animation="fadeInDown" data-delay=".4s">Hotel & Resourt</h3>
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
                                        <h1 data-animation="fadeInUp" data-delay=".4s">top hotel in the city</h1>
                                        <h3 data-animation="fadeInDown" data-delay=".4s">Hotel & Resourt</h3>
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
                            <form action="">
                                <div class="booking-wrap d-flex justify-content-between align-items-center">

                                    <!-- select in date -->
                                    <div class="single-select-box mb-30">
                                        <!-- select out date -->
                                        <div class="boking-tittle">
                                            <span> Check In Date:</span>
                                        </div>
                                        <div class="boking-datepicker">
                                            <input id="datepicker1" placeholder="10/12/2020"/>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <!-- select out date -->
                                        <div class="boking-tittle">
                                            <span>Check OutDate:</span>
                                        </div>
                                        <div class="boking-datepicker">
                                            <input id="datepicker2" placeholder="12/12/2020"/>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>Adults:</span>
                                        </div>
                                        <div class="select-this">
                                            <form action="#">
                                                <div class="select-itms">
                                                    <select name="select" id="select1">
                                                        <option value="">1</option>
                                                        <option value="">2</option>
                                                        <option value="">3</option>
                                                        <option value="">4</option>
                                                    </select>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>Children:</span>
                                        </div>
                                        <div class="select-this">
                                            <form action="#">
                                                <div class="select-itms">
                                                    <select name="select" id="select2">
                                                        <option value="">1</option>
                                                        <option value="">2</option>
                                                        <option value="">3</option>
                                                        <option value="">4</option>
                                                    </select>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box mb-30">
                                        <div class="boking-tittle">
                                            <span>BadSize:</span>
                                        </div>
                                        <div class="select-this">
                                            <form action="#">
                                                <div class="select-itms">
                                                    <select name="select" id="select3">
                                                        <option value="">King</option>
                                                        <option value="">Queen</option>
                                                        <option value="">Twin</option>
                                                        <option value="">Double</option>
                                                        <option value="">Cot</option>
                                                    </select>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Single Select Box -->
                                    <div class="single-select-box pt-45 mb-30">
                                        <a href="#" class="btn select-btn">Book Now</a>
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
                                    <h3>Our Rooms</h3>
                                </div>
                                <h3 class="archivment-back">Our Rooms</h3>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-4 col-lg-6 col-md-6">
                            <!-- Single Room -->
                            <div class="single-room mb-50">
                                <div class="room-img">
                                    <a href="rooms.html"><img src="assets/img/rooms/room1.jpg" alt=""></a>
                                </div>
                                <div class="room-caption">
                                    <h3><a href="rooms.html">Classic Double Bed</a></h3>
                                    <div class="per-night">
                                        <span><u>$</u>150 <span>/ par night</span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6">
                            <!-- Single Room -->
                            <div class="single-room mb-50">
                                <div class="room-img">
                                    <a href="rooms.html"><img src="assets/img/rooms/room2.jpg" alt=""></a>
                                </div>
                                <div class="room-caption">
                                    <h3><a href="rooms.html">Classic Double Bed</a></h3>
                                    <div class="per-night">
                                        <span><u>$</u>150 <span>/ par night</span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6">
                            <!-- Single Room -->
                            <div class="single-room mb-50">
                                <div class="room-img">
                                    <a href="rooms.html"> <img src="assets/img/rooms/room3.jpg" alt=""></a>
                                </div>
                                <div class="room-caption">
                                    <h3><a href="rooms.html">Classic Double Bed</a></h3>
                                    <div class="per-night">
                                        <span><u>$</u>150 <span>/ par night</span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6">
                            <!-- Single Room -->
                            <div class="single-room mb-50">
                                <div class="room-img">
                                    <a href="rooms.html"><img src="assets/img/rooms/room4.jpg" alt=""></a>
                                </div>
                                <div class="room-caption">
                                    <h3><a href="rooms.html">Classic Double Bed</a></h3>
                                    <div class="per-night">
                                        <span><u>$</u>150 <span>/ par night</span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6">
                            <!-- Single Room -->
                            <div class="single-room mb-50">
                                <div class="room-img">
                                    <a href="rooms.html"><img src="assets/img/rooms/room5.jpg" alt=""></a>
                                </div>
                                <div class="room-caption">
                                    <h3><a href="rooms.html">Classic Double Bed</a></h3>
                                    <div class="per-night">
                                        <span><u>$</u>150 <span>/ par night</span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6">
                            <!-- Single Room -->
                            <div class="single-room mb-50">
                                <div class="room-img">
                                    <a href="rooms.html"> <img src="assets/img/rooms/room6.jpg" alt=""></a>
                                </div>
                                <div class="room-caption">
                                    <h3><a href="rooms.html">Classic Double Bed</a></h3>
                                    <div class="per-night">
                                        <span><u>$</u>150 <span>/ par night</span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="room-btn pt-70">
                            <a href="#" class="btn view-btn1">View more <em class="ti-angle-right"></em> </a>
                        </div>
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

