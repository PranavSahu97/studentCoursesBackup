# studentCoursesBackup

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursesBackup/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: 
ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=<inputFile.txt> -Darg1=<outputFile.txt> -Darg2=<outputFile1.txt> -Darg3=<outputFile2.txt> -Darg4=<outputFile3.txt> 

Example:

The last argument is the Logger value. 
ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=inputFile.txt -Darg1=outputFile.txt -Darg2=outputFile1.txt -Darg3=outputFile2.txt -Darg4=outputFile3.txt 

-----------------------------------------------------------------------
## Description
Assignment Goal
Apply the design principles you have learned so far to develop software for the given problem.
Team Work
•	You need to work alone on this assignment.
•	You cannot discuss with anyone the design pattern(s) to be used for this assignment.
Programming Language
You are required to use Java.
Compilation Method
•	You are required to use ANT to compile the code.
Policy on sharing of code
•  EVERY line of code that you submit in this assignment should be written by you. Do NOT show your code to any other group. Our code-comparison software can very easily detect similarities.
•  Post to the listserv if you have any questions about the requirements. Do NOT post your code to the listserv asking for help with debugging. However, it is okay to post design/concept questions on programming in Java/C/C++.
Project Description
Backup System for Student Course Assignments
•	Create a class Node to store the B-Number (4 digit int) and an arraylist of course names (strings). The possible course names are "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K".
•	Write code for a tree that has the features to insert, search, and delete Nodes. You need to write code for the tree by yourself. However, you are allowed to get help from a book or online source (for example the source code for BST, AVL, 2-3, 2-3-4, etc.) If you do so, cite the URL of where you got the code from both in your source code and also README.md. It will be considered CHEATING if you do not cite the source of any code on tree that you use/adapt.
•	As you need to modify the code to implement the Observer pattern, you can't just use an in-built tree in Java. Each Node of the tree should implement both the Subject and the Observer interface.
•	Populate the tree using the data from an input file, input.txt, that has the following format:
•	1234:C
•	2345:D
•	1234:A
•	1234:D
•	1234:E
•	2345:F
•	3425:C
•	...
•	
•	For class participation, send me input.txt and delete.txt so that I can post it to the course assignment page.
•	The inputs may not be unique (some entries may repeat). So, either ignore the repeated inputs manually (if you use an ArrayList, for example), or use a key-value data structure that ensures unique entries.
•	Your tree builder should be such that when you create a node (Node_orig), you should clone it twice to get two Nodes (let's say backup_Node_1 and backup_Node_2). Setup backup_Node_1 and backup_Node_2 as observers of Node_orig. Node_orig, backup_Node_1, and backup_Node_2, should be instances of the same Node class. As Node_orig is the subject, you should store the references of backup_Node_1 and backup_Node_2 in a data structure for listeners (array list of references, for example).
•	Apply the following delete operations, one line at a time, from the file delete.txt. The file has the following format:
•	1234:C
•	2345:D
•	1234:A
•	...
•	Search for the node with the B_Number in the line, and then delete the corresponding course in that Node. If that course does not exist to delete, then ignore and move to the next line. Once the changes to the Node_orig are done, the changes should be updated to both the corresponding nodes. Note that the updates for a line in delete.txt should be sent, before the next line is processed.
•	From the command line accept the following args: input.txt, delete.txt, output1.txt, output2.txt, output3.txt debugValuue
•	Add a method to your tree, printNodes(Results r, ...), that stores in Results the list of courses for each student, sorted by the B_Number in ascending order.
•	Your driver code should do the following:
o	Read command line arguments.
o	Build the three trees, based on input.txt and the observer pattern.
o	Apply updates according to delete.txt
o	Create a new Results instance and call printNodes(...) on each of the three trees to store the BNumber and list of courses to store in Results. So, each of the three trees will use a different instance of Results.
o	Call a method in Results, via the method in FileDisplayInterface, to write the data stored in Results to output1.txt, output2.txt, and output3.txt, for the three trees.
o	You can run a "diff" on the three output files to make sure your Observer pattern worked correctly.
o	When you read in an input file, you should try to insert the course into the main tree corresponding to the node that has the BNumber. If a node with the BNumber exists, you should update that node in the main tree, notifyAll(...) to the two corresponding listener nodes. If no such node with that BNumber exists in the main tree, then do the following:
	create a Node (lets call it new node) with that BNumber and course.
	lone this new node twice.
	setup the two cloned nodes as listeners of the new node (add their references to the listeners list in the new node).
	insert the node in the main tree (using recursive insert from the root).
	insert the two cloned nodes in the backup trees similarly (recursively from the root)
o	Note that notifyAll(...) is being used for processing delete.txt and also when you need to add a course to an existing BNumber from the input.txt list. So, you can add an enum as a parameter to the notifyAll(...) to indicate whether the notifyAll(...) call, which calls update(...) on the listener nodes, is for insert or delete.
o	In your README.md, briefly describe how the observer pattern has been implemented.
o	Create and use your own MyLogger scheme for debugging.
Sample Input Files from Students
.
Code Organization
•	Your directory structure should be the following:
•	 -firstName_lastName_assign_3
•	   ---studentCoursesBackup
•	   ----- README.md
•	     ----- src
•	          ----- build.xml
•	          ---studentCoursesBackup
•	       	   ----------driver
•	           -----------------Driver.java
•	           ----------util
•	           -----------------TreeBuilder.java
•		   -----------------Results.java
•		   -----------------FileProcessor.java
•	      	   -----------------FileDisplayInterface.java	   
•	   	   -----------------StdoutDisplayInterface.java
•	      	   -----------------MyLogger.java	   
•	           ----------myTree
•		   -----------------Node.java
•	           -----------------SubjectI.java
•	           -----------------ObserverI.java
•	           ----------other packages and classes that you need




-----------------------------------------------------------------------
## Justification

####Observer pattern:
Node.java implements SubjectI, ObjectI and Cloneable.
Node class has one-to-many relationship defined.
myRoot in TreeBuilder is the original root. TreeBuilder has 3 instances of node, one instance is the original root and other two instances are backups.
While creating a backup node, the original node is used by node object to create duplicate nodes i.e backup nodes.
in case of an update to any node, the notify method in SubjectI is invoked which in turn calls Update method of ObserverI.
A deep copy method exists in clone() method as if there is a change in the original node, data in backup node will also get updated which affects the Observer Pattern implementation.
If the B Number is not present in the tree, an error message displays on the screen.  

####TreeBuilder.java
I have used a Binary Search Tree. All methods prevailing to this BST are written in this class.
It has a Time Complexity of O(n). This slows down performance when number of inputs are higher. The search and delete operations in a BST take O(n) complexity as well. 

#### Driver.java
Contains the main method.

####MyLogger.java
I was unable to complete the MyLogger part. 


####Reference for BST
https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/


-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.""

Date: 07/09/2019.


