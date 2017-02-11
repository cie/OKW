package okw.gui.adapter.selenium;

import java.util.*;

import javax.xml.xpath.XPathExpressionException;

import okw.exceptions.OKWFrameObjectMethodNotImplemented;
import okw.exceptions.OKWGUIObjectNotFoundException;
import okw.gui.*;
import okw.log.Logger_Sngltn;
import okw.LogMessenger;
import okw.OKW_Const_Sngltn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class SeAnyChildWindow extends AnyChildwindow
{	
    // Logger Instance holen
    protected Logger_Sngltn MyLogger = Logger_Sngltn.getInstance();
    
    protected LogMessenger LM = new LogMessenger("GUI");

    
    /**  \~german
    *  
    *  @param fpsLocator definiert die Objekterkennungseigenschaft des Objektes. Dieser wird als XPATH angegeben
    *  @param fpParams Locatoren z.B. von Elternobjekten, die zu einem gesamt Locator verkettet werden sollen.
    *  
    *  \~english
    *  
    *  \~
    *  @author Zoltán Hrabovszki
    *  @date 2013.05.03
    */
    public SeAnyChildWindow(String Locator, OKWLocator... fpLocators)
    {
    	super(Locator, fpLocators);
    }


    /** \~german
     *  Klickt auf das aktuelle Objekt.
     *  
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.11.11
     */
    public void ClickOn()
    {
        try
        {
            this.LogFunctionStartDebug("ClickOn");

            // Wenn das Objekt nicht existiert mit Exception beenden...
            if (!this.getExists())
            {
                String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "SeAnyWin.ClickOn()");
                throw new OKWGUIObjectNotFoundException(lvsLM);
            }

            this.Me().click();
        }
        finally
        {
            this.LogFunctionEndDebug();
        }

        return;
    }

    
   /** \~german
	  *  Ermittelt den textuellen Inhalt der Überschrift eines HTML-Tags anhand des Attributee "textContent".
	  *   
	  *  @return Rückgabe des Textuellen Inhaltes der Caption/Überschrift.
	  *  \~english
	  *  \~
	  *  @author Zoltán Hrabovszki
	  *  @date 2013.12.07
	  */
	public ArrayList<String> getCaption()
	{
	    ArrayList<String> lvLsReturn = new ArrayList<String>();

	    try
	    {
	        this.LogFunctionStartDebug("GetCaption");
	
	        // Wenn das Objekt nicht existiert mit Exception beenden...
	        if (!this.getExists())
	        {
	            String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "GetCaption()");
	            throw new OKWGUIObjectNotFoundException(lvsLM);
	        }
	        
	        // The Attribute "value" wird als Beschriftung angezeigt...
	        lvLsReturn.add(this.Me().getAttribute("textContent"));
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvLsReturn);
	    }
	    return lvLsReturn;
	}


	  /** \~german
     *  Prüft die Existenz des aktuellen Objektes.
     *  
     *  - Methode ist Selenium spezifisch.
     *  - Elementare Funktion, muss hier defiert werden.
     *  - Es wird nicht auf das Objekt gewarten!
     *  - Es wird nur ein eizigesmal die Existenz geprüft.
     *  - Es wird keine Ausnahme ausgelöst, wenn das Object nicht vorhanden ist.
     *  - Wenn Das Objekt nicht eindeitig ist wird false Zurückgeliefert.
     *  
     *  @return true, falls das Objekt eindeutig gefunden worden ist ist, sonst false.
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2017.01.31
     */
    public Boolean getExists()
    {
        Boolean lvbReturn = false;
        List<WebElement> meme = null;

        try
        {
          this.LogFunctionStartDebug("getExists");

           String myLocator = this.getLocator();
        
           meme = SeDriver.getInstance().driver.findElements(By.xpath(myLocator));
        
           if (meme.size() == 0)
           {
              lvbReturn = false;
           }
           else if (meme.size() > 1)
           {
              String lvsPrintMe = "Locator ist nicht eindeutig, es wurden mehrer GUI-Objekt gefunden:\n Locator: >>" + this.getLocator() + "<<";
              this.MyLogger.LogWarning( lvsPrintMe );

              lvbReturn = false;
           }
           else
           {
              lvbReturn = true;
           }
        }
        catch (OKWGUIObjectNotFoundException e)
        {
           this.MyLogger.LogPrintDebug("NoSuchElementException");
           lvbReturn = false;
        }
        finally
        {
           this.LogFunctionEndDebug(lvbReturn);
        }
        return lvbReturn;
    }

    
  /** \~german
	 *  Methode liefert den aktuellen Zustand Wert des Focus.
	 *  
	 *  Quelle: http://stackoverflow.com/questions/7491806/in-selenium-how-do-i-find-the-current-object
	 * 
	 *  @return true, if has focus else false
	 *  \~
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.11.11
	 */
	public Boolean getHasFocus()
	{
	    Boolean lvbReturn = false;
	
	    try
	    {
	        this.LogFunctionStartDebug("getHasFocus");
	
	        // Wenn das Objekt nicht existiert mit Exception beenden...
	        if (!this.getExists())
	        {
	            String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "getHasFocus()");
	            throw new OKWGUIObjectNotFoundException(lvsLM);
	        }
	
	        WebElement currentElement = SeDriver.getInstance().driver.switchTo().activeElement();
	
	        lvbReturn = currentElement.equals(this.Me());
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvbReturn);
	    }
	
	    return lvbReturn;
	}


	  /** \~german
     *  Ermittelt, ob das aktuellen Objekt aktiv ist.
     *  
     *  @return true, falls das Objekt aktive ist, sonst false
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2014.04.19
     */
    public Boolean getIsActive()
    {
        Boolean lvbReturn = false;
        String lvDisabled = null;

        try
        {
            this.MyLogger.LogFunctionStartDebug("getIsActive");

            // Wenn das Objekt nicht existiert mit Exception beenden...
            if (!this.getExists())
            {
                String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "SeAnyWin.GetIsActive()");
                throw new OKWGUIObjectNotFoundException(lvsLM);
            }

            lvDisabled = this.Me().getAttribute("disabled");

            if (lvDisabled != null)
            {
            	if (lvDisabled.equals("true")) lvbReturn = false;
            }
            else
            {
                lvbReturn = true;
            }

        }
        finally
        {
            this.LogFunctionEndDebug(lvbReturn);
        }

        return lvbReturn;
    }

   /** \~german
	  *  Ermittelt den textuellen Inhalt des Labels.
	  *  
    *  Es wird das Attribute "textContent" des mit "id" an das aktuelle Objekt angebunde "Laben" gelesen.
    *  
    *  @return Rückgabe des Label-Textes.
    *  \~english
    *  \~
    *  @author Zoltán Hrabovszki
    *  @date 2014.06.17
    */
	public ArrayList<String> getLabel()
	{
	    ArrayList<String> lvLsReturn = new ArrayList<String>();

	    try
	    {
	        MyLogger.LogFunctionStartDebug("GetLabel");
	
	        // Wenn das Objekt nicht existiert mit Exception beenden...
	        if (!this.getExists())
	        {
	            String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "GetLabel()");
	            throw new OKWGUIObjectNotFoundException(lvsLM);
	        }
	        
	        // 1. Schritt: Attribute "id" is shown as Tooltip...
	        String lvsID = this.Me().getAttribute("id");
	        
	        // 2.schritt nun Tag Label mit for= "${lvsID}" finden.
	        WebElement label = SeDriver.getInstance().driver.findElement(By.xpath( "//label[@for='" + lvsID + "']" ));
	        lvLsReturn.add(label.getAttribute("textContent"));
	    }
	    finally
	    {
	         this.LogFunctionEndDebug(lvLsReturn);
	    }
	    
	    return lvLsReturn;
	}


	 /**  \~german
	 *  \brief
	 *  Ermittelt den textuellen Inhalt des ToolTips.
	 *  
   *  Es wird das Attribute "title" ausgelesen.
	 *  
	 *  @return
	 *  Rückgabe des Textuellen Inhaltes der Tooltips.
	 *  @return
	 *  \~english
	 *  \~
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.12.07
	 */
	public ArrayList<String> getTooltip()
	{
	    ArrayList<String> lvLsReturn = new ArrayList<String>();

	    try
	    {
	        this.LogFunctionStartDebug("GetTooltip");
	
	        // Wenn das Objekt nicht existiert mit Exception beenden...
	        if (!this.getExists())
	        {
	            String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "GetTooltip()");
	            throw new OKWGUIObjectNotFoundException(lvsLM);
	        }
	        
	        // The Attribute "Title" is shown as Tooltip...
	        lvLsReturn.add(this.Me().getAttribute("title"));
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvLsReturn);
	    }
	    
	    return lvLsReturn;
	}


	/**
	 *  Liest den aktuellen Wert/Text aus dem HTML-Tag.
	 * 
	 *  Es wird das Attribute "textContent" ausgelesen.
	 *
	 *  @return
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.12.14
	 */
	public ArrayList<String> getValue()
	{
	    ArrayList<String> lvLsReturn = new ArrayList<String>();
	
	    try
	    {
	        this.LogFunctionStartDebug("getValue");
	
          // Wenn das Objekt nicht existiert mit Exception beenden...
          if (!this.getExists())
          {
              String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "GetTooltip()");
              throw new OKWGUIObjectNotFoundException(lvsLM);
          }

          // Get Value from TextField and put this into the return ArrayList<String>
	        lvLsReturn.add(this.Me().getAttribute("textContent"));
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvLsReturn);
	    }
	
	    return lvLsReturn;
	}


	/** \~german
   *  Ermittelt den textuellen Inhalt der Beschriftung (Caption).
   *  
   *  Diese Methode ist der Einstiegspunkt für LogCaption-Anpassungen durch Methoden überschreibung.
   *  
   *  @return Rückgabe des Tooltiptextes.
   *  Interface schreibt ein Listen-Element als Rückgabewert vor.
   *  \~english
   *  \~
   *  @author Zoltán Hrabovszki
   *  @date 2013.12.07
   */
  public ArrayList<String> LogCaption()
  {
      ArrayList<String> lvLsReturn = null;
  
      try
      {
          this.LogFunctionStartDebug("LogCaption");
  
          lvLsReturn = this.getCaption();
      }
      finally
      {
          this.LogFunctionEndDebug(lvLsReturn);
      }
      return lvLsReturn;
  }


     /**
      * \~german
     *  Ermittelt den textuellen Inhalt des ToolTips.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methoden überschreibung.
     *  
     *  @return Rückgabe des Textuellen Inhaltes der Tooltips.
     *  Interface schreibt ein Listen-Element als Rückgabewert vor.
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public Boolean LogExists()
    {
        Boolean lvbReturn = null;

        try
        {
            this.LogFunctionStartDebug("LogExists");
            lvbReturn = this.getExists();
        }
        finally
        {
             this.LogFunctionEndDebug(lvbReturn);
        }

        return lvbReturn;
    }

     /**
      *  Ermittelt für LoggeWert, ob das aktuelle Objekt den Fokus hat.
      *  
      *  Diese Methode ist der Einstiegspunkt für Loggewert-Anpassungen durch Methodenüberschreibung.
      *  
      *  @return true falls Objekt den Fokus hat, sonst false
      *  
      *  @author Zoltan Hrabovszki
      *  @date 2013.04.11
      */
    public Boolean LogHasFocus()
    {
        Boolean lvbReturn = null;
    
        try
        {
            this.LogFunctionStartDebug("LogHasFocus");
            lvbReturn = this.getHasFocus();
        }
        finally
        {
            this.LogFunctionEndDebug(lvbReturn);
        }
    
        return lvbReturn;
    }


    /**  \~german
     *  Ermittelt, ob das aktuelle Objket Aktiv ist.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methoden überschreibung.
     *  
     *  @return Rückgabe des Textuellen Inhaltes der Tooltips.
     *  Interface schreibt ein Listen-Element als Rückgabewert vor.
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public Boolean LogIsActive()
    {
        Boolean lvbReturn = null;

        try
        {
            this.LogFunctionStartDebug("LogIsActive");
            lvbReturn = this.getIsActive();
        }
        finally
        {
            this.LogFunctionEndDebug(lvbReturn);
        }

        return lvbReturn;
    }

     /**  \~german
     *  Logt den textuellen Inhalt des Labels eines Objektes.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methoden überschreibung.
     *  
     *  @return
     *  Rückgabe des Textuellen Inhaltes der Tooltips.
     *  Interface schreibt ein Listen-Element als Rückgabewert vor.
     *  @return
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public ArrayList<String> LogLabel()
    {
        ArrayList<String> lvLsReturn = null;
    
        try
        {
            this.LogFunctionStartDebug("LogLabel");
            lvLsReturn = this.getLabel();
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }

        return lvLsReturn;
    }


     /**  \~german
     *  \brief
     *  Logt den textuellen Inhalt des ToolTips.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methoden überschreibung.
     *  
     *  @return
     *  Rückgabe des Textuellen Inhaltes der Tooltips.
     *  Interface schreibt ein Listen-Element als Rückgabewert vor.
     *  @return
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public ArrayList<String> LogTooltip()
    {
        ArrayList<String> lvLsReturn = null;
    
        try
        {
            this.LogFunctionStartDebug("LogTooltip");
    
            lvLsReturn = this.getTooltip();
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }
    
        return lvLsReturn;
    }


    /**
      *  Ermittelt den Wert des Objktes für das Schlüsselwort Loggewert.
      *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen
      *  durch Methoden überschreibung.
      *  
      *  @return Rückgabe des Textuellen Inhaltes der markierten Textes.
      *  @author Zoltan Hrabovszki
      *  @date 2013.12.14
      *  @throws Exception 
      */
    public ArrayList<String> LogValue() throws Exception
    {
        ArrayList<String> lvLsReturn = new ArrayList<String>();
    
        try
        {
            this.LogFunctionStartDebug("LogValue");
            lvLsReturn = this.getValue();
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }
    
        return lvLsReturn;
    }


    /** \~german
     *  Ermittelt aus dem gegebenen Locator das DOM-Elelement, welches das Objekt representiert.
     *  
     *  @return Refernz auf das gefunde DOM-Element
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.11.11
     */
    public WebElement Me()
    {
        this.MyLogger.LogFunctionStartDebug("Me");
        WebElement me = null;

        List<WebElement> meme = null;
        // OKWIni.Instance.TimeOutExists;

        try
        {
          String myLocator = this.getLocator();
          
            meme = SeDriver.getInstance().driver.findElements(By.xpath(myLocator));
            
            if (meme.size() == 0)
            {
                String lvsPrintMe = "GUI-Objekt wurde nicht gefunden: Locator: >>" + myLocator + "<<";
                
                this.MyLogger.LogPrint( "????????????????????????????????????????????????????????????" );
                this.MyLogger.LogPrint( lvsPrintMe );
                this.MyLogger.LogPrint( "????????????????????????????????????????????????????????????" );

                throw new OKWGUIObjectNotFoundException( lvsPrintMe );
            }
            else if (meme.size() > 1)
            {
                String lvsPrintMe = "Locator ist nicht eindeutig, es wurden mehrer GUI-Objekt gefunden:\n Locator: >>" + this.getLocator() + "<<";
                
                this.MyLogger.LogPrint( "????????????????????????????????????????????????????????????" );
                this.MyLogger.LogPrint( lvsPrintMe );
                this.MyLogger.LogPrint( "????????????????????????????????????????????????????????????" );

                throw new OKWGUIObjectNotFoundException( lvsPrintMe );
            }
            else
            {
                me = meme.get(0);
            }
        }
        /*
         catch( Exception e )
        {
          System.out.println( e.getMessage() );
        }
        */
        finally
        {
            if (me != null)
            {
                this.MyLogger.LogFunctionEndDebug(me.toString());
            }
            else
            {
                this.MyLogger.LogFunctionEndDebug();
            }
        }

        return me;
    }
    
    
    
    /** \~german
     *  Ermittelt ob das Atuelle Objekt existiert, für das Schlüsselwort MerkeVorhanden.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeExist-Anpassungen durch Methodenüberschreibung.
     *  
     *  @return Rückgabe des Textuellen Inhaltes der Tooltips.
     *  Interface schreibt ein Listen-Element als Rückgabewert vor.
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public Boolean MemorizeExists()
    {
        Boolean lvbReturn = false;

        try
        {
            this.MyLogger.LogFunctionStartDebug("MemorizeExists");

            lvbReturn = this.getExists();
        }
        finally
        {
            this.LogFunctionEndDebug(lvbReturn);
        }

        return lvbReturn;
    }

    
     /**  \~german
     *  Ermittelt für das Schlüsselwort MerkeIstAktive, ob das aktuelle Objekt aktiv ist.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeIstAktive-Anpassungen durch Methodenüberschreibung.
     *  
     *  @return true, falls das aktuelle Objekt aktiv ist, sonst false *
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2014.04.19
     */
    public Boolean MemorizeIsActive()
    {
        Boolean bOK = false;
        Boolean lvbReturn = false;

        try
        {
            this.MyLogger.LogFunctionStartDebug("MemorizeIsActive");

            lvbReturn = this.getIsActive();
            bOK = true;
        }
        finally
        {
            if (bOK)
            {
                this.MyLogger.LogFunctionEndDebug(lvbReturn);
            }
            else
            {
                this.MyLogger.LogFunctionEndDebug();
            }
        }

        return lvbReturn;
    }

    
    /**  \~german
      *  \brief
      *  Ermittelt den textuellen Inhalt des ToolTips für das Schlüsselwort MerkeWert.
      *  
      *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methodenüberschreibung.
      *  
      *  @return
      *  Rückgabe des Textuellen Inhaltes der Tooltips.
      *  Interface schreibt ein Listen-Element als Rückgabewert vor.
      *  @return
      *  \~english
      *  \~
      *  @author Zoltán Hrabovszki
      *  @date 2013.12.07
      */
    public ArrayList<String> MemorizeTooltip()
    {
        ArrayList<String> lvLsReturn = null;

        try
        {
            this.LogFunctionStartDebug("MemorizeTooltip");

            lvLsReturn = this.getTooltip();
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }

        return lvLsReturn;
    }

     /**  \~german
     *  \brief
     *  Ermittelt den textuellen Inhalt des Labels für das Schlüsselwort MerkeWert.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methodenüberschreibung.
     *  
     *  @return
     *  Rückgabe des Textuellen Inhaltes der Tooltips.
     *  Interface schreibt ein Listen-Element als Rückgabewert vor.
     *  @return
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public ArrayList<String> MemorizeLabel()
    {
        ArrayList<String> lvLsReturn = null;

        try
        {
            this.LogFunctionStartDebug("MemorizeLabel");

            lvLsReturn = this.getLabel();
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }

        return lvLsReturn;
    }

    /**  \~german
     *  Ermittelt ob das Objekt den Fokus besitz und liefert diesen für das Schlüsselwort MerkeWert.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methodenüberschreibung.
     *  
     *  @return true falls Objekt den Fokus hat, sonst false
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public Boolean MemorizeHasFocus()
    {
        Boolean lvbReturn = null;

        try
        {
            this.LogFunctionStartDebug("MemorizeHasFocus");

            lvbReturn = this.getHasFocus();
        }
        finally
        {
            this.LogFunctionEndDebug(lvbReturn);
        }

        return lvbReturn;
    }

    /** \~german
     *  Ermittelt den textuellen Inhalt des Caption/Überschrift für das Schlüsselwort MerkeWert.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methodenüberschreibung.
     *  
     *  @return Rückgabe des Textuellen Inhaltes der Caption/Überschrift.
     *  Interface schreibt ein Listen-Element als Rückgabewert vor.
     *  
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.07
     */
    public ArrayList<String> MemorizeCaption()
    {
        ArrayList<String> lvLsReturn = null;

        try
        {
            MyLogger.LogFunctionStartDebug("MemorizeCaption");
            lvLsReturn = this.getCaption();
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }

        return lvLsReturn;
    }


     /**  \~german
     *  \brief
     *  Ermittelt den Wert, im Allgemeinen den textuellen Inhalt eines Objektes
     *  für das Schlüsselwort MerkeWert.
     *  
     *  Diese Methode ist der Einstiegspunkt für MerkeWert-Anpassungen durch Methodenüberschreibung.
     *  
     *  @return
     *  Rückgabe des Textuellen Inhaltes des aktuellen Objektes.
     *  \~english
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.12.14
     */
    public ArrayList<String> MemorizeValue() throws Exception
    {
        ArrayList<String> lvLsReturn = new ArrayList<String>();

        try
        {
            MyLogger.LogFunctionStartDebug("MemorizeValue");
            lvLsReturn = this.getValue();
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }

        return lvLsReturn;
    }

    
   /**  \~german
     *  Prüft die nicht Existenz des aktuellen Objektes.
     *  
     *  __Anmerkung:__ Falls ein Objekt zunächst noch vorhand ist und dann aber verschwindet,
     *  dann es sinnvoll sein eine gewisse Zeit(Timeout) zu prüfen, ob das Objekt existiert,
     *  Erst wenn diese Zeit verstrichen ist, wird gemeldet, das das Objekt nicht vorhanden ist.
     *  Diese Funktion ist der gegenspieler zu __Exists.
     *  
     *  @return true, falls das Objekt NICHT vorhanden ist. Sonst false
     *  \~
     *  @author Zoltán Hrabovszki
     *  @date 2013.11.11
     */
    public Boolean NotExists()
    {
        this.LogFunctionStartDebug("NotExists");
        Boolean lvb_Return = null;
    
        try
        {
            this.Me();
            lvb_Return = false;
        }
        catch (NoSuchElementException e)
        {
            this.MyLogger.LogPrint("NoSuchElementException");
            lvb_Return = true;
        }
        finally
        {
            this.LogFunctionEndDebug(lvb_Return);
        }
    
        return lvb_Return;
    }


  /** \~
   *  @author Zoltán Hrabovszki
   *  @date 2016.10.06
   */
  public void Select(ArrayList<String> Values)  throws Exception
  {
  
    try
    {
      this.LogFunctionStartDebug( "Select" );
  
      String lvsLM = this.LM.GetMessage( "Common", "OKWFrameObjectMethodNotImplemented", "Select( ArrayList<String> )" );
      throw new OKWFrameObjectMethodNotImplemented( lvsLM );
    }
    finally
    {
      this.LogFunctionEndDebug();
    }
  }


  /** \~german
   *  Muss in den Menü-Objekten Implementiert werden!
   * 
   *  Daher wird hier ein OKWFrameObjectMethodNotImplemented ausgelöst!
   * 
   *  \~english
   *  A SeInputButton has no value! -> Trigger
   *  OKWFrameObjectMethodNotImplemented!
   *  \~
   *  @author Zoltán Hrabovszki
   *  @date 2016.10.06
   */
  public void SelectMenu()
  {
    // ArrayList<String> lvLsReturn = new ArrayList<String>();
    try
    {
      this.LogFunctionStartDebug( "SelectMenu" );
  
      String lvsLM = this.LM.GetMessage( "Common", "OKWFrameObjectMethodNotImplemented", "SelectMenu()" );
      throw new OKWFrameObjectMethodNotImplemented( lvsLM );
    }
    finally
    {
      this.LogFunctionEndDebug();
    }
  }


  /** \~german
   *  Muss in den Menü-Objekten Implementiert werden!
   *  Daher wird hier ein OKWFrameObjectMethodNotImplemented ausgelöst!
   *  \~english
   *  A SeInputButton has no value! -> Trigger
   *  OKWFrameObjectMethodNotImplemented!
   *  \~
   *  @author Zoltán Hrabovszki
   *  @date 2016.10.06
   */
  public void SelectMenu_Value(ArrayList<String> Values)
  {
  
    try
    {
      this.LogFunctionStartDebug( "SelectMenu_Value" );
  
      String lvsLM = this.LM.GetMessage( "Common", "OKWFrameObjectMethodNotImplemented", "SelectMenu_Value()" );
      throw new OKWFrameObjectMethodNotImplemented( lvsLM );
    }
    finally
    {
      this.LogFunctionEndDebug();
    }
  }


  /**  \~german
   *  Setzt den Focus auf das Objekt.
   *  
   *  __Anmerkung:__ Kleiner Trick: Selenium kennt keine öffentliche c#-Methode
   *  für das setzen des Fokus. So weit ich es verstanden habe, wird intern jedoch
   *  methode SetFocus verwendt, wenn <tt>SendKeys</tt> aufgerufen wird.
   *  Quelle: http://stackoverflow.com/questions/7491806/in-selenium-how-do-i-find-the-current-object
   *  
   *  \~english
   *  \~
   *  @author Zoltán Hrabovszki
   *  @date 2013.11.11
   */
  public void SetFocus()
  {
      this.LogFunctionStartDebug("SetFocus");
  
      try
      {
          // Wenn das Objekt nicht existiert mit Exception beenden...
          if (!this.getExists())
          {
              String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "SetFocus()");
              throw new OKWGUIObjectNotFoundException(lvsLM);
          }
          
          this.Me().sendKeys("");
      }
      finally
      {
          this.LogFunctionEndDebug();
      }
      
      return;
  }


  /** \~german
   *  \brief
   *  @return
   *  \~english
   *  \brief
   *  \~
   *  @author Zoltán Hrabovszki
   *  @date 2016.10.06
   */
  public void SetValue( ArrayList<String> Values ) throws XPathExpressionException, Exception
  {
  
    try
    {
      this.LogFunctionStartDebug( "SetValue" );
  
      String lvsLM = this.LM.GetMessage( "Common", "OKWGUIObjectNotFoundException", "SetValue()" );
      throw new OKWFrameObjectMethodNotImplemented( lvsLM );
    }
    finally
    {
      this.LogFunctionEndDebug();
    }
  }


  /**  \~german
	 *  \brief
	 *  Tastatur Eingabe an das Objekt
	 *  
	 *  @param fps_Values'>
	 *  Werte, die via Tastatur eingegeben werden sollen.
	 *  
	 *  \~
	 *  @author Zoltan Hrabovszki
	 *  @date 2013.04.11
	 */
	public void TypeKey(ArrayList<String> fps_Values)
	{
	    try
	    {
	        this.LogFunctionStartDebug("TypeKey", "fps_Values", fps_Values.toString());
	
	        // Wenn das Objekt nicht existiert mit Exception beenden...
	        if (!this.getExists())
	        {
	            String lvsLM = this.LM.GetMessage("Common", "OKWGUIObjectNotFoundException", "SeAnyWin.TypeKey()");
	            throw new OKWGUIObjectNotFoundException(lvsLM);
	        }
	
	        // We are using a local-Variable to prevent multiple call of Me() in foreach-loop
	        WebElement lv_WebElement = this.Me();
	
	        // Loop through all List-Values with foreach...
	        for (String Value : fps_Values)
	        {
	          Logger_Sngltn.getInstance().LogPrintDebug(">>" + Value + "<<");
	          
	          if (Value.equals( OKW_Const_Sngltn.getInstance().GetOKWConst4Internalname( "DELETE" ) ))
	          {
	            lv_WebElement.clear();
	          }
	          else
	          {
	            lv_WebElement.sendKeys( Value );
	          }
	        }
	    }
	    finally
	    {
	        this.LogFunctionEndDebug();
	    }
	    
	    return;
	}


	 /**
	  * \~german
	  *  Ermittelt den textuellen Inhalt des ToolTips für Prüfewert.
	  *  
	  *  Diese Methode ist der Einstiegspunkt für PrüfeWert-Anpassungen durch Methodenüberschreibung.
	  *  
	  *  @return	Rückgabe des Textuellen Inhaltes der Tooltips. Es wird immer der aktuelle Wert des Objektes zurückgeliefert
	  *  Interface schreibt ein Listen-Element als Rückgabewert vor.
	  *  \~english
	  *  \~
	  *  @author Zoltán Hrabovszki
	  *  @date 2013.12.07
	  */
	public ArrayList<String> VerifyCaption() throws InterruptedException
	{
	    this.LogFunctionStartDebug("VerifyCaption");
	
	    ArrayList<String> lvLsReturn = new ArrayList<String>();
	
	    try
	    {
	        // Nun mit dem erwarteten Sollwert und GetValue_TOOLTIP ggf. auf den Wert Warten.
	        lvLsReturn = this.getCaption( );
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvLsReturn);
	    }
	
	    return lvLsReturn;
	}


	 /**  \~german
	 *  Ermittelt/Prüft, ob das aktuelle Objekt existiert.
	 *  
	 *  Diese Methode ist der Einstiegspunkt für PrüfeWert-Anpassungen durch Methodenüberschreibung.
	 *  	 *  
	 *  @return Rückgabe des Textuellen Inhaltes der Tooltips. Es wird immer der aktuelle Wert des Objektes zurückgeliefert
	 *  Interface schreibt ein Listen-Element als Rückgabewert vor.
	 *  
	 *  \~english
	 *  \~
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.12.07
	 */
	public Boolean VerifyExists() throws InterruptedException
	{
	    this.LogFunctionStartDebug("VerifyExists");
	
	    Boolean lvbReturn = null;
	
	    try
	    {
	        lvbReturn = this.getExists( );
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvbReturn);
	    }
	
	    return lvbReturn;
	}


	 /**
	  * \~german
	 *  Ermittelt/Prüft, ob das aktuelle Objekt aktiv ist.
	 *  
	 *  Diese Methode ist der Einstiegspunkt für PrüfeIstAktive-Anpassungen durch Methodenüberschreibung.
	 *  
	 *  @return true, falls das Objekt aktiv ist sonst false
	 *  \~english
	 *  \~
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.12.07
	 */
	public Boolean VerifyIsActive( ) throws InterruptedException
	{
	    this.LogFunctionStartDebug("VerifyIsActive");
	
	    Boolean lvbReturn = null;
	
	    try
	    {	
	        // Wert GetIsActive ggf. auf den erwarteten Wert warten.
	        lvbReturn = this.getIsActive( );
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvbReturn);
	    }
	
	    return lvbReturn;
	}


	/** \~german
	 *  Ermittelt ob das GUI-Objekt den Fokus hat.
	 *  
	 *  Dies ist der Einstiegspunkt für die Anpassung der Methote VerifyHasFocus (Methodenüberschreibung).
	 *  
	 *  @return true falls das Objekt den Fokus hat, sonst false.
	 *  \~english
	 *  Determines whether the GUI object has the focus.
	 * 
	 *  This is the entry point for the adaptation of the method Verify Has Focus (method override).
	 *  \~
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.12.07
	 */
	public Boolean VerifyHasFocus() throws InterruptedException
	{
	    Boolean lvbReturn = false;
	    
	    try
	    {
	        this.LogFunctionStartDebug("VerifyHasFocus");
	
	        // Nun mit dem erwarteten Sollwert und GetHasFocus ggf. auf den Wert Warten.
	        lvbReturn = this.getHasFocus( );
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvbReturn);
	    }
	    return lvbReturn;
	}


	 /**  
	  * \~german
	 *  Ermittelt den textuellen Inhalt des Labels für Prüfewert.
	 *  
	 *  @remark Sollte der Erwartetet Wert zunächt nicht mit dem aktuellen Wert nicht übereinstimmen,
	 *  dann wird gewartet bis entweder der erwartete Wert sich im GUI objekt einstellt
	 *  oder der TimeOut erreicht wird.<br/>
	 *  Diese Methode ist der Einstiegspunkt für PrüfeWert-Anpassungen durch Methodenüberschreibung.
	 *  
	 *  @param fplsExpectedValue'>Erwarteter Wert, auf den ggf. bis zum TimeOut gewartet wird.
	 *  @return Rückgabe des Textuellen Inhaltes des Labels.
	 *  Interface schreibt ein Listen-Element als Rückgabewert vor.
	 *  \~english
	 *  \~
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.12.07
	 */
	public ArrayList<String> VerifyLabel() throws InterruptedException
	{
	    this.LogFunctionStartDebug("VerifyLabel");
	
	    ArrayList<String> lvLsReturn = new ArrayList<String>();
	
	    try
	    {
	        // Nun mit dem erwarteten Sollwert und GetValue_TOOLTIP ggf. auf den Wert Warten.
	        lvLsReturn = this.getLabel( );
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvLsReturn);
	    }
	
	    return lvLsReturn;
	}


  /** \~german
	 *  Ermittelt den textuellen Inhalt des ToolTips für Prüfewert.
	 *  
	 *  Diese Methode ist der Einstiegspunkt für PrüfeWert-Anpassungen durch Methodenüberschreibung.
	 *  
	 *  @return Rückgabe des Textuellen Inhaltes der Tooltips. Es wird immer der aktuelle Wert des Objektes zurückgeliefert
	 *  Interface schreibt ein Listen-Element als Rückgabewert vor.
	 *  \~english
	 *  \~
	 *  @author Zoltán Hrabovszki
	 *  @date 2013.12.07
   */
	public ArrayList<String> VerifyTooltip() throws InterruptedException
	{
	    MyLogger.LogFunctionStartDebug("VerifyTooltip");
	
	    ArrayList<String> lvLsReturn = new ArrayList<String>();
	
	    try
	    {
	        // Nun mit dem erwarteten Sollwert und GetValue_TOOLTIP ggf. auf den Wert Warten.
	        lvLsReturn = this.getTooltip( );
	    }
	    finally
	    {
	        this.LogFunctionEndDebug(lvLsReturn);
	    }
	
	    return lvLsReturn;
	}


	 /**  \brief
     *  Ermittelt den textuellen Inhalt des markierten Textes für Prüfewert.
     *  
     *  Diese Methode ist der Einstiegspunkt für PrüfeWert-Anpassungen durch Methodenüberschreibung.
     *  
     *  @return Rückgabe des aktuellen Objekt-Wert.
     *  @author Zoltan Hrabovszki
     *  @date 2013.12.14
     */
    public ArrayList<String> VerifyValue( ) throws Exception
    {
        ArrayList<String> lvLsReturn = null;

        try
        {
            this.LogFunctionStartDebug("VerifyValue");

            // get the Actual Value.
            lvLsReturn = this.getValue(  );
        }
        finally
        {
            this.LogFunctionEndDebug(lvLsReturn);
        }

        return lvLsReturn;
    }
}