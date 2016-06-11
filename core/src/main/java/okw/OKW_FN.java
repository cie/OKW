package okw;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/// \~german
/// \brief Annotation @Interface für die Zuordnung des Fachlichen Name-s eines GUI-Objektes im Frame.
/// \see; http://tutorials.jenkov.com/java-reflection/annotations.html
/// == github Issue
/// issue https://github.com/Hrabovszki1023/OKW/issues/37
///
/// \~english
/// \brief
///
/// \~
/// \author Zoltan Hrabovszki
/// \date 2015-11-28

@Target( { ElementType.TYPE, // Usable in Class-es
    ElementType.FIELD } ) // Usable in Field-s
@Inherited
@Retention( RetentionPolicy.RUNTIME )
public @interface OKW_FN {

  // / \~german
  // / \brief_F_untionaler _N_ame des GUI-Objektes
  // /
  // / \~english
  // / \brief Holds the Functional Name of the GUI-Object.
  // /
  // / \~
  // / \author Zoltan Hrabovszki
  // / \date 2015-11-28
  String FN() default "";

  /**
   * TimeOut in Secunden [s] für das Schlüsselwort VerifyHasFocus. Default ist
   * 30s. Nach erreichen der Zeit wird die Prüfung mit Fehler beendet. in [s]
   */
  int VerifyHasFocus_TO() default 30;

  /**
   * Polling Time in [ms].Die Periodendauer des Pollens. in [ms]
   */
  int VerifyHasFocus_PT() default 1000;

  /**
   * TimeOut in [s]
   */
  int VerifyIsActive_TO() default 30;

  /**
   * Polling Time in [ms]
   */
  int VerifyIsActive_PT() default 1000;

  /**
   * TimeOut in [s]
   */
  int VerifyLabel_TO() default 30;

  /**
   * Polling Time in [ms]
   */
  int VerifyLabel_PT() default 1000;

  /**
   * TimeOut in [s]
   */
  int VerifySelectedValue_TO() default 30;

  /**
   * Polling Time in [ms]
   */
  int VerifySelectedValue_PT() default 1000;

  /**
   * TimeOut in [s]
   */
  int VerifyTooltip_TO() default 30;

  /**
   * Polling Time in [ms]
   */
  int VerifyTooltip_PT() default 1000;

  /**
   * TimeOut in [s]
   */
  int VerifyValue_TO() default 30;

  /**
   * Polling Time in [ms]
   */
  int VerifyValue_PT() default 1000;

  /**
   * TimeOut in [s]
   */
  int VerifyExists_TO() default 30;

  /**
   * Polling Time in [ms]
   */
  int VerifyExist_PT() default 1000;

}