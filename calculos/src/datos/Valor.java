package datos;

import java.util.Date;


public class Valor {
	
	private long volumen;
	private double precioAccion;
	private Date fecha;
	
	
	public Valor(long volumen, double precioAccion, Date fecha) {
		this.volumen = volumen;
		this.precioAccion = precioAccion;
		this.fecha = fecha;
	}
	
	public Valor() {
	}

	public long getVolumen() {
		return volumen;
	}

	public void setVolumen(long volumen) {
		this.volumen = volumen;
	}

	public double getPrecioAccion() {
		return precioAccion;
	}

	public void setPrecioAccion(double precioAccion) {
		this.precioAccion = precioAccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Precio accion: " + precioAccion + 
			   "; Volumen: " + volumen + 
			   "; Fecha: " + fecha;
	}
	
}
