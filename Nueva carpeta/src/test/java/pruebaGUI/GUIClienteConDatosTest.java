package pruebaGUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class GUIClienteConDatosTest {

	Robot robot;
	Controlador controlador;
	FalsoOptionPane op;
	Empresa empresa= Empresa.getInstance();
	
	public GUIClienteConDatosTest() {
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
		this.empresa.agregarCliente("juan", "1234", "Juan Perez");
		Cliente cliente = new Cliente("Juan", "1234", "Juan Perez");
		
		this.empresa.agregarCliente("admin","admin","admin");
		
		Auto auto = new Auto("ABC456", 4, true);
		this.empresa.agregarVehiculo(auto);
		ChoferPermanente chofer = new ChoferPermanente("12345678", "Chofer", 2020, 2);
		this.empresa.agregarChofer(chofer);
		Pedido pedido = new Pedido(cliente, 2, true, false, 2, Constantes.ZONA_STANDARD);
		this.empresa.agregarPedido(pedido);
		this.empresa.crearViaje(pedido, chofer, auto);
	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) this.controlador.getVista();
		ventana.setVisible(false);
		this.empresa.getClientes().clear();
		this.empresa.getVehiculos().clear();
		this.empresa.getChoferes().clear();
		this.empresa.getPedidos().clear();
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
	public void testCalificarViaje() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoCliente(ventana);
		
		this.robot.delay(TestUtils.getDelay());
		JButton calificarPagar = (JButton) TestUtils.getComponentForName(ventana, Constantes.CALIFICAR_PAGAR);
		JTextArea pedidoViajeAct = (JTextArea) TestUtils.getComponentForName(ventana,Constantes.PEDIDO_O_VIAJE_ACTUAL);
		JTextField calificacion = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CALIFICACION_DE_VIAJE);
	
		
	}

}
