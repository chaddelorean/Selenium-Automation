package com.qatest.automation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cowens
 * Date: 9/20/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class automationlog {

    private String location;
    private PrintWriter logCSV;
    private LTO2013Menu menu;

    public automationlog(String location)
    {
        this.location = location;
    }

    public void appendLog(String exec, String buyer, String ordernum, String market)
    {
        Date date = new Date();
        String stringDate = date.toString();
        logCSV.append(exec + "," + buyer +"," + market + "," + ordernum + "," + date);
    }

    public void saveLog()
    {
        final JFileChooser fc = new JFileChooser();
        String fileLocation;
        fc.setDialogTitle("Save LTO Automation Log");
        fc.setMultiSelectionEnabled(false);
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Values (CSV)", "csv");
        fc.setFileFilter(filter);
        int option = fc.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION)
        {
            location = fc.getSelectedFile().getAbsolutePath();
        }

        if (!location.contains(".csv"))
        {
            location += ".csv";
        }

        try {
             logCSV = new PrintWriter(location, "UTF-8");
             logCSV.append("Executive,Buyer,Market,OrderNumber,Date");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        binarySettings globalSettings = menu.getGlobalSettings();
        globalSettings.writeBinary(location);
    }

    public void openLog(String file)
    {
        File f = new File(file);
        if (!f.exists())
        {
            saveLog();
        }
        else
            location = file;
    }

    public String getLocation()
    {
        return location;
    }
}
