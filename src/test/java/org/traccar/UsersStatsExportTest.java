package org.traccar;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsersStatsExportTest {
		String[][] usersStats = null;
		UsersStatsExport kk = new UsersStatsExport();

		/*
		 * A unit test that checks an invalid null input
		 * that causes an IllegalArgumentException
		 */
		@Test (expected = IllegalArgumentException.class)
		public void testxml_user_creator() {
			kk.xml_user_creator(usersStats);
		}

	}