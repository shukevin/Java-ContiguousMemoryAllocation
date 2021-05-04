
/**
 * Helper class for BlockLinkedList(nodes).
 * 
 * @author Kevin Shu
 * 
 *         This work complies with the JMU Honor Code.
 *
 */
public class Node {

    private Block block;
    private Node next;
    private Node prev;

    /**
     * Class constructor for Node object.
     * @param block - Node's block value.
     */
    Node(Block block) {
	this.block = block;
	this.next = null;
	this.prev = null;
    }

    Node(Block block, Node next, Node prev) {
	this.block = block;
	this.next = next;
	this.prev = prev;
    }

    public void setPrev(Node prev) {
	this.prev = prev;
    }
    
    public void setNext(Node next) {
	this.next = next;
    }

    public void setBlock(Block block) {
	this.block = block;
    }
    
    public Node getPrev() {
	return this.prev;
    }

    public Node getNext() {
	return this.next;
    }

    public Block getBlock() {
	return this.block;
    }

}
