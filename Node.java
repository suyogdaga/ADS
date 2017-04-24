class Node
{
	int value;
	int frequency;
	Node left;
	Node right;
	boolean isLeaf;
	
	public Node(){
		value = -1;
		frequency = -1;
		left = null;
		right = null;
		isLeaf = false;
	}

public Node (int value,int frequency)
{
	this.value= value;
	this.frequency= frequency;
	this.left = null;
	this.right = null;
	this.isLeaf=false;
}
public Node (Node A, Node B)
{
	this.value =-1;
	this.frequency = A.frequency + B.frequency;
	this.left = A;
	this.right = B;
}
}

class TreeNode
{
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode()
	{
		this.value = -1;
		this.left = null;
		this.right = null;
	}
}

