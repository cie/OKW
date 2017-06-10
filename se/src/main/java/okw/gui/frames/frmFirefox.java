package okw.gui.frames;

import okw.gui.adapter.selenium.SeURL;
import okw.gui.adapter.selenium.webdriver.SeFirefoxApp;
import okw.OKW;

/**
 * @ingroup groupSeleniumFrames
 * 
 * @author zoltan
 *
 */
@OKW (FN="Firefox")
public class frmFirefox extends SeFirefoxApp
{
    @OKW(FN="URL")
    public SeURL URL = new SeURL();
}