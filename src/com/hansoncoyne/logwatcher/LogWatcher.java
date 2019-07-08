package com.hansoncoyne.logwatcher;

import java.io.*;
import java.util.*;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import org.apache.regexp.RE;
import alt.dev.jmta.JMTA;

public class LogWatcher {
	Props _props;

    public static void main(String[] args) {
        try {
		LogWatcher logWatcher = new LogWatcher();
		Props props = new Props();
		logWatcher.setProps(props);
		logWatcher.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        } 
    }
    private void run() 
	throws Exception {
	    String line;
	    RE r = new RE(_props.regexp);
	    StringBuffer cmd = new StringBuffer();
	    cmd.append(_props.tailCmd).append(" ").append(_props.logFile);
	    Process tail = Runtime.getRuntime().exec(cmd.toString());
	    DataInputStream in = new DataInputStream(tail.getInputStream());
	    Session session = Session.getDefaultInstance( System.getProperties() );
	    while ((line = in.readLine()) != null) {
		boolean matched = r.match(line);
		if (matched) {
			System.out.println(line);
			MimeMessage message = new MimeMessage(session);
			message.addRecipients(Message.RecipientType.TO, _props.mailTo); 
			message.setSubject("JLogWatcherMessage");
			message.setText(line);
			JMTA.send( message );
		}
	    }
	}
	public void setProps(Props props) {
		_props = props;
	}
}
