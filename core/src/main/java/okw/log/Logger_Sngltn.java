/*
==============================================================================
  Author: Zoltan Hrabovszki <zh@openkeyword.de>

  Copyright © 2012, 2013, 2014, 2015 IT-Beratung Hrabovszki
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
package okw.log;

import java.util.*;

import okw.OKW_HandleException;
import okw.OKW_Const_Sngltn;

public class Logger_Sngltn
{

	private static Boolean cvbDebugMode = false;

	public Boolean getDebugMode()
	{
		return cvbDebugMode;
	}

	public void setDebugMode( Boolean fpbDebugMode )
	{
		cvbDebugMode = fpbDebugMode;
		
		for (ILogger myLogger : LoggerList)
		{
			myLogger.setDebugMode(cvbDebugMode);
		}

	}

	private static ArrayList<ILogger>		LoggerList;

	private static Logger_Sngltn	Instance;

	public static Logger_Sngltn getInstance()
	{
		// Lazy Initialization (If required then only)

		if (Instance == null)
		{
			// Thread Safe. Might be costly operation in some case
			synchronized (OKW_Const_Sngltn.class)
			{
				if (Instance == null)
				{
					Instance = new Logger_Sngltn();
					Init();

				}
			}
		}
		return Instance;

	}

	private Logger_Sngltn()
	{
		try
		{
			LoggerList = new ArrayList<ILogger>();
		}
		catch (Exception e)
		{
			OKW_HandleException.StopRunning(e, Instance.getClass());
		}
	}

	/// \~german
	/// \brief Fügt einen ILogger der Logger-Liste Logger.LoggerList
	///
	/// \param fpLogger Logger, der
	/// \return
	///
	/// \~english
	/// \brief \todo TODO: Brief Description.
	///
	/// \todo TODO: Detailed Description
	///
	/// \param fpbTrueOrFalse
	/// \return
	///
	/// \~
	/// \author Zoltán Hrabovszki
	/// \date 2015.05.01
	public void AddLogger( ILogger fpLogger )
	{
		LoggerList.add(fpLogger);
	}

	/// \~german
	/// \brief Initialisiert die Klasse.
	///
	/// - Löscht die Liste LoggerList
	/// - Schaltet den DebugMode aus.
	/// - Fügt Log2Console hinzu
	///
	/// \todo TODO: Ausführliche Beschreibung
	///
	/// \~english
	/// \brief \todo TODO: Brief Description.
	///
	/// \todo TODO: Detailed Description
	///
	/// \~
	/// \author Zoltán Hrabovszki
	/// \date 2015.05.01
	public static void Init()
	{
		LoggerList.clear();
		
		LoggerList.add( new Log2Console() );
		cvbDebugMode = false;
	}

	/// \brief
	/// LogError Function:
	/// Logs an error message to the results file.
	///
	/// \param fps_Message
	public void LogError( String fps_Message )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogError(fps_Message);
		}
	}

	/// \brief
	/// LogException Function:
	/// Logs a Script Exception to the results file.
	///
	/// \param fps_Message
	public void LogException( String fps_Message )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogException(fps_Message);
		}
	}

	public void LogFunctionEnd( String fps_Return )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEnd(fps_Return);
		}
	}

	public void LogFunctionEnd( Boolean fpb_Return )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEnd(fpb_Return);
		}
	}

	public void LogFunctionEnd( List<String> fpls_Return )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEnd(fpls_Return);
		}
	}

	public void LogFunctionEnd()
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEnd();
		}
	}

	public void LogFunctionEndDebug()
	{

		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEndDebug();
		}
	}

	public void LogFunctionEndDebug( String fps_Return )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEndDebug(fps_Return);
		}
	}

	public void LogFunctionEndDebug( List<String> fps_Return )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEndDebug(fps_Return);
		}
	}

	public void LogFunctionEndDebug( Boolean fpb_Return )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionEndDebug(fpb_Return);
		}
	}

	public void LogFunctionStart( String fps_FunctionName, String... fpsParameter )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionStart(fps_FunctionName, fpsParameter);
		}
	}

	public void LogFunctionStartDebug( String fps_FunctionName, String... fpsParameter )
	{

		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogFunctionStartDebug(fps_FunctionName, fpsParameter);
		}
	}

	public void LogKeyWordEnd()
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogKeyWordEnd();
		}
	}

	public void LogSequenceEnd()
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogSequenceEnd( );
		}
	}


	public void LogTestcaseEnd()
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogTestcaseEnd();
		}
	}

	
	/// \~german
	/// \brief Ausgabe eines Schlüsselwortes
	///
	/// \todo TODO: Ausführliche Beschreibung
	///
	/// \param Parameterbezeichner \todo TODO: Beschreibung
	/// \return
	///
	/// \~english
	/// \brief \todo TODO: Brief Description.
	///
	/// \todo TODO: Detailed Description
	///
	/// \param fpbTrueOrFalse
	/// \return
	///
	/// \~
	/// \author Zoltán Hrabovszki
	/// \date 2015.05.01
	public void LogKeyWordStart( String fpsKeyWordName, String... fpsParameter )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogKeyWordStart(fpsKeyWordName, fpsParameter);
		}
	}

	public void LogSequenceStart(String fps_KeyWordName, String fpsWinowFN, String fps_SequensName, String... fpsParameter)
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogSequenceStart( fps_KeyWordName, fpsWinowFN, fps_SequensName, fpsParameter );
		}
	}

	
	/// \~german
	/// \brief Ausgabe des Testfalls
	///
	/// \todo TODO: Ausführliche Beschreibung
	///
	/// \param Parameterbezeichner \todo TODO: Beschreibung
	/// \return
	///
	/// \~english
	/// \brief \todo TODO: Brief Description.
	///
	/// \todo TODO: Detailed Description
	///
	/// \param fpbTrueOrFalse
	/// \return
	///
	/// \~
	/// \author Zoltán Hrabovszki
	/// \date 2015.05.01
	public void LogTestcaseStart( String fps_TestcaseName )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogTestcaseStart( fps_TestcaseName );
		}
	}

	/// \brief
	/// LogPrint Function:
	/// Prints the values of expressions to the results file.
	///
	/// \param fps_Message
	public void LogPrint( String fps_Message )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogPrint(fps_Message);
		}
	}

	/// \~german
	/// \brief Loggt eine Nachricht
	///
	/// Die Nachricht fpsMessage wird im Ergebniss ausgegeben.
	///
	/// \param fpsMessage Nachricht, die Ausgegeben werden soll.
	///
	/// \~english
	/// \brief \todo TODO: Brief sescription.
	///
	/// \todo TODO: Detailed Description
	///
	/// \param fpsMessage
	/// \return
	///
	/// \~
	/// \author Zoltán Hrabovszki
	/// \date 2015.05.01
	/// \brief
	/// Prints the message fps_Messageof expressions to the results file, if
	/// cvb_DebugMode
	///
	/// \param fps_Message Message to Print
	public void LogPrintDebug( String fpsMessage )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogPrintDebug(fpsMessage);
		}
	}

	/// \~german
	/// \brief Logt Soll-Ist Vergeleich.
	///
	/// \todo TODO: Ausführliche Beschreibung erstellen
	///
	/// \param fpsActual Ist-Wert, welche geprüft werden soll.
	/// \param fpsExpected Erwarteter Soll-Wert, gegen den geprüft wird.
	/// \return
	///
	/// \~english
	/// \brief \todo TODO: Brief Description.
	///
	/// \todo TODO: Detailed Description
	///
	/// \param fpbTrueOrFalse
	/// \return
	///
	/// \~
	/// \author Zoltán Hrabovszki
	/// \date 2015.05.01
	public void LogVerify( String fpsActual, String fpsExpected )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogVerify(fpsActual, fpsExpected);
		}
	}

	/// \brief
	/// LogWarning Function:
	/// Logs a warning to the results file.
	///
	/// \param fps_Message
	public void LogWarning( String fps_Message )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.LogWarning(fps_Message);
		}
	}

	/// \brief
	/// Closes a hierarchical level in the results file that was opened with
	/// ResOpenList.
	/// Use ResOpenList to start a new hierarchical level.
	/// The Print commands that follow it are indented until you close the level
	/// with ResCloseList.
	/// When you view the results file, the item sName is initially collapsed,
	/// indicated by the [+] icon next to it.
	/// You can expand sName to reveal the indented items under it.
	///
	public void ResCloseList()
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.ResCloseList();
		}
	}

	/// \brief
	/// Closes a hierarchical level in the results file that was opened with
	/// ResOpenList.
	/// Use ResOpenList to start a new hierarchical level.
	/// The Print commands that follow it are indented until you close the level
	/// with ResCloseList.
	/// When you view the results file, the item sName is initially collapsed,
	/// indicated by the [+] icon next to it.
	/// You can expand sName to reveal the indented items under it.
	///
	public void ResCloseListDebug()
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.ResCloseListDebug();
		}
	}

	/// \brief
	/// Creates a new hierarchical level in the results file.
	/// Use ResOpenList to start a new hierarchical level.
	/// The Print commands that follow it are indented until you close the level
	/// with ResCloseList.
	/// When you view the results file, the item sName is initially collapsed,
	/// indicated by the [+] icon next to it.
	/// You can expand sName to reveal the indented items under it.
	///
	/// \param fps_Name
	public void ResOpenList( String fps_Name )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.ResOpenList(fps_Name);
		}
	}

	/// \~german
	/// \brief Erzeugt eine hierachische Log-Ebene in der Ergenbniss-Ausgabe.
	///
	/// \todo TODO: Ausführliche Beschreibung erstellen.
	///
	/// \param fpsListCaption Überschrift der Log-Ebene.
	///
	/// \~english
	/// \brief
	/// Creates a new hierarchical level in the results file.
	///
	/// Use ResOpenList to start a new hierarchical level.
	/// The Print commands that follow it are indented until you close the level
	/// with ResCloseList.
	/// When you view the results file, the item sName is initially collapsed,
	/// indicated by the [+] icon next to it.
	/// You can expand sName to reveal the indented items under it.
	///
	/// \param fpsListCaption Caption of the resultlist.
	///
	/// \~
	/// \author Zoltán Hrabovszki
	/// \date 2015.05.01
	public void ResOpenListDebug( String fpsListCaption )
	{
		for (ILogger myLogger : LoggerList)
		{
			myLogger.ResOpenListDebug(fpsListCaption);
		}
	}
}