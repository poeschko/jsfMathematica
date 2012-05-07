package com.poeschko.jsfmathematica;

import com.wolfram.jlink.*;

public class Query {
    // see
    // http://reference.wolfram.com/mathematica/JLink/tutorial/WritingJavaProgramsThatUseMathematica.html
    // for a list of typical values for different operating systems
    private static String kernelLinkArgs = "-linkmode launch -linkname '\"/Applications/Mathematica.app/Contents/MacOS/MathKernel\" -mathlink'";
    
    private String query;
    private String result;
    private boolean executed = false;
    
    KernelLink ml;
    
    public Query() {
        ml = null;

        try {
            ml = MathLinkFactory.createKernelLink(kernelLinkArgs);
            ml.discardAnswer(); // get rid of initial InputNamePacket sent after launch
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
        }
    }
    
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
        result = ml.evaluateToInputForm(query, 0);
        return "executed";
    }
}
