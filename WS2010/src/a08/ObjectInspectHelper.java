package a08;

/**
 * 
 * @author Bernie und Ert
 * 
 *         In dieser Klasse befinden sich die Methoden, zum Inspizieren von
 *         Objekten. Die Methoden sind ausgelagert aus der ExplorerTree Klasse, 
 *         um diese übersichtlicher zu gestalten.
 * 
 */

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ObjectInspectHelper {
	
	/**
	 * 
	 * @param c Übergabeklassenobjekt
	 * @return String Liefert die Modifier des übergebenen Klassenobjekts zurück
	 */
	public String printClassModifiers(Class<?> c) {
		return c.getModifiers() == 0 ? "no class-modifier (Package)" : Modifier.toString(c.getModifiers());
	}
	
	/**
	 * 
	 * @param o Übergabeobjekt
	 * @return String Liefert alle Annotationen einer Klasse zurück
	 */
	public String printClassAnnotations(Object o) {
		StringBuilder sb = new StringBuilder();
		Annotation[] annos = o.getClass().getDeclaredAnnotations();
		sb.append("\n");
		for (Annotation a : annos) {
			sb.append("  - " + a + "\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param m Übergabemethode
	 * @return String Liefert die Modifier des übergebenen Methodenobjekts zurück
	 */
	public String printMethodModifiers(Method m) {
		return Modifier.toString(m.getModifiers());
	}

	/**
	 * 
	 * @param o Übergabeobejct
	 * @return String Liefert alle Oberklassen und die Superklasse des Übergabeobjekts
	 */
	public String printSuperclasses(Object o) {
		StringBuilder sb = new StringBuilder();
		Class<?> subclass = o.getClass();
		Class<?> superclass = subclass.getSuperclass();
		while (superclass != null) {
			String className = superclass.getName();
			sb.append("\n").append("  - ").append(className);
			subclass = superclass;
			superclass = subclass.getSuperclass();
		}
		sb.append(" (Superklasse)\n");
		return sb.toString();
	}
	
	/**
	 * 
	 * @param o Übergabeobjekt
	 * @return String Liefert alle implementierten Interfaces des Übergabeobejekts mit zugehörigen Annotationen 
	 */
	public String printInterfaces(Object o){
		StringBuilder sb = new StringBuilder();
		Class<?> c = o.getClass();
		Class<?>[] theInterfaces = c.getInterfaces();
		if(theInterfaces.length != 0){
		sb.append("\n");
		for (int i = 0; i < theInterfaces.length; i++) {
			sb.append("  - ").append(theInterfaces[i].getCanonicalName()).append("\n");
			Annotation[] anno = theInterfaces[i].getDeclaredAnnotations();
			if(anno.length != 0){
				for (Annotation a : anno) {
					sb.append("    - " + a + "\n");
				}
			}
		}
		return sb.toString();
		}
		return "No Interfaces implemented\n";
	}
	
	/**
	 * 
	 * @param o Übergabeobjekt
	 * @return String Liefert alle Konstruktoren des Übergabeobjekts zurück
	 */
	public String printConstructors(Object o) {
		StringBuilder sb = new StringBuilder();
		Class<?> c = o.getClass();
		Constructor<?>[] theConstructors = c.getDeclaredConstructors();
		sb.append("\n");
		for (int i = 0; i < theConstructors.length; i++) {
			sb.append("  - ").append(theConstructors[i]).append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param field Übergebenes Feld
	 * @param o zugehöriges Übergabeobjekt
	 * @return String Liefert Name, Typ, Modifier(s) und Wert des übergebenen Feldes
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String printFieldInfos(Field field, Object o) throws IllegalArgumentException, IllegalAccessException{
		StringBuilder sb = new StringBuilder();
		Class<?> typeClass = field.getType();
		sb.append("- Name: " + field.getName() + "\n");
		sb.append("\n- Typ: " + typeClass.getName() + "\n");
		sb.append("\n- Modifier(s): " + printClassModifiers(typeClass) + "\n");
		sb.append("\n- Wert: " + field.get(o) + "\n");
		return sb.toString();
	}
	
	/**
	 * 
	 * @param method Übergabemethodenobjekt
	 * @return String Liefert Name, Annotationen, Modifiers, Rückgabetyp und Übergabeparameter der übergebenen Methode
	 */
	public String printMethod(Method method) {
		StringBuilder sb = new StringBuilder();
		sb.append("- Name: " + method.getName() + "\n");
//		method.setAccessible(true);
		Annotation[] annos = method.getDeclaredAnnotations();
		sb.append("\n- Annotation(s):\n");
		for(Annotation a : annos){
			sb.append("  - " + a + "\n");
		 }
		sb.append("\n- Modifier(s): " + printMethodModifiers(method) + "\n");
		sb.append("\n- Rückgabetyp: " + method.getReturnType().getSimpleName() + "\n");
		Class<?>[] parameterTypes = method.getParameterTypes();
		sb.append("\n- Übergabeparamtertyp:");
		for (Class<?> p : parameterTypes) {
			sb.append(" " + p.getSimpleName() + " ");
		}
		return sb.toString();
	}
	

}
