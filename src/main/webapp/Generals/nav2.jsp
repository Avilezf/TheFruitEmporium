<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/ServletControlador?accion=home">The Fruit Emporium</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menú
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="${pageContext.request.contextPath}/AdminController?accion=pedidos" class="nav-link">Pedido</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/AdminController?accion=envio" class="nav-link">Envio</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/AdminController?accion=buscar" class="nav-link">Registro</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Productos</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/AdminController?accion=addproduct">Agregar Productos</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/AdminController?accion=modproduct">Modificar Productos</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/AdminController?accion=drpproduct">Eliminar Productos</a>
                    </div>
                </li>

            </ul>
        </div>
    </div>
</nav>