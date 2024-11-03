package prueba;

import static org.junit.Assert.assertSame;

import org.junit.Before;

import modeloDatos.Administrador;
import modeloDatos.Cliente;
import modeloDatos.Usuario;
import modeloNegocio.Empresa;

public class SetUsuarioLogueadoTest {

	Empresa empresa;
	Administrador admin;
	Cliente cliente;
	Usuario usuario;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
	}
	
	public void setUsuarioLogueadoTest() {
		empresa.setUsuarioLogeado(admin);
		usuario=empresa.getUsuarioLogeado();
		assertSame("Las instancias deberian ser iguales",usuario,admin);
	}
}
