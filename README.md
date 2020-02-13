# BNBAT--Biological-Network-Building-and-Analysis-Tool
Java GUI tool that takes a protein-protein interaction edge list and presents information on nodes and edges.

Executable .jar file can be found in NetworkBuilder -> dist 

Creation of a network can be achieved through this application by either manually entering ‘nodes’ into a list of nodes, one at a time, and then using the nodes from this list and manually creating ‘edges’ between two nodes. Alternatively a pre-existing edge list text file can be read into the programme and a will be network constructed; this is achieved through a script written in Java which will iterate through the provided file and add nodes to the node list and then recreate the edge list. Incorporated in the code is the ability to recognise a duplicate edge, if a duplicate edge is found it will not be added to the list; a similar function is also applied to the node list so that a node will not appear twice. Once a network has been constructed by the programme analysis can be performed on the data; the programme is able to retrieve information on node degrees, such as finding the degree of a specified node, finding the average degree for the network, finding the hubs of the network, and the degree distribution.
