package com.poeschko.jsfmathematica;

import com.wolfram.jlink.*;

public class Query {
    // see
    // http://reference.wolfram.com/mathematica/JLink/tutorial/WritingJavaProgramsThatUseMathematica.html
    // for a list of typical values for different operating systems
    private static String kernelLinkArgs = "-linkmode launch -linkname '\"/Applications/Mathematica.app/Contents/MacOS/MathKernel\" -mathlink'";
    //private static String linkLibPath = "/usr/lib/java/libJLinkNativeLibrary.jnilib";
    
    private String query;
    private String result;
    private boolean executed = false;
    
    KernelLink ml;
    
    public Query() {
        ml = null;

        //System.out.println("Creating link to: " + kernelPath + " (lib: " + linkLibPath + ")");
        try {
            //System.setProperty("com.wolfram.jlink.libdir", linkLibPath);
            ml = MathLinkFactory.createKernelLink(kernelLinkArgs);
            ml.discardAnswer(); // get rid of initial InputNamePacket sent after launch
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
        }
    }
    
    /*@Override
    public void finalize() {
        ml.close();
    }*/
    
    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    
    public String getResult() {
        return result;
    }
    
    public boolean getExecuted() {
        return executed;
    }

    public String execute() {
        executed = true;
        //result = "hi";
        result = ml.evaluateToInputForm(query, 0);
        if (result.startsWith("Error occurred"))
            result = "Error";
        /*try {
            ml.evaluate(query);
            ml.waitForAnswer();
            Expr result = ml.getExpr();
            result.toString();
            //result = ml.getString();
        } catch (MathLinkException e) {
            result = "Error occurred: " + e.getMessage();
        }*/
        return "executed";
    }
}
