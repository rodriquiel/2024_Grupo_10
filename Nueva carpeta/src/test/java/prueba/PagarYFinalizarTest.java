package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import excepciones.ClienteSinViajePendienteException;
import modeloDatos.Administrador;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;

public class PagarYFinalizarTest {

	Empresa empresa;
	Administrador admin;
	Chofer chofer;
	Cliente cliente;
	Pedido pedido;
	Vehiculo auto;
	Viaje viaje;

	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		empresa.setUsuarioLogeado(admin);
		chofer=new ChoferPermanente("42949155","Pedro",1999,3);
		cliente=new Cliente("Juan123","ABC123","Juan");
		auto=new Auto("ABC456",3,true);
		pedido=new Pedido(cliente,3,false,false,30,Constantes.ZONA_PELIGROSA);
		viaje=new Viaje(pedido,chofer,auto);
	}
	
	public void TestPagarYFinalizarE1() {
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			empresa.pagarYFinalizarViaje(3);
			assertEquals("La cantidad de viajes iniciados deberia ser 0",empresa.getViajesIniciados().size(),0);
			assertSame("La instancia de los viajes deberian ser iguales",viaje,empresa.getViajesTerminados().get(0));
		}catch(ClienteSinViajePendienteException excep){
			
		}catch(Exception e) {
			
		}
	}
	
	public void TestPagarYFinalizarE2() {
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			empresa.pagarYFinalizarViaje(3);
			fail("Deberia lanzar la excepcion - ClienteSinViajePendienteException -");
		}catch(ClienteSinViajePendienteException excep){
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE);
		}catch(Exception e) {
			
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
