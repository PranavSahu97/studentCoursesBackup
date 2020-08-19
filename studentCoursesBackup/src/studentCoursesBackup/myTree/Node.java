package studentCoursesBackup.myTree;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Pranav Sahu
 *
 */
public class Node implements SubjectI, ObserverI, Cloneable
{ 
    
    private int bNo;
    private Node left, right;
    private ArrayList<String> course; 
    ObserverI backUp_Node_1, backUp_Node_2;

    public enum operations {
        insert, delete
    }

    public Node(int bNoIn, String courseIn) {
        super();
        this.bNo = bNoIn;
        this.course = new ArrayList<>();
        course.add(courseIn);
        this.left = null;
        this.right = null;
    }

    public void register(int bNoIn, String courseIn) {
        backUp_Node_2 = cloneNode(bNoIn, courseIn);
        backUp_Node_1 = cloneNode(bNoIn, courseIn);
    }
    
    public void unRegister(int bNoIn, String courseIn) {
        backUp_Node_2 = null;
        backUp_Node_1 = null;
    }

    public int getbNo() {
        return bNo;
    }

    public void setbNo(int bNoIn) {
        this.bNo = bNoIn;
    }

    public ArrayList<String> getCourse() {
        return course;
    }

    public void setCourse(ArrayList<String> courseIn) {
        this.course = courseIn;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node leftIn) {
        this.left = leftIn;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node rightIn) {
        this.right = rightIn;
    }

    @Override
    public String toString() {
        String r = bNo + ": ";
        for (String string : course) {
            r = r.concat(string + " ");
        }
        return r;
    }

    private Node cloneNode(int bNoIn, String courseIn) {
        return new Node(bNoIn, courseIn);
    }

    public Node getbackUp_Node_1() {
        return (Node) backUp_Node_1;
    }

    public void setbackUp_Node_1(Node subjectI) {
        this.backUp_Node_1 = subjectI;
    }

    public Node getbackUp_Node_2() {
        return (Node) backUp_Node_2;
    }

    public void setbackUp_Node_2(Node observerI) {
        this.backUp_Node_2 = observerI;
    }

    public void notifyAll(String courseIn, String operationIn) {
        backUp_Node_1 = notify(backUp_Node_1, courseIn, operationIn);
        backUp_Node_2 = notify(backUp_Node_2, courseIn, operationIn);
    }

    @Override
    public Node notify(ObserverI obIn, String courseIn, String operationIn) {
        return obIn.update(courseIn, operationIn);
    }

    @Override
    public Node update(String courseIn, String operationIn) {
        ArrayList<String> courses = this.getCourse();
        if(operationIn.equals(operations.insert.toString())) {
            if(!courses.contains(courseIn)) {
                courses.add(courseIn);
                this.setCourse(courses);
            }
        } else if(operationIn.equals(operations.delete.toString())) {
            if(courses.contains(courseIn)) {
                courses.remove(courses.indexOf(courseIn));
                this.setCourse(courses);
            }
        }
        return (Node)this;
    }
} 

