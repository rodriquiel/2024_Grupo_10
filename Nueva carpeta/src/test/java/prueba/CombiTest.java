package prueba;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import util.Constantes;

class CombiTest {

	@Test
	public void testGetPuntajePedido1() {
		Combi combi = new Combi("ABC123",6,true);
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 6, true, true, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 10*pedido.getCantidadPasajeros() + 100;
		assertEquals(puntajeEsperado,combi.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);		
	}

	@Test
	public void testGetPuntajePedido2() {
		Combi combi = new Combi("ABC123",6,true);
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 20, true, true, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",combi.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido3() {
		Combi combi = new Combi("ABC123",6,true);
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 6, false, false, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 10*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,combi.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);		
	}
}
