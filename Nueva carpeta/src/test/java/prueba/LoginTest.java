package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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

public class LoginTest {

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
	public void TestLoginE1() {
		
		try {
			empresa.agregarCliente("Juan123","ABC123","Juan");
			usuario=empresa.login("Juan123","ABC123");
			assertSame("Las instancias de usuario y cliente deberian ser iguales",usuario,cliente);
		}catch(Exception e) {
			fail("No deberia lanzar excepciones");
		}
	}
	
	@Test
	public void TestLoginE2() {
		
		try {
			//No agrego el cliente a la empresa
			usuario=empresa.login("Juan123","ABC123");
			fail("Deberia lanzar la excepcion - UsuarioNoExisteException  -");
		}catch(UsuarioNoExisteException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.USUARIO_DESCONOCIDO);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente de - UsuarioNoExisteException  -");
		}
	}
	
	@Test
	public void TestLoginE3() {
		
		try {
			empresa.agregarCliente("Juan123","ABC123","Juan");
			usuario=empresa.login("Juan123","ABC456");//contrasenia incorrecta
			fail("Deberia lanzar la excepcion - PasswordErroneaException  -");
		}catch(PasswordErroneaException excep) {
			assertEquals("El mensaje de la excepcion esta mal",excep.getMessage(),util.Mensajes.PASS_ERRONEO);
		}catch(Exception e) {
			fail("No deberia lanzar una excepcion diferente de - PasswordErroneaException  -");
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
