package pruebaControlador;

import modeloDatos.Chofer;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import pruebaGUI.FalsoOptionPane;
import vista.IOptionPane;
import vista.Ventana;

public class VentanaFalsa extends Ventana {
	FalsoOptionPane optionPaneFalso;
	Pedido pedido;
	Chofer choferDisponible;
	Vehiculo vehiculoDisponible;
	String tipoVehiculo;
	String patente;
	int plazas;
	boolean mascota;
	String tipoChofer;
	String nombreChofer;
	String DNIChofer;
	int anioChofer;
	int hijosChofer;
	int calificacion;
	int cantidadPax;
	boolean baul;
	int cantKm;
	String tipoZona;
	String nombreReal;
	String usserName;
	String password;
	String confirmPassword;
	
	public VentanaFalsa() {
	}
	
	
	@Override
	public IOptionPane getOptionPane() {
		return this.optionPaneFalso;
	}
	
	public void setOptionPaneFalso(FalsoOptionPane optionPaneFalso) {
		this.optionPaneFalso = optionPaneFalso;
	}
	
	
	
	@Override
	public void actualizar() {
		super.actualizar();
	}
	
	
	
	@Override
	public Pedido getPedidoSeleccionado() {
		return this.pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
	@Override
	public Chofer getChoferDisponibleSeleccionado() {
		return this.choferDisponible;
	}
	
	public void setChoferDisponible(Chofer choferDisponible) {
		this.choferDisponible = choferDisponible;
	}
	
	
	
	@Override
	public Vehiculo getVehiculoDisponibleSeleccionado() {
		return this.vehiculoDisponible;
	}
	
	public void setVehiculoDisponible(Vehiculo vehiculoDisponible) {
		this.vehiculoDisponible = vehiculoDisponible;
	}
	
	
	
	@Override
	public String getTipoVehiculo() {
		return this.tipoVehiculo;
	}
	
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	
	
	@Override
	public String getPatente() {
		return this.patente;
	}
	
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	
	
	
	@Override
	public int getPlazas() {
		return this.plazas;
	}
	
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	
	
	
	@Override
	public boolean isVehiculoAptoMascota() {
		return this.mascota;
	}
	
	public void setMascota(boolean mascota) {
		this.mascota = mascota;
	}
	
	
	
	@Override
	public String getTipoChofer() {
		return this.tipoChofer;
	}
	
	public void setTipoChofer(String tipoChofer) {
		this.tipoChofer = tipoChofer;
	}
	
	
	
	@Override
	public String getNombreChofer() {
		return this.nombreChofer;
	}
	
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	
	
	
	@Override
	public String getDNIChofer() {
		return this.DNIChofer;
	}
	
	public void setDNIChofer(String dNIChofer) {
		DNIChofer = dNIChofer;
	}
	
	
	
	@Override
	public int getAnioChofer() {
		return this.anioChofer;
	}
	
	public void setAnioChofer(int anioChofer) {
		this.anioChofer = anioChofer;
	}
	
	
	
	@Override
	public int getHijosChofer() {
		return super.getHijosChofer();
	}
	
	public void setHijosChofer(int hijosChofer) {
		this.hijosChofer = hijosChofer;
	}
	
	
	
	@Override
	public int getCalificacion() {
		return this.calificacion;
	}
	
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	
	
	@Override
	public int getCantidadPax() {
		return this.cantidadPax;
	}
	
	public void setCantidadPax(int cantidadPax) {
		this.cantidadPax = cantidadPax;
	}
	
	
	
	@Override
	public boolean isPedidoConMascota() {
		return this.mascota;
	}
	
	
	
	@Override
	public boolean isPedidoConBaul() {
		return this.baul;
	}
	
	public void setBaul(boolean baul) {
		this.baul = baul;
	}
	
	
	
	@Override
	public int getCantKm() {
		return this.cantKm;
	}
	
	public void setCantKm(int cantKm) {
		this.cantKm = cantKm;
	}
	
	
	
	@Override
	public String getTipoZona() {
		return this.tipoZona;
	}
	
	public void setTipoZona(String tipoZona) {
		this.tipoZona = tipoZona;
	}
	
	
	
	@Override
	public String getRegNombreReal() {
		return this.nombreReal;
	}
	
	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}
	
	
	
	@Override
	public String getRegUsserName() {
		return this.usserName;
	}
	
	public void setUsserName(String usserName) {
		this.usserName = usserName;
	}
	
	
	
	@Override
	public String getRegPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	@Override
	public String getRegConfirmPassword() {
		return this.confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	@Override
	public String getUsserName() {
		return this.usserName;
	}
	
	
	
	@Override
	public String getPassword() {
		return this.password;
	}
}
