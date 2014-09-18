package datos;

import java.io.ObjectInputStream.GetField;
import java.util.Date;
import java.util.EventObject;


//Clase para definir un nuevo tipo de evento
class MiEvento extends EventObject {

// Variable de instancia para diferencia a cada objeto de este tipo
	private Valor valor = new Valor();
	private NoVisual noVisual = new NoVisual();

 // Constructor parametrizado
 MiEvento( Object obj ) {
     // Se le pasa el objeto como parametro a la superclase
     super( obj );
     // Se guarda el identificador del objeto
     noVisual = (NoVisual) obj;
     valor = new Valor(noVisual.getVolumen(), noVisual.getPrecioAccion(), noVisual.getFecha());
     }

public Valor getValor() {
	return valor;
}

public void setValor(Valor valor) {
	this.valor = valor;
}


 }
