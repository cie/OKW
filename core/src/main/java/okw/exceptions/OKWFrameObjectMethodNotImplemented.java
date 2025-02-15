/*
==============================================================================
  Author: Zoltán Hrabovszki <zh@openkeyword.de>

  Copyright © 2012 - 2019 IT-Beratung Hrabovszki
  www.OpenKeyWord.de
============================================================================== 

This file is part of OpenKeyWord.

OpenKeyWord is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

OpenKeyWord is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with OpenKeyWord.  If not, see <http://www.gnu.org/licenses/>.

Diese Datei ist Teil von OpenKeyWord.

OpenKeyWord ist Freie Software: Sie können es unter den Bedingungen
der GNU General Public License, wie von der Free Software Foundation,
Version 3 der Lizenz oder (nach Ihrer Wahl) jeder späteren
veröffentlichten Version, weiterverbreiten und/oder modifizieren.

OpenKeyWord wird in der Hoffnung, dass es nützlich sein wird, aber
OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
Siehe die GNU General Public License für weitere Details.

Sie sollten eine Kopie der GNU General Public License zusammen mit 
OpenKeyWord erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/
package okw.exceptions;

/** \~german
 * \brief
 * Die Ausnahme <code>OKWFrameObjectMethodNotImplemented</code> wird ausgelöst, 
 * wenn im Frame Objekt eine gegebene Methode zwar vorhanden ist aber die Implementierung
 * nur als Dummy-Methode in der Basis-Klasse angelegt ist.
 * \details
 * 
 * \~english
 * \brief
 * The exeption <code>OKWFrameObjectMethodNotFoundException</code> is triggered, 
 * if a given method is existent but it is a dummy implementation of the base-class.
 * \details 
 * An OKW-Methode can be missed, if 
 * -# a project specific object is to be defined...
 * -# a method is not implemented in the OKW-GUI-classes
 * \todo TODO: jnic text was not logical and was corrected - please proove it
 * 
 * The missing method must bei possibly implemented. The Exception gives hints about
 * _what_ method is missing and to be implemented.
 * 
 * \~
 * \author Zoltán Hrabovszki
 * \date 2016.10.06
 */
public class OKWFrameObjectMethodNotImplemented extends RuntimeException 
{

	private static final long serialVersionUID = -2374148675439496162L;

	public OKWFrameObjectMethodNotImplemented()
    {
	    super();
    }

    public OKWFrameObjectMethodNotImplemented( String message )
    {
    	super(message);
    }
}