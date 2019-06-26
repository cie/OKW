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
/// <code>OKWDirectorySeperatorNotImplementedException</code>-Ausnahme wird ausgelöst, 
/// wenn das Betriebssystem einen unbekannten (in OKW nicht berücksichtigten) Pfad-Trenner hat.
/// 
/// \~english
/// \brief
/// <code>OKWNotAllowedValueException</code>-Execption is triggerd, if a given value is prohibited.
/// 
/// \~
/// \author Zoltán Hrabovszki
/// \date 2015.11.25
public class OKWDirectorySeperatorNotImplementedException extends RuntimeException
{
	private static final long serialVersionUID = 6157985323655266095L;

	public OKWDirectorySeperatorNotImplementedException()
    {
    }

    public OKWDirectorySeperatorNotImplementedException(String message)
    {
    	super(message);
    }
}