package datos;

import java.awt.Component;
import java.util.Date;


//Clase para crear eventos de nuestro tipo. Esta es una version
//muy sencilla que solo permite que se registre un Receptor
//para el tipo de evento que hemos creado
class NoVisual extends Component {
 // El identificador de este objeto
 String ID; 
 // Referencia al receptor unico
 MiClaseEventListener miReceptor;

 private long volumen;
	private double precioAccion;
	private Date fecha;
	
	
	public NoVisual(long volumen, double precioAccion, Date fecha) {
		this.volumen = volumen;
		this.precioAccion = precioAccion;
		this.fecha = fecha;
	}
	
	public NoVisual() {
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
	
 // Construye un objeto NoVisual
 public NoVisual( String ID ) {
     this.ID = ID;
     }

 public void addMiEventoListener( MiClaseEventListener receptor ) {
     // No se permite que intente incorporar mas de un receptor
     if( miReceptor == null ) 
         miReceptor = receptor;
     else {
         System.out.println( "No se soportan multiples Receptores" );
         // Se sale, si se intentan registrar varios objetos Receptor
         System.exit( 0 );
         }
     }

 public void generarMiEvento() {
     miReceptor.capturarMiEvento( new MiEvento(this) );
     }
 }