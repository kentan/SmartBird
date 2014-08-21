package org.smartbirdpj.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerFactory {
	private final static String LOGGING_PROPERTIES = "logging.conf";
	private final static String NAME = "sbrestful";
	private static Logger logger = null;

	public static Logger getLogger() {
		if(logger != null) return logger;
		
		logger = Logger.getLogger(NAME);

		final InputStream inStream = LoggerFactory.class.getClassLoader()
				.getResourceAsStream(LOGGING_PROPERTIES);
		if (inStream == null) {
			logger.info(LOGGING_PROPERTIES + "was not found");
		} else {
			try {
				LogManager.getLogManager().readConfiguration(inStream);
			} catch (IOException e) {
				// TOOD
			} catch (SecurityException e) {
				// TOOD
			} finally {
				try {
					if (inStream != null) {
						inStream.close();
					}
				} catch (IOException e) {
					// TOOD
				}
			}
		}

		return logger;
	}
}
