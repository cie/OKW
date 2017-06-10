package okw.SeInputRadio;

import okw.log.Logger_Sngltn;
import okw.log.log2html.Log2HTML;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class SeInputRadio_HTMLUnitDriver_Test extends SeInputRadio_EN_Test
{
	static Log2HTML myLog2HTML = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		ApplicationName = "HTMLUnit";
		
       Logger_Sngltn.getInstance();
		// Reset des Loggers: Alle geladenen Instanzen löschen
       Logger_Sngltn.init();

       myLog2HTML = new Log2HTML("target/OKWResult_SeInputRadio_HTMLUnitDriver_Test.html");
       Logger_Sngltn.getInstance().addLogger(myLog2HTML);
        
       Logger_Sngltn.getInstance().setDebugMode(false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		//myLog2HTML.Result2HTML();
	}
}
