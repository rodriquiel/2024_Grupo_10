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
		assertNotNull("singleton devuelve un objeto null",empresa1);
		assertTrue("el singleton instancia dos objetos distintos",empresa1 == empresa2);
	}
	@Test
	public void ConstructorEmpresaTest()
	{
		assertNotNull("la lista de clientes es null",empresa1.getClientes());
		assertNotNull("la lista de choferes es null",empresa1.getChoferes());
		assertNotNull("la lista de vehiculos es null",empresa1.getVehiculos());
		assertNotNull("la lista de vehiculos desocupados es null",empresa1.getVehiculosDesocupados());
		assertNotNull("la lista de choferes desocupados es null",empresa1.getChoferesDesocupados());
		assertNotNull("la lista de pedidos es null",empresa1.getPedidos());
	}
	@After
	public void TearDown()
	{
		empresa1=null;
		empresa2=null;
	}

}
