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

	Auto auto;
	
	@Before
	public void setUp() {
		this.auto = new Auto("ABC123",2,true); 
	}
	
	@Test
	public void testConstructorAuto() {
		assertNotNull(this.auto,"El auto debe ser distinto de null");
	}
	
	@Test
	public void testGetPatente() {
		String patenteEsperada = "ABC123";
		assertEquals(patenteEsperada,this.auto.getPatente(),"La patente debe ser 'ABC123'");
	}
	
	@Test
	public void testGetCantidadPlazas() {
		int cantEsperada = 2;
		assertEquals(cantEsperada,this.auto.getCantidadPlazas(),"La cantidad de plazas debe ser " + cantEsperada);
	}

	@Test
	public void testIsMascota() {
		assertTrue(this.auto.isMascota());
	}
	
	@Test
	public void testGetPuntajePedido1() {
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 3, true, true, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 40*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,this.auto.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);
		
	}
	
	@Test
	public void testGetPuntajePedido2() {
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 6, false, false, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",this.auto.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido3() {
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 3, false, false, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 30*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,this.auto.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);	
	}	
	
}

