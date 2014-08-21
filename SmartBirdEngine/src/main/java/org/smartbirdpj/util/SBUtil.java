package org.smartbirdpj.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class SBUtil {
	public static void logThrowable(Logger logger,Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);

		logger.severe(sw.toString());
	}
}
