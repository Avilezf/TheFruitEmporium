<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Pedidos - TheFruitEmporium</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">


        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body class="goto-here">
        <%@include file="/Generals/upper.jsp"%>
        <%@include file="/Generals/nav.jsp" %>
        <!-- END nav -->

        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="home.html">Inicio</a></span> <span>Carrito</span></p>
                        <h1 class="mb-0 bread">Mi Carrito</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section ftco-cart">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 ftco-animate">
                        <div class="cart-list">
                            <table class="table">
                                <thead class="thead-primary">
                                    <tr class="text-center">
                                        <th>Pedido N°&nbsp;${idPedido}</th>
                                        <th>Nombre del Producto</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="i" begin="0" end="${size}" step="1">
                                        <tr class="text-center">

                                            <td class="image-prod"><div class="img" style="background-image:url(${productos.get(i).img});"></div></td>

                                            <td class="product-name">
                                                <h3>${productos.get(i).nombre}</h3><!-------------------------->                                            
                                            </td>

                                            <td class="price">&#36; ${productos.get(i).precio}</td>

                                            <td class="quantity">
                                                <div class="input-group mb-3">
                                                    <input type="text" name="quantity" class="quantity form-control input-number" value="${cantidad.get(i)}" min="1" max="100">
                                                </div>
                                            </td>

                                            <td class="total">&#36; ${precios.get(i)}</td>
                                        </tr><!-- END TR-->
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 mt-4 cart-wrap ftco-animate">
                    <h6>Observaciones</h6>
                    <div class="cart-total mb-3">
                        <div class="form-group">
                            <label type="text" name = "descripcion" id = "descripcion">${descripcion}</label>
                        </div>
                    </div>
                    <div class="cart-total mb-3">
                        <h3>Total del Carrito</h3>
                        <p class="d-flex">
                            <span>Subtotal</span>
                            <span>&#36; ${pedido.subtotal}</span>
                        </p>
                        <p class="d-flex">
                            <span>Enví­o</span>
                            <span>$ &nbsp; 4000</span>
                        </p>
                        <!--
                        <p class="d-flex">
                            <span>Descuento</span>
                            <span>$3.00</span>
                        </p>
                        -->
                        <hr>
                        <p class="d-flex total-price">
                            <span>Total</span>
                            <span>&#36; ${pedido.total}</span>
                        </p>
                    </div>

                    <form action="${pageContext.request.contextPath}/AdminController?accion=ok" method="POST">
                        <input type="hidden" name="pedido" id="pedido" value="${idPedido}">
                        <button id="luis" type="submit" class="btn btn-primary py-3 px-4">VALIDAR</button>
                    </form>
                    <br>
                    <form action="${pageContext.request.contextPath}/AdminController?accion=no" method="POST">
                        <input type="hidden" name="pedido" id="pedido" value="${idPedido}">
                        <button type="submit" class="btn btn-danger py-3 px-4">NO VALIDAR</button>
                    </form>

                </div>
            </div>
        </section>

        <!-- SUSCRIPCIÓN -->
        <!--%@include file="/Generals/subscribe.jsp" %-->

        <!-- Footer -->
        <%@include file="/Generals/footer.jsp" %>



        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>

        <script>
            $(document).ready(function () {

                var quantitiy = 0;
                $('.quantity-right-plus').click(function (e) {

                    // Stop acting like a button
                    e.preventDefault();
                    // Get the field name
                    var quantity = parseInt($('#quantity').val());

                    // If is not undefined

                    $('#quantity').val(quantity + 1);


                    // Increment

                });

                $('.quantity-left-minus').click(function (e) {
                    // Stop acting like a button
                    e.preventDefault();
                    // Get the field name
                    var quantity = parseInt($('#quantity').val());

                    // If is not undefined

                    // Increment
                    if (quantity > 0) {
                        $('#quantity').val(quantity - 1);
                    }
                });

            });
        </script>

    </body>
</html>