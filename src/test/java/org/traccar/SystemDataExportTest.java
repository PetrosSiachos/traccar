package org.traccar;

import org.junit.Test;

/**
 * An class that provides test case for the 
 * SystemDataExport class, for demonstrating Unit Testing.
 * @author siachos_petros (siachospetros@gmail.com)
 */
public class SystemDataExportTest {

	SystemDataExport kk = new SystemDataExport();
	
	/*
	 * A unit test that checks an invalid 7 input
	 * that causes an IllegalArgumentException
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testXmlcreator() {
		kk.xmlcreator(7);
	}

}
