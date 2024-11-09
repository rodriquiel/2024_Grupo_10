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
	}
	
	@Test
	public void TestLogOut() {
		
		try {
		empresa.login("admin", "admin");//entro como admin para gestion
		empresa.logout();
		assertNull("El usuario logueado deberia ser null",empresa.getUsuarioLogeado());
		}catch(Exception e) {
			fail("no deberia lanzar excepcion");
		}
	}
	
	@After
	public void tearDown() {
		empresa.getClientes().clear();
	}
}
