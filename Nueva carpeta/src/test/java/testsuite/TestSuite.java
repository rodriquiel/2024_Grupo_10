package testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import modeloDatos.Administrador;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Combi;
import modeloNegocio.Empresa;
import persistencia.EmpresaDTO;
import prueba.AdministradorTest;
import prueba.AutoTest;
import prueba.CalificacionDeChoferTest;
import prueba.ChoferPermanenteTest;
import prueba.ChoferTemporarioTest;
import prueba.CombiTest;
import prueba.CrearViajeTest;
import prueba.EmpresaSinlgetonTest;
import prueba.EmpresaTest;
import prueba.Empresa_AgregarTest;
import prueba.GetPedidoDeCliente;
import prueba.GetUsuarioLogueadotest;
import prueba.GetViajeDeClienteTest;
import prueba.LogOutTest;
import prueba.LoginTest;
import prueba.MotoTest;
import prueba.PagarYFinalizarTest;
import prueba.PedidoTest;
import prueba.SetUsuarioLogueadoTest;
import prueba.UsuarioTest;
import prueba.ViajeTest;
import prueba.isAdminTest;
import pruebaControlador.ControladorTestMetodosBasicos;
import pruebaControlador.MetodosConMensajesAMostrar;
import pruebaControlador.MetodosConMensajesAMostrar2;
import pruebaControlador.MetodosConMensajesAMostrar3;
import pruebaGUI.GUIEnabledDisabledAdminTest;
import pruebaGUI.GUIEnabledDisabledClienteTest;
import pruebaGUI.GUIEnabledDisabledLogRegTest;
import pruebaGUI.GUILogRegConDatosTest;
import pruebaGUI.GUILogRegVacioTest;
import pruebaPersistencia.EmpresaDTOTest;
import pruebaPersistencia.PersistenciaTest;

@RunWith(Suite.class)
@SuiteClasses({GUIEnabledDisabledLogRegTest.class,GUIEnabledDisabledAdminTest.class,GUIEnabledDisabledClienteTest.class,
	GUILogRegConDatosTest.class,GUILogRegVacioTest.class,EmpresaDTOTest.class,PersistenciaTest.class,ControladorTestMetodosBasicos.class,
	MetodosConMensajesAMostrar.class,MetodosConMensajesAMostrar2.class,MetodosConMensajesAMostrar3.class,AdministradorTest.class,
	AutoTest.class,CalificacionDeChoferTest.class,ChoferPermanenteTest.class,ChoferTemporarioTest.class,CombiTest.class,
	CrearViajeTest.class,Empresa_AgregarTest.class,EmpresaSinlgetonTest.class,EmpresaTest.class,GetPedidoDeCliente.class,
	GetUsuarioLogueadotest.class,GetViajeDeClienteTest.class,isAdminTest.class,LoginTest.class,LogOutTest.class,
	MotoTest.class,PagarYFinalizarTest.class,PedidoTest.class,SetUsuarioLogueadoTest.class,UsuarioTest.class,ViajeTest.class})
	


public class TestSuite {

}
