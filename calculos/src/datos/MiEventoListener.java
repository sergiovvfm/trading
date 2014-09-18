package datos;

import java.util.EventListener;

//Define el interfaz para el nuevo tipo de receptor de eventos
interface MiEventoListener extends EventListener {
 void capturarMiEvento( MiEvento evt );
 }

