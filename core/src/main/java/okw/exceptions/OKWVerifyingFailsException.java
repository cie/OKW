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

/// \~german
/// \brief
/// <code>OKWVerifyingFailsException</code>-Ausnahme wird ausgelöst, wenn ein Soll-Ist-Vergleich fehlschlägt.<br>
/// 
/// \~english
/// \brief
/// <code>OKWVerifyingFailsException</code>-Exception is triggered, if an actuel-expected-verification fails.<br>
/// 
/// \~
/// \author Zoltán Hrabovszki
/// \date 2014.04.27
public class OKWVerifyingFailsException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4827749401449144048L;

	public OKWVerifyingFailsException()
    {
    }

    public OKWVerifyingFailsException(String message)
    {
    	super(message);
    }
}