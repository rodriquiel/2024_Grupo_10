package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import persistencia.PersistenciaBIN;
import vista.IVista;
import vista.Ventana;


public class ControladorTest {

	Controlador controlador;
	
	@Before
	public void SetUp() {
		controlador=new Controlador();
	}
	
	@Test
	public void  TestControlador()
	{
		assertNotEquals(controlador.getVista(),null);
		assertNotEquals(controlador.getPersistencia(),null);
		assertEquals(controlador.getFileName(),"empresa.bin");
	}
	
	@Test
	public void leerTest()
	{
		File arch=new File("empresa.bin");
		if(arch.exists())
			arch.delete();
		
		controlador.leer();
		if(!arch.exists())
			fail("");
		
	}
	
	@Test
	public void escribirTest()
	{
		File arch=new File("empresa.bin");
		controlador.escribir();
		assertTrue(arch.exists());
	}
	
	@Test
	public void getYsetVistaTest()
	{
		IVista vista=new Ventana();
		controlador.setVista(vista);
		assertEquals(controlador.getVista(),vista);

	}
	@Test
	public void GetySetPersistenciaBINTest()
	{
		PersistenciaBIN perp=new PersistenciaBIN();
		controlador.setPersistencia(perp);
		assertEquals(controlador.getPersistencia(),perp);
	}
	
	@Test
	public void GetYsetFilenameTest()
	{
		controlador.setFileName("salida.bin");
		assertEquals(controlador.getFileName(),"salida.bin");
	}
	
	@Test
	public void NuevoViajeTest()
	{
		
	}
	
	
}
