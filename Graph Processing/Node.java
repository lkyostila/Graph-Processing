/*
 * A graph processing program.
 *
 * A project work for course Data Structures
 *
 * Node-class. 
 */
public class Node {

    /*
    * Attributes.
     */
    private float x;
    private float y;
    private Node[] Neighbors;
    private boolean visited;

    /*
    * Accessors
     */
    public float x() {
        return x;
    }
    public void x(float n) {
        x = n;
    }
    public float y() {
        return y;
    }
    public void y(float n) {
        y = n;
    }
    public Node[] Neighbors() {
        return Neighbors;
    }
    public void Neighbors(Node[] n) {
        Neighbors = n;
    }
    public boolean visited() {
        return visited;
    }
    public void visited(boolean n) {
        visited = n;
    }

    /* Constructor.
     */
    public Node(float n, float m, Node[] j, boolean k) {
        x(n);
        y(m);
        Neighbors(j);
        visited(k);
    }

   @Override
   public String toString() {
      return x + " , " + y;
   }
}

