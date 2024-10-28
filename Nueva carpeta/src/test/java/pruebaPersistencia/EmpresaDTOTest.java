package pruebaPersistencia;

import static org.junit.Assert.assertEquals;

import org.junit.After;
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
import modeloDatos.Cliente;
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
		String nombreEsperado = Empresa.getInstance().getClientes().get("Juan").getNombreReal();
		String passEsperada = Empresa.getInstance().getClientes().get("Juan").getPass();
		EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
		assertEquals(empresaDTO.getClientes().size(),Empresa.getInstance().getClientes().size());
		assertEquals("El user del cliente deberia ser " + userEsperado,empresaDTO.getClientes().get(userEsperado).getNombreUsuario(),userEsperado);
		assertEquals("El nombre del cliente deberia ser " + nombreEsperado,empresaDTO.getClientes().get(userEsperado).getNombreReal(),nombreEsperado);
		assertEquals("El pass del cliente deberia ser " + passEsperada,empresaDTO.getClientes().get(userEsperado).getPass(),passEsperada);
	}

	@Test
	public void testGetSetVehiculo() {
		String patenteEsperada = Empresa.getInstance().getVehiculos().get("ABC123").getPatente();
		int asientosEsperado = Empresa.getInstance().getVehiculos().get("ABC123").getCantidadPlazas();
		boolean mascotaEsperado = Empresa.getInstance().getVehiculos().get("ABC123").isMascota();
		EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
		assertEquals(empresaDTO.getVehiculos().size(),Empresa.getInstance().getVehiculos().size());
		assertEquals("El user del cliente deberia ser " + patenteEsperada,empresaDTO.getVehiculos().get(patenteEsperada).getPatente(),patenteEsperada);
		assertEquals(empresaDTO.getVehiculos().get(patenteEsperada).getCantidadPlazas(),asientosEsperado);
		assertEquals(empresaDTO.getVehiculos().get(patenteEsperada).isMascota(),mascotaEsperado);
	}
	
	@Test
	public void testGetSetPedido() {
		Cliente cliente = Empresa.getInstance().getClientes().get("Juan");
		int kmEsperado = Empresa.getInstance().getPedidos().get(cliente).getKm();
		int cantPasajerosEsperado = Empresa.getInstance().getPedidos().get(cliente).getCantidadPasajeros();
		String zonaEsperada = Empresa.getInstance().getPedidos().get(cliente).getZona();
		boolean baulEsperado = Empresa.getInstance().getPedidos().get(cliente).isBaul();
		boolean mascotaEsperado = Empresa.getInstance().getPedidos().get(cliente).isMascota();
		EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
		assertEquals(empresaDTO.getPedidos().size(),Empresa.getInstance().getPedidos().size());
		assertEquals(empresaDTO.getPedidos().get(cliente).getCantidadPasajeros(),cantPasajerosEsperado);
		assertEquals(empresaDTO.getPedidos().get(cliente).getKm(),kmEsperado);
		assertEquals(empresaDTO.getPedidos().get(cliente).getZona(),zonaEsperada);
		assertEquals(empresaDTO.getPedidos().get(cliente).isBaul(),baulEsperado);
		assertEquals(empresaDTO.getPedidos().get(cliente).isMascota(),mascotaEsperado);
	}
	
	@After
	public void tearDown() {
		Empresa.getInstance().getClientes().clear();
		Empresa.getInstance().getChoferes().clear();
		Empresa.getInstance().getVehiculos().clear();
		Empresa.getInstance().getPedidos().clear();
	}
}
