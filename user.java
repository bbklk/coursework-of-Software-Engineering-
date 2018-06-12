import java.util.*;

public class user {
	
	public static void main(String[] args)
	{
		user u = new user();
		String cho;
		
		System.out.println("Please enter your username:");
		Scanner uN = new Scanner(System.in);
		String userNameN = uN.nextLine();
		
		System.out.println("Please enter your passboard:");
		Scanner pB = new Scanner(System.in);
		String passBoard = pB.nextLine();
		
		System.out.print("1.Electricity. \n2.Gas.\nPlease enter your choice:\n");
		Scanner c = new Scanner(System.in);
		String choice = c.nextLine();
		
		do
		{
			if ( choice.equals("1"))
				u.electricity();
			else
				u.gas();
			
			System.out.print("1.Electricity. \n2.Gas.\n3.Exit.\nPlease enter your choice:\n");
			Scanner c1 = new Scanner(System.in);
			cho = c1.nextLine();
			
		}while(!cho.equals("3"));
	}
	
	public void electricity()
	{
		Random rand = new Random();
		int usage = rand.nextInt(100);
		double cost = usage * 0.5;
		System.out.print("Your live electricity usage is : " +usage +"\nYour live electricity cost is : " +cost +"\n");
	}
	
	public void gas()
	{
		Random rand = new Random();
		int usage = rand.nextInt(100);
		double cost = usage * 1.3;
		System.out.print("Your live gas usage is : " +usage +"\nYour live gas cost is : " +cost +"\n");
	}

}
