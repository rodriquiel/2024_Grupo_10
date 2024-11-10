package prueba;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloNegocio.Empresa;

public class EmpresaSinlgetonTest {
	Empresa empresa1;
	Empresa empresa2;
	
	@Before
	public void SetUp()
	{
		empresa1=Empresa.getInstance();
		empresa2=Empresa.getInstance();
	}
	
	@Test
	public void SingletonTest()
	{
		assertNotNull("El singleton devuelve un objeto null",empresa1);
		assertTrue("El singleton instancia dos objetos distintos",empresa1 == empresa2);
	}
	
	@Test
	public void ConstructorEmpresaTest()
	{
		assertNotNull("La lista de clientes es null",empresa1.getClientes());
		assertNotNull("La lista de choferes es null",empresa1.getChoferes());
		assertNotNull("La lista de vehiculos es null",empresa1.getVehiculos());
		assertNotNull("La lista de vehiculos desocupados es null",empresa1.getVehiculosDesocupados());
		assertNotNull("La lista de choferes desocupados es null",empresa1.getChoferesDesocupados());
		assertNotNull("La lista de pedidos es null",empresa1.getPedidos());
		assertNotNull("La lista de viajes iniciados es null", empresa1.getViajesIniciados());
		assertNotNull("La lista de viajes terminados es null", empresa1.getViajesTerminados());
	}
	
	@After
	public void TearDown()
	{
		empresa1=null;
		empresa2=null;
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

}
