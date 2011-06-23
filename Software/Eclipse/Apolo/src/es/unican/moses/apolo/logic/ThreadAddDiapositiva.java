/*
Apolo is a digital photographs classification system inspired on 
traditional lightened classification boxes for slide classification.

Copyright (C) 2011  Angel Tezanos Ibañez

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

package es.unican.moses.apolo.logic;

import java.io.File;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

//Paquetes propios
import es.unican.moses.apolo.ui.widgets.GUIDiapositiva;
import es.unican.moses.apolo.ui.widgets.GUIMesa;



/**
 * Thread que añade fotos a la GUIMesa
 * Incrementa un progress bar
 * @author Angel
 * Void -> Por que no devuelve la tarea ningun resultado
 * Integer -> Es lo que publica de vez en cuando el hilo en background
 */
public class ThreadAddDiapositiva extends SwingWorker<Void, Integer> {
	
	private GUIMesa gui_mesa;
	private File[] files;
	private JProgressBar progreso;
	 
	/**
	 * Constructor
	 * @wbp.parser.entryPoint
	 */
	public ThreadAddDiapositiva(GUIMesa gui_mesa, File[] files, JProgressBar progreso){
		this.gui_mesa = gui_mesa;
		this.files = files;
		this.progreso = progreso;
	}
	
	/**
	 * Ejecuta este metodo en otro hilo.
	 */
	@Override
	protected Void doInBackground() throws Exception {
		//Variables para calculo del paso del progressbar
		float paso = 100f/files.length; 
		float total=0;
		
		for (int i = 0; i< files.length; i++){
			
			//Creamos la diapositiva
			GUIDiapositiva gui_diapositiva = new GUIDiapositiva(gui_mesa, files[i], gui_mesa.getNumeroDeapositivas()+1);
			//Añadimos la diapositiva a la mesa
			gui_mesa.addGUIDiapositivia(gui_diapositiva);
			
			//Calculo del paso del JProgressBar
			total += paso;
			if ((int) total > progreso.getValue()){
				publish((int)total);
			}
		}
		return null;
	}
	
	/**
	 * Ejecuta esto al finalizar el hilo de trabajo
	 */
	@Override
	protected void done() {
		progreso.setValue(100);
	}
	
	/**
	 * Modifica este valor el hilo padre.
	 */
	@Override
    protected void process(List<Integer> valor) {
		progreso.setValue(valor.get(0));
    }

}
