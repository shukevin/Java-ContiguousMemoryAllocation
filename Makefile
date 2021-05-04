JFLAGS = -g
JC = javac
JVM= java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Block.java\
	BlockLinkedList.java\
	Driver.java\
	FileParse.java\
	Memory.java\
	Node.java\
	Operation.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class: