<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Editar/Agregar Producto - TheFruitEmporium</title>
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

        <style>
            .box.has-advanced-upload {
                background-color: white;
                outline: 2px dashed black;
                outline-offset: -10px;
            }
            .box.has-advanced-upload .box__dragndrop {
                display: inline;
            }
            .box.is-dragover {
                background-color: grey;
            }
            .box.has-advanced-upload {
                background-color: white;
                outline: 2px dashed black;
                outline-offset: -10px;
            }
            .box.has-advanced-upload .box__dragndrop {
                display: inline;
            }
        </style>

        <script>
            if (isAdvancedUpload) {
                e.preventDefault();

                var ajaxData = new FormData($form.get(0));

                if (droppedFiles) {
                    $.each(droppedFiles, function (i, file) {
                        ajaxData.append($input.attr('name'), file);
                    });
                }

                $.ajax({
                    url: $form.attr('action'),
                    type: $form.attr('method'),
                    data: ajaxData,
                    dataType: 'json',
                    cache: false,
                    contentType: false,
                    processData: false,
                    complete: function () {
                        $form.removeClass('is-uploading');
                    },
                    success: function (data) {
                        $form.addClass(data.success == true ? 'is-success' : 'is-error');
                        if (!data.success)
                            $errorMsg.text(data.error);
                    },
                    error: function () {
                        // Log the error, show an alert, whatever works for you
                    }
                });
            }
        </script>
    </head>
    <body class="goto-here">
        <%@include file="/Generals/upper.jsp"%>
        <%@include file="/Generals/nav2.jsp" %>
        <!-- END nav -->

        <div class="hero-wrap hero-bread" style="background-image: url('images/car.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="${pageContext.request.contextPath}/ServletControlador?accion=home">Home</a></span> <span>Checkout</span></p>
                        <h1 class="mb-0 bread">Agregar o Editar Producto</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section"> 
            <form action="${pageContext.request.contextPath}/PaidController?accion=agregar" class="billing-form" method="POST" enctype="multipart/form-data"> 
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 ftco-animate">

                            <div class="row align-items-end">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Nombre</label>
                                        <input name="nombre" id="nombre" type="text" class="form-control" value= "${producto.nombre}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="price">Precio</label>
                                        <input name="precio" id="precio" type="text" class="form-control" value= "${producto.precio}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="quantity">Cantidad</label>
                                        <input name="cantidad" id="cantidad"  type="text" class="form-control" value= "${producto.cantidad}" placeholder="" required>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="category">Categoria</label>
                                        <input name="categoria" id="categoria"  type="text" class="form-control" value= "${producto.categoria}" placeholder="1: Vegetales 2:Frutas 3:Pulpa 4:Secos" required>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <!--
                                    <div class="form-group mt-4">
                                        <div class="radio">
                                            <label class="mr-3"><input type="radio" name="optradio"> Create an Account? </label>
                                            <label><input type="radio" name="optradio"> Ship to different address</label>
                                        </div>
                                    </div>
                                    -->                                   
                                </div>
                            </div>
                            <!-- END -->
                        </div>
                        <div class="col-xl-5">
                            <div class="row mt-5 pt-3">
                                <div class="col-md-12">
                                    <div class="cart-detail p-3 p-md-4">
                                        <h5>Agregar Imagen</h5>
                                        <br>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="img">Direccion Imagen</label>
                                                <input name="img" id="img"  type="text" class="form-control" value= "${producto.img}" placeholder="" required>
                                            </div>
                                        </div>
                                        <input name="id" id="id"  type="hidden" class="form-control" value= "${producto.ide}" placeholder="">
                                        <div class="box__input">
                                            <input class="box__file" type="file" name="files[]" id="file" data-multiple-caption="{count} files selected" multiple />
                                            <label for="file"><strong>Choose a file</strong><span class="box__dragndrop"> or drag it here</span>.</label>
                                        </div>
                                        <br>
                                        <p><button type="submit" class="btn btn-primary py-3 px-4">Editar/Agregar</button></p>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- .col-md-8 -->
                    </div>
                </div>
            </form> <!---- POST----->
            <!--
            <form method="POST" enctype="multipart/form-data" action="fup.cgi">
                File to upload: <input type="file" name="upfile"><br/>
                Notes about the file: <input type="text" name="note"><br/>
                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>
        </section>



            <!-- SUSCRIPCIÓN -->

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