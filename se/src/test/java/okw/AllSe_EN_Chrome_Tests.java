package okw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith( Suite.class )
@SuiteClasses(
{ 
    okw.seback.SeBACK_EN_Chrome_Test.class,
    okw.sebrowserchild.SeBrowserChild_EN_Chrome_Test.class,
    okw.sebutton.SeButton_EN_Chrome_Test.class,
    okw.seinputbutton.SeInputButton_EN_Chrome_Test.class,
    okw.seinputcheckbox.SeInputCheckbox_EN_Chrome_Test.class,
    okw.seinputfield.SeInputField_EN_Chrome_Test.class,
    okw.seinputradio.SeInputRadio_EN_Chrome_Test.class,
    okw.selink.SeLink_EN_Chrome_Test.class,
    okw.semaximize.SeMAXIMIZE_EN_Chrome_Test.class,
    okw.seradiolist.SeRadioList_EN_Chrome_Test.class,
    okw.seselect.SeSelect_EN_Chrome_Test.class,
    okw.setextarea.SeTextarea_EN_Chrome_Test.class,
} )
public class AllSe_EN_Chrome_Tests
{

}
