package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;

class ChoferTemporarioTest {

	@Before
	public void setUp() {
		Chofer.setSueldoBasico(2000.0);
	}
	
	@Test
	public void testConstructorChofer() {
		ChoferTemporario chofer = new ChoferTemporario("24532189", "Pedro");
		assertNotNull(chofer, "El chofer debe ser distinto de null");
	}
	
	@Test
	public void testGetDni() {
		ChoferTemporario chofer = new ChoferTemporario("24532189", "Pedro");
		String dniEsperado = "24532189";
		assertEquals(dniEsperado,chofer.getDni(),"El DNI debe ser " + dniEsperado);
	}
	
	@Test
	public void testGetNombre(){
		ChoferTemporario chofer = new ChoferTemporario("24532189", "Pedro");
		String nombreEsperado = "Pedro";
		assertEquals(nombreEsperado,chofer.getNombre(),"El nombre debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetSueldoBasico() {
		double resultadoEsperado = 2000.0;
		double resultadoReal = Chofer.getSueldoBasico();
		assertEquals(resultadoReal,resultadoEsperado,"El sueldo basico debe ser " + resultadoEsperado);
	}
	
	@Test
	public void testGetSueldoNetoyBruto() {
		ChoferTemporario chofer = new ChoferTemporario("24532189", "Pedro");
		assertEquals(chofer.getSueldoNeto(),chofer.getSueldoBruto(),"El sueldo bruto debe ser igual al neto para temporarios");
	}
	
	
	
	
	

}
