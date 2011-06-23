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

package es.unican.moses.apolo.ui.dragAndDrop;

/**
 * Interfaz GhostDropListener
 * Interfaz para trabajar con los objetos soltados
 *
 */
public interface GhostDropListener{
	public void ghostDropped(GhostDropEvent e);
}
