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

import javax.management.DescriptorKey;

@MyAnnotation(name="MeineIFaceAnnotation",  value = "123456")
interface IDummyClass {

	public abstract Integer getPrivateInteger();

	public abstract void setPrivateInteger(Integer privateInteger);

	@Deprecated
	@DescriptorKey(value = "1337")
	public abstract Integer getPublicInteger();

	@Deprecated
	public abstract void setPublicInteger(Integer publicInteger);

}