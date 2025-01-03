package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import util.Constantes;

public class CombiTest {

	Combi combi;
	Cliente cliente;
	
	
	@Before
	public void setUp() {
		this.combi = new Combi("ABC123",6,true);
		this.cliente  = new Cliente("Juan95", "1234", "Juan Perez");
	}
	
	@Test
	public void testGetPuntajePedido1() {
		Pedido pedido = new Pedido(this.cliente, 6, true, true, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 10*pedido.getCantidadPasajeros() + 100;
		assertEquals(puntajeEsperado,this.combi.getPuntajePedido(pedido),0.001);		
	}

	@Test
	public void testGetPuntajePedido2() {
		Pedido pedido = new Pedido(this.cliente, 20, true, true, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",this.combi.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido3() {
		Pedido pedido = new Pedido(this.cliente, 6, false, false, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 10*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,this.combi.getPuntajePedido(pedido),0.001);		
	}
}
