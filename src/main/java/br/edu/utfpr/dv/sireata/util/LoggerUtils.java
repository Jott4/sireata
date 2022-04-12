package br.edu.utfpr.dv.sireata.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtils {
    public static void LogAndExcept(Exception e) throws Exception {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);

        throw new Exception(e.getMessage());

    }

}
