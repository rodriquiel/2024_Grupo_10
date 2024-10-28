package pruebaPersistencia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Pedido;
import modeloNegocio.Empresa;
import persistencia.EmpresaDTO;
import persistencia.UtilPersistencia;

public class EmpresaDTOTest {

	@Before
	public void setUp() throws ChoferRepetidoException, UsuarioYaExisteException, VehiculoRepetidoException, SinVehiculoParaPedidoException, ClienteNoExisteException, ClienteConViajePendienteException, ClienteConPedidoPendienteException {
		Chofer chofer = new ChoferTemporario("11111111", "Chofer");
		Auto auto = new Auto("ABC123", 4, true);
		Empresa.getInstance().agregarCliente("Juan", "1234", "Juan Carlos");
		Pedido pedido = new Pedido(Empresa.getInstance().getClientes().get("Juan"), 4, false, false, 10, util.Constantes.ZONA_STANDARD);
		Empresa.getInstance().agregarVehiculo(auto);
		Empresa.getInstance().agregarChofer(chofer);
		Empresa.getInstance().agregarPedido(pedido);
	}
	
	@Test
	public void testGetSetChoferes() {
		String dniEsperado = Empresa.getInstance().getChoferes().get("11111111").getDni();
		String nombreEsperado = Empresa.getInstance().getChoferes().get("11111111").getNombre();
		EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
		assertEquals(empresaDTO.getChoferes().size(),Empresa.getInstance().getChoferes().size());
		assertEquals("El DNI del chofer deberia ser " + dniEsperado,empresaDTO.getChoferes().get(dniEsperado).getDni(),dniEsperado);
		assertEquals("El nombre del chofer deberia ser " + nombreEsperado,empresaDTO.getChoferes().get(dniEsperado).getNombre(),nombreEsperado);
	}
	
	@Test
	public void testGetSetCliente() {
		String userEsperado = Empresa.getInstance().getClientes().get("Juan").getNombreUsuario();
		
	}

	@After
}
