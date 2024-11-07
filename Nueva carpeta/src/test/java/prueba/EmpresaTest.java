package prueba;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;

public class EmpresaTest {
	Empresa empresa1;
	
	@Before
	public void setUp()
	{
		empresa1=Empresa.getInstance();
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
	public void VehiculosOrdenadosPorPedidos(Pedido pedido)
	{
		
	}
	

}
