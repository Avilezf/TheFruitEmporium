<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/ServletControlador?accion=home">The Fruit Emporium</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menú
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="home.html" class="nav-link">Inicio</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tienda</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <!--<a class="dropdown-item" href="shop.html">Tienda</a>-->
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=wishlist">Lista de productos</a>
                        <!--<a class="dropdown-item" href="product-single.html">Producto</a>-->
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=cart">Carrito</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=checkout">Checkout</a>
                    </div>
                </li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=about" class="nav-link">Sobre nosotros</a></li>
                <!--<li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li> -->
                <li class="nav-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=contact" class="nav-link">Contacto</a></li>
                <li class="nav-item cta cta-colored"><a href="${pageContext.request.contextPath}/ServletControlador?accion=cart" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

            </ul>
        </div>
    </div>
</nav>