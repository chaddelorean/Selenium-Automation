package com.qatest.automation;


import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: cowens
 * Date: 9/20/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class binarySettings {
    private String file;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public binarySettings(String file)
    {
        this.file = file;

        File checkfile = new File(this.file);
        if(checkfile.exists())
        {
            loadBinary();
        }
        else
        {
            createBinary();
        }

    }

    public void createBinary()
    {
        try{
            out = new ObjectOutputStream(new FileOutputStream(file));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void loadBinary()
    {
        try{
            in = new ObjectInputStream(new FileInputStream(file));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void writeBinary(Object obj)
    {
        try {
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Object readBinary()
    {
        Object obj = null;
        try {
            obj = (Object)in.readObject();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return obj;
    }
}


