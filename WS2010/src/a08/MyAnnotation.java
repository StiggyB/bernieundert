package a08;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Dies ist eine recht "nutzlose" Klasse, sie dient uns nur
 *         zu Testzwecken bei der "Objektschnueffelei"
 *         
 * @version 0.1beta
 *  
 * 
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    public String name();
    public String value();

}

