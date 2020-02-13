/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkbuilder;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {
    NetworkClass network = new NetworkClass(); //Initialize the Network
    
    // Below are Text fields, which link to the gui, the user can type into which are then used in the buttons.
    @FXML TextField nodeNameGetText;
    @FXML TextField edge1NameGetText;
    @FXML TextField edge2NameGetText;
    @FXML TextField findDegreeGetText;
    @FXML TextField degreeOfNode;
    @FXML TextField averageDeg;
    
    // Below are Text areas, which link to the gui, that will show print outs.
    @FXML TextArea listOfNodes;
    @FXML TextArea listOfEdges;
    @FXML TextArea degHub;
    @FXML TextArea distribution;
    
    // Below is a series of button actions which are linked to their respective buttons via JavaFX Scene Builder. These tell the button what to do once pressed.
    @FXML
    private void createNetworkButtonAction(ActionEvent event) {
        listOfNodes.clear(); //clears the text area before printing out
        listOfEdges.clear(); //clears the text area before printing out
        degreeOfNode.clear(); //clears the text area before printing out
        averageDeg.clear(); //clears the text area before printing out
        degHub.clear(); //clears the text area before printing out
        distribution.clear(); //clears the text area before printing out
        String userFile = ""; // Create an empty string for the function to use.
        FileChooser fileChooser = new FileChooser(); // Opens up a new file chooser for the ui.
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){ 
            userFile = selectedFile.getAbsolutePath(); // If a file is selected this will get the patch and make it a string.
            network.readFile(userFile); // Function will then take this path and use the file to construct a network.
        }
        listOfNodes.appendText("Node List:");
        for(NodeClass node : network.nodeList){
            listOfNodes.appendText("\n"+node.toString()); //prints node list to text area
        }
        listOfEdges.appendText("Edge List:");
        for(EdgeClass edge : network.edgeList){
            listOfEdges.appendText("\n"+edge.toString()); //prints edge list to text area
        }
    }   
    @FXML
    private void addNodeButtonAction(ActionEvent event) {        
        listOfNodes.clear();
        String nodeName = nodeNameGetText.getText(); // Take the user input of the text field and makes it a string to be used by the function
        network.addtoNodeList(nodeName);
        nodeNameGetText.clear(); //clears the text field
        listOfNodes.appendText("Node List:");
        for(NodeClass node : network.nodeList){
            listOfNodes.appendText("\n"+node.toString()); //prints node list to text area
        }
    }
    @FXML
    private void addEdgeButtonAction(ActionEvent event) {
        listOfEdges.clear();
        String edge1 = edge1NameGetText.getText(); // Take the user input of the text field and makes it a string to be used by the function
        String edge2 = edge2NameGetText.getText(); // Take the user input of the text field and makes it a string to be used by the function
        network.addtoEdgeList(edge1, edge2);
        edge1NameGetText.clear(); //clears the text field
        edge2NameGetText.clear(); //clears the text field
        listOfEdges.appendText("Edge List:");
        for(EdgeClass edge : network.edgeList){
            listOfEdges.appendText("\n"+edge.toString()); //prints edge list to text area
        }
    }
    @FXML
    private void findDegreeButtonAction(ActionEvent event) {
        degreeOfNode.clear(); //clears the text area before printing out
        String findDegree = findDegreeGetText.getText(); // Take the user input of the text field and makes it a string to be used by the function
        degreeOfNode.appendText(findDegree + " has a degree of: " + network.nodeDegree(findDegree));
        findDegreeGetText.clear(); //clears the text field
    }
    @FXML
    private void averageDegreeAction(ActionEvent event) {
        averageDeg.clear(); //clears the text area before printing out
        averageDeg.appendText(network.avgDeg()); //prints output to text area
    }
    @FXML
    private void findHubButtonAction(ActionEvent event) {
        degHub.clear(); //clears the text area before printing out
        degHub.appendText(network.degreeHub()); //prints output to text area
    }
    @FXML
    private void degreeDistributionButtonAction(ActionEvent event) {
        distribution.clear(); //clears the text area before printing out
        distribution.appendText("Degree\tFrequency"); //prints the titles of each column.
        for( Object freq : network.degreeDist().keySet()){
            distribution.appendText("\n" + freq + "\t" + network.degreeDist().get(freq)); //iterates through the map, and prints the key then the frequency for that degree.
        }
    }
    @FXML
    private void exportTableButtonAction(ActionEvent event) {
        FileChooser saveFileChooser = new FileChooser(); // Opens up a new file chooser for the ui.
        //Set an extension filter so only txt files can be written.
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        saveFileChooser.getExtensionFilters().add(extFilter);
        //open the save file dialog
        File saveFile = saveFileChooser.showSaveDialog(null);
        if(saveFile != null){ //if a file name is entered this will save the file.
            SaveFile(distribution.getText(), saveFile); 
        }
    }
    //Function for writing file.
    private void SaveFile(String content, File file){
        try{
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        }
        catch (IOException ex){}
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
