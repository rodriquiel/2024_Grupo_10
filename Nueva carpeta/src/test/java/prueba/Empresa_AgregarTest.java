package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Administrador;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class Empresa_AgregarTest {
	
	Empresa empresa;
	Administrador admin;
	Chofer chofer1,chofer2;
	Cliente cliente1,cliente2;
	Pedido pedido1, pedido2;
	Vehiculo auto1,auto2,moto,combi;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		empresa.setUsuarioLogeado(admin);
	}
	
	@Test
	public void TestAgregarChoferE1() {
		chofer1=new ChoferPermanente("40767176","Juan",1999,3);
		try {
			empresa.agregarChofer(chofer1);
			HashMap<String,Chofer> hash=empresa.getChoferes();
			if(hash.size()!=1) {
				fail("Solo deberia haber un chofer en la empresa");
			}
		}catch(ChoferRepetidoException excep) {
			fail("No deberia lanzar excepcion porque el chofer ingresado deberia ser el unico");
		}
	}
	
	@Test
	public void TestAgregarChoferE2() {
		chofer1=new ChoferPermanente("40767176","Juan",1999,3);
		chofer2=new ChoferPermanente("40767176","Juan",1999,3);
		try {
			empresa.agregarChofer(chofer1);
			empresa.agregarChofer(chofer2);
			fail("Deberia haber lanzado la excepcion - ChoferRepetidoException -");
			
		}catch(ChoferRepetidoException excep) {
			assertEquals("El DNI deberia ser "+chofer2.getDni(),chofer2.getDni(),excep.getDniPrentendido());
			assertSame("La instancia de los choferes deberian ser iguales en la excepcion - ChoferRepetidoException -",chofer2,excep.getChoferExistente());
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CHOFER_YA_REGISTRADO);
		}catch(Exception e) {
			fail("No deberia lanzar otro tipo de excepcion que no sea - ChoferRepetidoException -");
		}
	}
	
	@Test
	public void TestAgregarClienteE1() {
		try {
			empresa.agregarCliente("juan123","ABC123","Juan");
			HashMap<String,Cliente> hash=empresa.getClientes();
			if(hash.size()!=1) {
				fail("Solo deberia haber un cliente en la empresa");
			}
		}catch(UsuarioYaExisteException excep) {
			fail("No deberia lanzar excepcion porque el cliente ingresado deberia ser el unico");
		}
	}
	
	@Test
	public void TestAgregarClienteE2() {
		try {
			empresa.agregarCliente("Juan123","ABC123","Juan");
			cliente2= new Cliente("Juan123","ABC123","Juan");
			empresa.agregarCliente("Juan123","ABC123","Juan");
			fail("Deberia haber lanzado la excepcion - UsuarioYaExisteException -");
			
		}catch(UsuarioYaExisteException excep) {
			assertEquals("El usuario repetido deberia ser "+cliente2.getNombreUsuario(),cliente2.getNombreUsuario(),excep.getUsuarioPretendido());
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.USUARIO_REPETIDO);
		}catch(Exception e) {
			fail("No deberia lanzar otro tipo de excepcion que no sea - UsuarioYaExisteException -");
		}
	}
	
	@Test
	public void TestAgregarPedidoE1() {
		cliente1=new Cliente("Juan123","ABC123","Juan");
		auto1=new Auto("ABC456",3,true);
		pedido1=new Pedido(cliente1,3,false,false,30,Constantes.ZONA_PELIGROSA);
		try {
			empresa.agregarVehiculo(auto1);
			empresa.agregarPedido(pedido1);
			pedido2=empresa.getPedidoDeCliente(cliente1);
			if(pedido2==null) {
				fail("Deberia haber un pedido");
			}
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion");
		}
	}
	
	@Test
	public void TestAgregarPedidoE2() {
		try {
			empresa.agregarCliente("Juan123","ABC123","Juan");
			cliente1=empresa.getClientes().get("Juan123");
			pedido1=new Pedido(cliente1,3,false,false,30,Constantes.ZONA_PELIGROSA);
			empresa.agregarPedido(pedido1);
			fail("Deberia lanzar la excepcion - SinVehiculoParaPedidoException -");
		}catch(SinVehiculoParaPedidoException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.SIN_VEHICULO_PARA_PEDIDO);
			
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - SinVehiculoParaPedidoException -");
	    }
    }
	
	@Test
	public void TestAgregarPedidoE3() {
		auto1=new Auto("ABC456",3,true);
		chofer1=new ChoferPermanente("40767176","Juan",1999,3);
		cliente1= new Cliente("Carlos257","CAR57","Carlos");//creo al cliente pero NO lo agrego a la empresa
		try {
			empresa.agregarVehiculo(auto1);
			empresa.agregarChofer(chofer1);
			pedido1=new Pedido(cliente1,3,false,false,30,Constantes.ZONA_PELIGROSA);
			empresa.agregarPedido(pedido1);
			fail("Deberia lanzar la excepcion - ClienteNoExisteException -");
		}catch(ClienteNoExisteException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CLIENTE_NO_EXISTE);
			
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - ClienteNoExisteException -");
	    }
    }
	
	@Test
	public void TestAgregarPedidoE4() {
		cliente1=new Cliente("Juan123","ABC123","Juan");
		auto1=new Auto("ABC456",3,true);
		auto2=new Auto("DEF456",3,true);
		chofer1=new ChoferPermanente("40767176","Juan",1999,3);
		chofer2=new ChoferPermanente("38557987","Pedro",1995,1);
		try {
			empresa.agregarCliente("Juan123","ABC123","Juan");
			empresa.agregarVehiculo(auto1);
			empresa.agregarVehiculo(auto2);
			empresa.agregarChofer(chofer1);
			empresa.agregarChofer(chofer2);
			pedido1=new Pedido(cliente1,3,false,false,30,Constantes.ZONA_PELIGROSA);
			pedido2=new Pedido(cliente1,1,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
			empresa.agregarPedido(pedido1);
			empresa.agregarPedido(pedido2);
			fail("Deberia lanzar la excepcion - ClienteConPedidoPendienteException -");
		}catch(ClienteConPedidoPendienteException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CLIENTE_CON_PEDIDO_PENDIENTE);
			
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - ClienteConPedidoPendienteException -");
	    }
    }
	
	@Test
	public void TestAgregarPedidoE5() {
		auto1=new Auto("ABC456",3,true);
		auto2=new Auto("DEF456",3,true);
		chofer1=new ChoferPermanente("40767176","Juan",1999,3);
		chofer2=new ChoferPermanente("38557987","Pedro",1995,1);
		try {
			empresa.agregarVehiculo(auto1);
			empresa.agregarVehiculo(auto2);
			empresa.agregarChofer(chofer1);
			empresa.agregarChofer(chofer2);
			empresa.agregarCliente("Juan123","ABC123","Juan");
			cliente1=empresa.getClientes().get("Juan123");
			pedido1=new Pedido(cliente1,3,false,false,30,Constantes.ZONA_PELIGROSA);
			pedido2=new Pedido(cliente1,1,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
			empresa.agregarPedido(pedido1);
			empresa.crearViaje(pedido1, chofer1, auto1);
			empresa.agregarPedido(pedido2);
			fail("Deberia lanzar la excepcion - ClienteConViajePendienteException -");
		}catch(ClienteConViajePendienteException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.CLIENTE_CON_VIAJE_PENDIENTE);
			
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente a - ClienteConViajePendienteException -");
	    }
    }
	
	@Test
	public void TestAgregarVehiculoE1() {
		auto1=new Auto("ABC456",3,true);
		try {
			empresa.agregarVehiculo(auto1);
			HashMap<String,Vehiculo> hash=empresa.getVehiculos();
			if(hash.size()!=1) {
				fail("Solo deberia haber un vehiculo en la empresa");
			}
		}catch(VehiculoRepetidoException excep) {
			fail("No deberia lanzar excepcion porque el vehiculo ingresado deberia ser el unico");
		}
	}
	
	@Test
	public void TestAgregarVehiculoE2() {
		auto1=new Auto("ABC456",3,true);
		auto2=new Auto("ABC456",3,true);
		try {
			empresa.agregarVehiculo(auto1);
			empresa.agregarVehiculo(auto2);
			fail("Deberia haber lanzado la excepcion - VehiculoRepetidoException -");
			
		}catch(VehiculoRepetidoException excep) {
			assertEquals("La patente deberia ser "+auto2.getPatente(),auto2.getPatente(),excep.getPatentePrentendida());
			assertSame("Las instancias de los vehiculos deberian ser iguales en la excepcion - VehiculoRepetidoException -",auto2,excep.getVehiculoExistente());
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.VEHICULO_YA_REGISTRADO);
		}catch(Exception e) {
			fail("No deberia lanzar otro tipo de excepcion que no sea - VehiculoRepetidoException -");
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
	
