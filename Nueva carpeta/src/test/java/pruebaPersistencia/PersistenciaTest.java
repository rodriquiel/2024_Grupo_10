package pruebaPersistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.UsuarioYaExisteException;
import modeloNegocio.Empresa;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

public class PersistenciaTest {

	String nombreArch = "persistencia.xml";
	
	@Before
	public void setUp() {
		File archivo = new File(this.nombreArch);
		if(archivo.exists()) {
			archivo.delete();
		}
	}
	
	@Test
	public void testCrearArchivo() {
		try {
			File archivo = new File(this.nombreArch);
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			PersistenciaBIN persistencia = new PersistenciaBIN();
			persistencia.abrirOutput(this.nombreArch);
			persistencia.escribir(empresaDTO);
			assertTrue("Deberia existir el archivo "+this.nombreArch, archivo.exists());
		}
		catch (FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void testEmpresaClientesVacio() {
		try {
			PersistenciaBIN persistencia = new PersistenciaBIN();
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput(this.nombreArch);
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput(this.nombreArch);
			empresaDTO = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTO);
			persistencia.cerrarInput();
			assertEquals("El HashMap de pedidos deberia estar vacio", Empresa.getInstance().getClientes().size(),0);
		} catch (FileNotFoundException e1) {
			fail("No deberia lanzar excepcion: "+ e1.getMessage());
		} catch (IOException e2) {
			
		} catch (ClassNotFoundException e3) {
			fail("No deberia lanzar excepcion: "+ e3.getMessage());
		}
	}
	
	@Test public void testEmpresaClientes() {
		try {
			PersistenciaBIN persistencia = new PersistenciaBIN();
			Empresa.getInstance().agregarCliente("Juan", "1234", "Juan Carlos");
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput(this.nombreArch);
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput(this.nombreArch);
			empresaDTO = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTO);
			persistencia.cerrarInput();
			assertEquals("El HashMap de pedidos deberia tener un cliente", Empresa.getInstance().getClientes().size(),1);
		} catch (FileNotFoundException e1) {
			fail("No deberia lanzar excepcion: "+ e1.getMessage());
		} catch (IOException e2) {
			
		} catch (ClassNotFoundException e3) {
			fail("No deberia lanzar excepcion: "+ e3.getMessage());
		} catch (UsuarioYaExisteException e4) {
			fail("No deberia lanzar excepcion: "+ e4.getMessage());		}
	}
	
	@After
	public void tearDown() {
		Empresa.getInstance().getClientes().clear();
	}
}
