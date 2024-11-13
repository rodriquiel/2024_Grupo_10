package pruebaGUI;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class GUIAdminConDatosTest {
	
	Robot robot;
	Controlador controlador;
	FalsoOptionPane op;
	Empresa empresa= Empresa.getInstance();
	
	public GUIAdminConDatosTest() {
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
		//Cliente cliente = new Cliente("Juan", "1234", "Juan Perez");
		this.empresa.agregarCliente("juan", "1234", "Juan Perez");
		this.empresa.agregarCliente("jose", "1111", "Jose Gutierrez");
		this.empresa.agregarCliente("martin", "1122", "Martin Gomez");
		this.empresa.agregarCliente("admin","admin","admin");
		
		Auto auto = new Auto("ABC456", 4, true);
		Moto moto = new Moto("DEF789");
		this.empresa.agregarVehiculo(auto);
		this.empresa.agregarVehiculo(moto);
		ChoferPermanente chofer = new ChoferPermanente("12345678", "Chofer", 2020, 2);
		ChoferTemporario chofert = new ChoferTemporario("11223344", "Carlitos");
		this.empresa.agregarChofer(chofer);
		this.empresa.agregarChofer(chofert);
		
		Pedido pedido1 = new Pedido(this.empresa.getClientes().get("juan"), 2, true, false, 2, Constantes.ZONA_STANDARD);
		this.empresa.agregarPedido(pedido1);
		Pedido pedido2 = new Pedido(this.empresa.getClientes().get("jose"), 1, false, false, 5, Constantes.ZONA_SIN_ASFALTAR);
		this.empresa.agregarPedido(pedido2);
		Pedido pedido3 = new Pedido(this.empresa.getClientes().get("martin"), 1, false, false, 4, Constantes.ZONA_PELIGROSA);
		this.empresa.agregarPedido(pedido3);
		this.empresa.crearViaje(pedido1, chofer, auto);

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
	
	private void terminarViaje(Ventana ventana) {
		this.robot.delay(TestUtils.getDelay());
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("juan", robot);
		TestUtils.clickComponent(pass, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(aceptarLogin, robot);
		
		JButton calificarPagar = (JButton) TestUtils.getComponentForName(ventana, Constantes.CALIFICAR_PAGAR);
		JTextField calificacion = (JTextField) TestUtils.getComponentForName(ventana,Constantes.CALIFICACION_DE_VIAJE);
		JButton cerrarsesion = (JButton) TestUtils.getComponentForName(ventana, Constantes.CERRAR_SESION_CLIENTE);
		this.robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(calificacion, robot);
		TestUtils.tipeaTexto("4", robot);
		TestUtils.clickComponent(calificarPagar, robot);
		this.robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(cerrarsesion, robot);
		
	}

	@Test
	public void testCrearViajeValido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		
		JList pedidosPendientes = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_PEDIDOS_PENDIENTES);
		JList choferesLibres = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_CHOFERES_LIBRES);
		JList vehiculosDisp = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VEHICULOS_DISPONIBLES);
		JButton NuevoViaje = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_VIAJE);
		
		Assert.assertEquals("La cantidad de elementos de la lista de vehiculos disponibles deberia ser 0",vehiculosDisp.getModel().getSize(),0);
		
		pedidosPendientes.setSelectedIndex(0);
		TestUtils.clickComponent(pedidosPendientes, robot);
		this.robot.delay(TestUtils.getDelay());
		
		Assert.assertNotEquals("La cantidad de elementos de la lista de vehiculos disponibles no deberia ser 0",vehiculosDisp.getModel().getSize(),0);
		
		choferesLibres.setSelectedIndex(0);
		TestUtils.clickComponent(choferesLibres, robot);
		vehiculosDisp.setSelectedIndex(0);
		TestUtils.clickComponent(vehiculosDisp, robot);
		TestUtils.clickComponent(NuevoViaje, robot);
		this.robot.delay(TestUtils.getDelay());

		Assert.assertEquals("La cantidad de elementos de la lista de pedidos pendientes deberia ser 1",1,pedidosPendientes.getModel().getSize());
		Assert.assertEquals("La cantidad de elementos de la lista de choferes libres deberia ser 0",0,choferesLibres.getModel().getSize());
		Assert.assertEquals("La cantidad de elementos de la lista de vehiculos disponibles deberia ser 0",0,vehiculosDisp.getModel().getSize());	
		Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado",NuevoViaje.isEnabled());
	}
	
	@Test
	public void testCrearViajeInvalido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JList pedidosPendientes = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_PEDIDOS_PENDIENTES);
		JList vehiculosDisp = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VEHICULOS_DISPONIBLES);
		JList choferesLibres = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_CHOFERES_LIBRES);
		JButton NuevoViaje = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_VIAJE);

		pedidosPendientes.setSelectedIndex(0);
		TestUtils.clickComponent(pedidosPendientes, robot);
		choferesLibres.setSelectedIndex(0);
		TestUtils.clickComponent(choferesLibres, robot);
		vehiculosDisp.setSelectedIndex(0);
		TestUtils.clickComponent(vehiculosDisp, robot);
		TestUtils.clickComponent(NuevoViaje, robot);
		
		pedidosPendientes.setSelectedIndex(0);
		TestUtils.clickComponent(pedidosPendientes, robot);
		this.robot.delay(TestUtils.getDelay());
		
		
		Assert.assertEquals("La cantidad de elementos de la lista de vehiculos disponibles deberia ser 0",0,vehiculosDisp.getModel().getSize());
		Assert.assertFalse("El boton de nuevo viaje deberia estar deshabilitado",NuevoViaje.isEnabled());

	}
	
	@Test
	public void testGestionChoferyViajes() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		terminarViaje(ventana);
		ingresoAdmin(ventana);
		
		JList viajesChofer = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VIAJES_DE_CHOFER);
		JList choferesTotal = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_CHOFERES_TOTALES);
		JTextField puntaje = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CALIFICACION_CHOFER);
		JTextField sueldo = (JTextField) TestUtils.getComponentForName(ventana, Constantes.SUELDO_DE_CHOFER);
		JList histViajes = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VIAJES_HISTORICOS);
		
		choferesTotal.setSelectedIndex(0);
		TestUtils.clickComponent(choferesTotal, robot);
		
		Assert.assertNotEquals("La cantidad de elementos de la lista de viajes del chofer deberia ser distinto de 0",0,viajesChofer.getModel().getSize());
		Assert.assertNotEquals("La cantidad de elementos de la lista de viajes historico deberia ser distinto de 0",0,histViajes.getModel().getSize());

		Assert.assertEquals("El puntaje del chofer deberia ser 4", "4.0", puntaje.getText());
		Assert.assertNotEquals("El salario del chofer deberia ser distinto de 0", "0.0",sueldo.getText());		
	}
	
	@Test
	public void testListadoClientes() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JList clientes = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTADO_DE_CLIENTES);
		Assert.assertEquals("La cantidad de elementos de la lista de viajes del chofer deberia ser 4",4,clientes.getModel().getSize());
	}
	
	@Test
	public void testSueldosAPagar() {
		float total = 0;
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JTextField sueldos = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TOTAL_SUELDOS_A_PAGAR);

		Iterator<Chofer> iterator = this.empresa.iteratorChoferes();
		while (iterator.hasNext()) {
		    Chofer ch = iterator.next();
		    total += ch.getSueldoBruto();
		}
		
		String sueldosAPagar = sueldos.getText().split(",")[0];
		
		Assert.assertEquals(total,Float.valueOf(sueldosAPagar),0.01);
	}
	
	

}
