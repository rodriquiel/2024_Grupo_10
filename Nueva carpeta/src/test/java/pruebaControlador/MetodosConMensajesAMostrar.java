package pruebaControlador;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import modeloDatos.Administrador;
import modeloDatos.Cliente;
import modeloNegocio.Empresa;
import pruebaGUI.FalsoOptionPane;
import util.Mensajes;
import vista.IVista;



public class MetodosConMensajesAMostrar {
	Controlador controlador;
	FalsoOptionPane opFalso;
	VentanaFalsa ventana;
	
	@Before
	public void setUp(){
		opFalso = new FalsoOptionPane();
		ventana = new VentanaFalsa();
		this.controlador = new Controlador();
		this.ventana.setOptionPaneFalso(opFalso);;
		this.controlador.setVista(ventana);
	}

	@After
	public void tearDown(){
		controlador = null;
		
		Empresa.getInstance().getClientes().clear();
		Empresa.getInstance().getChoferes().clear();
		Empresa.getInstance().getVehiculos().clear();
		Empresa.getInstance().getVehiculosDesocupados().clear();
		Empresa.getInstance().getChoferesDesocupados().clear();
		Empresa.getInstance().getPedidos().clear();
		Empresa.getInstance().getViajesIniciados().clear();
		Empresa.getInstance().getViajesTerminados().clear();
		
	}
	
	

	@Test
	public void testLeerArchInexistente() {
		File arch = new File("empresa.bin");
		if(arch.exists())
			 arch.delete();
		
		controlador.leer();
		
		assertTrue("El metodo 'leer()' deberia intentar escribir un archivo nuevo con nombre 'empresa.bin'.", arch.exists());
	}
	
	@Test
	public void testLeerArchExistente() {
		Cliente cli1 = null;
		Cliente cli2 = null;
		
		File arch = new File("empresa.bin");
		if(arch.exists())
		  arch.delete();
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli2 = (Cliente) Empresa.getInstance().login("usuario1","1234");
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		controlador.escribir();
		controlador.leer();
		
		try {
			cli1 = (Cliente) Empresa.getInstance().login("usuario1","1234");
		} 
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		assertEquals("NO Recupera los datos de la clase Empresa (singleton), conservados en el archivo indicado por el atributo 'fileName'.", cli2.getNombreReal(), cli1.getNombreReal());
		assertEquals("NO Recupera los datos de la clase Empresa (singleton), conservados en el archivo indicado por el atributo 'fileName'.", cli2.getNombreUsuario(), cli1.getNombreUsuario());
		assertEquals("NO Recupera los datos de la clase Empresa (singleton), conservados en el archivo indicado por el atributo 'fileName'.", cli2.getPass(), cli1.getPass());
	}

	
	
	
	
	@Test
	public void testEscribir() {
		Cliente cli1 = null;
		Cliente cli2 = null;
		
		File arch = new File("empresa.bin");
		if(arch.exists())
		  arch.delete();
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli2 = (Cliente) Empresa.getInstance().login("usuario1","1234");
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		controlador.escribir();
		controlador.leer();
		
		try {
			cli1 = (Cliente) Empresa.getInstance().login("usuario1","1234");
		} 
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		assertEquals("NO Recupera los datos de la clase Empresa (singleton), conservados en el archivo indicado por el atributo 'fileName'.", cli2.getNombreReal(), cli1.getNombreReal());
		assertEquals("NO Recupera los datos de la clase Empresa (singleton), conservados en el archivo indicado por el atributo 'fileName'.", cli2.getNombreUsuario(), cli1.getNombreUsuario());
		assertEquals("NO Recupera los datos de la clase Empresa (singleton), conservados en el archivo indicado por el atributo 'fileName'.", cli2.getPass(), cli1.getPass());
	}
	
	
	
	
	
	
	@Test
	public void testLoginAdministradorPassIncorrecta() {
		ventana.setUsserName("admin");
		ventana.setPassword("31");

		controlador.login();
		assertEquals("Deberia decir: "+ Mensajes.PASS_ERRONEO.getValor(), Mensajes.PASS_ERRONEO.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testLoginClientePassIncorrecta() {
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
		} 
		catch (UsuarioYaExisteException e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		ventana.setUsserName("usuario1");
		ventana.setPassword("31");

		controlador.login();
		assertEquals("Deberia decir: "+ Mensajes.PASS_ERRONEO.getValor(), Mensajes.PASS_ERRONEO.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testLoginClienteNoExiste() {
		ventana.setUsserName("usuario1");
		ventana.setPassword("31");

		controlador.login();
		assertEquals("Deberia decir: "+ Mensajes.USUARIO_DESCONOCIDO.getValor(), Mensajes.USUARIO_DESCONOCIDO.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testLoginCliente() {
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
		} 
		catch (UsuarioYaExisteException e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		ventana.setUsserName("usuario1");
		ventana.setPassword("1234");

		controlador.login();
		Cliente cliLogeado = (Cliente)Empresa.getInstance().getUsuarioLogeado();
		Cliente cliagregado = Empresa.getInstance().getClientes().get("usuario1");
		assertEquals("Deberia cumplirse que se logeo al mismo cliente por el metodo 'login'.",cliagregado,cliLogeado);

	}

	@Test
	public void testLoginAdministrador() {
		ventana.setUsserName("admin");
		ventana.setPassword("admin");
		
		controlador.login();
		Administrador admin = (Administrador)Empresa.getInstance().getUsuarioLogeado();
		assertEquals("Deberia cumplirse que se logeo al mismo administrador por el metodo 'login'.",Administrador.getInstance(),admin);

	}

	
	
	
	
	
	@Test
	public void testLogoutAdministrador() {
		File arch = new File("empresa.bin");
		if(arch.exists())
		  arch.delete();
		
		try {
			Empresa.getInstance().login("admin","admin");
		} 
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		controlador.logout();
		assertTrue("El metodo 'logout()' del controlador NO deslogeo al administrador.",!Empresa.getInstance().isAdmin());
		assertTrue("El metodo 'logout()' del controlador no invoca al método escribir().", arch.exists());
	}
	
	@Test
	public void testLogoutCliente() {
		File arch = new File("empresa.bin");
		if(arch.exists())
		  arch.delete();
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			Empresa.getInstance().login("usuario1","1234");
		} 
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		controlador.logout();
		assertNull("El metodo 'logout()' del controlador NO deslogeo al cliente.", Empresa.getInstance().getUsuarioLogeado());
		assertTrue("El metodo 'logout()' del controlador no invoca al método escribir().", arch.exists());
	}
}
