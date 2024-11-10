package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ClienteSinViajePendienteException;
import excepciones.SinViajesException;
import excepciones.UsuarioYaExisteException;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;

public class EmpresaTest {
	Empresa empresa1;
	Cliente cliente1;
	
	@Before
	public void setUp()
	{
		empresa1=Empresa.getInstance();
		cliente1=new Cliente("juan95","1234","juan");
		try {
			empresa1.agregarCliente("juan95","1234","juan");
		} catch (UsuarioYaExisteException e) {
		}
	}
	
	@After
	public void tearDown(){
		empresa1=null;
		Empresa.getInstance().getClientes().clear();
		Empresa.getInstance().getChoferes().clear();
		Empresa.getInstance().getVehiculos().clear();
		Empresa.getInstance().getVehiculosDesocupados().clear();
		Empresa.getInstance().getChoferesDesocupados().clear();
		Empresa.getInstance().getPedidos().clear();
		Empresa.getInstance().getViajesIniciados().clear();
		Empresa.getInstance().getViajesTerminados().clear();
		Empresa.getInstance().logout();
		cliente1=null;
	}
	
	
	
	@Test
	public void getYsetclientesTest() 
	{
		HashMap<String,Cliente> clientes1=new HashMap<String, Cliente>();
		
		empresa1.setClientes(clientes1);
		
		assertEquals(empresa1.getClientes(),clientes1);
		
		
	}
	@Test
	public void getysetChoferesDesocupadosTest()
	{
		ArrayList<Chofer>choferesDescupados1=new ArrayList<Chofer>();
		
		empresa1.setChoferesDesocupados(choferesDescupados1);
		
		assertEquals(empresa1.getChoferesDesocupados(),choferesDescupados1);
	}
	
	@Test
	public void GetySetChoferesTest()
	{
		HashMap<String,Chofer> choferes=new HashMap<String, Chofer>();
		
		empresa1.setChoferes(choferes);
		assertEquals(empresa1.getChoferes(),choferes);
		
	}
	
	@Test
	public void getYsetVehiculosTest() {
		HashMap<String, Vehiculo> vehiculos=new HashMap<String, Vehiculo>();
		
		empresa1.setVehiculos(vehiculos);
		assertEquals(empresa1.getVehiculos(),vehiculos);
	}
	
	@Test
	public void GetySetVehiculosDesocupadosTest() {
		ArrayList<Vehiculo> vehiculosDescupados=new ArrayList<Vehiculo>();
		
		empresa1.setVehiculosDesocupados(vehiculosDescupados);
		
		assertEquals(empresa1.getVehiculosDesocupados(),vehiculosDescupados);
	}
	
	@Test
	public void getySetPedidosTest() {
		HashMap<Cliente,Pedido>pedidos=new HashMap<Cliente, Pedido>();
		
		empresa1.setPedidos(pedidos);
		assertEquals(empresa1.getPedidos(),pedidos);
	}
	
	@Test
	public void getYsetViajesInciadosTest()
	{
		HashMap<Cliente,Viaje> viajesIniciados=new HashMap<Cliente, Viaje>();
		
		empresa1.setViajesIniciados(viajesIniciados);
		assertEquals(empresa1.getViajesIniciados(),viajesIniciados);
	}
	
	@Test
	public void setYgetViajesTerminadosTest() {
		ArrayList<Viaje>viajesTerminados=new ArrayList<Viaje>();
		
		empresa1.setViajesTerminados(viajesTerminados);
		assertEquals(empresa1.getViajesTerminados(),viajesTerminados);
	}
	
