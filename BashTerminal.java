/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #5
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

/*
* BashTerminal contain a single main method which allows a user to interact with a file system implemented 
* by an instance of DirectoryTree using the commands.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/

import java.util.Scanner;

public class BashTerminal {
	/*
	 * Runs a program which takes user input and builds a DirectoryTree using the commands indicated above.
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String scan = "";
		System.out.println("Starting bash terminal.");
		DirectoryTree tree = new DirectoryTree();
		tree.DirectoryTreeSetReferences(10);

		try {
			while(scan != "exit") {
				System.out.print("[114457594@Pop]: $ ");
				scan = scanner.nextLine();

				if(scan.equals("pwd")) 
					System.out.println(tree.presentWorkingDirectory());
				if(scan.equals("ls"))
					System.out.println(tree.listDirectory());
				if(scan.equals("ls -R")){
					System.out.println();
					tree.helperPrint();
					System.out.println();
				}
				if(scan.contains("cd ") && !scan.contains("cd /") && !scan.contains("root/") && !scan.contains("cd ..")) {
					String[] temp = scan.split(" ", 2);
					String dir = temp[1];
					tree.changeDirectory(dir);
				}
				if(scan.contains("cd ") && scan.contains("/") && !scan.contains("cd /")) {
					String[] temp = scan.split(" ", 2);
					String dir = temp[1];
						tree.changeDirectoryPath(dir);
				}
				if(scan.equals("cd /"))
					tree.resetCursor();
				if(scan.contains("mkdir")) {
					String[] temp = scan.split(" ", 2);
					String dir = temp[1];
					tree.makeDirectory(dir);
				}
				if(scan.contains("touch ")) {
					String[] temp = scan.split(" ", 2);
					String dir = temp[1];
					tree.makeFile(dir);
				}
				if(scan.contains("find ")) {
					String[] temp = scan.split(" ", 2);
					String dir = temp[1];
					tree.helperFind(dir);
				}
				if(scan.contains("mv ")) {
					String[] temp = scan.split(" ", 3);
					String src = temp[1];
					String dst = temp[2];
					tree.move(src, dst);
				}
				if(scan.equals("cd ..")) {
					tree.returnParent();
				}
				if(scan.equals("exit")) {
					System.out.println("Program terminating normally");
					return;
				}
				if(!(scan.equals("pwd")) && !(scan.equals("ls")) && !(scan.equals("ls -R")) && !(scan.contains("cd ")) 
						&& !(scan.contains("mkdir")) && !(scan.contains("touch")) && !(scan.contains("find ")) 
						&& !(scan.contains("mv ")) && !(scan.equals("exit"))){
					System.out.println("Please enter a correct input.");
				}
			}
			scanner.close();
		} catch(NotADirectoryException e) {
			System.out.println("Can not add a directory to a file.");
		} catch (IllegalArgumentException e) {
			System.out.println("The name is invalid or empty.");
		} catch (FullDirectoryException e) {
			System.out.println("All child references of this directory are occupied.");
		} 
	}
}
