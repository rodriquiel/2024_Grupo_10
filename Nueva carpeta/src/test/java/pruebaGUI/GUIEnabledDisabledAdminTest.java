package pruebaGUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import util.Constantes;
import vista.Ventana;

public class GUIEnabledDisabledAdminTest {

	Robot robot;
	Controlador controlador;
	
	public GUIEnabledDisabledAdminTest(){
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
	
	private void ingresoAdmin(Ventana ventana) {
		this.robot.delay(TestUtils.getDelay());
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(aceptarLogin, robot);
		this.robot.delay(TestUtils.getDelay());
	}


	@Test
	public void testVentanaAdminVacia() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JButton agregarVehiculo = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_VEHICULO);
		JButton NuevoViaje = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_VIAJE);
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName(ventana, Constantes.CERRAR_SESION_ADMIN);
		
		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado",cerrarSesion.isEnabled());
		Assert.assertFalse("El boton de agregar chofer deberia estar deshabilitado",agregarChofer.isEnabled());
		Assert.assertFalse("El boton de agregar vehiculo deberia estar deshabilitado",agregarVehiculo.isEnabled());
		Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado",NuevoViaje.isEnabled());
	}
	
	@Test
	public void testAgregarChoferSoloDNI() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtils.getComponentForName(ventana, Constantes.DNI_CHOFER);
		
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("12345678", robot);
		
		Assert.assertFalse("El boton de agregar chofer deberia estar deshabilitado",agregarChofer.isEnabled());		
	}

	@Test
	public void testAgregarChoferSoloNombre() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_CHOFER);
		
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Carlos", robot);
		
		Assert.assertFalse("El boton de agregar chofer deberia estar deshabilitado",agregarChofer.isEnabled());		
	}
	
	@Test
	public void testAgregarChoferSoloCantidadHijos() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_CANT_HIJOS);
		
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("1", robot);
		
		Assert.assertFalse("El boton de agregar chofer deberia estar deshabilitado",agregarChofer.isEnabled());		
	}
	
	@Test
	public void testAgregarChoferSoloAnio() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField anio = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_ANIO);
		
		TestUtils.clickComponent(anio, robot);
		TestUtils.tipeaTexto("2024", robot);
		
		Assert.assertFalse("El boton de agregar chofer deberia estar deshabilitado",agregarChofer.isEnabled());		
	}
	
	@Test
	public void testAgregarChoferCompletoValidoPerm() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JRadioButton btnPermanente = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PERMANENTE);
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtils.getComponentForName(ventana, Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_CHOFER);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_CANT_HIJOS);
		JTextField anio = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_ANIO);
		
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("12345678", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Carlos", robot);
		TestUtils.clickComponent(btnPermanente, robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(anio, robot);
		TestUtils.tipeaTexto("2024", robot);
		
		this.robot.delay(TestUtils.getDelay());
		Assert.assertTrue("El boton de agregar chofer deberia estar habilitado",agregarChofer.isEnabled());		
	}
	
	
	@Test
	public void testAgregarChoferCompletoInvalidoPerm1() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JRadioButton btnPermanente = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PERMANENTE);
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtils.getComponentForName(ventana, Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_CHOFER);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_CANT_HIJOS);
		JTextField anio = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_ANIO);
		
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("12345678", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Carlos", robot);
		TestUtils.clickComponent(btnPermanente, robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("-1", robot);
		TestUtils.clickComponent(anio, robot);
		TestUtils.tipeaTexto("2024", robot);
		
		Assert.assertFalse("El boton de agregar chofer deberia estar deshabilitado",agregarChofer.isEnabled());		
	}
	
	@Test
	public void testAgregarChoferCompletoInvalidoPerm2() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JRadioButton btnPermanente = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PERMANENTE);
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtils.getComponentForName(ventana, Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_CHOFER);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_CANT_HIJOS);
		JTextField anio = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_ANIO);
		
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("12345678", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Carlos", robot);
		TestUtils.clickComponent(btnPermanente, robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(anio, robot);
		TestUtils.tipeaTexto("1899", robot);
		
		Assert.assertFalse("El boton de agregar chofer deberia estar deshabilitado",agregarChofer.isEnabled());		
	}
	
	@Test
	public void testAgregarChoferCompletoValidoTemp() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JRadioButton btnTemp = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.TEMPORARIO);
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtils.getComponentForName(ventana, Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_CHOFER);
		
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("12345678", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Carlos", robot);
		TestUtils.clickComponent(btnTemp, robot);
		
		Assert.assertTrue("El boton de agregar chofer deberia estar habilitado",agregarChofer.isEnabled());		
	}
}
