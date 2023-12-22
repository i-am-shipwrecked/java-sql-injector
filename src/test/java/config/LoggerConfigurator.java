package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class LoggerConfigurator {
    static Logger LOGGER = LogManager.getLogger(LoggerConfigurator.class);

    public static void configureLog4j() {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
        File logDirectory = new File("logs");
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }
        LOGGER.info("Log4j is configured.");
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
