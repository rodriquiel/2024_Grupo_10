package pruebaGUI;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Moto;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class GUIClienteVacioTest {

	Robot robot;
	Controlador controlador;
	FalsoOptionPane op; 
	
	public GUIClienteVacioTest() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		this.controlador = new Controlador();
		this.op = new FalsoOptionPane();
		this.controlador.getVista().setOptionPane(op);
		Empresa.getInstance().agregarCliente("juan", "1234", "Juan Perez");

	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) this.controlador.getVista();
		ventana.setVisible(false);
		Empresa.getInstance().getClientes().clear();
		Empresa.getInstance().getVehiculos().clear();
	}

	private void ingresoCliente(Ventana ventana) {
		this.robot.delay(TestUtils.getDelay());
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(aceptarLogin, robot);
		this.robot.delay(TestUtils.getDelay());
	}
	
	@Test
	public void generarPedidoSinVehiculo() {
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
		TestUtils.clickComponent(nuevoPedido, robot);
		this.robot.delay(TestUtils.getDelay()*2);
		
		Assert.assertEquals("Deberia decir: "+Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), op.getMensaje());	
	}

	@Test
	public void generarPedidoConVehiculoNoCumple() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		Moto moto = new Moto("ABC123");
		try {
			Empresa.getInstance().agregarVehiculo(moto);
		} catch (VehiculoRepetidoException e) {
			fail("No se pudo agregar el vehiculo");
		}
		
		this.robot.delay(TestUtils.getDelay());
		JTextField cantPax = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_KM);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);
		JCheckBox btnMascota = (JCheckBox) TestUtils.getComponentForName(ventana, Constantes.CHECK_MASCOTA);

		
		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("2", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("2", robot);
		TestUtils.clickComponent(btnMascota, robot);
		TestUtils.clickComponent(nuevoPedido, robot);
		this.robot.delay(TestUtils.getDelay()*2);
		
		Assert.assertEquals("Deberia decir: "+Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), op.getMensaje());	
	}
	
	@Test
	public void generarPedidoConVehiculoCumple() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		Auto auto = new Auto("ABC456", 4, true);
		try {
			Empresa.getInstance().agregarVehiculo(auto);
		} catch (VehiculoRepetidoException e) {
			fail("No se pudo agregar el vehiculo");
		}
		
		this.robot.delay(TestUtils.getDelay());
		JTextField cantPax = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_PAX);
		JTextField cantKm = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CANT_KM);
		JButton nuevoPedido = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_PEDIDO);
		JCheckBox btnMascota = (JCheckBox) TestUtils.getComponentForName(ventana, Constantes.CHECK_MASCOTA);
		JCheckBox btnBaul = (JCheckBox) TestUtils.getComponentForName(ventana, Constantes.CHECK_BAUL);
		JRadioButton btnStandar = (JRadioButton)  TestUtils.getComponentForName(ventana,Constantes.ZONA_STANDARD);
		JRadioButton btnSinAsfaltar = (JRadioButton)  TestUtils.getComponentForName(ventana,Constantes.ZONA_SIN_ASFALTAR);
		JRadioButton btnPeligrosa = (JRadioButton)  TestUtils.getComponentForName(ventana,Constantes.ZONA_PELIGROSA);
		JTextArea pedidoViajeAct = (JTextArea) TestUtils.getComponentForName(ventana,Constantes.PEDIDO_O_VIAJE_ACTUAL);
		JTextField calificacion = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CALIFICACION_DE_VIAJE);
		JTextField valorViaje = (JTextField) TestUtils.getComponentForName(ventana,Constantes.VALOR_VIAJE);

		TestUtils.clickComponent(cantPax, robot);
		TestUtils.tipeaTexto("2", robot);
		TestUtils.clickComponent(cantKm, robot);
		TestUtils.tipeaTexto("2", robot);
		TestUtils.clickComponent(btnMascota, robot);
		TestUtils.clickComponent(nuevoPedido, robot);
		this.robot.delay(TestUtils.getDelay());
		
		//Se prueba los botones y campos deshabilitados aca ya que no tenia mucho sentido crear un vehiculo y un pedido solo para probar el Enabled de los componentes
		Assert.assertNotNull("La lista de pedidos no deberia ser nula",pedidoViajeAct.getText());
		Assert.assertFalse("El TextField de cantidad de pasajeros deberia estar deshabilitado",cantPax.isEnabled());
		Assert.assertFalse("El TextField de cantidad de km deberia estar deshabilitado",cantKm.isEnabled());
		Assert.assertFalse("El boton de zona estandar deberia estar deshabilitado",btnStandar.isEnabled());
		Assert.assertFalse("El boton de zona sin asfaltar deberia estar deshabilitado",btnSinAsfaltar.isEnabled());
		Assert.assertFalse("El boton de zona peligrosa deberia estar deshabilitado",btnPeligrosa.isEnabled());
		Assert.assertFalse("El boton de mascota deberia estar deshabilitado",btnMascota.isEnabled());
		Assert.assertFalse("El boton de baul deberia estar deshabilitado",btnBaul.isEnabled());
		Assert.assertTrue("El TextField valor de viaje deberia estar vacio",valorViaje.getText().trim().isEmpty());
		Assert.assertFalse("El campo para calificar el viaje deberia estar deshabilitado",calificacion.isEnabled());
	}
}
