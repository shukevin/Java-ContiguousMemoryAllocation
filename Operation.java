
/**
 * Holds information pertaining to each operation.
 * 
 * @author Kevin Shu
 * 
 *         This work complies with the JMU Honor Code.
 *
 */
public class Operation {

    private int reference;
    private int operation;
    private int argument;

    /**
     * Class constructor for operations.
     * 
     * @param reference - value of operation reference.
     * @param operation - value of operation mode.
     * @param argument  - value of operation data.
     */
    public Operation(int reference, int operation, int argument) {
	this.reference = reference;
	this.operation = operation;
	this.argument = argument;
    }

    /**
     * Getter for reference value.
     * 
     * @return - reference value.
     */
    public int getReference() {
	return reference;
    }

    /**
     * Getter for operation value.
     * 
     * @return - operation value.
     */
    public int getOperation() {
	return operation;
    }

    /**
     * Getter for argument value.
     * 
     * @return - argument value.
     */
    public int getArgument() {
	return argument;
    }

    /**
     * toString method printing out operation contents for testing purposes.
     */
    public String toString() {
	return reference + ", " + operation + ", " + argument;
    }

}
