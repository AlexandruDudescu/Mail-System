/**
 * Test Class to initialize and test the GUI. 
 * @author Kevin Wang
 *
 */
class ProjectTester 
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		//Controller class initialization
		AccountManager accountManager = new AccountManager();
		EmailManager emailManager = new EmailManager();
		
		//GUI initialization
		GUI gui = new GUI();
	}
}