package prueba;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import modeloDatos.Administrador;
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class isAdminTest {

	Empresa empresa;
	Administrador admin;
	Cliente cliente;
	Usuario usuario;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		cliente=new Cliente("Juan123","ABC123","Juan");
	}
	
	public void TestIsAdminE1() {
		empresa.setUsuarioLogeado(admin);
		assertTrue("Deberia ser true",empresa.isAdmin());
	}
	
	public void TestIsAdminE2() {
		empresa.setUsuarioLogeado(usuario);
		assertFalse("Deberia ser false",empresa.isAdmin());
	}

}
