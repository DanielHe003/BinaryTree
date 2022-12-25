/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #5
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

/*
* DirectoryTree implements a ternary (3-child) tree of DirectoryNodes. The class contain a 
* reference to the root of the tree, a cursor for the present working directory, and various methods for insertion and deletion.
* The DirectoryTree class provide an implementation for the operations defined for the system. The class contain methods for 
* moving the cursor through the file system, printing the filepath of the present working directory (cursor location), listing 
* the directories and files in the present working directory, printing the entire file system, and finding a file in the 
* file system.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/

public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor;
	private DirectoryNode temp;
	private DirectoryNode tempCur;
	private DirectoryNode[] ref;
	private int depth;
	private int count;
	private DirectoryNode tempCursor = cursor;

	/*
	 * Constructs an DirectoryTree objects with null or 0 fields.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The tree contains a single DirectoryNode named "root", and both cursor and root reference this node.
	 *  <dd> Do not confuse the name of the directory with the name of the reference variable. The DirectoryNode
	 *   member variable of DirectoryTree named root should reference a DirectoryNode who's name is "root", i.e. 
	 *   root.getName().equals("root") is true.
	 * 
	 */
	public DirectoryTree() {
		root = new DirectoryNode("root", false);
		root.setName("root");
		cursor = root;
		temp = root;
	}
	
	/*
	 * Does the same thing as the DirectoryTree() method but uses inputted fields to set the fields.
	 * 
	 * @param root
	 * 		New root for a tree.
	 */
	public DirectoryTree(DirectoryNode root) {
		this.root = root;
		this.root.setName("root");
		cursor = root;
		temp = root;
	}
	
	/*
	 * Constructs an DirectoryTree objects that can take children nodes up to indicated input.
	 * 
	 * @param nodes
	 * 	number of inputs it can accept.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> number of references are now allowed.
	 * 
	 */
	public void DirectoryTreeSetReferences(int nodes) {
		ref = new DirectoryNode[nodes];
	}
	
	/*
	 * Sets the node to a reference.
	 * 
	 * @param i
	 * 	index of a array
	 * @param a
	 * 	node to be indexed and referrenced.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> the node is referenced by the array index
	 * 
	 */
	public void setRef(int i, DirectoryNode a) {
		ref[i] = a;
		a.setParent(cursor);
	}

	/*
	 * Moves the cursor to the root node of the tree.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The cursor now references the root node of the tree.
	 * 
	 */
	public void resetCursor() {
		cursor = root;
	}

	/*
	 * Moves the cursor to the directory with the name indicated by name.
	 * 
	 * @param p
	 * 		New p for the queue
	 * 
	 * <dt> Preconditions:
	 * 	<dd>'name' references a valid directory ('name' cannot reference a file).
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The cursor now references the directory with the name indicated by name. 
	 *      If a child could not be found with that name, then the user is prompted 
	 *      to enter a different directory name. If the name was not a directory, a 
	 *      NotADirectoryException has been thrown
	 * 
	 * @throws NotADirectoryException
	 * 	Thrown if the node with the indicated name is a file, as files cannot be 
	 * 	selected by the cursor, or cannot be found.
	 */

	public void changeDirectory(String name) throws NotADirectoryException{
		try {
			if(cursor.getLeft() == null && cursor.getRight() == null && cursor.getMiddle() == null)
				throw new NoDirectoryException();
			if((cursor.getLeft() != null && !cursor.getLeft().getName().equals(name)) && 
					(cursor.getRight() != null && !cursor.getRight().getName().equals(name)) && 
					(cursor.getMiddle() != null && !cursor.getMiddle().getName().equals(name)))
				throw new NoDirectoryException();
			if((cursor.getLeft() != null && cursor.getLeft().getName().equals(name) && cursor.getLeft().getIsFile()) || 
					(cursor.getRight() != null && cursor.getRight().getName().equals(name) && cursor.getRight().getIsFile()) || 
					(cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name) && cursor.getMiddle().getIsFile()))
				throw new NotADirectoryException();

			if(cursor.getLeft() != null && cursor.getLeft().getName().equals(name)) {
				temp = cursor;
				cursor = cursor.getLeft();
				cursor.setParent(temp);
			}
			if(cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)) {
				temp = cursor;
				cursor = cursor.getMiddle();
				cursor.setParent(temp);
			}
			if(cursor.getRight() != null && cursor.getRight().getName().equals(name)) {
				temp = cursor;
				cursor = cursor.getRight();
				cursor.setParent(temp);
			}
			
			temp = root;
		} catch(NotADirectoryException e) {
			System.out.println("ERROR: Cannot change directory into a file.");
		} catch(NoDirectoryException e) {
			System.out.println("ERROR: No such directory named '" + name + "'.");
		}
	}

	/*
	 * Returns a String containing the path of directory names from the root node of the 
	 * tree to the cursor, with each name separated by a forward slash "/".
	 * e.g. root/home/user/Documents if the cursor is at Documents in the example above.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The cursor remains at the same DirectoryNode.
	 * 
	 */
	public String presentWorkingDirectory() {
		DirectoryNode a = cursor;
		String s = a.getName();
		while(a.getParent() != null) {
			a = a.getParent();
			s = a.getName() + "/" + s;
		}
		return s;
	}
	
	/*
	 * Does the same job as presentWorkingDirectory but just takes a node and prints path.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The cursor remains at the same DirectoryNode.
	 * 
	 */
	public void presentWorkingDirectory(DirectoryNode node) {
		DirectoryNode a = node;
		String s = a.getName();
		while(a.getParent() != null) {
			a = a.getParent();
			s = a.getName() + "/" + s;
		}
		System.out.println(s);
	}

	/*
	 * Returns a String containing a space-separated list of names of all the child directories or files of the cursor.
	 * e.g. dev home bin if the cursor is at root in the example above.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The cursor remains at the same DirectoryNode.
	 * 
	 * @returns 
	 *   A formatted String of DirectoryNode names.
	 */
	public String listDirectory() {
		String s = "";
		if(cursor.getLeft() != null) {
			s += cursor.getLeft().getName();
		}
		if(cursor.getMiddle() != null) {
			s += " " + cursor.getMiddle().getName();
		}
		if(cursor.getRight() != null) {
			s += " " + cursor.getRight().getName();
		}
		return s;
	}

	/*
	 * Helper method to print out the directory tree.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>Directory tree was printed
	 */
	public void helperPrint() {
		tempCur = cursor;
		depth = 0;
		printDirectoryTree();
	}

	/*
	 * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The cursor remains at the same DirectoryNode.
	 */
	public void printDirectoryTree(){
		if(tempCur == null)
			return;

		String s = "";
		String t = "";
		if(tempCur.getIsFile() == true)
			s = "-";
		if(tempCur.getIsFile() == false)
			s = "|-";
		s += tempCur.getName();
		for(int i = 0; i <depth; i++)
			t += "    ";
		t += s;
		System.out.println(t);

		if(tempCur.getLeft() != null) {
			tempCur = tempCur.getLeft();
			depth += 1;
			printDirectoryTree();
			tempCur = tempCur.getParent();
			depth -= 1;
		}
		if(tempCur.getMiddle() != null) {
			tempCur = tempCur.getMiddle();
			depth += 1;
			printDirectoryTree();
			tempCur = tempCur.getParent();
			depth -= 1;
		}
		if(tempCur.getRight() != null) {
			tempCur = tempCur.getRight();
			depth += 1;
			printDirectoryTree();
			tempCur = tempCur.getParent();
			depth -= 1;
		}
	}

	/*
	 * Creates a directory with the indicated name and adds it to the children of the 
	 * cursor node. Remember that children of a node are added in left-to-right order.
	 * 
	 * @param name
	 * 		The name of the directory to add.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
	 * 
	 * <dt> Postconditions:
	 * 	<dd> A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.
	 * 
	 * @throws IllegalArgumentException: 
	 * 		Thrown if the 'name' argument is invalid.
	 * 
	 * @throws FullDirectoryException: 
	 *		Thrown if all child references of this directory are occupied.
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException{
		try {
			if(name.contains(" ") || name.contains("/") || name.isEmpty())
				throw new IllegalArgumentException();
			if(cursor.getLeft() != null && cursor.getRight() != null && cursor.getMiddle() != null)
				throw new FullDirectoryException();
			if(cursor.getIsFile() == true)
				throw new NotADirectoryException();

			DirectoryNode a = new DirectoryNode(name, false);

			if(cursor.getLeft() == null) {
				a.setParent(cursor);
				cursor.setLeft(a);
			}
			else if(cursor.getMiddle() == null) {
				a.setParent(cursor);
				cursor.setMiddle(a);
			}
			else if(cursor.getRight() == null) {
				a.setParent(cursor);
				cursor.setRight(a);
			} else if(ref != null) {
				
			}
		} catch(FullDirectoryException e) {
			System.out.println("ERROR: Present directory is full.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: Name is Invalid");
		} catch (NotADirectoryException e) {
			System.out.println("Can not add a directory to a file");
		}
	}

	/*
	 * Creates a file with the indicated name and adds it to the children of the cursor 
	 * node. Remember that children of a node are added in left-to-right order.
	 * 
	 * @param name
	 * 		The name of the file to add.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
	 * 
	 * <dt> Postconditions:
	 * 	<dd> A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.
	 * 
	 * @throws IllegalArgumentException: 
	 * 		Thrown if the 'name' argument is invalid.
	 * 
	 * @throws FullDirectoryException: 
	 *		Thrown if all child references of this directory are occupied.
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException{
		try {
			if(name.contains(" ") || name.contains("/") || name.isEmpty())
				throw new IllegalArgumentException();
			if(cursor.getLeft() != null && cursor.getRight() != null && cursor.getMiddle() != null)
				throw new FullDirectoryException();
			if(cursor.getIsFile() == true)
				throw new NotADirectoryException();

			DirectoryNode a = new DirectoryNode(name, true);

			if(cursor.getLeft() == null) {
				a.setParent(cursor);
				cursor.setLeft(a);
			}
			else if(cursor.getMiddle() == null) {
				a.setParent(cursor);
				cursor.setMiddle(a);
			}
			else if(cursor.getRight() == null) {
				a.setParent(cursor);
				cursor.setRight(a);
			}
		} catch (NotADirectoryException e) {
			System.out.println("Can not add a directory to a file");
		} catch(FullDirectoryException e) {
			System.out.println("ERROR: Present directory is full.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: Name is Invalid");
		}
	}
	
	/*
	 * Helper method to find a node and prints it, sets tempCur to root.
	 * 
	 * @param name
	 * 		The name of the node to find.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
	 * 	<dd> tempCur was set to root.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> DirectoryNode was found and printed out
	 */
	public void helperFind(String name) {
		tempCur = root;
		count = 0;
		find(name);
		try {
			if(count == 0) {
				throw new NoFileException();
			}
			} catch(NoFileException e) {
				System.out.println("ERROR: No such file exits.");
			}
	}
	
	/*
	 * Method to find a node and prints it.
	 * 
	 * @param name
	 * 		The name of the node to find.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
	 * 
	 * <dt> Postconditions:
	 * 	<dd> DirectoryNode was found and printed out
	 */
	public void find(String name) {
		if(name.equals(tempCur.getName())) {
			count++;
			presentWorkingDirectory(tempCur);
		}
		
		if(tempCur.getLeft() != null) {
			tempCur = tempCur.getLeft();
			find(name);
			tempCur = tempCur.getParent();
		}
		if(tempCur.getMiddle() != null) {
			tempCur = tempCur.getMiddle();
			find(name);
			tempCur = tempCur.getParent();
		}
		if(tempCur.getRight() != null) {
			tempCur = tempCur.getRight();
			find(name);
			tempCur = tempCur.getParent();
		}
	}
	
	/*
	 * Returns the cursor's parent node.
	 * 
	 * 
	 * <dt> Preconditions:
	 * 	<dd> cursor is not at root and is not null.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> returned the parent of the node.
	 * 
	 * @return
	 * 		Parent of the Node.
	 */
	public void returnParent() {
		try {
			if(cursor == root)
				throw new RootException();
			if(cursor != root)
				cursor = cursor.getParent();
		} catch(RootException e) {
			System.out.println("ERROR: Already at root directory.");
		}
	}
	
	/*
	 * Changes the cursor to the path inputted.
	 * 
	 * @param path
	 * 		parameter where cursor is suppose to be.
	 * 
	 * <dt> Preconditions:
	 * 	<dd> path is not null.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> Cursor set to the path inputted
	 * 
	 * @throws NotADirectoryException
	 * 		If the directory is a file it throws this error.
	 */
	public void changeDirectoryPath(String path) throws NotADirectoryException {
		try {
			tempCursor = cursor;
			String[] temp = path.split("/");
			if(temp[0] != null)
				cursor = root;
			for(int i = 0; i<temp.length; i++) {
				changeDirectory(temp[i]);
			}
		} catch(NotADirectoryException e) {
			cursor = tempCursor;
			System.out.println("ERROR: Cannot change directory into a file.");
		}
	}
	
	/*
	 * Changes the cursor to the path inputted.
	 * 
	 * @param src
	 * 		path where the node should be moved from
	 * @param dst
	 * 		path where the node should be moved to
	 * 
	 * <dt> Preconditions:
	 * 	<dd> both paths are not null.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> Nodes were moved from one path to another
	 * 
	 */
	public void move(String src, String dst) {
		String[] source = src.split("/");
		String[] dest = dst.split("/");
		DirectoryNode sr = root;
		DirectoryNode ds = root;
		DirectoryNode temp;
		DirectoryNode remove;
		DirectoryNode destParent;
		
		for(int i = 1; i<source.length; i++) {
			if(sr.getLeft() != null && sr.getLeft().getName().equals(source[i]))
				sr = sr.getLeft();
			else if(sr.getMiddle() != null && sr.getMiddle().getName().equals(source[i]))
				sr = sr.getMiddle();
			else if(sr.getRight() != null && sr.getRight().getName().equals(source[i]))
				sr = sr.getRight();
		}
		for(int i = 1; i<dest.length; i++) {
			if(ds.getLeft() != null && ds.getLeft().getName().equals(dest[i]))
				ds = ds.getLeft();
			else if(ds.getMiddle() != null && ds.getMiddle().getName().equals(dest[i]))
				ds = ds.getMiddle();
			else if(ds.getRight() != null && ds.getRight().getName().equals(dest[i]))
				ds = ds.getRight();
		}
		
		temp = sr.getParent();
		remove = sr;
		destParent = ds;
		
		if(temp.getRight().getName().equals(remove.getName()))
			temp.setRight(null);
		if(temp.getMiddle().getName().equals(remove.getName()))
			temp.setMiddle(null);
		if(temp.getLeft().getName().equals(remove.getName()))
			temp.setLeft(null);
		
		if(destParent.getLeft() == null || destParent.getRight() == null || destParent.getMiddle() == null)
			remove.setParent(destParent);
		
		if(destParent.getLeft() == null) {
			destParent.setLeft(remove);
		}
		else if(destParent.getMiddle() == null) {
			destParent.setMiddle(remove);
		}
		else if(destParent.getRight() == null) {
			destParent.setRight(remove);
		}
	}
}

