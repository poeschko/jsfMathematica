/**
 * To test the kernel link, do the following in test/:
 *   javac -classpath .:../WebContent/WEB-INF/lib/JLink.jar TestProgram.java
 *   java -classpath .:../WebContent/WEB-INF/lib/JLink.jar TestProgram
 */

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class TestProgram {
    private static String kernelLinkArgs = "-linkmode launch -linkname '\"/Applications/Mathematica.app/Contents/MacOS/MathKernel\" -mathlink'";
    //private static String linkLibPath = "/Applications/Mathematica.app/SystemFiles/Links/JLink/SystemFiles/Libraries/MacOSX-x86-64/libJLinkNativeLibrary.jnilib";
    
    private static KernelLink ml;
    
    public static void main(String[] argv) {
        ml = null;
    
        //System.out.println("Creating link to: " + kernelPath + " (lib: " + linkLibPath + ")");
        try {
            //System.setProperty("com.wolfram.jlink.libdir", linkLibPath);
            System.out.println("Creating link");
            ml = MathLinkFactory.createKernelLink(kernelLinkArgs);
            System.out.println("Link created");
            ml.discardAnswer(); // get rid of initial InputNamePacket sent after launch
            
            ml.close();
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
        }
    }
}