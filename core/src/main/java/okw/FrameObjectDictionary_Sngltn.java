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

package okw;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import okw.core.IOKW_FN;
import okw.exceptions.*;
import okw.log.Logger_Sngltn;

/** \~german
 *  Verwaltet zentral die Frame-Klassen: Instanziert Frame-Klassen zu Frame-Objekten
 *  und liefert die Referenzen auf diese zurück.
 *  
 *  \todo TODO: ZH Dokumentaion erweitern.
 *  \~english
 *  \brief
 *  \todo TODO: JN Übersetzung/Review
 *  
 *  \~
 *  \author Zoltan Hrabovszki
 *  \date 2014-10-10
 */
public class FrameObjectDictionary_Sngltn
{

  // / \~german
  // / \brief
  // / Zentrale Liste, genauer Dictionary/Hash der aktuell benutzten
  // / Frame-Objekte.
  // /
  // / Der Zugriff erfolgt über die Methoden:
  // / - FrameObjectDictionary.GetObjectByName(String)
  // / - FrameObjectDictionary.GetObjectByName(String,String)
  // / - FrameObjectDictionary.Init()
  // /
  // / \note Feld ist Public
  // / \~english
  // / \brief
  // / \todo TODO: JN Übersetzung/Review
  // /
  // / \~
  // / \author Zoltan Hrabovszki
  // / \date 2014.10.10
  // / \todo TODO: Bedingte Kompilierung einfügen für "Test" Feld muss Public
  // / sein, wenn unittets durchgeführt werden soll.
  public static Map<String, Object>   myFrameObjectDictionary = new HashMap<String, Object>();

  public static Map<String, OKW>   myAnnotationDictionary = new HashMap<String, OKW>();
  
  /**
   *  \copydoc Logger_Sngltn::getInstance()
   */
  private static Logger_Sngltn Log = Logger_Sngltn.getInstance();

  
  /**
   *  \copybrief LogMessenger
   */
  private static LogMessenger  LM;

  
  private static FrameObjectDictionary_Sngltn Instance = null;

  
  /** \~german
   *  \brief
   *  Konstuktor der Klasse. Es wird die Methode
   *  aufgerufen.
   * 
   *  \~english
   * 
   *  \~
   *  @author Zoltan Hrabovszki
   *  @date 2014.10.10
   */
  private FrameObjectDictionary_Sngltn()
  {
  }

  public static FrameObjectDictionary_Sngltn getInstance() throws XPathExpressionException, JAXBException, ParserConfigurationException, SAXException,
          IOException
  {
    Log.LogFunctionStartDebug( "FrameObjectDictionary.getInstance" );

    // Lazy Initialization (If required then only)
    if ( Instance == null )
    {
      // Thread Safe. Might be costly operation in some case
      synchronized ( FrameObjectDictionary_Sngltn.class )
      {
        if ( Instance == null )
        {
          Instance = new FrameObjectDictionary_Sngltn();
          try
          {
            init();
          }
          catch (Exception e)
          {
            OKW_HandleException.StopRunning( e, Instance.getClass() );
          }
        }
      }
    }

    Log.LogFunctionEndDebug( );

    return Instance;
  }

