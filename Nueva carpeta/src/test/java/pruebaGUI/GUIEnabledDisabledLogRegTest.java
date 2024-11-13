package pruebaGUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import util.Constantes;
import vista.Ventana;


public class GUIEnabledDisabledLogRegTest {

	Robot robot;
	Controlador controlador;
	
	
	
	public GUIEnabledDisabledLogRegTest() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		this.controlador = new Controlador();
	}
	

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) this.controlador.getVista();
		ventana.setVisible(false);
	}

	@Test
	public void testLoginVacios() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		Assert.assertFalse("El boton de login deberia estar deshabilitado", aceptarLogin.isEnabled());
	}
	
	@Test
	public void testLoginSoloNombre() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		
		Assert.assertFalse("El boton de login deberia estar deshabilitado", aceptarLogin.isEnabled());
	}
	
	@Test
	public void testLoginSoloPass() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		
		Assert.assertFalse("El boton de login deberia estar deshabilitado", aceptarLogin.isEnabled());
	}
	
	@Test
	public void testLoginCompleto() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);

		Assert.assertTrue("El boton de login deberia estar habilitado", aceptarLogin.isEnabled());
	}
	
	@Test
	public void testRegistroBtnCancelar() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JButton cancelar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_CANCELAR);
		
		Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelar.isEnabled());
	}
	
	@Test
	public void testRegistroVacio() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroSoloUsuario() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroSoloPass() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}

	@Test
	public void testRegistroSoloConfirmarPass() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField confPass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		TestUtils.clickComponent(confPass, robot);
		TestUtils.tipeaTexto("1234", robot);
		
		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroSoloNombre() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Juan Perez", robot);

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroSinUsuario() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField confPass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);

		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(confPass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Juan Perez", robot);

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroSinPass() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField confPass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);

		this.robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(confPass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Juan Perez", robot);

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroSinConfirma() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Juan Perez", robot);

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroSinNombre() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField confPass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(confPass, robot);
		TestUtils.tipeaTexto("1234", robot);


		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registrar2.isEnabled());
	}
	
	@Test
	public void testRegistroLleno() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField confPass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(confPass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Juan Perez", robot);

		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		Assert.assertTrue("El boton de registro deberia estar habilitado", registrar2.isEnabled());
	}
}
