package org.traccar;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseProtocolTest {
	BaseProtocol mm = new BaseProtocol();
	
	@Test (expected = RuntimeException.class)
	public void testSendTextCommand() {
		mm.SendTextCommand(0, traccar);
	}

}