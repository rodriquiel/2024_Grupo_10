package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import excepciones.ChoferNoDisponibleException;
import excepciones.ClienteConViajePendienteException;
import excepciones.PedidoInexistenteException;
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
		empresa.setUsuarioLogeado(admin);
		chofer=new ChoferPermanente("42949155","Pedro",1999,3);
		cliente=new Cliente("Juan123","ABC123","Juan");
		auto=new Auto("ABC456",3,true);
		pedido=new Pedido(cliente,3,false,false,30,Constantes.ZONA_PELIGROSA);
	}
	
	public void TestCrearViajeE1() {
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			assertEquals("La cantidad de viajes iniciados deberia ser 1",empresa.getViajesIniciados().size(),1);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion");
		}
	}
	
	public void TestCrearViajeE2() {
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto);
			//no agregue pedido a la empresa
			empresa.crearViaje(pedido, chofer, auto);
			fail("Deberia lanzar la excepcion - PedidoInexistenteException -");
		}catch(PedidoInexistenteException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.PEDIDO_INEXISTENTE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - PedidoInexistenteException -");
		}
	}

	public void TestCrearViajeE3() {
		
		try {
			//No agrego chofer a la empresa
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto);
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			fail("Deberia lanzar la excepcion - ChoferNoDisponibleException -");
		}catch(ChoferNoDisponibleException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CHOFER_NO_DISPONIBLE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - ChoferNoDisponibleException -");
		}
	}
	
	public void TestCrearViajeE4() {
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			//No agrego el vehiculo a la empresa
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			fail("Deberia lanzar la excepcion - VehiculoNoDisponibleException -");
		}catch(VehiculoNoDisponibleException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.VEHICULO_NO_DISPONIBLE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - VehiculoNoDisponibleException -");
		}
	}
	
	public void TestCrearViajeE4bis() {
		
		chofer2=new ChoferPermanente("40767176","Juan",1999,3);
		cliente2=new Cliente("Tomi123","ABC456","Tomas");
		pedido2=new Pedido(cliente2,3,false,false,30,Constantes.ZONA_PELIGROSA);
		
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarChofer(chofer2);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarCliente("Tomi123","ABC456","Tomas");
			empresa.agregarVehiculo(auto);//este es el único vehiculo de la empresa
			empresa.agregarPedido(pedido);
			empresa.agregarPedido(pedido2);
			empresa.crearViaje(pedido, chofer, auto);
			empresa.crearViaje(pedido2, chofer, auto);//aca deberia lanzar la excepcion (creo...)
			fail("Deberia lanzar la excepcion - VehiculoNoDisponibleException -");
		}catch(VehiculoNoDisponibleException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.VEHICULO_NO_DISPONIBLE);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - VehiculoNoDisponibleException -");
		}
	}
	
	public void TestCrearViajeE5() {
		
		moto=new Moto("XYZ015");
	
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(moto);
			empresa.agregarPedido(pedido);
			empresa.crearViaje(pedido, chofer, auto);
			fail("Deberia lanzar la excepcion - VehiculoNoValidoException -");
		}catch(VehiculoNoValidoException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.VEHICULO_NO_VALIDO);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - VehiculoNoValidoException -");
		}
	}

	public void TestCrearViajeE6() {
		
		chofer2=new ChoferPermanente("40767176","Juan",1999,3);
		pedido2=new Pedido(cliente,1,false,false,30,Constantes.ZONA_PELIGROSA);
	
		try {
			empresa.agregarChofer(chofer);
			empresa.agregarChofer(chofer2);
			empresa.agregarCliente("Juan123","ABC123","Juan");
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
