package org.traccar;

import static org.junit.*;

import org.junit.Test;
import org.traccar.Context;
import org.junit.Assert;

public class ContextTest {
	Context mm = new Context();
	@Test (expected = Exception.class)
	public void testInitString() {

		mm.init("test");
	}

}
