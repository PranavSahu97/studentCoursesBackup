package studentCoursesBackup.util;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Pranav Sahu
 *
 */
import studentCoursesBackup.driver.Driver;
import studentCoursesBackup.myTree.Node;

public class TreeBuilder {
	private Node myRoot, backUp_Node_1, backUp_Node_2;
	private Node n1, n2;
	public static BufferedReader br=null;

	public void fileInsert() throws IOException, CloneNotSupportedException	{
		String line="";
		FileProcessor fileProcessor = new FileProcessor(Driver.inputFilePath);
		while (br != null) {
			line = fileProcessor.readLine(br);
			if (line == null) {
				break;
			}
			int bNo = 0;
			String[] div = line.split(":",-1);
			try {
				bNo = Integer.parseInt(div[0]);	
			}catch(NumberFormatException e) {
				System.err.println("B-Number should be an Integer");
				System.exit(0);
			}
			insert(bNo,div[1]);
		}
	}


	public Node getMyRoot() {
		return myRoot;
	}

	public void setMyRoot(Node myRoot) {
		this.myRoot = myRoot;
	}

	public Node getbackUp_Node_1() {
		return backUp_Node_1;
	}

	public void setbackUp_Node_1(Node subjectNode) {
		this.backUp_Node_1 = subjectNode;
	}

	public Node getbackUp_Node_2() {
		return backUp_Node_2;
	}

	public void setbackUp_Node_2(Node observerNode) {
		this.backUp_Node_2 = observerNode;
	}

	public TreeBuilder() {
		super();
		myRoot = null;
	}

	@Override
	public String toString() {
		String r = "Original tree Root: " + myRoot.toString() + " || First Backup tree Root: "
				+ backUp_Node_1.toString() + " || Second Backup tree Root: " + backUp_Node_2.toString();

		return r;
	}

	public boolean isEmpty() {
		return (myRoot == null);
	}

	public Node searchBNo(int bNoIn) {
		if (myRoot != null) {
			return searchBNo(myRoot, bNoIn);
		}
		return null;
	}

	private Node searchBNo(Node nodeIn, int bNoIn) {
		Node sNode = null;
		if (nodeIn != null) {
			if (nodeIn.getbNo() > bNoIn) {
				sNode = searchBNo(nodeIn.getLeft(), bNoIn);
			} else if (nodeIn.getbNo() < bNoIn) {
				sNode = searchBNo(nodeIn.getRight(), bNoIn);
			} else {
				sNode = nodeIn;
			}
		}
		return sNode;
	}

	public void insert(int bNoIn, String courseIn) throws CloneNotSupportedException {
		if ((bNoIn <= 9999) && (courseIn.compareTo("L") < 0)) {
			myRoot = insert(bNoIn, courseIn, myRoot);
			if (n1 != null && n2 != null) {
				backUp_Node_1 = insertNewNode(bNoIn, courseIn, backUp_Node_1, n1);
				backUp_Node_2 = insertNewNode(bNoIn, courseIn, backUp_Node_2, n2);
			}
		}
	}

	private Node insert(int bNoIn, String courseIn, Node rootNodeIn) throws CloneNotSupportedException
	{
		Node node = searchBNo(bNoIn);
		if (node != null) {
			ArrayList<String> arr = node.getCourse();
			if (!arr.contains(courseIn)) {
				arr.add(courseIn);
				node.setCourse(arr);
				node.notifyAll(courseIn, Node.operations.insert.toString());
			}
		} else {
			if (rootNodeIn == null) {
				rootNodeIn = new Node(bNoIn, courseIn);
				rootNodeIn.register(bNoIn, courseIn);
				n1 = rootNodeIn.getbackUp_Node_1();
				n2 = rootNodeIn.getbackUp_Node_2();
			} else if (bNoIn < rootNodeIn.getbNo()) {
				rootNodeIn.setLeft(insert(bNoIn, courseIn, rootNodeIn.getLeft()));
			} else if (bNoIn > rootNodeIn.getbNo()) {
				rootNodeIn.setRight(insert(bNoIn, courseIn, rootNodeIn.getRight()));
			}
		}
		//System.out.println(rootNodeIn);
		return rootNodeIn;
	}

	private Node insertNewNode(int bNoIn, String courseIn, Node rootNodeIn, Node nodeIn) {
		if (rootNodeIn == null) {
			rootNodeIn = nodeIn;
		} else if (bNoIn < rootNodeIn.getbNo()) {
			rootNodeIn.setLeft(insertNewNode(bNoIn, courseIn, rootNodeIn.getLeft(), nodeIn));
		} else if (bNoIn > rootNodeIn.getbNo()) {
			rootNodeIn.setRight(insertNewNode(bNoIn, courseIn, rootNodeIn.getRight(), nodeIn));
		}

		return rootNodeIn;
	}

	private void inOrder(Results result, Node nodeIn){
		Node node_1;
		node_1=nodeIn;
		if(node_1!=null){
			inOrder(result, node_1.getLeft());
			result.storeNewResults(String.valueOf(node_1));
			inOrder(result, node_1.getRight());
		}
	}

	public void delete(int bNoIn, String courseIn) {
		Node node = searchBNo(bNoIn);
		if (node != null) {
			ArrayList<String> arr = node.getCourse();
			if (arr.contains(courseIn)) {
				arr.remove(arr.indexOf(courseIn));
				node.setCourse(arr);
							
				node.notifyAll(courseIn, Node.operations.delete.toString());
			}
		} else {
			System.out.println("Student record for " + bNoIn + " does not exist.");
		}
		node=searchBNo(bNoIn);
	}

	public void fileDelete() throws CloneNotSupportedException
	{
		String line = "";
		FileProcessor fileProcessor = new FileProcessor(Driver.deleteFilePath);
		while (br != null){
			line = fileProcessor.readLine(br);
			if (line == null){
				break;
			}
			int bNo = 0;
			String[] div = line.split(":",-1);
			try {
				bNo = Integer.parseInt(div[0]);	
			}catch(NumberFormatException e) {
				System.err.println("B-Number should be an Integer");
				System.exit(0);
			}
			delete(bNo,div[1]);
		}
	}

	public void printNodes(Results results,Results result1, Results result2) {
		inOrder(results, myRoot);
		results.writeToFile(Driver.outputFilePath1);
		System.out.println("Values of Node");
		results.writeToStdout();
		inOrder(result1, backUp_Node_1);
		result1.writeToFile(Driver.outputFilePath2);
		System.out.println("Values of Backup Node 1");
		result1.writeToStdout();
		inOrder(result2, backUp_Node_2);
		result2.writeToFile(Driver.outputFilePath3);
		System.out.println("Values of Backup Node 2");
		result2.writeToStdout();
	}
	
}

