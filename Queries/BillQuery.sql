select * from pedidos


select  productos.nombre,
 		productos.precio,
		pedidosproductos.cantproducto,
		pedidosproductos.cantproducto * productos.precio as valproducto,
		pedidos.idpedidos,
		pedidos.subtotal,
		pedidos.total,
		pedidos.yearf,
		pedidos.monthf,
		pedidos.dayf,
		usuarios.nombre,
		usuarios.apellido,
		usuarios.direccion,
		usuarios.celular,
		usuarios.add,
		usuarios.pais,
		usuarios.cedula
 from productos 
 full join
 pedidosproductos on 
 productos.ide = pedidosproductos.idproducto
 full join pedidos on
 pedidosproductos.idpedido = pedidos.idpedidos
 full join usuarios on
 pedidos.idcliente = usuarios.id
 where pedidos.idpedidos = 26
 order by productos.nombre;