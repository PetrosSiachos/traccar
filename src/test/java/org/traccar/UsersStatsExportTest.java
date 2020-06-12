package org.traccar;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * An class that provides test case for the
 * UsersStatsExport class, for demonstrating Unit Testing.
 * @author siachos_petros (siachospetros@gmail.com)
 */
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