package datos;

//

//  java1113.java
//  Copyright (c) 1997, Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
//
//   Compilador: javac 1.1.4
//        Autor: Agustin Froufe
//     Creacion: 02-Oct-1997  13:06:15
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
  * Este ejemplo muestra una forma sencilla de crear, capturar y
  * procesar eventos propios creados por el programador
  */

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;




public class lanzador {
    public static void main(String[] args){
        // Se intancia un objeto de este tipo
        new lanzador();
        }

    // Constructor de la clase
    public lanzador(){
    	
        NoVisual primerObjNoVisual = new NoVisual(Long.parseLong("100"), Double.parseDouble("584.445"), new Date());
        primerObjNoVisual.addMiEventoListener( new MiClaseEventListener() );
        // Crea un evento
        primerObjNoVisual.generarMiEvento();

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        primerObjNoVisual = new NoVisual(Long.parseLong("200"), Double.parseDouble("584.445"), new Date());
        primerObjNoVisual.addMiEventoListener( new MiClaseEventListener() );
        primerObjNoVisual.generarMiEvento();
           
        }
    }


