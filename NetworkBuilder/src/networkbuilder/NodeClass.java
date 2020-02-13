package networkbuilder;

public class NodeClass {
	
	public String Name; 
	
	public NodeClass() {
		this.Name="";
	}
	
	public NodeClass(String n) {
		this.Name=n;
	}
	        
        public String toString(){
            return this.Name;
        }
}