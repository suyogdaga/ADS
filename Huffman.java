import java.util.HashMap;

class HuffmanTree
{
	Node root;
	static HashMap<Integer,String> hashy;
	
	public HuffmanTree(Node x)
	{
		hashy= new HashMap<>();
		this.root = x;
	}
	
	 void printPreorder(Node node,String s)
	    {
	        if (node == null)
	            return;
	        if(node.isLeaf == true){
	        	hashy.put(node.value, s);
	        }
	        printPreorder(node.left,s+"0");
	        printPreorder(node.right,s+"1");
	    }
 
	 
}