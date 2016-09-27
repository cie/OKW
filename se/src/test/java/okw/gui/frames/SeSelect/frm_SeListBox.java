package okw.gui.frames.SeSelect;

import okw.OKW;
import okw.gui.adapter.selenium.SeBrowserChild;
import okw.gui.adapter.selenium.SeSelect;

  
  @OKW(FN="SeListBox")
  public class frm_SeListBox extends SeBrowserChild
  {

      @OKW(FN="NichtVorhanden", 
              VerifyLabel_PT=500, VerifyLabel_TO=1,
              VerifyValue_PT=500, VerifyValue_TO=1,
              VerifyTooltip_PT=500, VerifyTooltip_TO=1,
              VerifyCaption_PT=500, VerifyCaption_TO=1,
              VerifyExists_PT=500, VerifyExists_TO=1,
              VerifyIsActive_PT=500, VerifyIsActive_TO=1,
              VerifyHasFocus_PT=500, VerifyHasFocus_TO=1)
      public SeSelect NichtVorhanden;

      @OKW(FN="Künstler", 
              VerifyLabel_PT=500, VerifyLabel_TO=1,
              VerifyValue_PT=500, VerifyValue_TO=1,
              VerifyTooltip_PT=500, VerifyTooltip_TO=1,
              VerifyCaption_PT=500, VerifyCaption_TO=1,
              VerifyIsActive_PT=500, VerifyIsActive_TO=1,
              VerifyExists_PT=500, VerifyExists_TO=1,
              VerifyHasFocus_PT=500, VerifyHasFocus_TO=1)
      public SeSelect Künstler;

      @OKW(FN="Blumen", 
              VerifyLabel_PT=500, VerifyLabel_TO=1,
              VerifyValue_PT=500, VerifyValue_TO=1,
              VerifyTooltip_PT=500, VerifyTooltip_TO=1,
              VerifyCaption_PT=500, VerifyCaption_TO=1,
              VerifyIsActive_PT=500, VerifyIsActive_TO=1,
              VerifyExists_PT=500, VerifyExists_TO=1,
              VerifyHasFocus_PT=500, VerifyHasFocus_TO=1)
      public SeSelect Blumen;

      @OKW(FN="Fahrzeug", 
              VerifyLabel_PT=500, VerifyLabel_TO=1,
              VerifyValue_PT=500, VerifyValue_TO=1,
              VerifyTooltip_PT=500, VerifyTooltip_TO=1,
              VerifyCaption_PT=500, VerifyCaption_TO=1,
              VerifyIsActive_PT=500, VerifyIsActive_TO=1,
              VerifyExists_PT=500, VerifyExists_TO=1,
              VerifyHasFocus_PT=500, VerifyHasFocus_TO=1)
      public SeSelect Fahrzeug;

      public frm_SeListBox() 
      {
          super ("//*[@title='ListBox/Auswahliste: Einträge vorselektiert'");
  
          NichtVorhanden = new SeSelect("//*[@name='NichtVorhanden']");

          Künstler =  new SeSelect("//*[@name='Künstler']");
          Blumen = new SeSelect("//*[@name='Blumen']");
          Fahrzeug = new SeSelect("//*[@name='Fahrzeug']");
      }

  }
