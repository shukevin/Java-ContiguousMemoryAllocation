import java.util.ArrayList;

/**
 * Handles memory block operations.
 * 
 * @author Kevin Shu
 * 
 *         This work complies with the JMU Honor Code.
 *
 */
public class Memory {

    private BlockLinkedList blocks;
    private ArrayList<Operation> operations;
    private final int bytes = 1023;

    /**
     * Class constructor for memory operations.
     * 
     * @param operations - List of operations to be performed.
     */
    public Memory(ArrayList<Operation> operations) {
	this.operations = operations;
	blocks = new BlockLinkedList(new Node(new Block(0, bytes)));
    }

    /**
     * Prints out each memory block contents in the list.
     */
    public void printString() {
	Node next = blocks.getHead();
	System.out.println("------------------------------");
	System.out.println("Final memory space:");

	while (next != null) {
	    System.out.println(next.getBlock().toString());
	    next = next.getNext();
	}
    }

    /**
     * Handles allocation/deallocation for file input.
     * 
     * @param mode - mode of memory management to be performed.
     * @return - task completion status.
     */
    public boolean memoryOperation(int mode) {
	boolean success = false;
	// MODES: First-fit(1), Best-fit(2), Worst-fit(3)
	for (Operation op : operations) {
	    // MERGE BLOCKS
	    merge();
	    // ALLOCATE
	    if (op.getOperation() == 1) {
		switch (mode) {
		case 1:
		    success = allocateFF(op);
		    break;
		case 2:
		    success = allocateBF(op);
		    break;
		case 3:
		    success = allocateWF(op);
		    break;
		default:
		    System.err.println("Invalid mode argument");
		    break;
		}
	    }
	    // DEALLOCATE
	    else if (op.getOperation() == 2) {
		success = deallocate(op);
	    }
	    // MERGE BLOCKS
	    merge();

	    // Check if the operation was successful
	    if (!success) {
		// Prints error message with corresponding failed operation, then stops
		// reading operations.
		if (op.getOperation() == 2) {
		    errorMessageD(op);
		} else {
		    errorMessage(op);
		}
		// Merge upon exit
		merge();
		return success;
	    }
	}
	// Merge upon exit
	merge();
	return success;

    }

    /**
     * Allocation procedure of worst-fit algorithm.
     * 
     * @param op - operation to be run.
     * @return - task completion status
     */
    public boolean allocateWF(Operation op) {
	Node node = blocks.getHead();
	Block b;
	Block worst = null;
	Node worstNode = null;
	Block edit;

	// Find the worst fit block
	while (node != null) {
	    b = node.getBlock();
	    if (!b.getAllocated() && (b.getSize() >= op.getArgument())
		    && (worst == null || b.getSize() > worst.getSize())) {
		worst = b;
		worstNode = node;
	    }
	    node = node.getNext();
	}

	// allocate the operation in the worst fit block.
	if (worst != null) {
	    edit = new Block(worst.getStart() + op.getArgument(), worst.getEnd());
	    Node editNode = new Node(edit, worstNode.getNext(), worstNode);
	    worst.setAllocated(true);
	    worst.setReference(op.getReference());
	    worst.setEnd(worst.getStart() + op.getArgument() - 1);
	    worst.setSize();
	    if (edit.getSize() > 0) {
		worstNode.setNext(editNode);
	    }
	} else {
	    return false;
	}
	return true;
    }

