package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.SinViajesException;
import modeloDatos.Administrador;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class CalificacionDeChoferTest {

	Empresa empresa;
	Administrador admin;
	Chofer chofer;
	Cliente cliente;
	Pedido pedido;
	Vehiculo auto;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		empresa.setUsuarioLogeado(admin);
		chofer=new ChoferPermanente("42949155","Pedro",1999,3);
		cliente=new Cliente("Juan123","ABC123","Juan");
		auto=new Auto("ABC456",3,true);
		pedido=new Pedido(cliente,3,false,false,30,Constantes.ZONA_PELIGROSA);
	}
	
	@Test
	public void TestCalificacionDeChoferE1() {
		
		double calificacion=0;
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			empresa.pagarYFinalizarViaje(3);
			calificacion=empresa.calificacionDeChofer(chofer);
			if(calificacion!=3) {
				fail("La calificacion debería ser 3");
			}
		}catch(SinViajesException excep) {
			fail("No deberia lanzar la escepcion - SinViajesException - porque el chofer ya hizo un viaje");
		}
		catch(Exception e) {
			fail("No deberia lanzar excepciones");
		}
	}
	
	public void TestCalificacionDeChoferE2() {
		
		double calificacion=0;
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			calificacion=empresa.calificacionDeChofer(chofer);
			fail("Debería haber lanzado la excepcion - SinViajesException - ya que el chofer no tiene viajes");
		}catch(SinViajesException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CHOFER_SIN_VIAJES);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente de - SinViajesException -");
		}
	}
	
	@After
	public void tearDown() {
		empresa.getChoferes().clear();
		empresa.getChoferesDesocupados().clear();
		empresa.getClientes().clear();
		empresa.getPedidos().clear();
		empresa.getVehiculos().clear();
		empresa.getVehiculosDesocupados().clear();
		empresa.getViajesIniciados().clear();
		empresa.getViajesTerminados().clear();
	}
}
