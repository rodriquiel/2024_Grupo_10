package pruebaControlador;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import excepciones.UsuarioYaExisteException;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import pruebaGUI.FalsoOptionPane;
import util.Constantes;
import util.Mensajes;

public class MetodosConMensajesAMostrar2 {
	Controlador controlador;
	FalsoOptionPane opFalso;
	VentanaFalsa ventana;
	
	@Before
	public void setUp(){
		opFalso = new FalsoOptionPane();
		ventana = new VentanaFalsa();
		this.controlador = new Controlador();
		this.ventana.setOptionPaneFalso(opFalso);
		this.controlador.setVista(ventana);
	}

	@After
	public void tearDown(){
		controlador = null;
		
		Empresa.getInstance().getClientes().clear();
		Empresa.getInstance().getChoferes().clear();
		Empresa.getInstance().getVehiculos().clear();
		Empresa.getInstance().getVehiculosDesocupados().clear();
		Empresa.getInstance().getChoferesDesocupados().clear();
		Empresa.getInstance().getPedidos().clear();
		Empresa.getInstance().getViajesIniciados().clear();
		Empresa.getInstance().getViajesTerminados().clear();
		
	}

	
	
	@Test
	public void testNuevoChoferPermanenteExitoso() {
		ventana.setTipoChofer(Constantes.PERMANENTE);
		ventana.setNombreChofer("Jose");
		ventana.setDNIChofer("43666918");
		ventana.setAnioChofer(2000);
		ventana.setHijosChofer(2);
		
		controlador.nuevoChofer();
		
		ChoferPermanente chP = (ChoferPermanente) Empresa.getInstance().getChoferes().get("43666918");
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getNombreChofer(), chP.getNombre());
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getDNIChofer(), chP.getDni());
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getAnioChofer(), chP.getAnioIngreso());
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getHijosChofer(), chP.getCantidadHijos());
	}
	
	@Test
	public void testNuevoChoferTemporarioExitoso() {
		ventana.setTipoChofer(Constantes.TEMPORARIO);
		ventana.setNombreChofer("Jose");
		ventana.setDNIChofer("43666918");
		
		controlador.nuevoChofer();
		
		ChoferTemporario chT = (ChoferTemporario) Empresa.getInstance().getChoferes().get("43666918");
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getNombreChofer(), chT.getNombre());
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getDNIChofer(), chT.getDni());

	}
	
	@Test
	public void testNuevoChoferTemporarioExistente() {
		ChoferTemporario chT=null;
		
		try {
			chT = new ChoferTemporario("43666918", "jorge");
			Empresa.getInstance().agregarChofer(chT);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setTipoChofer(Constantes.TEMPORARIO);
		ventana.setNombreChofer("Jose");
		ventana.setDNIChofer("43666918");
		
		controlador.nuevoChofer();
		assertEquals("La excepcion deberia decir: "+ Mensajes.CHOFER_YA_REGISTRADO.getValor(), Mensajes.CHOFER_YA_REGISTRADO.getValor(), this.opFalso.getMensaje());

	}
	
	
	
	
	
	

	@Test
	public void testCalificarPagarExitoso() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
		Viaje viajeCreado=null;
		Auto v1=null;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			v1 = new Auto("NOW678", 3, false);
			chT = new ChoferTemporario("43666918", "jorge");
			Empresa.getInstance().agregarVehiculo(v1);
			Empresa.getInstance().agregarChofer(chT);
			
			ped = new Pedido(cli, 1,false,false,50,Constantes.ZONA_PELIGROSA);
			Empresa.getInstance().agregarPedido(ped);
			Empresa.getInstance().crearViaje(ped, chT, v1);

		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		viajeCreado = new Viaje(ped, chT, v1);
		ventana.setCalificacion(4);
		controlador.calificarPagar();
		Viaje viajeFializado = Empresa.getInstance().getViajesTerminados().get(0);
		
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", ventana.getCalificacion(), viajeFializado.getCalificacion());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getChofer().getDni(), viajeFializado.getChofer().getDni());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getChofer().getNombre(), viajeFializado.getChofer().getNombre());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getVehiculo().getCantidadPlazas(), viajeFializado.getVehiculo().getCantidadPlazas());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getVehiculo().getPatente(), viajeFializado.getVehiculo().getPatente());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getPedido().getCantidadPasajeros(), viajeFializado.getPedido().getCantidadPasajeros());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getPedido().getKm(), viajeFializado.getPedido().getKm());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getPedido().getZona(), viajeFializado.getPedido().getZona());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getPedido().getCliente().getNombreReal(), viajeFializado.getPedido().getCliente().getNombreReal());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getPedido().getCliente().getNombreUsuario(), viajeFializado.getPedido().getCliente().getNombreUsuario());
		assertEquals("El viaje finalizado por 'calificarPagar()' no es el mismo que el creado.", viajeCreado.getPedido().getCliente().getPass(), viajeFializado.getPedido().getCliente().getPass());
		
	}
	
	@Test
	public void testCalificarPagarClienteSinViaje() {
		ventana.setCalificacion(4);
		controlador.calificarPagar();
		assertEquals("La excepcion deberia decir: "+ Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor(), Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor(), this.opFalso.getMensaje());
	}
	
	
	
	
	

	@Test
	public void testNuevoPedidoExitoso() {
		Pedido pedEsperado=null, pedAgregado=null;
		Cliente cli=null;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			Auto v1 = new Auto("NOW678", 3, false);
			Empresa.getInstance().agregarVehiculo(v1);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setCantidadPax(2);
		ventana.setMascota(false);
		ventana.setBaul(false);
		ventana.setCantKm(20);
		ventana.setTipoZona(Constantes.ZONA_PELIGROSA);
		pedEsperado = new Pedido(cli, ventana.getCantidadPax(),ventana.isPedidoConMascota(),ventana.isPedidoConBaul(),ventana.getCantKm(),ventana.getTipoZona());
		
		controlador.nuevoPedido();
		pedAgregado = Empresa.getInstance().getPedidos().get(cli);
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.getCantidadPasajeros(),pedAgregado.getCantidadPasajeros());
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.getKm(),pedAgregado.getKm());
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.getZona(),pedAgregado.getZona());
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.isBaul(),pedAgregado.isBaul());
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.isMascota(),pedAgregado.isMascota());
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.getCliente().getNombreReal(),pedAgregado.getCliente().getNombreReal());
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.getCliente().getNombreUsuario(),pedAgregado.getCliente().getNombreUsuario());
		assertEquals("El pedido agregado es diferente al creado por el metodo 'nuevoPedido()'.", pedEsperado.getCliente().getPass(),pedAgregado.getCliente().getPass());
	}
	
	@Test
	public void testNuevoPedidoVehiculoNoSatisface() {
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			Empresa.getInstance().login("usuario1","1234");
			Auto v1 = new Auto("NOW678", 3, false);
			Empresa.getInstance().agregarVehiculo(v1);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setCantidadPax(9);
		ventana.setMascota(false);
		ventana.setBaul(false);
		ventana.setCantKm(20);
		ventana.setTipoZona(Constantes.ZONA_PELIGROSA);
		assertEquals("La excepcion deberia decir: "+ Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoPedidoVehiculoInexistente() {
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			Empresa.getInstance().login("usuario1","1234");
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setCantidadPax(9);
		ventana.setMascota(false);
		ventana.setBaul(false);
		ventana.setCantKm(20);
		ventana.setTipoZona(Constantes.ZONA_PELIGROSA);
		assertEquals("La excepcion deberia decir: "+ Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoPedidoClienteConViaje() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			Auto v1 = new Auto("NOW678", 3, false);
			Auto v2 = new Auto("ETC567",3,true);
			chT = new ChoferTemporario("43666918", "jorge");
			Empresa.getInstance().agregarVehiculo(v1);
			Empresa.getInstance().agregarVehiculo(v2);
			Empresa.getInstance().agregarChofer(chT);
			
			ped = new Pedido(cli, 1,false,false,50,Constantes.ZONA_PELIGROSA);
			Empresa.getInstance().agregarPedido(ped);
			Empresa.getInstance().crearViaje(ped, chT, v1);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setCantidadPax(2);
		ventana.setMascota(false);
		ventana.setBaul(false);
		ventana.setCantKm(20);
		ventana.setTipoZona(Constantes.ZONA_PELIGROSA);
		controlador.nuevoPedido();
		assertEquals("La excepcion deberia decir: "+ Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor(), Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoPedidoClienteConPedido() {
		Pedido ped=null;
		Cliente cli=null;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			Auto v1 = new Auto("NOW678", 3, false);
			Auto v2 = new Auto("ETC567",3,true);
			Empresa.getInstance().agregarVehiculo(v1);
			Empresa.getInstance().agregarVehiculo(v2);
			
			ped = new Pedido(cli, 1,false,false,50,Constantes.ZONA_PELIGROSA);
			Empresa.getInstance().agregarPedido(ped);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setCantidadPax(2);
		ventana.setMascota(false);
		ventana.setBaul(false);
		ventana.setCantKm(20);
		ventana.setTipoZona(Constantes.ZONA_PELIGROSA);
		controlador.nuevoPedido();
		assertEquals("La excepcion deberia decir: "+ Mensajes.CLIENTE_CON_PEDIDO_PENDIENTE.getValor(), Mensajes.CLIENTE_CON_PEDIDO_PENDIENTE.getValor(), this.opFalso.getMensaje());

	}
	
	
	
	
	
	@Test
	public void testRegistrarUsuarioNuevo() {
		Cliente cliCreado = new Cliente("juan95","45","juan");
		
		ventana.setNombreReal("juan");
		ventana.setUsserName("juan95");
		ventana.setPassword("45");
		ventana.setConfirmPassword("45");
		
		controlador.registrar();
		Cliente cliReg = Empresa.getInstance().getClientes().get("juan95");
		assertEquals("El usuario cliente agregado debe set el mismo que el creado con los datos ingresados.", cliCreado.getNombreReal(), cliReg.getNombreReal());
		assertEquals("El usuario cliente agregado debe set el mismo que el creado con los datos ingresados.", cliCreado.getNombreUsuario(), cliReg.getNombreUsuario());
		assertEquals("El usuario cliente agregado debe set el mismo que el creado con los datos ingresados.", cliCreado.getPass(), cliReg.getPass());

	}
	
	@Test
	public void testRegistrarUsuarioExistente() {
		try {
			Empresa.getInstance().agregarCliente("juan95","45","juan");
		} 
		catch (UsuarioYaExisteException e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setNombreReal("juan");
		ventana.setUsserName("juan95");
		ventana.setPassword("45");
		ventana.setConfirmPassword("45");
		
		controlador.registrar();
		assertEquals("Deberia decir: "+ Mensajes.USUARIO_REPETIDO.getValor(), Mensajes.USUARIO_REPETIDO.getValor(), this.opFalso.getMensaje());

	}

	@Test
	public void testRegistrarPassYConfirmNoCoinciden() {
		ventana.setNombreReal("juan");
		ventana.setUsserName("juan95");
		ventana.setPassword("45");
		ventana.setConfirmPassword("61");
		
		controlador.registrar();
		assertEquals("Deberia decir: "+ Mensajes.PASS_NO_COINCIDE.getValor(), Mensajes.PASS_NO_COINCIDE.getValor(), this.opFalso.getMensaje());

	}

}
