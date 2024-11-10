package pruebaControlador;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import persistencia.PersistenciaBIN;
import vista.IVista;
import vista.Ventana;

public class ControladorTestMetodosBasicos {
	Controlador controlador;

	@Before
	public void setUp(){
		controlador=new Controlador();
	}

	@After
	public void tearDown(){
		controlador = null;
	}
	

	@Test
	public void testControlador() {
		assertNotNull("El atributo 'vista' NO deberia ser nulo.",controlador.getVista());
		assertNotNull("El atributo 'persistencia' NO deberia ser nulo.", controlador.getPersistencia());
		assertEquals("El atributo 'fileName' NO se inicializa con 'empresa.bin'.","empresa.bin",controlador.getFileName());
	}
	
	@Test
	public void getYsetVistaTest()
	{
		IVista vista=new Ventana();
		controlador.setVista(vista);
		assertEquals("El geter o setter del atributo 'vista' anda mal.",vista,controlador.getVista());

	}
	@Test
	public void GetySetPersistenciaBINTest()
	{
		PersistenciaBIN perp=new PersistenciaBIN();
		controlador.setPersistencia(perp);
		assertEquals("El geter o setter del atributo 'persistencia' anda mal.",perp,controlador.getPersistencia());
	}
	
	@Test
	public void GetYsetFilenameTest()
	{
		controlador.setFileName("salida.bin");
		assertEquals("El geter o setter del atributo 'fileName' anda mal.","salida.bin",controlador.getFileName());
	}

}
