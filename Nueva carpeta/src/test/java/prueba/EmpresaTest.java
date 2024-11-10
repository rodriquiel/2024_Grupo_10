package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	}
	
	@After
	public void tearDown(){
		Empresa.getInstance().getClientes().clear();
		Empresa.getInstance().getChoferes().clear();
		Empresa.getInstance().getVehiculos().clear();
		Empresa.getInstance().getVehiculosDesocupados().clear();
		Empresa.getInstance().getChoferesDesocupados().clear();
		Empresa.getInstance().getPedidos().clear();
		Empresa.getInstance().getViajesIniciados().clear();
		Empresa.getInstance().getViajesTerminados().clear();
		Empresa.getInstance().logout();
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
}
