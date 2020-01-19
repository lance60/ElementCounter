import java.util.InputMismatchException;

public class Range {
	public static String EVEN_MODIFIER = "e";
	public static String ODD_MODIFIER = "o";
	public static String NO_MODIFIER = "n";
	
	protected int min;
	protected int max;
	protected String modifier = NO_MODIFIER;
	
	public Range(int min, int max)
	{
		this.min = min;
		this.max = max;
	}
	
	public Range(int min, int max, String modifier)
	{
		this(min,max);
		
		if(!isValidModifier(modifier))
		{
			throw new InputMismatchException("Unexpected modifier found");
		}
		else
		{
			this.modifier = modifier;
		}
	}
	
	public Range(String input)
	{
		parseRange(input);
	}
	
	public void parseRange(String input)
	{
		String[] delimitedInput = input.split("-");
		if(delimitedInput.length > 2)
		{
			throw new InputMismatchException("Multiple delimiters found");
		}
		else if(delimitedInput.length > 1)
		{
			String[] delimitedModifier = delimitedInput[1].split("_");
			String minStr = delimitedInput[0];
			String maxStr = delimitedModifier[0];
			
			if(!isValidInput(minStr) || !isValidInput(maxStr))
			{
				throw new InputMismatchException("Unexpected characters found");
			}
			
			if(delimitedModifier.length > 1)
			{
				if(!isValidModifier(delimitedModifier[1]))
				{
					throw new InputMismatchException("Unexpected modifier found");
				}
				else
				{
					this.modifier = delimitedModifier[1];
				}
			}
			
			this.min = Integer.parseInt(minStr);
			this.max = Integer.parseInt(maxStr);
			
		}
		else
		{
			throw new InputMismatchException("Range not defined");
		}
	}
	
	public boolean isValidInput(String input)
	{
		for(int i = 0; i < input.length(); i++)
		{
			if(!Character.isDigit(input.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidModifier(String modifier)
	{
		return modifier.equals(EVEN_MODIFIER) || modifier.equals(ODD_MODIFIER) || modifier.equals(NO_MODIFIER);
	}
	
	private int getRangeCount(int min, int max, int step)
	{
		int count = 0;
		
		for(int i = min; i <= max; i += step)
		{
			count++;
		}
		
		return count;
	}
	
	private int getRangeCount(int min, int max, String modifier)
	{
		if((modifier.equals(EVEN_MODIFIER) && min % 2 != 0) || (modifier.equals(ODD_MODIFIER) && min % 2 == 0))
		{
			min++;
		}
		return getRangeCount(min, max, 2);
	}
	
	private int getRangeCount(int min, int max)
	{
		return getRangeCount(min,max,1);
	}
	
	public int getRangeCount()
	{
		if(modifier == NO_MODIFIER)
		{
			return getRangeCount(min,max);
		}
		else
		{
			return getRangeCount(min,max,modifier);
		}
	}

}
