package org.traccar;

import static org.junit.Assert.*;

import org.junit.Test;
import org.traccar.Context;

public class ContextTest {
	Context mm = new Context();
	@Test (expected = Exception.class)
	public void testInitString() {
		mm.init("test");
		
	}

}
