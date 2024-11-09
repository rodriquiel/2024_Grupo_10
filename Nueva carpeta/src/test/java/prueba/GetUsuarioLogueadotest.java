package prueba;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Administrador;
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class GetUsuarioLogueadotest {

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
	public void TestGetUsuarioLogueadoE1() {
		
		try {
			empresa.login("admin", "admin");
			usuario=empresa.getUsuarioLogeado();
			assertSame("Las instancias deberian ser iguales",admin,(Administrador)usuario);
		} catch (Exception e) {
			fail("No deberia lanzar excepciones");	
		}
		
	}
	
	@Test
	public void TestGetUsuarioLogueadoE2() {
		
		try {
			empresa.agregarCliente("Juan123", "ABC123", "Juan");
			cliente=empresa.getClientes().get("Juan123");
			empresa.login("Juan123", "ABC123");
			usuario=empresa.getUsuarioLogeado();
			assertSame("Las instancias deberian ser iguales",cliente,(Cliente)usuario);
		} catch (Exception e) {
			fail("No deberia lanzar excepciones");	
		}
		
	}
	
	@After
	public void tearDown() {
		empresa.getClientes().clear();
	}
}
