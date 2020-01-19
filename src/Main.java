import java.util.Scanner;

public class Main {
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter set of numbers, separated by comma. Range can be defined as a-b_x, where x can be 'e' for even and 'o' for odd: ");
		String userInput = sc.next().replaceAll("\\s", "");
		sc.close();
		
		System.out.println(countInput(userInput));
	}
	
	public static int countInput(String input)
	{
		String[] delimitedInput = input.split(",");
		int count = 0;
		
		for(int i = 0; i < delimitedInput.length; i++)
		{
			if(delimitedInput[i].indexOf("-") != -1)
			{
				Range range = new Range(delimitedInput[i]);
				count += range.getRangeCount();
			}
			else
			{
				count++;
			}
		}
		
		return count;
	}
	
}
