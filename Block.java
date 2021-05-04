
public class Block {

    private int reference;
    private boolean allocated;
    private int size;
    private int start;
    private int end;

    public Block(int start, int end) {
	this.size = end - start + 1;
	this.start = start;
	this.end = end;
	allocated = false;
	reference = -1;
    }

    public void setSize() {
	this.size = end - start + 1;
    }

    public void setStart(int start) {
	this.start = start;
    }

    public void setEnd(int end) {
	this.end = end;
    }

    public void setAllocated(boolean a) {
	this.allocated = a;
    }

    public void setReference(int ref) {
	this.reference = ref;
    }

    public boolean getAllocated() {
	return allocated;
    }

    public int getEnd() {
	return end;
    }

    public int getStart() {
	return start;
    }

    public int getSize() {
	return end - start + 1;
    }

    public int getReference() {
	return reference;
    }

    public String toString() {
	return "reference: " + reference + "\n\tstart: " + start + "\n\tend: " + end + "\n\tallocated: " + allocated
		+ "\n\tsize: " + size;
    }

}