	@Test
	public void VehiculosOrdenadosPorPedidosTest()
	{
		Moto moto1=new Moto("jdk");
		Moto moto2=new Moto("123");
		
		Pedido pedido1=new Pedido(null, 0, false, false, 0, null);
	try {	
		empresa1.agregarVehiculo(moto1);
		empresa1.agregarVehiculo(moto2);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		ArrayList<Vehiculo>vehiculosOrdenados=empresa1.vehiculosOrdenadosPorPedido(pedido1);
		assertTrue("los vehiculos no estan ordenados",vehiculosOrdenados.get(0).getPuntajePedido(pedido1)>=vehiculosOrdenados.get(1).getPuntajePedido(pedido1));
	}
	
	@Test
	public void validarPedidoTest() {
		Pedido pedido1=new Pedido(cliente1, 0, false, false, 0, null);
		
		assertFalse("el pedido no deberia ser valido",empresa1.validarPedido(pedido1));
		
	}
	
	@Test
	public void ValidarPedidoTest2()
	{
		Moto moto1=new Moto("123");
		try {
			empresa1.agregarVehiculo(moto1);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		Pedido pedido1=new Pedido(cliente1, 1, false, false, 10,Constantes.ZONA_STANDARD);
		
		assertTrue("el pedido deberia ser valido",empresa1.validarPedido(pedido1));
		
	}
	
	@Test
	public void GetTotalSalariosTest() {
		Chofer ch1=new ChoferTemporario("456","tomi");
		
		ch1.setSueldoBasico(450.0);
		try {
			empresa1.agregarChofer(ch1);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals("no devuelve los salarios correctamente",empresa1.getTotalSalarios(),450.0);
	}
	
	@Test
	public void GetHistorialDeViajeporClienteTest()
	{
		assertEquals("deberia ser igual a cero",empresa1.getHistorialViajeCliente(cliente1).size(),0);
		
		Pedido p1=new Pedido(cliente1, 1, false, false, 10,Constantes.ZONA_STANDARD);
		Chofer ch1=new ChoferTemporario("45131031", "raul");
		Vehiculo m1= new Moto("123");
	
		
		try {
			empresa1.agregarChofer(ch1);
			empresa1.agregarVehiculo(m1);
			empresa1.agregarPedido(p1);
			empresa1.crearViaje(p1, ch1, m1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals("deberia ser igual a 1",empresa1.getHistorialViajeCliente(cliente1).size(), 1);
	}
	
	@Test
	public void GetHistorialDeViajesChoferTest()
	{
		Chofer ch1=new ChoferTemporario("43131031","juan");
		try {
			empresa1.agregarChofer(ch1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals("deberia ser 0",empresa1.getHistorialViajeChofer(ch1).size(), 0);
		Pedido p1=new Pedido(cliente1, 1, false, false, 10,Constantes.ZONA_STANDARD);
		Vehiculo m1= new Moto("123");
		
		try {
			empresa1.agregarChofer(ch1);
			empresa1.agregarVehiculo(m1);
			empresa1.agregarPedido(p1);
			empresa1.crearViaje(p1, ch1, m1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals("deberia ser igual a 1",empresa1.getHistorialViajeChofer(ch1).size(),1);
		
	}
	
	@Test
	public void GetPedidoClienteTest()
	{
		assertNull("deberia se null",empresa1.getPedidoDeCliente(cliente1));
		Pedido p1=new Pedido(cliente1, 1, false, false, 10,Constantes.ZONA_STANDARD);
		try {
			empresa1.agregarPedido(p1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals("deberia ser igual p1",empresa1.getPedidoDeCliente(cliente1),p1);
	}
	@Test
	public void GetviajeDelClienteTest()
	{
		assertNull("deberia ser null",empresa1.getViajeDeCliente(cliente1));
		Pedido p1=new Pedido(cliente1, 1, false, false, 10,Constantes.ZONA_STANDARD);
		Chofer ch1=new ChoferTemporario("43212555","raul");
		Vehiculo m1= new Moto("123");
		
		try {
			empresa1.agregarChofer(ch1);
			empresa1.agregarVehiculo(m1);
			empresa1.agregarPedido(p1);
			empresa1.crearViaje(p1, ch1, m1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		assertNotNull("deberia no sere null",empresa1.getViajeDeCliente(cliente1));
	}
	@Test
	public void CalificacionDelChoferTest()
	{
		Pedido p1=new Pedido(cliente1, 1, false, false, 10,Constantes.ZONA_STANDARD);
		Chofer ch1=new ChoferTemporario("43212555","raul");
		Vehiculo m1= new Moto("123");
		
		try {
			empresa1.calificacionDeChofer(ch1);
			fail();
		} catch (SinViajesException e) {
		}
		
		try {
			empresa1.agregarChofer(ch1);
			empresa1.agregarVehiculo(m1);
			empresa1.agregarPedido(p1);
			empresa1.crearViaje(p1, ch1, m1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		try {
			empresa1.pagarYFinalizarViaje(3);
		} catch (ClienteSinViajePendienteException e) {
			// TODO Bloque catch generado automáticamente
		}
		try {
			assertTrue(empresa1.calificacionDeChofer(ch1)<=5.0 && empresa1.calificacionDeChofer(ch1)>0.0);
		} catch (SinViajesException e) {
			  fail();
		}
		
	}
	
}
