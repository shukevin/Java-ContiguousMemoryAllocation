
/**
 * Linked list class for memory nodes.
 * 
 * @author Kevin Shu
 * 
 *         This work complies with the JMU Honor Code.
 *
 */
public class BlockLinkedList {

    private Node head;
    
    /**
     * Default class constructor for BlockLinkedList.
     */
    public BlockLinkedList() {
	this.head = null;
    }
    
    /**
     * Class constructor for BlockLinkedList.
     * @param node - value to be assigned as the head node.
     */
    public BlockLinkedList(Node node) {
	this.head = node;
    }
    
    /**
     * Getter for head value of linked list.
     * @return
     */
    public Node getHead() {
	return head;
    }
    
}
