/*
    ==============================================================================
      Author: Zoltán Hrabovszki <zh@openkeyword.de>

      Copyright © 2012 - 2017 IT-Beratung Hrabovszki
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

package okw;


import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import okw.log.*;

@RunWith(Parameterized.class)
public class OKW_Helper_MatchStrIgnoreCaseTest
    {

    private Boolean ExpectedValue;
    
    private String InputValue_1;
    private String InputValue_2;
    
    /// \copydoc CurrentObject::Log()
    public static Logger_Sngltn Log;

    @Parameters( name = "{index}: {0} = MatchStrIgnoreCase[\"{1}\", \"{2}\"] ")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
        		{ true, "Fux, Hase*",   "Fux, Hase, Bär"},
        		{ true, "Fux*Bär",      "Fux, Hase, Bär"},
        		{ true, "Fux*Hase*Bär", "Fux, Hase, Bär"},
        		{ true, "Fux*Hase*Bär", "Fux, hase, bär"},
        		{ true, "Fux*Bär",      "Fux, hase, bär"},
        		{ true, "Fux, hase*",   "Fux, Hase, Bär"},
        		{ true, "Hase",         "hase"},
        		{ true, "*Hase, Bär",   "Fux, Hase, Bär"},
        		{ true, "*Hase*",       "Fux, Hase, Bär"},
        		{ true, "*Hase*",       "Fux, hASE, bär"},
        		{ true, "*Hase, bär",   "Fux, Hase, Bär"}

           });
    }
    
    public OKW_Helper_MatchStrIgnoreCaseTest(Boolean ExpectedValue, String InputValue_1, String InputValue_2) {
    	
    	   this.ExpectedValue = ExpectedValue;
    	    
    	   this.InputValue_1 = InputValue_1;
    	   this.InputValue_2 = InputValue_2;
    	   }

    
		//@BeforeClass
        public static void myTestFixtureSetUp()
        {
			Log = Logger_Sngltn.getInstance();
            // Reset des Loggers: Alle geladenen Instanzen löschen
            Logger_Sngltn.init();

            //Log.AddLogger(new Log2Console());
            Log.setDebugMode(false);
        }

        @Test
        public void tcMatchStr()
        {
            Boolean actual = false;
            Boolean expected = ExpectedValue;

            actual = OKW_Helper.matchStrIgnoreCase( InputValue_1, InputValue_2);
            assertEquals(expected, actual);
        }
}