  /** \~german
   *  Die Methode liefert das Frame-Object des gegebenen FN eines Fensterobjektes zurück.
   * 
   *  Das Objekt ist bereits erzeugt (instanziert),
   *  und liegt im FrameObjectDictionary 
   *  FrameObjectDictionary_Sngltn.myFrameObjectDictionary.
   * 
   *  Wenn das Object nicht im FrameObjectDictionary vorhanden ist, dann wird 
   *  die Ausnahme OKWFrameObjectParentNotFoundException ausgelöst.
   *  \see Für das Kindobjekt existiert eine zweite Ausprägung dieser
   *  Methode: FrameObjectDictionary.GetObjectByName(String,String)
   * 
   *  @param fpsParentObject FN des Fenster Objektes = Elternobjekt.
   *  @return Rückgabe des instantierten Fenster-Objektes.
   * 
   *  \~english
   *  The method delivers the frame Object of the given window Object.
   * 
   *  Here the Object can already be instantiated,
   *  in this case the reference will be taken from the Dictionary
   *  OKW_FrameObjectDictionary.cv_FrameObjectDictionary
   *  or the Object is not yet instantiated,
   *  then an instance is to be laid and a reference to the Object is stored.
   * 
   *  \see For the child Object another markedness ist existing.:
   *  FrameObjectDictionary.GetObjectByName(String,String)
   * 
   *  @param fpsParentObject Fachlicher Name des Fenster Objektes = Elternobjekt
   *  @return \todo TODO: ZH Rückgabewert beschreiben
   * 
   *  \~
   *  \author Zoltan Hrabovszki
   *  \date 2014.10.10
   */
  public Object getParentObjectByName( String FNParentObject ) throws XPathExpressionException
  {
    Object lvo_Return = null;

    Log.LogFunctionStartDebug( "FrameObjectDictionary.GetParentObjectByName", "FNParentObject", FNParentObject );
    try
    {
      // Gibt es den Schlüssel im Dictinary? - D.h. Gibt es schon eine
      // Instanz des Objektes im Speicher?
      Log.LogPrintDebug( LM.GetMessage( "GetParentObjectByName", "M1", FNParentObject ) );
      if ( myFrameObjectDictionary.containsKey( FNParentObject ) )
      {
        // Ja, das Objekt existiert.
        Log.LogPrintDebug( LM.GetMessage( "GetParentObjectByName", "M2" ) );
        lvo_Return = myFrameObjectDictionary.get( FNParentObject );
      }
      else
      {
        // Nein -> Frame zum Funtionalen NAme wurde nicht gefunden ->
        // Exception auslösen...
        // \todo TODO: Fehlermeldung einbauen...
        Log.LogPrintDebug( LM.GetMessage( "GetParentObjectByName", "M3" ) );

        String lvsMessage = LM.GetMessage( "GetParentObjectByName", "OKWFrameObjectParentNotFoundException", FNParentObject );
        throw new OKWFrameObjectParentNotFoundException( lvsMessage );
      }
    }
    finally
    {
      if ( lvo_Return != null )
      {
        Log.LogFunctionEndDebug( lvo_Return.toString() );
      }
      else
      {
        Log.LogFunctionEndDebug();
      }
    }

    return lvo_Return;
  }

