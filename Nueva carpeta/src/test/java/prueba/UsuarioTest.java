package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import modeloDatos.Cliente;

class UsuarioTest {

	@Test
	public void testConstructorUsuario(){
		Cliente usuarioPrueba = new Cliente("Juan95","12345","juan");
		assertNotNull(usuarioPrueba, "El cliente debe ser distinto de null");
	}
	
	@Test
	public void testGetNombreRealCliente() {
		Cliente usuarioPrueba = new Cliente("Juan95","12345","juan");
		String nombreEsperado = "juan";
		assertEquals(usuarioPrueba.getNombreReal(),nombreEsperado,"El nombre real debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetNombreUsuario() {
		Cliente usuarioPrueba = new Cliente("Juan95","12345","juan");
		String nombreEsperado = "Juan95";
		assertEquals(usuarioPrueba.getNombreUsuario(),nombreEsperado,"El nombre de usuario debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetPass() {
		Cliente usuarioPrueba = new Cliente("Juan95","12345","juan");
		String passEsperada = "12345";
		assertEquals(usuarioPrueba.getPass(),passEsperada,"La contraseña debe ser " + passEsperada);
	}
}
