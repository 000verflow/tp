package seedu.commands;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import seedu.data.ResourceList;
import seedu.ui.UI;

public class ExitCommand extends Command{
    private static final Logger LOGGER = Logger.getLogger(FindCommand.class.getName());

    static {
        // remove logs from showing in stdout
        try {
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }

            FileHandler fileHandler = new FileHandler("logs/findCommandLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);
        }
    }

    @Override
    public void execute(String statement, ResourceList resourcelist) throws IllegalArgumentException {
        assert statement != null : "Statement to execute cannot be null";
        assert resourcelist != null : "Parser must not be null";
        LOGGER.log(Level.INFO, "Executing ExitCommand...");
        UI ui = new UI();
        ui.showExitMessage();
    }
}