  /** \~english
   *  \~german
   *  Methode Ermittelt alle FN´s der Kinder des gegebene Fensters (Parent).<br>
   *  
   *  _Hinweis:_ Methode filtert alle FNs aus myFarmeObjectDictionary heraus, die mit FN_Parent Anfangen.<br>
   *  D.h. Es kann auch auch die Kinder eines Radiolist ermitteln. Siehe auch SeRadioList.
   *  
   *  @param FN_Parent Funktionaler Name des Fenster welches Durchsucht werden soll.
   *  @return Liste der gefunden Child-Keys´s, 
   *  \~
   *  @author Zoltan Hrabovszki
   *  \date 2016.11.18
   */
  public ArrayList<String> getAllChildKeysOfParent(String FN_Parent)
  {
    ArrayList<String> lvAlReturn = new ArrayList<String>();
    
    Set<String> lvKeys = myFrameObjectDictionary.keySet();
    
    for ( Object lvoKey : lvKeys )
    {
      String lvsKey = (String)lvoKey;
      
      if (lvsKey.startsWith( FN_Parent ) )
      {
          lvAlReturn.add( lvsKey );
      }
    }    
    return lvAlReturn;
  }
  
  
  /**
   * \~german \brief Die Methode liefert das Objekt des gegebenen
   * Kindobjekttests zurück.
   * 
   * \see Für das Fensterobjekt existiert eine zweite Ausprägung dieser Methode.
   * FrameObjectDictionary.GetObjectByName(String,String)
   * 
   * @param fps_ParentObject Name des Fenster-Objektes = Elternobjekt. \param
   * fps_ChildObject Name des Kindobjektes-Objektes.
   * 
   * @return Frame-Objekt-Referenz auf das Kindobjekt.
   * 
   * \~english
   * The method delivers the Object of the given ChildObjecttest.
   * 
   * \see For the window Object another markedness is existing.
   * FrameObjectDictionary.GetObjectByName(String,String)
   * 
   * @param fps_ParentObject Name des Fenster-Objektes = Elternobjekt. \param
   * fps_ChildObject Name des Kindobjektes-Objektes.
   * @return \todo TODO: Rückgabewert übersetzen/review
   * 
   * \~
   * @author Zoltan Hrabovszki
   * \date 2014.10.10 
   * \todo TODO:
   * @throws IllegalAccessException 
   * @throws IllegalArgumentException 
   */
  public Object getChildObjectByName( String fps_ParentObject, String fps_ChildObject ) throws XPathExpressionException, IllegalArgumentException, IllegalAccessException
  {
    Object lvo_Return = null;
    String lvs_ObjectName = fps_ParentObject + "." + fps_ChildObject;

    Log.LogFunctionStartDebug( "FrameObjectDictionary.GetChildObjectByName", "fps_ParentObject", fps_ParentObject, "fps_ChildObject", fps_ChildObject );

    // Gibt es den Schlüssel im Dictinary?
    Log.LogPrintDebug( LM.GetMessage( "GetChildObjectByName", "M1", fps_ChildObject, fps_ParentObject ) );
    try
    {
      if ( myFrameObjectDictionary.containsKey( lvs_ObjectName ) )
      {
        // Ja, das Objekt existiert.
        Log.LogPrintDebug( LM.GetMessage( "GetChildObjectByName", "M2" ) );
        
        // Hole die Referenz auf das Objekt aus dem Dictionary 
        lvo_Return = myFrameObjectDictionary.get( lvs_ObjectName );
        // lvo_Return = myField.get( this.GetParentObjectByName( fps_ParentObject ) );
      }
      else
      {
        // Nein -> Frame zum Funtionalen Name wurde nicht gefunden ->
        // Exception OKWFrameObjectChildNotFoundException auslösen...
        // \todo TODO: Fehlermeldung einbauen...
        Log.LogPrintDebug( LM.GetMessage( "GetChildObjectByName", "M3" ) );

        String lvsMessage = LM.GetMessage( "GetChildObjectByName", "OKWFrameObjectChildNotFoundException", lvs_ObjectName );
        throw new OKWFrameObjectChildNotFoundException( lvsMessage );
      }
    }
    finally
    {
      if ( lvo_Return == null )
      {
        Log.LogFunctionEndDebug();
      }
      else
      {
        Log.LogFunctionEndDebug( lvo_Return.toString() );
      }
    }
    return lvo_Return;
  }

  /**
   * \~german 
   * \brief Methode initialisiert diese Klasse.
   * 
   * Es wird - LogMessender-Instanz erzeugt. - das FrameObjektDictionary wird
   * gelöscht. - Es werden die Frame-Objekte geladen (instanziert)
   * 
   * \~english 
   * \brief \todo TODO: JN Übersetzung/Review
   * 
   * \~ 
   * @author Zoltan Hrabovszki
   * @date 2014.10.10
   */
  public static void init() throws ClassNotFoundException, InstantiationException, JAXBException, ParserConfigurationException, SAXException, IOException,
          XPathExpressionException, IllegalArgumentException, IllegalAccessException
  {
    Log.LogFunctionStartDebug( "FrameObjectDictionary.Init" );

    try
    {
      LM = new LogMessenger( "FrameObjectDictionary" );

      Log.LogPrintDebug( LM.GetMessage( "Init", "InitClear", "FrameObjectDictionary" ) );

      myFrameObjectDictionary.clear();

      frameScan( );

      Set<String> Keys = myFrameObjectDictionary.keySet();

      ArrayList<String> myList = new ArrayList<String>();

      for ( String Key : Keys )
      {
        myList.add( Key );
      }

      Collections.sort( myList );

      Log.ResOpenListDebug( "Frames" );

      for ( String Element : myList )
      {
        Log.LogPrintDebug( Element );
      }

      Log.ResCloseListDebug();

    }
    finally
    {
      Log.LogFunctionEndDebug();
    }
  }

