package prueba;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Administrador;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;

public class GetPedidoDeCliente {

	Empresa empresa;
	Administrador admin;
	Cliente cliente;
	Usuario usuario;
	Chofer chofer;
	Pedido pedido,pedidoAux;
	Vehiculo auto;
	
	@Before
	public void setUp() {
		empresa=Empresa.getInstance();
		admin=Administrador.getInstance();
		chofer=new ChoferPermanente("42949155","Pedro",1999,3);
		auto=new Auto("ABC456",3,true);
		try {
			empresa.login("admin", "admin");
			empresa.agregarCliente("Juan123", "ABC123", "Juan");
			cliente=empresa.getClientes().get("Juan123");
			empresa.agregarChofer(chofer);
			empresa.agregarVehiculo(auto);
		}catch(Exception e) {
			fail("No deberia lanzar excepciones en el setUp");
		}
	}
	
	@Test
	public void TestGetPedidoDeClienteE1() {
		
		try {
			pedido=new Pedido(cliente,3,false,false,30,Constantes.ZONA_PELIGROSA);
			empresa.agregarPedido(pedido);
			pedidoAux=empresa.getPedidoDeCliente(cliente);
			assertSame("Las instancias deberian ser iguales",pedidoAux,pedido);
		}catch(Exception e) {
			fail("No deberia lanzar excepciones");
		}
	}

	@Test
	public void TestGetPedidoDeClienteE2() {
		
		try {
			//pedido=new Pedido(cliente,3,false,false,30,Constantes.ZONA_PELIGROSA);
			//empresa.agregarPedido(pedido); NO AGREGO EL PEDIDO A LA EMPRESA
			pedidoAux=empresa.getPedidoDeCliente(cliente);
			assertNull("El pedido deberia ser null",pedidoAux);
		}catch(Exception e) {
			fail("No deberia lanzar excepciones");
		}
	}
	
	@After
	public void tearDown() {
		empresa.getChoferes().clear();
		empresa.getChoferesDesocupados().clear();
		empresa.getClientes().clear();
		empresa.getPedidos().clear();
		empresa.getVehiculos().clear();
		empresa.getVehiculosDesocupados().clear();
		empresa.getViajesIniciados().clear();
		empresa.getViajesTerminados().clear();
		empresa.logout();
	}
}
