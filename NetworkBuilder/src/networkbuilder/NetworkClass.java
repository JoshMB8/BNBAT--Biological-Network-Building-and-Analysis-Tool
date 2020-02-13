package networkbuilder;

import java.io.*;
import java.util.*;


public class NetworkClass {

    public ArrayList<NodeClass> nodeList;
    public ArrayList<EdgeClass> edgeList;
    
    //Default Network Constructor.
    
    public NetworkClass(){
        this.nodeList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
    }
    // Creation of a list of nodes.
    public void addtoNodeList(String Name){
        boolean ifFalse = false;
        for(NodeClass node : nodeList){
            if (node.toString().equals(Name)){
                ifFalse = true;     //this identifies a duplicate node.
            }
        }
        if (ifFalse == false){
            nodeList.add(new NodeClass(Name)); //If the node is not already in the list then add as a new node.
        }
    }
    //Creation of a list of edges.
    public void addtoEdgeList(String Name1, String Name2){
        boolean ifFalse = false;
        for(EdgeClass edge : edgeList){
            if((edge.node1.toString() + edge.node2.toString()).equals(Name1 + Name2) || (edge.node1.toString() + edge.node2.toString()).equals(Name2 + Name1)){
                ifFalse = true;     //this identifies a duplicate edge.
            }
        }
        NodeClass node1 = null;
        NodeClass node2 = null;
        
        if (ifFalse == false){
            for(NodeClass node : nodeList){
                if (node.toString().equals(Name1)){
                    node1 = node;
                }
                if(node.toString().equals(Name2)){
                    node2 = node;
                }
            }
          edgeList.add(new EdgeClass(node1, node2)); //adds a new edge to the edge list. 
        }
    }
    
    //Below allows for an edge-list file to be read in in order to create a network from it.
    public void readFile(String fileName){
        FileReader in = null;
        BufferedReader newIn = null;
        
        try{
            in = new FileReader(fileName);
            newIn = new BufferedReader(in);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        
        try{
            String line;
            while ((line = newIn.readLine()) != null) {
                String[] elements = line.split("\t");    //splits lines base on tab.
                
                addtoNodeList(elements[0]);
                addtoNodeList(elements[1]);
                addtoEdgeList(elements[0], elements[1]);
            }
            
            if (newIn != null)
                newIn.close();
            
            if (in != null)
                in.close();
        }
        catch (IOException ex){
        }
    }
// Below will get the degree for specified node.
    public int nodeDegree(String nodeName){
        int n = 0;
        for(EdgeClass edge : edgeList){
            if (edge.node1.toString().contains(nodeName)){
                n++;
            }
            if (edge.node2.toString().contains(nodeName)){
                n++;
            }
        }
        return n;
    }
    
    //Below will find the average degree for all nodes by iterating through the edgeList using all nodes in the nodeList. 
    public String avgDeg(){
        int i = 0;
        for(NodeClass node : nodeList){
            i = i + this.nodeDegree(node.toString());
        }
        double j = nodeList.size();
        double k = i/j;
        return Double.toString(k);
    }
    
    //Below finds the node with the highest degree and prints the result.
    public String degreeHub(){
        int i = 0;
        String string = "";
        String hub = "";
        for(NodeClass node : nodeList){
            if(this.nodeDegree(node.toString()) > i){
                i = this.nodeDegree(node.toString());
                string = node.toString();
            }
            hub = "The highest degree is: " + i + "\nwith node(s): " + string;
        }
        return hub;
    }
    //Below will return the degree distribution. A Map of Degrees and their frequencies is created which is then returned.
    public Map degreeDist(){
        HashMap <Integer, Integer> table = new HashMap<>();
        ArrayList<Integer> degreeList = new ArrayList<>();
        for (NodeClass node : this.nodeList){
            degreeList.add(this.nodeDegree(node.toString()));
        }        
        int highDeg = 0;
        for(NodeClass node : this.nodeList){
            if(this.nodeDegree(node.toString()) > highDeg){
                highDeg = this.nodeDegree(node.toString());
            }
        }
        int[] freqArray = new int[highDeg + 1];
        for(int i = 0; i < this.nodeList.size(); i++){
            freqArray[degreeList.get(i)] = freqArray[degreeList.get(i)] + 1;
        }
        int j = 0;
        for(int i = 0; i < highDeg + 1; i++){
            table.put(j, freqArray[i]);
            j++;
        }
        return table;
    }
}