  /** \~german
   *  \brief
   *  Methode erstellt eine Liste aller OKWGUI-Rahmenklassen (Frame class),
   *  die in der aktuell ausgeführten Assambly
   *  definiert sind.
   * 
   *  \note __WICHTIG:__ Es werden Klassen in die Liste aufgenommen, die
   *  -# dem Namepsace OKW.Frames zugeordent sind
   *  -# und mit dem Annotiation OKWGUIAttribute "geschmückt" ist.
   * 
   *  \return
   *  Liefert die Liste der Klasse, die der Namespace OKW.Frames und mit dem
   *  Attribute OKWGUI vershen sind.
   * 
   *  \~english
   *  \brief
   *  \note __IMPORTANT:__ the class is forced to be exactly, as specified.
   *  Here is no 'frame prefix' added.
   *  \return
   * 
   *  \~
   *  \author Zoltan Hrabovszki
   *  \date 2014.10.10
   * @throws XPathExpressionException 
   */
  public static ArrayList<Class<?>> getListOfOKWGuiClasses() throws ClassNotFoundException, IOException, XPathExpressionException
  {
    String lvsNamespace = "okw.gui.frames";
    ArrayList<Class<?>> lvALReturn = new ArrayList<Class<?>>();

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    // 1. Hole alle Klassen, die sich im Package "okw.gui.frames" befinden
    ArrayList<String> lvALClassNames = OKW_GetJavaClass.getClasses( lvsNamespace );

    try
    {
      // 2. Hole alle Klassen, die Annotation "OKW_FN" haben.
      for ( String lvsClass : lvALClassNames )
      {
        Class<?> aClass = classLoader.loadClass( lvsClass );

        // Wenn die Klasse die Annotaion OKW_FN hat, dann in die
        // Rückgabe-Liste eintrage
        if ( aClass.isAnnotationPresent( OKW.class ) )
        {
          lvALReturn.add( aClass );
        }
      }
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }

    return lvALReturn;
  }

  
  /** \~german
   *  Liefert die Instanz der Klasse.
   * 
   *  \param fps_ParentClassName Name der Klasse
   * 
   *  \~english
   *  \brief
   *
   * 
   *  \~
   *  @author Zoltan Hrabovszki
   *  @throws IOException 
   *  @date 2015.01.28
   *  \todo TODO: rename lvsFNParent to lvsFNWindow (Parent is not correct in all cases)
   */
  private static void frameScan(  ) throws InstantiationException, XPathExpressionException,
          IllegalArgumentException, IllegalAccessException, ClassNotFoundException, IOException
  {
    Object lvTypeInstanceAsObject = null;

    Log.ResOpenList( "List of Frames..." );
    
    try
    {
      // Alle Fenster Klassen, die eine Annotation ermitteln...
      ArrayList<Class<?>> lvOKWGuiClasses = getListOfOKWGuiClasses();

      // Für jede Klasse in der Liste...
      for ( Class<?> lvOKWGuiClass : lvOKWGuiClasses )
      {
        // Erzeuge eine Instanz der Window-Klasse
        lvTypeInstanceAsObject = OKW_GetJavaClass.createInstanceByType( lvOKWGuiClass );

        // FN (Fachlichen Namen) der Klasse aus der die Annotiation OKW.FN() auslesen.
        // Hierbei handelt es sich um des  ParentObject = Fenster
        OKW lvOKWWindow = getOKWFromObjekt( lvTypeInstanceAsObject );
        String lvsFNWindow = lvOKWWindow.FN();
        
        // Wenn Attribute vorhanden (!=null) und nicht Leer (!= "") dann ins Dictionary einfügen.
        if ( lvsFNWindow != null && !lvsFNWindow.isEmpty() )
        {
          Log.ResOpenList( "Parent: '" + lvsFNWindow + "'" );
          Log.LogPrint( "Type: '" + lvTypeInstanceAsObject.getClass().getName() + "'" );

          ((IOKW_FN)lvTypeInstanceAsObject).setFN( lvsFNWindow );
          ((IOKW_FN)lvTypeInstanceAsObject).setKN( lvsFNWindow );
          
          myFrameObjectDictionary.put( lvsFNWindow, lvTypeInstanceAsObject );
          myAnnotationDictionary.put( lvsFNWindow, lvOKWWindow );

          Log.LogPrintDebug( LM.GetMessage( "CreateInstanceByObjectName", "InstanceWasCreated", lvTypeInstanceAsObject.getClass().getName() ) );

          // Rekursive alle Felder (Fields) der Frame-Klasse durchsuchen...
          // und einlesen, wenn diese eine Annotaion besitzen.
          Field[] lvFields = lvTypeInstanceAsObject.getClass().getFields();

          for ( Field lvField : lvFields )
          {

            String myFieldType   = lvField.getType().getName();
            
            // Die Instance des Feldes holen...
            Object lvFieldInstance = lvField.get( lvTypeInstanceAsObject );
              
            if ( lvField.isAnnotationPresent( OKW.class ) )
            {
                OKW myFN = lvField.getAnnotation( OKW.class );
                // Get the value from property.

                String lvsFNChild  = myFN.FN();
                String lvsChildKey = lvsFNWindow + "." + lvsFNChild;

                Log.ResOpenList( "Child: '" + lvsFNChild + "'" );
                Log.LogPrint( "  Key: '" + lvsChildKey + "'" );
                Log.LogPrint( " Type: '" + myFieldType + "'" );

                // Add child-object to the dictionary
                myAnnotationDictionary.put( lvsChildKey, myFN );
                
                try
                {
                	 // Now here we tell the GUI-Adapter his FN and his Parent-FN...
                   (( IOKW_FN ) lvFieldInstance).setKN( lvsChildKey );
                   (( IOKW_FN ) lvFieldInstance).setFN( lvsFNChild );
                   (( IOKW_FN ) lvFieldInstance).setParentFN( lvsFNWindow );
                   myFrameObjectDictionary.put( lvsChildKey, lvFieldInstance );
                   
                   frameScanFieldsRecursively( lvField, lvFieldInstance, lvsFNWindow );
                }
                catch (java.lang.ClassCastException e)
                {
                   System.out.print("Uuupps! There is a ClassCastException... ");
                }
                finally
                {
                    Log.ResCloseList(); // Close Child	
                }
              }
              else
              {
                Log.LogPrint( "-GUI-Container-" );              
                frameScanFieldsRecursively( lvField, lvFieldInstance, lvsFNWindow );
              }
          }
        }
        Log.ResCloseList();
      }
    }
    finally
    {
      Log.ResCloseList();
    }
  }

  
  private static void frameScanFieldsRecursively( Field fpParentField, Object fpParentFieldInstance, String fpsWindowName )
  {
    //n++;
    
    try
    {
      // Get all Fields within fpFieldToScan
      Field[] lvFields = fpParentField.getType().getFields();

      for ( Field lvField : lvFields )
      {

        String FieldType = lvField.getType().getName();
        
        // Die Instance des Feldes holen...
        Object lvFieldInstance = lvField.get( fpParentFieldInstance );
                
        if ( FieldType.startsWith( "okw.gui." ) )
        {
          if ( lvField.isAnnotationPresent( OKW.class ) )
          {
            OKW myFN = lvField.getAnnotation( OKW.class );
            // Get the value from property.

            String lvsFNChild =  myFN.FN();
            String lvsKey = fpsWindowName + "." + lvsFNChild;
            
            if ( myFrameObjectDictionary.containsKey( lvsKey ) )
            {
              // \todo TODO: Meldung in Sprachabhängige datei auslagern.
              throw new OKWFrameObjectDictionaryDuplicateFNException( "There is another Object with the same FN!" + lvsKey );
            }
            else
            { 

              Log.ResOpenList( "Child: '" + lvsFNChild + "'" );
              Log.LogPrint( "  Key: '" + lvsKey + "'" );
              Log.LogPrint( " Type: '" + FieldType + "'" );

              myAnnotationDictionary.put( lvsKey, myFN );
              
              (( IOKW_FN ) lvFieldInstance).setKN( lvsKey );
              (( IOKW_FN ) lvFieldInstance).setFN( lvsFNChild );
              (( IOKW_FN ) lvFieldInstance).setParentFN( fpsWindowName );
              
              myFrameObjectDictionary.put( lvsKey, lvFieldInstance );
        	  
              frameScanFieldsRecursively( lvField, lvFieldInstance, fpsWindowName );
        	  Log.ResCloseList();
            }
          }
          else
          {
        	  frameScanFieldsRecursively( lvField, lvFieldInstance, fpsWindowName );
          }
        }
      }
    }
    catch (Exception e)
    {
      Log.LogException( e.getMessage() );
    }
  }

