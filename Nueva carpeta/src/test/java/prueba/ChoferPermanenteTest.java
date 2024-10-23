package prueba;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;

class ChoferPermanenteTest {

	ChoferPermanente chofer;
	
	@Before
	public void setUp() {
		this.chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		Chofer.setSueldoBasico(2000.0);
	}

	@Test
	public void testGetAnioIngreso() {
		int anioEsperado = 2020;
<<<<<<< Updated upstream
		assertEquals(anioEsperado,this.chofer.getAnioIngreso(),"El año de ingreso debe ser " + anioEsperado);
=======
		assertEquals("El año de ingreso debe ser " + anioEsperado,chofer.getAnioIngreso(),anioEsperado);
>>>>>>> Stashed changes
	}
	
	@Test
	public void testGetAntiguedad() {
<<<<<<< Updated upstream
		int antiguedadEsperada = 2024 - this.chofer.getAnioIngreso(); //Se toma 2024 ya que es el año en el cual se entrega el TP
		assertEquals(antiguedadEsperada,this.chofer.getAntiguedad(),"La Antiguedad debe ser " + antiguedadEsperada);
=======
		ChoferPermanente chofer = new ChoferPermanente("24532189", "Pedro", 2020, 6);
		int antiguedadEsperada = 2024 - chofer.getAnioIngreso(); //Se toma 2024 ya que es el año en el cual se entrega el TP
		assertEquals("La Antiguedad debe ser " + antiguedadEsperada,chofer.getAntiguedad(),antiguedadEsperada);
>>>>>>> Stashed changes
	}
	
	@Test
	public void testGetCantidadHijos() {
		int cantidadEsperada = 6;
<<<<<<< Updated upstream
		assertEquals(cantidadEsperada,this.chofer.getCantidadHijos(),"La cantidad de hijos debe ser " + cantidadEsperada);
=======
		assertEquals("La cantidad de hijos debe ser " + cantidadEsperada,chofer.getCantidadHijos(),cantidadEsperada);
>>>>>>> Stashed changes
	}
	
	@Test
	public void testSetCantidadHijos() {
		int cantidadEsperada = 4;
		chofer.setCantidadHijos(4);
<<<<<<< Updated upstream
		assertEquals(cantidadEsperada,this.chofer.getCantidadHijos(),"La cantidad de hijos debe ser " + cantidadEsperada);
=======
		assertEquals("La cantidad de hijos debe ser " + cantidadEsperada,chofer.getCantidadHijos(),cantidadEsperada);
>>>>>>> Stashed changes
	}
	
	@Test
	public void testGetSueldoBruto() {
		double sueldoBasico = Chofer.getSueldoBasico();
		double resultadoEsperado;
		double plusAntiguedad;
		double plusHijos = 0.07*this.chofer.getCantidadHijos();
		if (this.chofer.getAntiguedad()>20)
			plusAntiguedad = 1;
		else
			plusAntiguedad = 0.05*this.chofer.getAntiguedad();
		resultadoEsperado = sueldoBasico * (1 + plusAntiguedad + plusHijos);
		assertEquals(resultadoEsperado,this.chofer.getSueldoBruto(),0.01);
	}
	
	@Test
	public void testGetSueldoNeto() {
		double resultadoEsperado = this.chofer.getSueldoBruto()*0.86;
		assertEquals(resultadoEsperado,this.chofer.getSueldoNeto(),0.01);	
	}
	
}
