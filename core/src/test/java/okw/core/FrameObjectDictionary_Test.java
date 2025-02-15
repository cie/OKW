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

package okw.core;

import static org.junit.Assert.*;

import javax.xml.xpath.XPathExpressionException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import okw.*;
import okw.exceptions.*;
import okw.log.*;

/// \brief
/// This is a test class for FrameObjectDictionaryTest and is intended
/// to contain all FrameObjectDictionaryTest Unit Tests
/// 
public class FrameObjectDictionary_Test
{

  // / \brief
  // / This is a test class for FrameObjectDictionaryTest and is intended
  // / to contain all FrameObjectDictionaryTest Unit Tests
  // /
  static FrameObjectDictionary_Sngltn target; // = new

  // FrameObjectDictionary();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {

    Logger_Sngltn.getInstance();
    // Reset des Loggers: Alle geladenen Instanzen löschen
    Logger_Sngltn.init();

    // Logger_Sngltn.getInstance().AddLogger(new Log2Console());
    Logger_Sngltn.getInstance().setDebugMode( false );

    // System.out.Printline( "OKW_XML: " +
    // Environment.GetEnvironmentVariable("OKW_Xml"));

    target = FrameObjectDictionary_Sngltn.getInstance();
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  // / \~german
  // / \brief
  // / Prüft die Initialisierung der Klasse FrameObjectDictionary.
  // /
  // / Vorgehen: Klasse wird im ersten Schritt mit Frames geladen,
  // /
  // / \~german
  // / \brief
  // /
  // / \~
  // / \author Zoltan Hrabovszki
  // / \date 2014-09-28
  @Test
  public void tc_InitTest()
  {
  
    // ------------------------------------------------------------------------------------
    //
    // Frame laden.
    try
    {
      target.getChildObjectByName( "Rechner", "Taste_3" );
    }
    catch (XPathExpressionException | IllegalArgumentException | IllegalAccessException e)
    {
  
      fail( e.getMessage() );
    }
  
    // Prüfen ob die erwarteten Elemente Rechner, Taste_3 vorhanden sind.
    // Does the "Rechner" exists?
    Boolean object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "Rechner" );
    assertEquals( true, object_Exists );
  
