# Java-ContiguousMemoryAllocation

The following project was a school assignment for an Operating Systems class. 

## Project description
This project deals with memory management. The associated code simulates contiguous memory allocation using first-fit, best-fit, and worst-fit algorithms.
Your program will be given a text file as input. Your program should read the input file from standard input. The input file will list memory operations –one per line. Each line of input will contain three fields (all positive integers):

- A reference number (a unique identifier for that operation)
- An operation (either 1 for “allocate” or 2 for “de‐allocate”)
- An argument (a size in bytes for an allocate operation; a reference number for a de‐allocate operation)

So, for example, the following input file specifies 6 memory operations:

- 1 1 37
- 2 1 17
- 3 1 23
- 4 2 2
- 5 2 1
- 6 1 8

The first line (reference number 1) is a request to allocate 37 contiguous bytes of memory. 
The second line (reference number 2) is a request to allocate 17 contiguous bytes of memory. 
The third line (reference number 3) is a request toallocate 23 contiguous bytes of memory. 
The fourth line (reference number 4) is a request to de-­‐allocate the bytes allocated in line 2. 
The fifth line (reference number 5) is a request to de-­‐allocate the bytes allocated in line 1. 
The sixth line (reference number 6) is a request to allocate 8 contiguous bytes of memory.

You should have a constant in our program that represents the total number of bytes in memory which you should set to 1024.
Your program will need to read the input file and simulate how the memory operations would proceed under 3 different algorithms:
- First‐fit
- Best‐fit
- Worst‐fit

The simulation ends when either the last memory operation completes successfully or any memory operation cannot be satisfied. If all memory operations completesuccessfully, your program should output one line –the word “Success.” If a memory operation cannot be satisfied your program should output two lines: 
1) the word “Failed”, the reference number for the request that failed,and the number of bytes for the request that failed
2) the amount of external fragmentation (in bytes) when the request failed.


## How to run the code
1. Type: "make" in the makefile's directory to utilize the makefile.
	
2. Type: java Driver [file path] [mode 1-3] [out] Replace the bracketed text with the designated file path/mode/textThe [out] is optional. [out] displays the final state of the memory space.
		
Example: java Driver src/file.txt 1 out
	- The example above runs First-fit on the src/file.txt file and displays to system.out the final memory space.
			
Example: java Driver src/file.txt 2
	- The example above runs Best-fit on src/file.txt file.
			
Modes are:
	1 = First-fit
	2 = Best-fit
	3 = Worst-fit
			
The [out] argument will print to system.out in a format similar to:
	[mode] mode
	--------------------------
	Final memory space:
	...
	reference: #
		start: #
		end: #
		allocated: T/F
		size: #
	...
Any reference denoted by '-1' is considered to be unallocated memory(aka a 'hole').	
		

## Explanation of implementation
- Driver.java runs the program.
- Memory.java is an object that handles memory block management.
- Block.java is an object that holds information about a memory block (size, start/end point, reference, allocation)
- BlockLinkedList.java / Node.java acts as a linked list that contains each Block from Block.java.
- FileParse.java reads in a file and converts it into a list of Operation objects.
- Operation.java is an object that holds information about each operation(reference, operation, argument)

A successful run of the program will go as follows:
		Driver.java is ran, and takes user arguments as input.
		The FileParse object will parse the file given in the arguments and return a list of Operation objects.
		The Memory object is initiated which creates a BlockLinkedList with an empty Block of the given size(1024).
		The Memory object takes the list of Operation objects and loops through each operation attempting to conduct memory allocation of the given mode.
		Upon allocation, a node of the BlockLinkedList will be added with the corresponding block information. The previously deallocated block node will
		adjust in size.
		Upon successful allocation/deallocation of objects, the Memory object will return true which prints "Success".
    
In between memory allocation/deallocation calls, the merge() method of Memory will be called which goes through the linked list and merges
any two consecutive unallocated references(noted by Operation.reference = -1).
		
An error will occur when there is no valid space for an allocation, or if an invalid deallocation is made (deallocation on a non-existent
reference)
	
The program will output to system.err with an error message: 
	Failed | Reference: # | Bytes: #
	Fragmentation bytes: #
		
Fragmentation bytes is calculated by looping through the linked list and adding the sizes of each unallocated reference(Operation.reference = -1).
