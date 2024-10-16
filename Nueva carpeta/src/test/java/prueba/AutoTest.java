package prueba;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

class AutoTest {

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testConstructorAuto() {
		Auto auto = new Auto("ABC123",2,true);
		assertNotNull(auto,"El auto debe ser distinto de null");
	}
	
	@Test
	public void testGetPatente() {
		Auto auto = new Auto("ABC123",2,true);
		String patenteEsperada = "ABC123";
		assertEquals(patenteEsperada,auto.getPatente(),"La patente debe ser 'ABC123'");
	}
	
	@Test
	public void testGetCantidadPlazas() {
		Auto auto = new Auto("ABC123",2,true);
		int cantEsperada = 2;
		assertEquals(cantEsperada,auto.getCantidadPlazas(),"La cantidad de plazas debe ser " + cantEsperada);
	}

	@Test
	public void testIsMascota() {
		Auto auto = new Auto("ABC123",2,true);
		assertTrue(auto.isMascota());
	}
	
	@Test
	public void testGetPuntajePedido1() {
		Auto auto = new Auto("ABC123",2,true);
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 3, true, true, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 40*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,auto.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);
		
	}
	
	@Test
	public void testGetPuntajePedido2() {
		Auto auto = new Auto("ABC123",2,true);
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 6, false, false, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",auto.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido3() {
		Auto auto = new Auto("ABC123",2,true);
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 3, false, false, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 30*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,auto.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);	
	}	
	
}
