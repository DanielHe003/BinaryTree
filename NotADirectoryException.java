/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #5
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

/*
* Throws a exception when there trying to change into a directory for a file. It extends Exception.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/
public class NotADirectoryException extends Exception {
	
	/*
	 * Constructs a NotADirectory Exception
	 */
	
	public NotADirectoryException() {
	}

	/*
	 * Constructs a NotADirectory Exception with a String input
	 * 
	 * @param String
	 * 		A string that is used to construct the exception.
	 */
	public NotADirectoryException(String string) {
	}
}
