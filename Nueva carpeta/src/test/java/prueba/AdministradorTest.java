package prueba;

import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;

import modeloDatos.Administrador;

class AdministradorTest {

	@Test
	public void testGeiInstance() {
		Administrador admin1 = Administrador.getInstance();
		Administrador admin2 = Administrador.getInstance();
		assertSame("Las instancias deben ser las mismas", admin1, admin2);
	}

}
