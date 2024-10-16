package prueba;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import util.Constantes;

class MotoTest {

	@Test
	public void testGetPuntajePedido1() {
		Moto moto = new Moto("ABC123");
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 1, false, false, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 1000;
		assertEquals(puntajeEsperado,moto.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);		
	}

	@Test
	public void testGetPuntajePedido2() {
		Moto moto = new Moto("ABC123");
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 10, false, false, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",moto.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido3() {
		Moto moto = new Moto("ABC123");
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 1, true, false, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",moto.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido4() {
		Moto moto = new Moto("ABC123");
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 1, false, true, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",moto.getPuntajePedido(pedido));
	}
}
