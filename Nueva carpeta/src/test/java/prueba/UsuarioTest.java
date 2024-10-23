package prueba;

<<<<<<< Updated upstream
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
=======


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
>>>>>>> Stashed changes

import modeloDatos.Cliente;

class UsuarioTest {
	
	Cliente usuarioPrueba;
	
	@Before
	public void setUp() {
		this.usuarioPrueba = new Cliente("Juan95","12345","juan");
	}
	
	@Test
	public void testConstructorUsuario(){
<<<<<<< Updated upstream
		assertNotNull(this.usuarioPrueba, "El cliente debe ser distinto de null");
=======
		Cliente usuarioPrueba = new Cliente("Juan95","12345","juan");
		assertNotNull("El cliente debe ser distinto de null",usuarioPrueba);
>>>>>>> Stashed changes
	}
	
	@Test
	public void testGetNombreRealCliente() {
		String nombreEsperado = "juan";
		assertEquals(nombreEsperado,this.usuarioPrueba.getNombreReal(),"El nombre real debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetNombreUsuario() {
		String nombreEsperado = "Juan95";
		assertEquals(nombreEsperado,this.usuarioPrueba.getNombreUsuario(),"El nombre de usuario debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetPass() {
		String passEsperada = "12345";
		assertEquals(passEsperada,this.usuarioPrueba.getPass(),"La contraseña debe ser " + passEsperada);
	}
	
	@After
	void tearDown() {
		this.usuarioPrueba = null;
	}
}
