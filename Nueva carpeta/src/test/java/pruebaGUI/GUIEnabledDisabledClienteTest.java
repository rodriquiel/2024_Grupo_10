package pruebaGUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class GUIEnabledDisabledClienteTest {

	Robot robot;
	Controlador controlador;
	
	public GUIEnabledDisabledClienteTest(){
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		this.controlador = new Controlador();
		Empresa.getInstance().agregarCliente("Juan", "1234", "Juan Perez");
	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) this.controlador.getVista();
		ventana.setVisible(false);
		Empresa.getInstance().getClientes().clear();
	}
	
	private void ingresoCliente(Ventana ventana) {
		this.robot.delay(TestUtils.getDelay());
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(aceptarLogin, robot);
		this.robot.delay(TestUtils.getDelay());
	}

	@Test
	public void testVentanaClienteVacia() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		this.robot.delay(TestUtils.getDelay());
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName(ventana, Constantes.CERRAR_SESION_CLIENTE);
		JButton calificarPagar = (JButton) TestUtils.getComponentForName(ventana, Constantes.CALIFICAR_PAGAR);
		//JTextArea pedidoViajeAct = (JTextArea) TestUtils.getComponentForName(ventana,Constantes.PEDIDO_O_VIAJE_ACTUAL);
		JTextField calificacion = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CALIFICACION_DE_VIAJE);
		JTextField valorViaje = (JTextField) TestUtils.getComponentForName(ventana,Constantes.VALOR_VIAJE);
		JTextField cantPax = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_KM);
		JRadioButton btnStandar = (JRadioButton)  TestUtils.getComponentForName(ventana,Constantes.ZONA_STANDARD);
		JRadioButton btnSinAsfaltar = (JRadioButton)  TestUtils.getComponentForName(ventana,Constantes.ZONA_SIN_ASFALTAR);
		JRadioButton btnPeligrosa = (JRadioButton)  TestUtils.getComponentForName(ventana,Constantes.ZONA_PELIGROSA);
		JCheckBox btnMascota = (JCheckBox) TestUtils.getComponentForName(ventana, Constantes.CHECK_MASCOTA);
		JCheckBox btnBaul = (JCheckBox) TestUtils.getComponentForName(ventana, Constantes.CHECK_BAUL);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);
		//JTextArea viajesCliente = (JTextArea) TestUtils.getComponentForName(ventana,Constantes.LISTA_VIAJES_CLIENTE);
		
		Assert.assertTrue("El boton de cerrar sesion deberia estar habilitado", cerrarSesion.isEnabled());
		Assert.assertTrue("El TextField de cantidad pasajeros deberia estar habilitado", cantPax.isEnabled());
		Assert.assertTrue("El TextField de kilometros deberia estar habilitado", cantKm.isEnabled());
		Assert.assertTrue("El boton de zona estandar deberia estar habilitado", btnStandar.isEnabled());
		Assert.assertTrue("El boton de zona sin asfaltar deberia estar habilitado", btnSinAsfaltar.isEnabled());
		Assert.assertTrue("El boton de zona peligrosa deberia estar habilitado", btnPeligrosa.isEnabled());
		Assert.assertTrue("El check de mascota deberia estar habilitado", btnMascota.isEnabled());
		Assert.assertTrue("El check de baul deberia estar habilitado", btnBaul.isEnabled());
		
		Assert.assertFalse("El boton de calificar y pagar deberia estar deshabilitado",calificarPagar.isEnabled());
		Assert.assertFalse("El TextField de calificacion deberia estar deshabilitado",calificacion.isEnabled());
		Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevoPedido.isEnabled());
		//Assert.assertNull("El TextField de valor deberia estar vacio",valorViaje.getText());
	}
	
	@Test
	public void testNuevoPedidoSoloPasajeros() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		this.robot.delay(TestUtils.getDelay());
		JTextField cantPax = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_PAX);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("2", robot);
		
		Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevoPedido.isEnabled());
	}

	@Test
	public void testNuevoPedidoSoloKm() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		this.robot.delay(TestUtils.getDelay());
		JTextField cantKm = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_KM);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);

		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("2", robot);
		
		Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testNuevoPedidoCompletoValido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		this.robot.delay(TestUtils.getDelay());
		JTextField cantPax = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_KM);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("2", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("2", robot);
		this.robot.delay(TestUtils.getDelay());
		
		Assert.assertTrue("El boton de nuevo pedido deberia estar habilitado",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testNuevoPedidoCompletoKmInvalido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		this.robot.delay(TestUtils.getDelay());
		JTextField cantPax = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_KM);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("2", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("-1", robot);
		
		Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testNuevoPedidoCompletoPaxInvalido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		this.robot.delay(TestUtils.getDelay());
		JTextField cantPax = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_KM);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("0", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("2", robot);
		
		Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevoPedido.isEnabled());
	}
	
	
}
