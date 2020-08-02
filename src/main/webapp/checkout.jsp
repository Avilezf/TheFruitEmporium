<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Checkout - TheFruitEmporium</title>
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
                        <p class="breadcrumbs"><span class="mr-2"><a href="${pageContext.request.contextPath}/ServletControlador?accion=home">Home</a></span> <span>Checkout</span></p>
                        <h1 class="mb-0 bread">Checkout</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section"> 
            <form action="${pageContext.request.contextPath}/PaidController?accion=buy" class="billing-form" method="POST"> 
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 ftco-animate">

                            <h3 class="mb-4 billing-heading">Detalles de Pago: Pedido ${idpedido}</h3>
                            <div class="row align-items-end">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="firstname">Nombres</label>
                                        <input name="nombre" id="nombre" type="text" class="form-control" value= "${usuario.getNombre()}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="lastname">Apellidos</label>
                                        <input name="apellido" id="apellido" type="text" class="form-control" value= "${usuario.getApellido()}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="country">País</label>
                                        <input name="pais" id="pais"  type="text" class="form-control" value= "${usuario.getPais()}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="streetaddress">Dirección</label>
                                        <input name="direccion" id="direccion" type="text" class="form-control" value= "${usuario.getDireccion()}" placeholder="Ej: Cra 50 # 80 - 123" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input name="add" id="add" type="text" class="form-control" value= "${usuario.getAdd()}" placeholder="Ej: Apto 101, Casa 128, Conjunto Residencial" required>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="towncity">Ciudad</label>
                                        <input name="ciudad" id="ciudad"  type="text" class="form-control" value= "${usuario.getCiudad()}" placeholder="" required>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="id">Cédula</label>
                                        <input name="cedula" id="cedula" type="text" class="form-control" placeholder="" required>
                                    </div>
                                </div>

                                <div class="w-100"></div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="phone">Celular</label>
                                        <input name="celular" id="celular" type="text" class="form-control" value= "${usuario.getCelular()}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="emailaddress">Correo Electrónico</label>
                                        <input name="email" id="email" type="text" class="form-control" value= "${usuario.getEmail()}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-12">
                                    <!--
                                    <div class="form-group mt-4">
                                        <div class="radio">
                                            <label class="mr-3"><input type="radio" name="optradio"> Create an Account? </label>
                                            <label><input type="radio" name="optradio"> Ship to different address</label>
                                        </div>
                                    </div>
                                    -->    
                                    <div class="checkbox">
                                        <label><input name="yes" id="yes" type="checkbox" value="1" class="mr-2" required>Deseo actualizar mis datos</label>
                                    </div>                                 
                                </div>
                            </div>
                            <!-- END -->
                        </div>
                        <div class="col-xl-5">
                            <div class="row mt-5 pt-3">
                                <div class="col-md-12 d-flex mb-5">
                                    <div class="cart-detail cart-total p-3 p-md-4">
                                        <h3 class="billing-heading mb-4">Total en Compra</h3>
                                        <p class="d-flex">
                                            <span>Subtotal</span>
                                            <span>&#36; ${subtotal}</span>
                                        </p>

                                        <p class="d-flex">
                                            <span>Envío</span>
                                            <span>$ &nbsp; 4000</span>
                                        </p>
                                        <!--
                                        <p class="d-flex">
                                            <span>Discount</span>
                                            <span>$3.00</span>
                                        </p>
                                        -->
                                        <hr>
                                        <p class="d-flex total-price">
                                            <span>Total</span>
                                            <span>&#36; ${total}</span>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="cart-detail p-3 p-md-4">
                                        <h3 class="billing-heading mb-4">Método de Pago</h3>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="radio">
                                                    <label><input name="radio" id="radio" type="radio" name="optradio" value="0" class="mr-2"> Transacción</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="radio">
                                                    <label><input name="radio" id="radio" type="radio" name="optradio" value="1" class="mr-2"> Pago por QR</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="radio">
                                                    <label><input name="radio" id="radio" type="radio" name="optradio" value="2" class="mr-2"> Nequi</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="checkbox">
                                                    <label><input name="tyc" id="tyc" type="checkbox" value="1" class="mr-2" required="">he leído y acepto los términos y condiciones</label>
                                                </div>
                                            </div>
                                        </div>
                                        <input name="subtotal" id="subtotal" type="hidden" value="${subtotal}">
                                        <input name="total" id="total" type="hidden" value="${total}">
                                        <input name="idPedido" id="idPedido" type="hidden" value="${idpedido}">
                                        <p><button type="submit" class="btn btn-primary py-3 px-4">Pagar</button></p>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- .col-md-8 -->
                    </div>
                </div>
            </form> <!---- POST----->
        </section>



        <!-- SUSCRIPCIÓN -->
        <%@include file="/Generals/subscribe.jsp" %>

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