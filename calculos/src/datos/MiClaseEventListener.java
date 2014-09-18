package datos;


//Clase Receptor para responder a los eventos que se crean
class MiClaseEventListener implements MiEventoListener {
 public void capturarMiEvento( MiEvento evt ) {
     System.out.println(evt.getValor().toString());

     }
 }