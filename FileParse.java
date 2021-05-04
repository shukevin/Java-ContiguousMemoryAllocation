import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * File I/O that converts input file to a list of Operations.
 * 
 * @author Kevin Shu
 * 
 *         This work complies with the JMU Honor Code.
 *
 */
public class FileParse {

    /**
     * Parses the file with the given filename.
     * 
     * @param filename - file/file path of the given value.
     * @return - list of operations from file.
     */
    public ArrayList<Operation> parse(String filename) {
	int ref, op, arg;
	ArrayList<Operation> retVal;
	BufferedReader br;
	String line;
	String[] lineContent;
	File file = new File(filename);
	retVal = new ArrayList<Operation>();
	try {
	    br = new BufferedReader(new FileReader(file));
	    while ((line = br.readLine()) != null) {
		lineContent = line.split(" ");
		ref = Integer.parseInt(lineContent[0]);
		op = Integer.parseInt(lineContent[1]);
		arg = Integer.parseInt(lineContent[2]);

		retVal.add(new Operation(ref, op, arg));
	    }
	    br.close();
	    return retVal;
	} catch (Exception e) {
	    return null;
	}
    }
}
