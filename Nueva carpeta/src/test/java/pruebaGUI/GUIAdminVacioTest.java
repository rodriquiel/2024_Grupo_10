package pruebaGUI;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JList;
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

public class GUIAdminVacioTest {
	
	Robot robot;
	Controlador controlador;
	FalsoOptionPane op; 
	
	public GUIAdminVacioTest() {
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
		Empresa.getInstance().agregarCliente("admin", "admin", "admin");
	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) this.controlador.getVista();
		ventana.setVisible(false);
		Empresa.getInstance().getClientes().clear();
		Empresa.getInstance().getChoferes().clear();
		Empresa.getInstance().getVehiculos().clear();
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
	public void testAgregarChoferValido() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JRadioButton btnPermanente = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PERMANENTE);
		JButton agregarChofer = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtils.getComponentForName(ventana, Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_CHOFER);
		JTextField cantHijos = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_CANT_HIJOS);
		JTextField anio = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CH_ANIO);
		JList choferesTotal = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_CHOFERES_TOTALES);

		
		TestUtils.clickComponent(dni, robot);
		TestUtils.tipeaTexto("12345678", robot);
		TestUtils.clickComponent(nombre, robot);
		TestUtils.tipeaTexto("Carlos", robot);
		TestUtils.clickComponent(btnPermanente, robot);
		TestUtils.clickComponent(cantHijos, robot);
		TestUtils.tipeaTexto("1", robot);
		TestUtils.clickComponent(anio, robot);
		TestUtils.tipeaTexto("2024", robot);
		TestUtils.clickComponent(agregarChofer, robot);
		this.robot.delay(TestUtils.getDelay());
		
		choferesTotal.setSelectedIndex(0);
		TestUtils.clickComponent(choferesTotal, robot);
		this.robot.delay(TestUtils.getDelay());

		Assert.assertEquals("La cantidad de elementos de la lista de choferes no deberia ser 0",choferesTotal.getModel().getSize(),1);	
	}
	
	@Test
	public void testAgregarVehiculo() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JButton agregarVehiculo = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVO_VEHICULO);
		JRadioButton btnAuto = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.AUTO);
		JTextField cant = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CANTIDAD_PLAZAS);
		JTextField patente = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PATENTE);
		JList vehiculosTotal = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VEHICULOS_TOTALES);

		TestUtils.clickComponent(btnAuto, robot);
		TestUtils.clickComponent(patente, robot);
		TestUtils.tipeaTexto("ABC123", robot);
		TestUtils.clickComponent(cant, robot);
		TestUtils.tipeaTexto("4", robot);
		TestUtils.clickComponent(agregarVehiculo, robot);
		this.robot.delay(TestUtils.getDelay());
		
		vehiculosTotal.setSelectedIndex(0);
		TestUtils.clickComponent(vehiculosTotal, robot);
		this.robot.delay(TestUtils.getDelay());

		Assert.assertEquals("La cantidad de elementos de la lista de vehiculos deberia ser 1",vehiculosTotal.getModel().getSize(),1);	
	}
	
	@Test
	public void testListadosVacios() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JList vehiculosTotal = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VEHICULOS_TOTALES);
		JList choferesTotal = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_CHOFERES_TOTALES);
		JList pedidosPendientes = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_PEDIDOS_PENDIENTES);
		JList choferesLibres = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_CHOFERES_LIBRES);
		JList vehiculosDisp = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VEHICULOS_DISPONIBLES);
		JList viajesChofer = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VIAJES_DE_CHOFER);
		JList clientes = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTADO_DE_CLIENTES);
		JList histViajes = (JList) TestUtils.getComponentForName(ventana,Constantes.LISTA_VIAJES_HISTORICOS);
		
		Assert.assertEquals("La cantidad de elementos de la lista de vehiculos totales deberia ser 0",vehiculosTotal.getModel().getSize(),0);
		Assert.assertEquals("La cantidad de elementos de la lista de choferes totales deberia ser 0",choferesTotal.getModel().getSize(),0);	
		Assert.assertEquals("La cantidad de elementos de la lista de pedidos pendientes deberia ser 0",pedidosPendientes.getModel().getSize(),0);
		Assert.assertEquals("La cantidad de elementos de la lista de choferes libres deberia ser 0",choferesLibres.getModel().getSize(),0);
		Assert.assertEquals("La cantidad de elementos de la lista de vehiculos disponibles deberia ser 0",vehiculosDisp.getModel().getSize(),0);
		Assert.assertEquals("La cantidad de elementos de la lista de viajes del chofer seleccionado deberia ser 0",viajesChofer.getModel().getSize(),0);
		Assert.assertEquals("La cantidad de elementos de la lista de clientes deberia ser 1",clientes.getModel().getSize(),1);
		Assert.assertEquals("La cantidad de elementos de la lista de viajes historico deberia ser 0",histViajes.getModel().getSize(),0);
	}
	
	@Test
	public void testSueldosAPagarSinChoferes() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JTextField sueldos = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TOTAL_SUELDOS_A_PAGAR);
		
		Assert.assertEquals("El total de sueldos a pagar deberia ser 0,00",sueldos.getText(),"0,00");
	}
	
	@Test
	public void testInfoChoferSinChoferes() {
		Ventana ventana = (Ventana) this.controlador.getVista();
		this.robot.delay(TestUtils.getDelay());
		ingresoAdmin(ventana);
		
		JTextField puntaje = (JTextField) TestUtils.getComponentForName(ventana, Constantes.CALIFICACION_CHOFER);
		JTextField sueldo = (JTextField) TestUtils.getComponentForName(ventana, Constantes.SUELDO_DE_CHOFER);

		Assert.assertTrue("El JTextField de puntaje del chofer deberia estar vacio",puntaje.getText().isEmpty());
		Assert.assertTrue("El JTextField de sueldo del chofer deberia estar vacio",sueldo.getText().isEmpty());
	}

}