    // Is the Child also there?
    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "Rechner.Taste_3" );
    assertEquals( true, object_Exists );
  
    // ------------------------------------------------------------------------------------
    // Nach der Initialisiserung müssen die Objekte "Rechner" und Taste_3
    // erneut existieren.
    try
    {
      FrameObjectDictionary_Sngltn.init();
    }
    catch (Exception e)
    {
  
      fail( e.getMessage() );
    }
  
    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "Rechner" );
    assertEquals( true, object_Exists );
  
    // Is the Child also there?
    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "Rechner.Taste_3" );
    assertEquals( true, object_Exists );
  }

  // / \brief
  // / A test for CreateInstanceByObjectName
  // /
  @Test
  public void tc_GetObjectByName_Window()
  {
    Object actual = null;

    try
    {
      actual = target.getParentObjectByName( "Rechner" );
    }
    catch (XPathExpressionException e)
    {
      fail( e.getMessage() );
    }

    // Does the "Rechner" exists?
    Boolean object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "Rechner" );
    assertEquals( true, object_Exists );

    // We get the <<right>> object?
    Object expected = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.get( "Rechner" );

    assertEquals( expected.toString(), actual.toString() );

    assertEquals( expected, actual );
  }

  /**
   * \brief A test for CreateInstanceByObjectName
   */
  @Test
  public void tc_GetObjectByName_WindowAndChild()
  {

    // Does the "Rechner" exists?
    Boolean object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "Rechner" );
    assertEquals( true, object_Exists );

    // Child is Also there?
    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "Rechner.Taste_3" );
    assertEquals( true, object_Exists );
  }


  /**
   * \brief A test for CreateInstanceByObjectName
   */
  @Test
  public void tc_GetObjectByName_WindowAndChild_GuiContainer()
  {
    // Does the "Rechner" exists?
    Boolean object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "frmObjDictTest" );
    assertEquals( true, object_Exists );

    // Child is also there?
    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "frmObjDictTest.Name" );
    assertEquals( true, object_Exists );
  }
  
  
  /**
   * \brief A test for a Inline Class definition
   */
  @Test
  public void tc_GetObjectByName_WindowAndChild_InlineClassDef()
  {
    // Does the "Rechner" exists?
    Boolean object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "frmObjDictTest" );
    assertEquals( true, object_Exists );

    // Are the Child loaded?
    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "frmObjDictTest.Tastatur" );
    assertEquals( true, object_Exists );

    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "frmObjDictTest.Taste_2" );
    assertEquals( true, object_Exists );

    object_Exists = FrameObjectDictionary_Sngltn.myFrameObjectDictionary.containsKey( "frmObjDictTest.Taste_2" );
    assertEquals( true, object_Exists );
  }  
  
  /**
   * \~german
   * \brief
   * Prüft ob eine OKWFrameObjectWindowNotFoundException ausgelöst wird,
   * wenn das Fensterobjekt nicht existiert.
   *
   * Bedingung für das auslösen:
   * -# Das Fenster-Objekt _existiert nicht_.
   *
   * Das Kind-Objekt wird als parameter nicht angegeben.
   *
   * \~german
   * \brief
   *
   * \~
   * \author Zoltan Hrabovszki
   * \date 2014-09-28
   */
  @Test
  public void tc_CreatInstanceByObjectName_OKWFrameObjectWindowNotFoundException_1()
  {
    try
    {
      target.getParentObjectByName( "RechnerNotExists" );
      fail();
    }
    catch (OKWFrameObjectParentNotFoundException e)
    {
      Logger_Sngltn.getInstance().LogPrint( "OKWFrameObjectParentNotFoundException wurde ausgelöst, das ist für diesen Test OK!" );
    }
    catch (Exception e)
    {
      Logger_Sngltn.getInstance().LogPrint( "Exception: " + e.getClass().getName() );
      Logger_Sngltn.getInstance().LogPrint( "Nachricht: " + e.getMessage() );
      fail( "Falsche Exception: " + e.getMessage() );
    }
  }

  /**
   * \~german
   * \brief
   * Prüft ob eine OKWFrameObjectWindowNotFoundException ausgelöst wird,
   * wenn das Fensterobjekt nicht existiert.
   *
   * Bedingung für das auslösen:
   * -# Das Fenster-Objekt _existiert nicht_.
   * -# Das Kind-Objekt _existiert_ dann auch _nicht_.
   *
   * Das Kind-Objekt wird als parameter nicht angegeben.
   *
   *
   * \~german
   * \brief
   *
   * \~
   * \author Zoltan Hrabovszki
   * \date 2014-09-28
   */
  @Test
  public void tc_CreatInstanceByObjectName__OKWFrameObjectWindowNotFoundException()
  {

    try
    {
      target.getChildObjectByName( "RechnerNotExists", "Taste_3" );
      fail();
    }
    catch (OKWFrameObjectChildNotFoundException e)
    {
      Logger_Sngltn.getInstance().LogPrint( "OKWFrameObjectChildNotFoundException wurde ausgelöst, das ist für diesen Test OK!" );
    }
    catch (Exception e)
    {
      Logger_Sngltn.getInstance().LogPrint( "Exception: " + e.getClass().getName() );
      Logger_Sngltn.getInstance().LogPrint( "Nachricht: " + e.getMessage() );
      fail( "Falsche Exception: " + e.getMessage() );
    }
  }

  // / \~german
  // / \brief
  // / Prüft ob eine OKWFrameObjectChildNotFoundException ausgelöst wird
  // /
  // / Bedingung für das auslösen:
  // / -# Das Fenster-Objekt _existiert_.
  // / -# Das Kind-Objekt _existiert nicht_.
  // /
  // /
  // /
  // / \~german
  // / \brief
  // /
  // / \~
  // / \author Zoltan Hrabovszki
  // / \date 2014-09-28
  @Test
  public void tc_CreatInstanceByObjectName_OKWFrameObjectChildNotFoundException()
  {

    try
    {
      target.getChildObjectByName( "Rechner", "TasteNotExists" );
      fail();
    }
    catch (OKWFrameObjectChildNotFoundException e)
    {
      Logger_Sngltn.getInstance().LogPrint( "OKWFrameObjectChildNotFoundException wurde ausgelöst, das ist für diesen Test OK!" );
    }
    catch (Exception e)
    {
      Logger_Sngltn.getInstance().LogPrint( "Exception: " + e.getClass().getName() );
      Logger_Sngltn.getInstance().LogPrint( "Nachricht: " + e.getMessage() );
      fail( "Falsche Exception: " + e.getMessage() );
    }
  }
}
