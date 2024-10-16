package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;

class ChoferPermanenteTest {

	@Before
	public void setUp() {
		Chofer.setSueldoBasico(2000.0);
	}

	@Test
	public void testGetAnioIngreso() {
		ChoferPermanente chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		int anioEsperado = 2020;
		assertEquals(chofer.getAnioIngreso(),anioEsperado,"El año de ingreso debe ser " + anioEsperado);
	}
	
	@Test
	public void testGetAntiguedad() {
		ChoferPermanente chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		int antiguedadEsperada = 2024 - chofer.getAnioIngreso(); //Se toma 2024 ya que es el año en el cual se entrega el TP
		assertEquals(chofer.getAntiguedad(),antiguedadEsperada,"La Antiguedad debe ser " + antiguedadEsperada);
	}
	
	@Test
	public void testGetCantidadHijos() {
		ChoferPermanente chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		int cantidadEsperada = 6;
		assertEquals(chofer.getCantidadHijos(),cantidadEsperada,"La cantidad de hijos debe ser " + cantidadEsperada);
	}
	
	@Test
	public void testSetCantidadHijos() {
		ChoferPermanente chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		int cantidadEsperada = 4;
		chofer.setCantidadHijos(4);
		assertEquals(chofer.getCantidadHijos(),cantidadEsperada,"La cantidad de hijos debe ser " + cantidadEsperada);
	}
	
	@Test
	public void testGetSueldoBruto() {
		ChoferPermanente chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		double sueldoBasico = Chofer.getSueldoBasico();
		double resultadoEsperado;
		double plusAntiguedad;
		double plusHijos = 0.07*chofer.getCantidadHijos();
		if (chofer.getAntiguedad()>20)
			plusAntiguedad = 1;
		else
			plusAntiguedad = 0.05*chofer.getAntiguedad();
		resultadoEsperado = sueldoBasico * (1 + plusAntiguedad + plusHijos);
		assertEquals(chofer.getSueldoBruto(),resultadoEsperado,0.01);
	}
	
	@Test
	public void testGetSueldoNeto() {
		ChoferPermanente chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		double resultadoEsperado = chofer.getSueldoBruto()*0.86;
		assertEquals(chofer.getSueldoNeto(),resultadoEsperado,0.01);	
	}
	
}
