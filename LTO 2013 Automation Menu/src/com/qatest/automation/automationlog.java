package com.qatest.automation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
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

    public automationlog(String location)
    {
        this.location = location;
        if (this.location == null)
            saveLog();
        else
            openLog(this.location);
    }

    public void appendLog(String exec, String buyer, String ordernum, String market)
    {
        Date date = new Date();
        String stringDate = date.toString();
        logCSV.append(exec + "," + buyer +"," + market + "," + ordernum + "," + date + "\n");
        logCSV.flush();
    }

    public void saveLog()
    {
        final JFileChooser fc = new JFileChooser();
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
             logCSV.append("Executive,Buyer,Market,OrderNumber,Date\n");
             logCSV.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        LTO2013Menu.getLogProperties().put("logfile", location);
    }

    public void openLog(String file)
    {
        File f = new File(file);
        if (!f.exists())
        {
            saveLog();
        }
        else
        {
            location = file;

            try {
                logCSV = new PrintWriter(new BufferedWriter(new FileWriter(location, true)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public String getLocation()
    {
        return location;
    }
}
