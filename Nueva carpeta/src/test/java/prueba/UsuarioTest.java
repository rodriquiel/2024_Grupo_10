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
		assertEquals(nombreEsperado,usuarioPrueba.getNombreReal(),"El nombre real debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetNombreUsuario() {
		Cliente usuarioPrueba = new Cliente("Juan95","12345","juan");
		String nombreEsperado = "Juan95";
		assertEquals(nombreEsperado,usuarioPrueba.getNombreUsuario(),"El nombre de usuario debe ser " + nombreEsperado);
	}
	
	@Test
	public void testGetPass() {
		Cliente usuarioPrueba = new Cliente("Juan95","12345","juan");
		String passEsperada = "12345";
		assertEquals(passEsperada,usuarioPrueba.getPass(),"La contraseña debe ser " + passEsperada);
	}
}
