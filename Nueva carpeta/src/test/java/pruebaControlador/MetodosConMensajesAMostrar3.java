package pruebaControlador;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import excepciones.UsuarioYaExisteException;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import pruebaGUI.FalsoOptionPane;
import util.Constantes;
import util.Mensajes;

public class MetodosConMensajesAMostrar3 {
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
	public void testActionPerformedCerrarSesionCliente() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.CERRAR_SESION_CLIENTE);
		File arch = new File("empresa.bin");
		if(arch.exists())
		  arch.delete();
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			Empresa.getInstance().login("usuario1","1234");
		} 
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		controlador.actionPerformed(event);
		assertNull("El metodo 'logout()' del controlador NO deslogeo al cliente.", Empresa.getInstance().getUsuarioLogeado());
		assertTrue("El metodo 'logout()' del controlador no invoca al método escribir().", arch.exists());

	}
	
	@Test
	public void testActionPerformedCerrarSesionAdmin() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.CERRAR_SESION_ADMIN);
		File arch = new File("empresa.bin");
		
		if(arch.exists())
		  arch.delete();
		
		try {
			Empresa.getInstance().login("admin","admin");
		} 
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		controlador.actionPerformed(event);
		assertTrue("El metodo 'logout()' del controlador NO deslogeo al administrador.",!Empresa.getInstance().isAdmin());
		assertTrue("El metodo 'logout()' del controlador no invoca al método escribir().", arch.exists());

	}
	
	@Test
	public void testActionPerformedLogin() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.LOGIN);
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
		} 
		catch (UsuarioYaExisteException e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		ventana.setUsserName("usuario1");
		ventana.setPassword("1234");

		controlador.actionPerformed(event);
		Cliente cliLogeado = (Cliente)Empresa.getInstance().getUsuarioLogeado();
		Cliente cliagregado = Empresa.getInstance().getClientes().get("usuario1");
		assertEquals("Deberia cumplirse que se logeo al mismo cliente por el metodo 'login'.",cliagregado,cliLogeado);

	}
	
	@Test
	public void testActionPerformedRegistro() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.REG_BUTTON_REGISTRAR);
		Cliente cliCreado = new Cliente("juan95","45","juan");
		
		ventana.setNombreReal("juan");
		ventana.setUsserName("juan95");
		ventana.setPassword("45");
		ventana.setConfirmPassword("45");
		
		controlador.actionPerformed(event);
		Cliente cliReg = Empresa.getInstance().getClientes().get("juan95");
		assertEquals("El usuario cliente agregado debe set el mismo que el creado con los datos ingresados.", cliCreado.getNombreReal(), cliReg.getNombreReal());
		assertEquals("El usuario cliente agregado debe set el mismo que el creado con los datos ingresados.", cliCreado.getNombreUsuario(), cliReg.getNombreUsuario());
		assertEquals("El usuario cliente agregado debe set el mismo que el creado con los datos ingresados.", cliCreado.getPass(), cliReg.getPass());

	}
	
	@Test
	public void testActionPerformedNuevoPedido() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.NUEVO_PEDIDO);
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
		
		controlador.actionPerformed(event);
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
	public void testActionPerformedCalificarPagar() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.CALIFICAR_PAGAR);
		
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
		controlador.actionPerformed(event);
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
	public void testActionPerformedNuevoChofer() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.NUEVO_CHOFER);
		
		ventana.setTipoChofer(Constantes.PERMANENTE);
		ventana.setNombreChofer("Jose");
		ventana.setDNIChofer("43666918");
		ventana.setAnioChofer(2000);
		ventana.setHijosChofer(2);
		
		controlador.actionPerformed(event);
		
		ChoferPermanente chP = (ChoferPermanente) Empresa.getInstance().getChoferes().get("43666918");
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getNombreChofer(), chP.getNombre());
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getDNIChofer(), chP.getDni());
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getAnioChofer(), chP.getAnioIngreso());
		assertEquals("El chofer permanente creado por 'nuevoChofer()' NO es el registrado en la empresa.", ventana.getHijosChofer(), chP.getCantidadHijos());

	}
	
	@Test
	public void testActionPerformedNuevoVehiculo() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.NUEVO_VEHICULO);
		
		ventana.setTipoVehiculo(Constantes.AUTO);
		ventana.setPatente("532");
		ventana.setPlazas(3);
		ventana.setMascota(true);
		
		controlador.actionPerformed(event);
		Auto autoReg = (Auto) Empresa.getInstance().getVehiculos().get("532");
		assertEquals("El auto creado por 'nuevoVehiculo()' NO es el mismo que esta registrada en la empresa.", ventana.getPatente(), autoReg.getPatente());
		assertEquals("El auto creado por 'nuevoVehiculo()' NO es el mismo que esta registrada en la empresa.", ventana.getPlazas(), autoReg.getCantidadPlazas());
		assertEquals("El auto creado por 'nuevoVehiculo()' NO es el mismo que esta registrada en la empresa.", ventana.isVehiculoAptoMascota(), autoReg.isMascota());

	}
	
	@Test
	public void testActionPerformedNuevoViaje() {
		ActionEvent event = new ActionEvent(controlador, 0, Constantes.NUEVO_VIAJE);
		
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
		Auto v1=null;
		Viaje viajeReal;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			v1 = new Auto("NOW678", 3, false);
			chT = new ChoferTemporario("43666918", "jorge");
			Empresa.getInstance().agregarVehiculo(v1);
			Empresa.getInstance().agregarChofer(chT);
			
			ped = new Pedido(cli, 1,false,false,50,Constantes.ZONA_PELIGROSA);
			Empresa.getInstance().agregarPedido(ped);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(v1);
		controlador.actionPerformed(event);
		viajeReal = Empresa.getInstance().getViajesIniciados().get(cli);
		
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getChoferDisponibleSeleccionado().getDni(), viajeReal.getChofer().getDni());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getChoferDisponibleSeleccionado().getNombre(), viajeReal.getChofer().getNombre());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getVehiculoDisponibleSeleccionado().getCantidadPlazas(), viajeReal.getVehiculo().getCantidadPlazas());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getVehiculoDisponibleSeleccionado().getPatente(), viajeReal.getVehiculo().getPatente());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getVehiculoDisponibleSeleccionado().isMascota(), viajeReal.getVehiculo().isMascota());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCantidadPasajeros(), viajeReal.getPedido().getCantidadPasajeros());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getKm(),viajeReal.getPedido().getKm());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().isBaul(),viajeReal.getPedido().isBaul());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().isMascota(),viajeReal.getPedido().isMascota());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getZona(), viajeReal.getPedido().getZona());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCliente().getNombreReal(), viajeReal.getPedido().getCliente().getNombreReal());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCliente().getNombreUsuario(), viajeReal.getPedido().getCliente().getNombreUsuario());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCliente().getPass(), viajeReal.getPedido().getCliente().getPass());

	}
	
	
	
	
	

	@Test
	public void testNuevoViajeExitoso() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
		Auto v1=null;
		Viaje viajeReal;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			v1 = new Auto("NOW678", 3, false);
			chT = new ChoferTemporario("43666918", "jorge");
			Empresa.getInstance().agregarVehiculo(v1);
			Empresa.getInstance().agregarChofer(chT);
			
			ped = new Pedido(cli, 1,false,false,50,Constantes.ZONA_PELIGROSA);
			Empresa.getInstance().agregarPedido(ped);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(v1);
		controlador.nuevoViaje();
		viajeReal = Empresa.getInstance().getViajesIniciados().get(cli);
		
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getChoferDisponibleSeleccionado().getDni(), viajeReal.getChofer().getDni());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getChoferDisponibleSeleccionado().getNombre(), viajeReal.getChofer().getNombre());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getVehiculoDisponibleSeleccionado().getCantidadPlazas(), viajeReal.getVehiculo().getCantidadPlazas());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getVehiculoDisponibleSeleccionado().getPatente(), viajeReal.getVehiculo().getPatente());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getVehiculoDisponibleSeleccionado().isMascota(), viajeReal.getVehiculo().isMascota());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCantidadPasajeros(), viajeReal.getPedido().getCantidadPasajeros());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getKm(),viajeReal.getPedido().getKm());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().isBaul(),viajeReal.getPedido().isBaul());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().isMascota(),viajeReal.getPedido().isMascota());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getZona(), viajeReal.getPedido().getZona());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCliente().getNombreReal(), viajeReal.getPedido().getCliente().getNombreReal());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCliente().getNombreUsuario(), viajeReal.getPedido().getCliente().getNombreUsuario());
		assertEquals("El viaje iniciado por 'nuevoViaje()' no es el mismo que el creado.", ventana.getPedidoSeleccionado().getCliente().getPass(), viajeReal.getPedido().getCliente().getPass());

	}
	
	@Test
	public void testNuevoViajeChoferEsNull() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
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
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(null);
		ventana.setVehiculoDisponible(v1);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.PARAMETROS_NULOS.getValor(), Mensajes.PARAMETROS_NULOS.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoViajePedidoEsNull() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
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
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(null);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(v1);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.PARAMETROS_NULOS.getValor(), Mensajes.PARAMETROS_NULOS.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoViajeVehiculoEsNull() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
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
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(null);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.PARAMETROS_NULOS.getValor(), Mensajes.PARAMETROS_NULOS.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoViajeVehiculoNoDisponible() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
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
			Empresa.getInstance().getVehiculosDesocupados().clear();
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(v1);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.VEHICULO_NO_DISPONIBLE.getValor(), Mensajes.VEHICULO_NO_DISPONIBLE.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoViajeChoferNoDisponible() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
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
			Empresa.getInstance().getChoferesDesocupados().clear();
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(v1);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.CHOFER_NO_DISPONIBLE.getValor(), Mensajes.CHOFER_NO_DISPONIBLE.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoViajePedidoNoRegistrado() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
		Auto v1=null;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			v1 = new Auto("NOW678", 3, false);
			chT = new ChoferTemporario("43666918", "jorge");
			Empresa.getInstance().agregarVehiculo(v1);
			Empresa.getInstance().agregarChofer(chT);
			
			ped = new Pedido(cli, 1,false,false,50,Constantes.ZONA_PELIGROSA);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(v1);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.PEDIDO_INEXISTENTE.getValor(), Mensajes.PEDIDO_INEXISTENTE.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoViajeVehiculoNoSatisfacePedido() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
		Auto a1=null;
		Moto m1=null;
		
		try {
			Empresa.getInstance().agregarCliente("usuario1","1234","juan");
			cli = (Cliente)Empresa.getInstance().login("usuario1","1234");
			m1 = new Moto("ETC431");
			a1 = new Auto("NOW678", 3, false);
			chT = new ChoferTemporario("43666918", "jorge");
			Empresa.getInstance().agregarVehiculo(a1);
			Empresa.getInstance().agregarVehiculo(m1);
			Empresa.getInstance().agregarChofer(chT);
			
			ped = new Pedido(cli, 2,false,false,50,Constantes.ZONA_PELIGROSA);
			Empresa.getInstance().agregarPedido(ped);
		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(m1);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.VEHICULO_NO_VALIDO.getValor(), Mensajes.VEHICULO_NO_VALIDO.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoViajeClienteConViaje() {
		Pedido ped=null;
		Cliente cli=null;
		ChoferTemporario chT=null;
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
		
		ventana.setPedido(ped);
		ventana.setChoferDisponible(chT);
		ventana.setVehiculoDisponible(v1);
		controlador.nuevoViaje();
		
		assertEquals("La excepcion deberia decir: "+ Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor(), Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor(), this.opFalso.getMensaje());

	}
	
	
	
	

	@Test
	public void testNuevoVehiculoExistente() {
		Moto m1=null;
		
		try {
			m1 = new Moto("532");
			Empresa.getInstance().agregarVehiculo(m1);

		}
		catch (Exception e) {
			fail("Se lanzo la excepcion con el mensaje: "+e.getMessage());
		}
		
		ventana.setTipoVehiculo(Constantes.MOTO);
		ventana.setPatente("532");
		
		controlador.nuevoVehiculo();
		assertEquals("La excepcion deberia decir: "+ Mensajes.VEHICULO_YA_REGISTRADO.getValor(), Mensajes.VEHICULO_YA_REGISTRADO.getValor(), this.opFalso.getMensaje());

	}
	
	@Test
	public void testNuevoVehiculoMoto() {
		ventana.setTipoVehiculo(Constantes.MOTO);
		ventana.setPatente("532");
		
		controlador.nuevoVehiculo();
		Moto motoReg = (Moto)Empresa.getInstance().getVehiculos().get("532");
		assertEquals("La moto creada por 'nuevoVehiculo()' NO es la misma que esta registrada en la empresa.", ventana.getPatente(), motoReg.getPatente());
		
	}
	
	@Test
	public void testNuevoVehiculoCombi() {
		ventana.setTipoVehiculo(Constantes.COMBI);
		ventana.setPatente("532");
		ventana.setPlazas(8);
		ventana.setMascota(true);
		
		controlador.nuevoVehiculo();
		Combi combiReg = (Combi) Empresa.getInstance().getVehiculos().get("532");
		assertEquals("La combi creada por 'nuevoVehiculo()' NO es la misma que esta registrada en la empresa.", ventana.getPatente(), combiReg.getPatente());
		assertEquals("La combi creada por 'nuevoVehiculo()' NO es la misma que esta registrada en la empresa.", ventana.getPlazas(), combiReg.getCantidadPlazas());
		assertEquals("La combi creada por 'nuevoVehiculo()' NO es la misma que esta registrada en la empresa.", ventana.isVehiculoAptoMascota(), combiReg.isMascota());

	}
	
	@Test
	public void testNuevoVehiculoAuto() {
		ventana.setTipoVehiculo(Constantes.AUTO);
		ventana.setPatente("532");
		ventana.setPlazas(3);
		ventana.setMascota(true);
		
		controlador.nuevoVehiculo();
		Auto autoReg = (Auto) Empresa.getInstance().getVehiculos().get("532");
		assertEquals("El auto creado por 'nuevoVehiculo()' NO es el mismo que esta registrada en la empresa.", ventana.getPatente(), autoReg.getPatente());
		assertEquals("El auto creado por 'nuevoVehiculo()' NO es el mismo que esta registrada en la empresa.", ventana.getPlazas(), autoReg.getCantidadPlazas());
		assertEquals("El auto creado por 'nuevoVehiculo()' NO es el mismo que esta registrada en la empresa.", ventana.isVehiculoAptoMascota(), autoReg.isMascota());

	}

}
