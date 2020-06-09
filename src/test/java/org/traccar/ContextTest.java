package org.traccar;

import static org.junit.Assert.*;

import org.junit.Test;
import org.traccar.Context;

public class ContextTest {
	Context mm = new Context();
	@Test
	public void testInitString() {

		Assert.assertEquals("e", mm.init("test"));
	}

}
