package prueba;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;

class ChoferTemporarioTest {

	ChoferTemporario chofer;
	
	@Before
	public void setUp() {
		this.chofer = new ChoferTemporario("24532189", "Pedro");
		Chofer.setSueldoBasico(2000.0);
	}
	
	@Test
	public void testConstructorChofer() {
<<<<<<< Updated upstream
		assertNotNull(this.chofer, "El chofer debe ser distinto de null");
=======
		ChoferTemporario chofer = new ChoferTemporario("24532189", "Pedro");
		assertNotNull("El chofer debe ser distinto de null",chofer);
>>>>>>> Stashed changes
	}
	
	@Test
	public void testGetDni() {
		String dniEsperado = "24532189";
		assertEquals(dniEsperado,this.chofer.getDni(),"El DNI debe ser " + dniEsperado);
	}
	
	@Test
	public void testGetNombre(){
		String nombreEsperado = "Pedro";
		assertEquals(nombreEsperado,this.chofer.getNombre(),"El nombre debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetSueldoBasico() {
		double resultadoEsperado = 2000.0;
		double resultadoReal = Chofer.getSueldoBasico();
		assertEquals("El sueldo basico debe ser " + resultadoEsperado,resultadoReal,resultadoEsperado);
	}
	
	@Test
	public void testGetSueldoNetoyBruto() {
<<<<<<< Updated upstream
		assertEquals(this.chofer.getSueldoNeto(),this.chofer.getSueldoBruto(),"El sueldo bruto debe ser igual al neto para temporarios");
=======
		ChoferTemporario chofer = new ChoferTemporario("24532189", "Pedro");
		assertEquals("El sueldo bruto debe ser igual al neto para temporarios",chofer.getSueldoNeto(),chofer.getSueldoBruto());
>>>>>>> Stashed changes
	}

}
