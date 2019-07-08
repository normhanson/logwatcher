package com.hansoncoyne.logwatcher;

import java.io.*;
import java.util.*;

public class Props {
    private static String PROPS = "LogWatcher.properties";
    private static String REGEXP = "regexp";
    private static String LOG_FILE = "logfile";
    private static String TAIL_CMD = "tail.cmd";
    private static String MAIL_TO = "mailto";

    protected String regexp;
    protected String logFile;
    protected String tailCmd;
    protected String mailTo;

    public Props() 
	throws IOException {
	InputStream is = this.getClass().getResourceAsStream (PROPS);
        Properties props = new Properties();
        props.load(is);
	tailCmd = props.getProperty(TAIL_CMD);
	logFile = props.getProperty(LOG_FILE);
	regexp = props.getProperty(REGEXP);
	mailTo = props.getProperty(MAIL_TO);
    }
}
