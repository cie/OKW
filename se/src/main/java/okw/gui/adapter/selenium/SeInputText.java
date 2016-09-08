package okw.gui.adapter.selenium;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import okw.gui.OKWLocator;

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

    // , IOKW_SimpleDataObj
    /// \~german
    /// \brief
    /// Diese Klasse implmenetiert die Methoden der IOKW_SimpleDataObj für ein DOM-Texfield<br/>.
    /// GUI-Automatisierungswerkzeug: Selenium.<br/>
    /// Die meisten Methoden werden aus der abtrakten Klasse SeSimpleDataObjekt geerbt.
    /// 
    /// \~
    /// \author Zoltan Hrabovszki
    /// \date 2014.06.2014
    public class SeInputText extends SeSimpleDataObjBase
    {

        /// \brief
        /// 
        /// 
        /// \param Locator definiert die Objekterkennungseigenschaft des Objektes und wird als XPATH angegeben.
        public SeInputText(String Locator, OKWLocator... fpLocators)
        {
        	super(Locator, fpLocators);
        }


        /// \~german
        /// \brief
        /// Ermittelt den textuellen Inhalt eines Textfeldes.<br/>.
        /// GUI-Automatisierungswerkzeug: Selenium.<br/>
        /// 
        /// \return
        /// Gibt den Textuellen Inhaltes eines DOM-TextField-s zurück.
        /// Es korrespondieren je eine Zeile des GUI-Objektes mit jeweil einem Listen-Element.<br/>
        /// Ein Textfield besteht aus einerZeile: Daher wird der Wert des Textfield-s im ListenElement[0] abgelegt.
        /// Zurückgegeben.
        /// \return
        /// \~
        /// \author Zoltan Hrabovszki
        /// \date 2014.06.2014
        @Override
        public ArrayList<String> getValue()
        {
            ArrayList<String> lvLsReturn = new ArrayList<String>();
            Boolean bOK = false;

            try
            {
                this.LogFunctionStartDebug("GetValue");

                // Get Value from TextField and put this into the return List<string>
                lvLsReturn.add(this.Me().getAttribute("value"));
                //lvLsReturn.add(this.Me().getText());
                bOK = true;
            }
            finally
            {
                if (bOK)
                {
                    this.LogFunctionEndDebug(lvLsReturn.toString());
                }
                else
                {
                    this.LogFunctionEndDebug();
                }
            }

            return lvLsReturn;
        }
        
        public void setAttribute( WebElement elem, String value )
        {
        	JavascriptExecutor js = (JavascriptExecutor) SeDriver.getInstance().driver; 

            String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";

            js.executeScript(scriptSetAttrValue, elem, "value", value);

        }

        /// \~german
        public void setValue(ArrayList<String> fpsValues)
        {
            this.LogFunctionStartDebug("SetValue", "fps_Values", fpsValues.toString());

            try
            {
                WebElement lvWebElement = this.Me();

                this.setAttribute(lvWebElement, fpsValues.get(0));
            }
            finally
            {
                this.LogFunctionEndDebug();
            }

            return;
        }

}