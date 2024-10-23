package prueba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import util.Constantes;

public class ViajeTest {
	
	Cliente cliente1;
	Pedido pedido1;
	Chofer chofer1;
	Vehiculo vehiculo1;
	Viaje viaje;
	
	@Before
	public void setUp() {
		cliente1=new Cliente("Juan123","123ABC","Juan Perez");
		chofer1= new ChoferPermanente("42948156","Juan",1990,3);
		vehiculo1= new Auto("ABC123",4,true);
		Viaje.setValorBase(2000);
	}
	
	@Test
	public void testConstructorViaje() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertNotNull("El viaje debería ser distinto de null",viaje);
	}
	
	@Test
	public void testFinalizarViaje() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		int calificacion=2;
		viaje.finalizarViaje(calificacion);
		assertEquals("La calificacion deberia ser "+calificacion,viaje.getCalificacion(),calificacion);
		assertEquals("El viaje deberia estar en true, indicando que esta finalizado",true,viaje.isFinalizado());
	}

	@Test
	public void testGetCalificacion() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		viaje.finalizarViaje(2);
		assertEquals("La calificacion deberia ser 2",2,viaje.getCalificacion());
	}
	
	@Test
	public void testGetChofer() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertSame("Los choferes deberian ser iguales",chofer1,viaje.getChofer());
	}
	
	@Test
	public void testGetPedido() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertSame("Los pedidos deberian ser iguales",pedido1,viaje.getPedido());
	}
	
	@Test
	public void testGetValorBase() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertEquals("El valor base del viaje deberia ser 2000",2000.0,Viaje.getValorBase(),0.001);
	}
	
	@Test
	public void testGetVehiculo() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertSame("Los vehiculos deberian ser iguales",vehiculo1,viaje.getVehiculo());
	}
	
	@Test
	public void testGetValorE1() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertEquals("El valor deberia ser 1070.9",1070.9,viaje.getValor(),0.001);
	}
	
	@Test
	public void testGetValorE2() {
		pedido1=new Pedido(this.cliente1,3,false,true,200,Constantes.ZONA_SIN_ASFALTAR);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertEquals("El valor deberia ser 1040.9",1040.9,viaje.getValor(),0.001);
	}
	
	@Test
	public void testGetValorE3() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_STANDARD);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertEquals("El valor deberia ser 1060.6",1060.6,viaje.getValor(),0.001);
	}
	
	@Test
	public void testGetValorE4() {
		pedido1=new Pedido(this.cliente1,3,true,false,200,Constantes.ZONA_PELIGROSA);
		viaje= new Viaje(pedido1,this.chofer1,this.vehiculo1);
		assertEquals("El valor deberia ser 1080.6",1080.6,viaje.getValor(),0.001);
	}
}
