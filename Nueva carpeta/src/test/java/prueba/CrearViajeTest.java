package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferNoDisponibleException;
import excepciones.ClienteConViajePendienteException;
import excepciones.PedidoInexistenteException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import modeloDatos.Administrador;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class CrearViajeTest {

	Empresa empresa;
	Administrador admin;
	Chofer chofer,chofer2;
	Cliente cliente,cliente2;
	Pedido pedido,pedido2;
	Vehiculo auto,moto;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		chofer=new ChoferPermanente("42949155","Pedro",1999,3);
		auto=new Auto("ABC456",3,true);
		try {
			empresa.login("admin","admin");
			empresa.agregarCliente("Juan123","ABC123","Juan");
			cliente=empresa.getClientes().get("Juan123");
			pedido=new Pedido(cliente,3,false,false,30,Constantes.ZONA_PELIGROSA);
		} catch (Exception e) {
			fail("No deberia lanzar excepcion en el setUp()");
		}
	}

	@Test
	public void TestCrearViajeE1() {
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			assertEquals("La cantidad de viajes iniciados deberia ser 1",empresa.getViajesIniciados().size(),1);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion");
		}
	}
	
	@Test
	public void TestCrearViajeE2() {
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarVehiculo(auto);
			//empresa.agregarPedido(pedido); no agregue pedido a la empresa
			empresa.crearViaje(pedido, chofer, auto);
			fail("Deberia lanzar la excepcion - PedidoInexistenteException -");
		}catch(PedidoInexistenteException excep) {
			assertSame("Los pedidos deberian ser iguales",excep.getPedido(),pedido);
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.PEDIDO_INEXISTENTE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - PedidoInexistenteException -");
		}
	}
	
	@Test
	public void TestCrearViajeE3() {
		
		try {
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			//empresa.agregarChofer(chofer); No agrego chofer a la empresa
			empresa.crearViaje(pedido, chofer, auto);
			fail("Deberia lanzar la excepcion - ChoferNoDisponibleException -");
		}catch(ChoferNoDisponibleException excep) {
			assertSame("Las instancias deberian ser iguales",excep.getChofer(),chofer);
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CHOFER_NO_DISPONIBLE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - ChoferNoDisponibleException -");
		}
	}
	
	
	@Test
	public void TestCrearViajeE4() {
		
		try {
			empresa.agregarChofer(chofer);
			//empresa.agregarVehiculo(auto); No agrego el vehiculo a la empresa
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			fail("Deberia lanzar la excepcion - VehiculoNoDisponibleException -");
		}catch(VehiculoNoDisponibleException excep) {
			assertSame("Los autos deberian ser iguales",excep.getVehiculo(),auto);
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.VEHICULO_NO_DISPONIBLE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - VehiculoNoDisponibleException -");
		}
	}
	
	
	@Test
	public void TestCrearViajeE5() {
		
		moto=new Moto("XYZ015");
	
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarVehiculo(moto);
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, moto);
			fail("Deberia lanzar la excepcion - VehiculoNoValidoException -");
		}catch(VehiculoNoValidoException excep) {
			assertSame("Los pedidos deberian ser iguales",excep.getPedido(),pedido);
			assertSame("Las instancias de moto deberian ser iguales",excep.getVehiculo(),moto);
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.VEHICULO_NO_VALIDO);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - VehiculoNoValidoException -");
		}
	}
	
	@Test
	public void TestCrearViajeE6() {
		
		chofer2=new ChoferPermanente("40767176","Juan",1999,3);
		pedido2=new Pedido(cliente,1,false,false,30,Constantes.ZONA_PELIGROSA);
		moto=new Moto("XYZ015");
	
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarChofer(chofer2);
			empresa.agregarVehiculo(auto);
			empresa.agregarVehiculo(moto);
			empresa.agregarPedido(pedido);
			empresa.agregarPedido(pedido2);
			empresa.crearViaje(pedido, chofer, auto);
			empresa.crearViaje(pedido2, chofer2, moto);
			fail("Deberia lanzar la excepcion - ClienteConViajePendienteException -");
		}catch(ClienteConViajePendienteException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CLIENTE_CON_VIAJE_PENDIENTE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - ClienteConViajePendienteException -");
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
