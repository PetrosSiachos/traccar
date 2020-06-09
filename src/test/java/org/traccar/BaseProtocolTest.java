package org.traccar;

import static org.junit.Assert.*;

import org.junit.Test;
import org.traccar.BaseProtocol;
public class BaseProtocolTest {
	BaseProtocol mm = new BaseProtocol();
	
	@Test (expected = RuntimeException.class)
	public void testSendTextCommand() {
		mm.sendTextCommand("test","test");
	}

}