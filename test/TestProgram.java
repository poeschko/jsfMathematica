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
    
    public static void main(String[] argv) {
        try {
            System.out.println("Creating link");
            KernelLink ml = MathLinkFactory.createKernelLink(kernelLinkArgs);
            System.out.println("Link created");
            ml.discardAnswer(); // get rid of initial InputNamePacket sent after launch        
            ml.close();
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
        }
    }
}