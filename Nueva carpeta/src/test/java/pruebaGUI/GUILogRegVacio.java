package pruebaGUI;

import static org.junit.Assert.*;

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
import util.Mensajes;
import vista.Ventana;

public class GUILogRegVacio {

	Robot robot;
	Controlador controlador;
	FalsoOptionPane op; 
	
	public GUILogRegVacio() {
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
	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) this.controlador.getVista();
		ventana.setVisible(false);
	}

	@Test
	public void testUsuarioDesconocido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
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
		
        Assert.assertEquals("Deberia decir: "+Mensajes.USUARIO_DESCONOCIDO.getValor(), Mensajes.USUARIO_DESCONOCIDO.getValor(), op.getMensaje());
	}
	
	@Test
	public void testPassNoCoincide() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		
		Ventana ventanaReg = (Ventana) this.controlador.getVista();
		JTextField confPass = (JTextField) TestUtils.getComponentForName(ventanaReg, Constantes.REG_CONFIRM_PASSWORD);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventanaReg, Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventanaReg, Constantes.REG_PASSWORD);
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventanaReg, Constantes.REG_USSER_NAME);
		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventanaReg, Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(confPass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Juan Perez", robot);

		TestUtils.clickComponent(registrar2, robot);
		
		this.robot.delay(TestUtils.getDelay());
		
        Assert.assertEquals("Deberia decir: "+Mensajes.PASS_NO_COINCIDE.getValor(), Mensajes.PASS_NO_COINCIDE.getValor(), op.getMensaje());
	}
	
}
