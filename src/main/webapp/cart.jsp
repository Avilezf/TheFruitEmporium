<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Carrito - TheFruitEmporium</title>
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

        <div class="hero-wrap hero-bread" style="background-image: url('images/carrito.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="home.html" style="color: #000">Inicio</a></span> <span style="color: #000">Carrito</span></p>
                        <h1 class="mb-0 bread" style="color: #000">Mi Carrito</h1>
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
                                        <th>Pedido N°</th>
                                        <th>${pedido}</th>
                                        <th>Nombre del Producto</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="i" begin="0" end="${num}" step="1">
                                        <tr class="text-center">
                                            <td class="product-remove"><a href="${pageContext.request.contextPath}/PaidController?accion=delete&idProducto=${productos.get(i).ide}&numPedido=${pedido}"><span class="ion-ios-close"></span></a></td>

                                            <td class="image-prod"><div class="img" style="background-image:url(${productos.get(i).img});"></div></td>

                                            <td class="product-name">
                                                <h3>${productos.get(i).nombre}</h3><!-------------------------->                                            
                                            </td>

                                            <td class="price">&#36; ${productos.get(i).precio}</td>

                                            <td class="quantity">
                                                <div class="input-group mb-3">
                                                    <input type="text" name="quantity" class="quantity form-control input-number" value="${cants.get(i)}" min="1" max="100">
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
                <div class="row justify-content-end">
                    <div class="col-lg-4 mt-5 cart-wrap ftco-animate">
                        <div class="cart-total mb-3">
                            <h3>Cupón</h3>
                            <p>Ingresa el código del cupón, si tienes uno </p>
                            <form action="#" class="info">
                                <div class="form-group">
                                    <label for="">Código de cupón</label>
                                    <input type="text" class="form-control text-left px-3" placeholder="">
                                </div>
                            </form>
                        </div>
                        <p><a href="checkout.html" class="btn btn-primary py-3 px-4">Aplicar cupón</a></p>
                    </div>


                    <div class="col-lg-4 mt-5 cart-wrap ftco-animate">
                        <!--
                        <div class="cart-total mb-3">
                            <h3>Precio  del enví­o</h3>
                            <p>Ingresa el destino del enví­o</p>
                            <form action="#" class="info">
                                <div class="form-group">
                                    <label for="">Country</label>
                                    <input type="text" class="form-control text-left px-3" placeholder="">
                                </div>
                                <div class="form-group">
                                    <label for="country">State/Province</label>
                                    <input type="text" class="form-control text-left px-3" placeholder="">
                                </div>
                                <div class="form-group">
                                    <label for="country">Zip/Postal Code</label>
                                    <input type="text" class="form-control text-left px-3" placeholder="">
                                </div>
                            </form>
                        </div>
                        <p><a href="checkout.html" class="btn btn-primary py-3 px-4">Estimate</a></p>
                        -->
                    </div>

                    <div class="col-lg-4 mt-5 cart-wrap ftco-animate">
                        <div class="cart-total mb-3">
                            <h3>Total del Carrito</h3>
                            <p class="d-flex">
                                <span>Subtotal</span>
                                <span>&#36; ${sum}</span>
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
                                <span>&#36; ${envio}</span>
                            </p>
                        </div>

                        <form action="${pageContext.request.contextPath}/CheckOutController?accion=checkout" method="POST">
                            <h6>Observaciones</h6>
                            <div class="cart-total mb-3">
                                <div class="form-group">
                                    <textarea type="text" name = "descripcion" id = "descripcion" class="text-left form-control" style="border-color: #bbb !important; height: 100px !important;" rows="5" placeholder="- Tomates grandes" ></textarea>
                                </div>
                            </div>

                            <div class="row justify-content-end">
                                <input type="hidden" name="pedido" id="pedido" value="${pedido}">
                                <input type="hidden" name="subtotal" id="subtotal" value="${sum}">
                                <input type="hidden" name="total" id="total" value="${envio}">
                                <input type="hidden" name="usuario" id="usuario" value="${usuario}">
                                <button id="luis" type="submit" class="btn btn-black py-3 px-4">Continuar con el pago</button>
                            </div>
                        </form>

                    </div>
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