  /** \~german
   * Holt die Referenz auf die Annotation des gegebenen Objektes.
   *
   * @param  fpObject
   * @return Refernz auf die OKW-Annotaion des Objectes
   */
  private static OKW getOKWFromObjekt( Object fpObject )
  {
    OKW lvsReturn = null;

    if ( fpObject.getClass().isAnnotationPresent( OKW.class ) )
    {
      lvsReturn = fpObject.getClass().getAnnotation( OKW.class );
    }

    return lvsReturn;
  }


  // / \~german
  // / \brief
  // / Erzeugt eine Instanz des OKW-Frame-Objekts.
  // /
  // / \return
  // / Die Instanz des OKW-Frame_Objekts.
  // /
  // / \param fps_ParentName Fachlicher Name des Eltern Fensters.
  // / \param fps_ChildName Fachlicher Name des Kind-Objektes im
  // Fenster-Objekt
  // / fps_ParentName.
  // /
  // / \~english
  // / \brief
  // / Generates an instance of the OKW-Frame-Object.
  // /
  // / \return
  // / The instance of OKW-Frame-Object.
  // /
  // / \param fps_ParentName name of parent window.
  // / \param fps_ChildName Name of child Object via fps_ParentObject given
  // / parent Object.
  // /
  // / \~
  // / \author Zoltan Hrabovszki
  // / \date 2014-10-10
  // / FIXME: Prüfen, ob diese Methoden namentlich ok sind. Wird das Eltern
  // / Obejkt angelegt?
  @SuppressWarnings( "unused" )
  private Object createInstanceByObjectName( String fps_ParentObject, String fps_ChildObject ) throws XPathExpressionException
  {
    Log.LogFunctionStartDebug( "FrameObjectDictionary.CreateInstanceByObjectName", "fps_ParentObject", fps_ParentObject, "fps_ChildObject", fps_ChildObject );

    Object myParentObject = null;
    Object myChildObject = null;
    Boolean bOK = false;

    try
    {
      // PerentObject holen und ggf. als instanz anlegen
      myParentObject = this.getParentObjectByName( fps_ParentObject );

      // Kindobjekt aufrufen...
      // aber nur wenn Paretobjekt gefunden worden ist.
      if ( myParentObject != null )
      {
        // FIXME: was ist wenn das myChildObject = null ist??Checken
        myChildObject = getChildObjectByName( myParentObject, fps_ChildObject );
      }
      else
      {
        // \todo TODO: Log Schreiben..
        throw new OKWFrameObjectParentNotFoundException( "Frame Objekt des Fensters" );
      }

      bOK = true;

    }
    finally
    {
      if ( bOK )
      {
        // Wir sind ohne Exception durchgekommen und wir nehmen an,
        // dass lvo_Obj != null
        // -> wir geben den Namen des Objektes zurück...
        Log.LogFunctionEndDebug( myChildObject.getClass().getName() );
      }
      else
      {
        // Irgend etwas ist faul wir rufen nur LogFunctionEndDebug
        // auf...
        Log.LogFunctionEndDebug();
      }
    }

    return myChildObject;
  }

  
  /** \~german
   *  Holt sich einen <b>öffentlichen</b> Feld Wert über den Namen
   * 
   *  @param fpo_ParentObject Frame des Fensters als Objekt.
   *  @param fps_ChildName Fachlicher Name des Kindobjektes, welches als
   *  Objekt zurückgegeben werden soll.
   *  @return Eigenschaftswert
   * 
   *  \~english
   *  Gets a <b>public</b> field value given its name
   * 
   *  @param fpo_ParentObject Object to inspect
   *  @param fps_ChildName Name of the field to retrieve the value from
   *  @return property value
   *  \~
   *  @author Zoltan Hrabovszki
   *  @date 2014-10-10
   * 
   * @throws XPathExpressionException
   */
  private Object getChildObjectByName( Object fpo_ParentObject, String fps_ChildName ) throws XPathExpressionException
  {
    Object lvo_Return = null;

    Log.LogFunctionStartDebug( "FrameObjectDictionary.GetChildObjectByName", "fpo_ParentObject", fpo_ParentObject.toString(), "fps_ChildName", fps_ChildName );

    try
    {
      Field fieldInfoObj = fpo_ParentObject.getClass().getField( fps_ChildName );

      // Existiert es das Kindobjekt >>{0:}<< im Objekt >>{1:}<<?
      Log.LogPrintDebug( LM.GetMessage( "GetChildObjectByName", "M1", fps_ChildName, fpo_ParentObject.getClass().getName() ) );

      // Ja, das Objekt existiert.
      Log.LogPrintDebug( LM.GetMessage( "GetChildObjectByName", "M2" ) );

      // Get the value from property.
      lvo_Return = fieldInfoObj;
    }
    catch (SecurityException e)
    {
      // \todo TODO: In Sprachabhängige datei Auslagern!
      Log.LogPrintDebug( "Frame Object must be declarec as Public!" );
      throw e;
    }
    catch (NoSuchFieldException e)
    {
      // Nein -> Exception auslösen und alles beenden!
      Log.LogPrintDebug( LM.GetMessage( "GetChildObjectByName", "M3" ) );

      throw new OKWFrameObjectChildNotFoundException( LM.GetMessage( "GetChildObjectByName", "ChildIsNotDefined_Exception", fps_ChildName, fpo_ParentObject
              .getClass().getName() ) );
    }
    finally
    {
      Log.LogFunctionEndDebug();
    }

    return lvo_Return;
  }

  
  public void printObjectDictionary()
  {

    Set<String> mySet = myFrameObjectDictionary.keySet();

    for ( String myKey : mySet )
    {
      System.out.println( "  Key: " + myKey );
      System.out.println( "Value: " + myFrameObjectDictionary.get( myKey ).toString() );
      System.out.println( " Type: " + myFrameObjectDictionary.get( myKey ).getClass().getName() );
      System.out.println( "---------------------------------------------------------------------\n" );
    }
  }


  /** \~german
   *  Holt für einen GUI-Object die angehängte Annotation
   * 
   *  @param fpKN Frame Objekt Schlüssel "FensterFN" oder "FensterFN.KindObjectFN".
   *  @return OKW-Annotation des Objektes.
   * 
   *  \~english
   * 
   *  @param fpKN Object to inspect
   *  @param fps_ChildName Name of the field to retrieve the value from
   *  @return property value
   *  \~
   *  @author Zoltan Hrabovszki
   *  @date 2017-02-11
   * 
   * @throws XPathExpressionException
   */
  public OKW getOKW( String fpKN )

  {  
      OKW Return = null;
      
      if (myAnnotationDictionary.containsKey(fpKN) )
      {
    	  Return = myAnnotationDictionary.get( fpKN );
      }
      
      return Return;
  }
}