package pruebaGUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloDatos.Administrador;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class GUILogRegConDatosTest {
	
	Robot robot;
	Controlador controlador;
	FalsoOptionPane op;
	Empresa empresa= Empresa.getInstance();
	
	public GUILogRegConDatosTest() {
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
	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) this.controlador.getVista();
		ventana.setVisible(false);
		this.empresa.getClientes().clear();
	}

	@Test
	public void testLogOkAdmin() {
		Administrador admin = Administrador.getInstance();
		Ventana ventana = (Ventana) this.controlador.getVista();
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
        
        Assert.assertEquals("Deberia coincidir el nombre de usuario con el nombre admin", "admin", admin.getNombreUsuario());
		JPanel panelAdmin = (JPanel) TestUtils.getComponentForName(ventana, Constantes.PANEL_ADMINISTRADOR);
        Assert.assertNotNull("No se abrio la ventana de Administrador",panelAdmin);
    }
	
	@Test
	public void testLogOkCliente() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("juan", robot);
        TestUtils.clickComponent(pass, robot);
        TestUtils.tipeaTexto("1234", robot);
        TestUtils.clickComponent(aceptarLogin, robot);
        this.robot.delay(TestUtils.getDelay());
        Assert.assertEquals("Deberia coincidir el nombre de usuario con el nombre juan", "juan", this.empresa.getClientes().get("juan").getNombreUsuario());
        //Como pruebo que el pane haya cambiado al que debia??
        JPanel panelCliente = (JPanel) TestUtils.getComponentForName(ventana, Constantes.PANEL_CLIENTE);
        Assert.assertNotNull("No se abrio la ventana de Cliente",panelCliente);
        //Como compruebo que el titulo (nombre real cliente) coincida con el nombre del cliente logueado?
        TitledBorder t = (TitledBorder)panelCliente.getBorder();
        Assert.assertEquals("El titulo del panel deberia ser Juan Perez","Juan Perez",t.getTitle());
	}
	
	@Test
	public void testLogUsuarioDesconocidoConDatos() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("carlos", robot);
        TestUtils.clickComponent(pass, robot);
        TestUtils.tipeaTexto("5678", robot);
        TestUtils.clickComponent(aceptarLogin, robot);
        
        Assert.assertEquals("Deberia decir: "+Mensajes.USUARIO_DESCONOCIDO.getValor(), Mensajes.USUARIO_DESCONOCIDO.getValor(), op.getMensaje());
     }
	
	@Test
	public void testPassErroneo() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("juan", robot);
        TestUtils.clickComponent(pass, robot);
        TestUtils.tipeaTexto("1235", robot);
        TestUtils.clickComponent(aceptarLogin, robot);
        
        Assert.assertEquals("Deberia decir: "+Mensajes.PASS_ERRONEO.getValor(), Mensajes.PASS_ERRONEO.getValor(), op.getMensaje());
	}
	
	@Test
	public void testUsuarioRepetido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		TestUtils.clickComponent(registrar, robot);
		this.robot.delay(TestUtils.getDelay());

		JTextField confPass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		JButton registrar2 = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(confPass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Juan Perez", robot);

		TestUtils.clickComponent(registrar2, robot);
		
		this.robot.delay(TestUtils.getDelay()*2);
		
        Assert.assertEquals("Deberia decir: "+Mensajes.USUARIO_REPETIDO.getValor(), Mensajes.USUARIO_REPETIDO.getValor(), op.getMensaje());
	
	}

}
