package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;

public class AutoTest {

	Auto auto;
	
	@Before
	public void setUp() {
		this.auto = new Auto("ABC123",2,true); 
	}
	
	@After
	public void TearDown()
	{
		Empresa.getInstance().getPedidos().clear();
	}
	
	@Test
	public void testConstructorAuto() {
		assertNotNull("El auto debe ser distinto de null",this.auto);
	}
	
	@Test
	public void testGetPatente() {
		String patenteEsperada = "ABC123";
		assertEquals(patenteEsperada,this.auto.getPatente(),"La patente debe ser 'ABC123'");
	}
	
	@Test
	public void testGetCantidadPlazas() {
		int cantEsperada = 2;
		assertEquals("La cantidad de plazas debe ser " + cantEsperada,cantEsperada,this.auto.getCantidadPlazas());
	}

	@Test
	public void testIsMascota() {
		assertTrue(this.auto.isMascota());
	}
	
	@Test
	public void testGetPuntajePedido1() {
		Cliente cliente  = new Cliente("Juan95", "1234", "Juan Perez");
		Pedido pedido = new Pedido(cliente, 3, true, true, 8, Constantes.ZONA_STANDARD);
		Empresa emp1=Empresa.getInstance();
		
		
		try {
			emp1.agregarPedido(pedido);
		} catch (Exception e) {
			fail("no tiene que tirar exepcion");
		}
		int puntajeEsperado = 40*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,this.auto.getPuntajePedido(pedido),0.001);	
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
		
		Empresa emp1=Empresa.getInstance();
		
		
		try {
			emp1.agregarPedido(pedido);
		} catch (Exception e) {
			fail("no tiene que tirar exepcion");
		}
		
		int puntajeEsperado = 30*pedido.getCantidadPasajeros();
		assertEquals(puntajeEsperado,this.auto.getPuntajePedido(pedido),0.001);	
	}	
	
}

