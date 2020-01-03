/*
 * A graph processing program.
 *
 * A project work for course Data Structures
 */

import java.io.*;
import java.util.*;
import java.lang.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Float;

public class T2018 {

    private Node newNode;
    private String line;
    private float x[];
    private float y[];
    public Node[] nodes;

    private void readInput() {
        nodes = new Node[400];
        x = new float[400];
        y = new float[400];
        try {
            BufferedReader br = new BufferedReader(new FileReader("Tdata.txt"));

            for (int i = 0; i < 400; i++) {
                line = br.readLine();
                String[] values = line.split(",");
                x[i] = Float.parseFloat(values[0]);
                y[i] = Float.parseFloat(values[1]);

                newNode = new Node(x[i], y[i], null, false);

                nodes[i] = newNode;
            }

        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    private void writeOutput() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            for (int i = 0; i < 10; i++) {
                bw.write(Float.toString(x[i]));
                bw.write(",");
                bw.write(Float.toString(y[i]));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Tiedosto kirjoitettu");
    }

    public static void main(String[] args) throws IOException {
        T2018 ht = new T2018();
        ht.readInput();
        ht.writeOutput();

        ht.defineNeighbors();

        ht.BFS("BFS.txt");

        ht.DFS("DFS.txt");
    }

    private static double calculateDistance(Node j, Node k) {
        double a = ((j.x() - k.x()) * (j.x() - k.x())) + ((j.y() - k.y()) * (j.y() - k.y()));
        double distance = Math.sqrt(a);
        return distance;
    }


    private void defineNeighbors() {
        for (int n = 0; n < nodes.length; n++) {
            Node[] tempNeighbors = new Node[2];

            Node currNode = nodes[n];

            double firstDistance = Integer.MAX_VALUE;
            double secondDistance = Integer.MAX_VALUE;

            for (int i = 0; i < nodes.length; i++) {

                if (i == n) {
                    i++;
                }

                if (i >= nodes.length) {
                    break;
                }
                double distance = calculateDistance(currNode, nodes[i]);
                for (int k = 0; k < 2; k++) {
                    if (distance < firstDistance) {
                        secondDistance = firstDistance;
                        tempNeighbors[1] = tempNeighbors[0];
                        firstDistance = distance;
                        tempNeighbors[0] = nodes[i];
                        if (tempNeighbors.length > 2) {
                            Node[] temp = new Node[2];
                            temp[0] = tempNeighbors[0];
                            temp[1] = tempNeighbors[1];
                            tempNeighbors = temp;
                        }
                    }
                    currNode.Neighbors(tempNeighbors);
                }
            }
        }
    }

    private void BFS(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (Node node : nodes) {
                if (node.visited() == false) {

                    LinkedList<Node> nodeQueue = new LinkedList<Node>();
                    node.visited(true);
                    nodeQueue.add(node);

                    while (!nodeQueue.isEmpty()) {
                        Node currNode = nodeQueue.poll();
                        Node neighbors[] = currNode.Neighbors();
                        for (Node temp : neighbors) {
                            if (temp != null && temp.visited() == false) {
                                temp.visited(true);
                                nodeQueue.add(temp);
                            }
                        }
                        bw.write(currNode.toString());
                        bw.newLine();
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Tiedosto " + fileName + " kirjoitettu");
    }

    private void DFS(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (Node newNode : nodes) {
                Stack<Node> nodeStack = new Stack<Node>();
                nodeStack.add(newNode);
                newNode.visited(true);
                while (nodeStack.isEmpty() == false) {
                    Node currNode = nodeStack.pop();
                    Node[] neighbors = currNode.Neighbors();
                    for (Node temp : neighbors) {
                        if (temp != null && temp.visited() == false) {
                            temp.visited(true);
                            nodeStack.add(temp);
                        }
                    }
                    bw.write(currNode.toString());
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Tiedosto " + fileName + " kirjoitettu");
    }
}
