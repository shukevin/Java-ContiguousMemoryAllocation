import java.util.ArrayList;

/**
 * Main driver class for Memory Allocation project.
 * 
 * @author Kevin Shu
 * 
 *         This work complies with the JMU Honor Code.
 *
 */
public class Driver {

    /**
     * Parses file and runs correlating operations.
     * 
     * @param args - parses mode/filename
     */
    public static void main(String args[]) {
	String filePath;
	int mode = -1;
	String out = "";
	FileParse fp = new FileParse();
	ArrayList<Operation> ops;

	// Verify arguments.
	if (args.length == 3) {
	    out = args[2];
	}
	filePath = args[0];
	try {
	    mode = Integer.parseInt(args[1]);
	} catch (NumberFormatException e) {
	    invalidInput();
	}

	if (mode < 0 || mode > 4) {
	    invalidInput();
	}
	// Parse file
	ops = fp.parse(filePath);
	
	// Creation of memory object
	Memory mem = new Memory(ops);
	
	// Runs operations on the given mode.
	// First-fit(1), Best-fit(2), Worst-fit(3)
	if (mem.memoryOperation(mode)) {
	    System.out.println("Success");
	}

	
	// If there is an argument 'out' at args[2], print out memory information
	if (out.equalsIgnoreCase("out")) {
	    if (mode == 1) {
		System.out.println("First-fit mode");
	    } else if (mode == 2) {
		System.out.println("Best-fit mode");
	    } else if (mode == 3) {
		System.out.println("Worst-fit mode");
	    }
	    mem.printString();
	}

    }

    /**
     * Prints to system.err when there are invalid arguments.
     */
    private static void invalidInput() {
	System.err.println("Invalid argument length");
	System.err.println("Format: java Driver filePath mode");
	System.exit(1);
    }

}
