package okw.log.log2html;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okw.junit.JUnitBase;
import okw.log.log2html.Log2HTML;

public class LogError_Test extends JUnitBase
{

    String ClassName = this.getClass().getSimpleName();
    
	@Before
	public void setUp() throws Exception
	{
	    
	    okw.OKW_Properties.getInstance().setProperty( "Log2HTML.Test", "true" );
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
    public void tc_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogError( "LogError");
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    @Test
    public void tc_LogFunction_1_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStart( "FunctionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEnd();        

        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogFunction_2_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStart( "FuncTionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEnd( true );

        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogFunction_3_Logerror_Test()
    {   
        ArrayList<String> lsReturn = new ArrayList<String>();
        
        lsReturn.add( "Sting 1" );
        lsReturn.add( "Sting 2" );
        lsReturn.add( "Sting 3" );
        
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStart( "FuncTionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEnd( lsReturn );

        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogFunction_4_Logerror_Test()
    {   
        
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStart( "FuncTionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEnd( "Return String" );

        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    
    @Test
    public void tc_LogTestcaseStart_Logerror_Normal_Test()
    {   
        Log2HTML myLog = new Log2HTML( "Log2HTML - " + name.getMethodName() );
                        
        myLog.LogTestcaseStart( name.getMethodName() );
             myLog.LogError( "LogError");
        myLog.LogTestcaseEnd();

        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    
    @Test
    public void tc_LogFunctionDebug_1_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStartDebug( "FuncTionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEndDebug();        
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    @Test
    public void tc_LogFunctionDebug_2_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStartDebug( "FuncTionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEndDebug( true );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    @Test
    public void tc_LogFunctionDebug_3_Logerror_Test()
    {   
        ArrayList<String> lsReturn = new ArrayList<String>();
        
        lsReturn.add( "Sting 1" );
        lsReturn.add( "Sting 2" );
        lsReturn.add( "Sting 3" );
        
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStartDebug( "FuncTionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEndDebug( lsReturn );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogFunctionDebug_4_Logerror_Test()
    {   
        
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogFunctionStartDebug( "FuncTionStart", "Param_1", "Value 1" );
             myLog.LogError( "LogError");
        myLog.LogFunctionEndDebug( "Return String" );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogLocalACCallStart_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogLocalACCallStart( "my source excerpt", "my Local AC Type" );;
             myLog.LogError( "LogError");
        myLog.LogLocalACCallEnd( );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogRemoteACCallStart_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogRemoteACCallStart( "sourceExcerpt", "my remote AC type" );
             myLog.LogError( "LogError");
        myLog.LogRemoteACCallEnd( );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogSequence_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.LogSequenceStart( "Sequence", "Window", "SequenzName", "SeqID" );
             myLog.LogError( "LogError");
        myLog.LogSequenceEnd( );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogStep_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
        
        myLog.LogTestcaseStart( "TestCase = " + name.getMethodName() );
        
        myLog.LogStepStart( "mycategoryName", "mycategoryType", "mychoiceValue", "myfeatureName", "mylocalCategoryName", "mysourceExcerpt", "TestStep" );
             myLog.LogError( "LogError");
        myLog.LogStepEnd( );
    
        myLog.LogTestcaseEnd();
        
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    
    @Test
    public void tc_LogKeyWordStart_Logerror_Normal_Test()
    {	
    	Log2HTML myLog = new Log2HTML( name.getMethodName() );
    					
    	myLog.LogKeyWordStart( "Gib ein", "Name", "Zoltan" );
    		myLog.LogError( "LogError");
    		myLog.LogKeyWordEnd();
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }

    @Test
    public void tc_ResultList_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.ResOpenList( "fps_ListHeader" );
             myLog.LogError( "LogError");
        myLog.ResCloseList( );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }
    

    @Test
    public void tc_ResultListDebug_Logerror_Test()
    {   
        Log2HTML myLog = new Log2HTML( name.getMethodName() );
                        
        myLog.ResOpenListDebug( "fps_ListHeader" );
             myLog.LogError( "LogError");
        myLog.ResCloseListDebug( );
    
        String Current = myLog.Result2JSON( "target/" + name.getMethodName() + ".json" );
        
        String Expected = this.loadUTF8FileFromResource( ClassName + "/" + name.getMethodName() + ".json" );
             
        assertEquals( Expected, Current );      
    }
}