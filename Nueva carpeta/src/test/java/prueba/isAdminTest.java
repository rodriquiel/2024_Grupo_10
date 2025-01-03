package prueba;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import modeloDatos.Administrador;
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class isAdminTest {

	Empresa empresa;
	Administrador admin;
	Cliente cliente;
	Usuario usuario;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		empresa.logout();
	}
	
	@Test
	public void TestIsAdminE1() {
		try {
			empresa.login("admin", "admin");
			assertTrue("Deberia ser true",empresa.isAdmin());
		} catch (Exception e) {
			fail("No deberia lanzar excepciones");
		}
	}
	
	@Test
	public void TestIsAdminE2() {
		
		try {
			empresa.login("admin", "admin");
			empresa.agregarCliente("Juan123","ABC123","Juan");
			cliente=empresa.getClientes().get("Juan123");
			empresa.logout();
			empresa.login("Juan123","ABC123");
			assertFalse("Deberia ser false",empresa.isAdmin());
		}catch(Exception e) {
			fail("No deberia lanzar excepciones");
		}
	}
	
	@After
	public void tearDown() {
		empresa.getClientes().clear();
	}
}
