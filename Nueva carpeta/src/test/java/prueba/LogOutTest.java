package prueba;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Administrador;
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class LogOutTest {

	Empresa empresa;
	Administrador admin;
	Cliente cliente;
	Usuario usuario;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		cliente=new Cliente("Juan123","ABC123","Juan");
	}
	
	@Test
	public void TestLogOut() {
		
		try {
		empresa.agregarCliente("Juan123","ABC123","Juan");
		empresa.login("Juan123","ABC123");
		empresa.logout();
		assertNull("El usuario logueado deberia ser null",empresa.getUsuarioLogeado());
		}catch(Exception e) {
			fail("no deberia lanzar excepcion");
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
