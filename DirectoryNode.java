/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #5
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

/*
* DirectoryNode which represents a node in the file tree. The DirectoryNode 
* class contain 3 DirectoryNode references, left, middle, and right. In addition,
* the class contain a String member variable name, which indicates the name of the 
* node in the tree.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/
public class DirectoryNode {
	private String name = "";
	private boolean isFile;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private DirectoryNode parent;

	/*
	 * Constructs an Router objects with null or 0 fields.
	 * 
	 * @param name
	 * 		Name of the node.
	 * @param name
	 *  	True if the node is a file, false if not.
	 *  
	 * <dt> Postconditions:
	 * 	<dd> Node was constructed.
	 * 
	 */
	public DirectoryNode(String name, boolean isFile) {
		this.name = name;
		this.isFile = isFile;
	}

	/*
	 * Sets the name of the DirectoryNode.
	 * 
	 * @param name
	 * 		New name for the DirectoryNode
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The DirectoryNode object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new DirectoryNode now has a new name field with the parameter name.
	 * 
	 * @throws Exception
	 * 		Throws a exception when the name is null or has a space or a slash
	 * 
	 */
	public void setName(String name) {
		try {
		if(name == null || name.contains(" ") || name.contains("/"))
			throw new Exception();
		this.name = name;
		} catch(Exception e) {
			System.out.println("Name is null");
		}
	}
	
	/*
	 * returns the String name
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This DirectoryNode object has been instantiated.
	 * 
	 * @return
	 * 	The DirectoryNode name
	 * 
	 */
	public String getName() {
		return name;
	}

	/*
	 * returns the DirectoryNode left reference
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This DirectoryNode object has been instantiated.
	 * 
	 * @return
	 * 	The DirectoryNode left reference.
	 * 
	 */
	public DirectoryNode getLeft() {
		return left;
	}

	/*
	 * returns the DirectoryNode Right reference
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This DirectoryNode object has been instantiated.
	 * 
	 * @return
	 * 	The DirectoryNode Right reference.
	 * 
	 */
	public DirectoryNode getRight() {
		return right;
	}

	/*
	 * returns the DirectoryNode Middle reference
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This DirectoryNode object has been instantiated.
	 * 
	 * @return
	 * 	The DirectoryNode Middle reference.
	 * 
	 */
	public DirectoryNode getMiddle() {
		return middle;
	}

	/*
	 * Sets the left reference of the DirectoryNode.
	 * 
	 * @param node 
	 * 		New node for the DirectoryNode
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The DirectoryNode object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new DirectoryNode now has a new DirectoryNode left reference.
	 * 
	 */
	public void setLeft(DirectoryNode node) {
		this.left = node;
	}

	/*
	 * Sets the right reference of the DirectoryNode.
	 * 
	 * @param node 
	 * 		New node for the DirectoryNode
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The DirectoryNode object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new DirectoryNode now has a new DirectoryNode right reference.
	 * 
	 */
	public void setRight(DirectoryNode node) {
		this.right = node;
	}

	/*
	 * Sets the middle reference of the DirectoryNode.
	 * 
	 * @param node 
	 * 		New node for the DirectoryNode
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The DirectoryNode object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new DirectoryNode now has a new DirectoryNode middle reference.
	 * 
	 */
	public void setMiddle(DirectoryNode node) {
		this.middle = node;
	}

	/*
	 * Sets the parent reference of the DirectoryNode.
	 * 
	 * @param parent 
	 * 		New parent for the DirectoryNode
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The DirectoryNode object was created.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new DirectoryNode now has a new DirectoryNode parent reference.
	 * 
	 */
	public void setParent(DirectoryNode parent){
		this.parent = parent;
	}

	/*
	 * returns the DirectoryNode parent reference
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This DirectoryNode object has been instantiated.
	 * 
	 * @return
	 * 	The DirectoryNode parent reference.
	 * 
	 */
	public DirectoryNode getParent() {
		return parent;
	}

	/*
	 * Returns true if the Node is a file.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This DirectoryNode object has been instantiated.
	 * 
	 * @return
	 *  True if it is a file.
	 * 
	 */
	public boolean getIsFile() {
		return isFile;
	}

	/*
	 * Adds a child to the node.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> The DirectoryNode newChild object has been instantiated.
	 * 
	 * @throws FullDirectoryException
	 * 		All directories are full.
	 * @throws NotADirectoryException
	 * 		The node is a file.
	 */
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
		if(newChild == null)
			throw new NullPointerException();
		if(left != null && middle != null && right != null)
			throw new FullDirectoryException();
		if(newChild.getIsFile())
			throw new NotADirectoryException();
	}
}