    /**
     * Allocation procedure of best-fit algorithm.
     * 
     * @param op - operation to be run.
     * @return - task completion status
     */
    public boolean allocateBF(Operation op) {
	Node node = blocks.getHead();
	Block b;

	Block best = null;
	Node bestNode = null;
	Block edit;

	// Find the best fit block
	while (node != null) {
	    b = node.getBlock();
	    // if its deallocated, big enough, and smaller than the current best
	    if (!b.getAllocated() && (b.getSize() >= op.getArgument())
		    && (best == null || b.getSize() < best.getSize())) {
		best = b;
		bestNode = node;
	    }

	    node = node.getNext();
	}
	// Allocate the operation in the best fit block.
	if (best != null) {

	    edit = new Block(best.getStart() + op.getArgument(), best.getEnd());
	    Node editNode = new Node(edit, bestNode.getNext(), bestNode);
	    best.setAllocated(true);
	    best.setReference(op.getReference());
	    best.setEnd(best.getStart() + op.getArgument() - 1);
	    best.setSize();
	    if (edit.getSize() > 0) {
		bestNode.setNext(editNode);
	    }
	} else {
	    return false;
	}

	return true;
    }

    /**
     * Allocation procedure of first-fit algorithm.
     * 
     * @param op - operation to be run.
     * @return - task completion status
     */
    private boolean allocateFF(Operation op) {
	Node node = blocks.getHead();
	Block b;
	Block temp;
	Block edit;
	Node newNode;
	boolean satisfied = false;

	while (node != null && !satisfied) {
	    b = node.getBlock();

	    // Ensure the block is of a valid size.
	    if (!b.getAllocated() && b.getSize() >= op.getArgument() && op.getArgument() != 0) {
		temp = new Block(b.getStart(), b.getStart() + op.getArgument() - 1);
		temp.setAllocated(true);
		temp.setReference(op.getReference());
		edit = new Block(temp.getEnd() + 1, b.getEnd());
		node.setBlock(temp);

		// If the edited node is 0, delete the node
		if (edit.getSize() == 0) {
		    // do nothing
		}
		// Otherwise, add the new node
		else {
		    newNode = new Node(edit, node.getNext(), node);
		    node.setNext(newNode);
		}

		satisfied = true;
	    }

	    node = node.getNext();
	}

	return satisfied;

    }

    /**
     * deallocation procedure.
     * 
     * @param op - operation to be run.
     * @return - task completion status
     */
    private boolean deallocate(Operation op) {
	Node node = blocks.getHead();
	Block b;
	boolean satisfied = false;

	while (node != null && !satisfied) {
	    b = node.getBlock();
	    if (b.getAllocated() && b.getReference() == op.getArgument()) {
		// If the next block is also deallocated, merge
		b.setAllocated(false);
		b.setReference(-1);
		satisfied = true;
	    }
	    node = node.getNext();
	}
	return satisfied;

    }

    /**
     * Merge procedure.
     */
    private void merge() {
	Node next = blocks.getHead();

	while (next != null) {
	    if (next.getNext() != null && next.getBlock().getReference() == -1
		    && next.getNext().getBlock().getReference() == -1) {
		// merge
		Block b = new Block(next.getBlock().getStart(), next.getNext().getBlock().getEnd());
		next.setBlock(b);
		next.setNext(next.getNext().getNext());
	    }
	    next = next.getNext();
	}
    }

    /**
     * Prints error information with give operation.
     * 
     * @param op - operation's error message.
     */
    private void errorMessage(Operation op) {
	int frag = 0;
	Node next = blocks.getHead();
	while (next != null) {
	    if (next.getBlock().getReference() == -1) {
		frag += next.getBlock().getSize();
	    }
	    next = next.getNext();
	}
	System.err.print("Failed | Reference: " + op.getReference() + " | Bytes: " + op.getArgument()
		+ "\nFragmentation bytes: " + frag + "\n");
    }

    /**
     * Prints error information for unsuccessful deallocation.
     * 
     * @param op - operation's error message.
     */
    private void errorMessageD(Operation op) {
	int frag = 0;
	Node next = blocks.getHead();
	while (next != null) {
	    if (next.getBlock().getReference() == -1) {
		frag += next.getBlock().getSize();
	    }
	    next = next.getNext();
	}
	System.err.print(
		"Failed | Reference: " + op.getReference() + " | ERROR: Attempt to deallocate an unreferenced block("
			+ op.getArgument() + ")" + "\nFragmentation bytes: " + frag + "\n");
    }

}
