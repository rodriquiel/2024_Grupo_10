package prueba;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Administrador;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;

public class GetViajeDeClienteTest {

	Empresa empresa;
	Administrador admin;
	Cliente cliente;
	Usuario usuario;
	Chofer chofer;
	Pedido pedido;
	Vehiculo auto;
	Viaje viaje;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		chofer=new ChoferPermanente("42949155","Pedro",1999,3);
		auto=new Auto("ABC456",3,true);
		try {
			empresa.login("admin", "admin");
			empresa.agregarCliente("Juan123", "ABC123", "Juan");
			cliente=empresa.getClientes().get("Juan123");
			empresa.agregarChofer(chofer);
			empresa.agregarVehiculo(auto);
			pedido=new Pedido(cliente,3,false,false,30,Constantes.ZONA_PELIGROSA);
			empresa.agregarPedido(pedido);
		}catch(Exception e) {
			fail("No deberia lanzar excepciones en el setUp");
		}
	}
	
	@Test
	public void TestGetViajeDeClienteE1() {
		try {
			empresa.crearViaje(pedido, chofer, auto);
			viaje=empresa.getViajeDeCliente(cliente);
			assertSame("Los viajes deberian ser iguales",viaje,empresa.getViajesIniciados().get(cliente));
		} catch (Exception e) {
			fail("No deberia lanzar excepciones");
		}
	}
	
	@Test
	public void TestGetViajeDeClienteE2() {
		try {
			//empresa.crearViaje(pedido, chofer, auto); NO CREO EL VIAJE PARA EL CLIENTE
			viaje=empresa.getViajeDeCliente(cliente);
			assertNull("El viaje deberia ser null",viaje);
		} catch (Exception e) {
			fail("No deberia lanzar excepciones");
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
		empresa.logout();
	}
}
