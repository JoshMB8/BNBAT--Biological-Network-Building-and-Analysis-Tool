package networkbuilder;

public class EdgeClass {
	
	NodeClass node1;
	NodeClass node2;
	
	public EdgeClass(NodeClass a, NodeClass b) {
		this.node1 = a;
		this.node2 = b;
	}
               
        @Override
        public String toString(){
            return  node1 + " - " + node2;
        }
}