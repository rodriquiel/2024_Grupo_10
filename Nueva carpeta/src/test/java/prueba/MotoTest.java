package prueba;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import util.Constantes;

class MotoTest {

	Moto moto;
	Cliente cliente;	
	
	@Before
	public void setUp() {
		this.moto = new Moto("ABC123");
		this.cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		
	}
	
	@Test
	public void testGetPuntajePedido1() {
		Pedido pedido = new Pedido(this.cliente, 1, false, false, 8, Constantes.ZONA_STANDARD);
		
		int puntajeEsperado = 1000;
		assertEquals(puntajeEsperado,this.moto.getPuntajePedido(pedido),"El puntaje debe ser " + puntajeEsperado);		
	}

	@Test
	public void testGetPuntajePedido2() {
		Pedido pedido = new Pedido(this.cliente, 10, false, false, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",this.moto.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido3() {
		Pedido pedido = new Pedido(this.cliente, 1, true, false, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",this.moto.getPuntajePedido(pedido));
	}
	
	@Test
	public void testGetPuntajePedido4() {
		Pedido pedido = new Pedido(this.cliente, 1, false, true, 8, Constantes.ZONA_STANDARD);
		
		assertNull("El puntaje debe ser null",this.moto.getPuntajePedido(pedido));
	}
}
