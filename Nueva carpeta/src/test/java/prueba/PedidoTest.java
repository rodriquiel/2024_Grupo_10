package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;


public class PedidoTest {

	Cliente cliente1;
	Pedido pedido;
	
	@Before
	public void setUp() {
		cliente1=new Cliente("Juan123","123ABC","Juan Perez");
	}
	
	@Test
	public void testCostructorPedido() {
		pedido=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		assertNotNull("El pedido debería ser distinto de null",pedido);
	}

	@Test
	public void testGetCliente() {
		pedido=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		assertSame("Los pedidos deberian ser iguales",cliente1,pedido.getCliente());
	}
	
	@Test
	public void testIsMascota() {
		pedido=new Pedido(cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		assertTrue("El transporte de mascota deberia ser true",pedido.isMascota());
	}
	
	@Test
	public void testIsBaul() {
		pedido=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		assertFalse("El uso de baul deberia ser false",pedido.isBaul());
	}
	
	@Test
	public void testGetKm() {
		pedido=new Pedido(cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals("EL valor de los km deberias ser 200",200,pedido.getKm());
	}
	
	@Test
	public void testGetZona() {
		pedido=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals(Constantes.ZONA_SIN_ASFALTAR,pedido.getZona(),"La zona deberia ser "+Constantes.ZONA_SIN_ASFALTAR);
	}
	
	@Test
	public void testGetCantidadPasajeros() {
		pedido=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals("La cantidad de pasajeros deberias ser 3",3,pedido.getCantidadPasajeros());
	}
}
