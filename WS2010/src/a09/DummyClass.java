package a09;

import java.io.Serializable;
import javax.management.DescriptorKey;
import javax.swing.JTree;

/**
 * Dies ist eine recht "nutzlose" Klasse, sie dient uns nur zu Testzwecken bei
 * der "Objektschnueffelei"
 * 
 * @author Bernie und Ert
 * @version 0.1beta
 */
@MyAnnotation(name = "DummyAnnotation", value = "Tach, Post!")
class DummyClass extends JTree implements Serializable, Cloneable, IDummyClass {

	private static final long serialVersionUID = 1L;

	private Integer privateInteger;
	public Integer publicInteger;

	public DummyClass() {
		this(1, 2);
	}

	// private DummyClass(int a) {
	// this(1, 2);
	// }

	public DummyClass(int i, int j) {
		privateInteger = i;
		publicInteger = j;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a08.IDummyClass#getPrivateInteger()
	 */
	@Override
	public Integer getPrivateInteger() {
		return privateInteger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a08.IDummyClass#setPrivateInteger(java.lang.Integer)
	 */
	@Override
	public void setPrivateInteger(Integer privateInteger) {
		this.privateInteger = privateInteger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a08.IDummyClass#getPublicInteger()
	 */
	@Override
	@Deprecated
	@DescriptorKey(value = "1337")
	public Integer getPublicInteger() {
		return publicInteger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a08.IDummyClass#setPublicInteger(java.lang.Integer)
	 */
	@Override
	@Deprecated
	public void setPublicInteger(Integer publicInteger) {
		this.publicInteger = publicInteger;
	}

}
