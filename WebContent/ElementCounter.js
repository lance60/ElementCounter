const EVEN_MODIFIER = "e";
const ODD_MODIFIER = "o";
const NO_MODIFIER = "n";

// Made my own exception. Kinda pointless for now
function InputMismatchException(message)
{
	const error = new Error(message);
	return error;
}

class Range
{
	constructor(min, max, modifier)
	{
		this.min = min;
		this.max = max;
		
		if(typeof modifier !== 'undefined')
		{
			this.modifier = modifier;
		}
		else
		{
			this.modifier = NO_MODIFIER;
		}
		
		if(this.modifier == NO_MODIFIER)
		{
			this.step = 1;
		}
		else
		{
			this.step = 2;
		}
	}
	
	Count(min, max, modifier)
	{
		var count = 0;
		
		if((modifier === EVEN_MODIFIER && min % 2 != 0) || (modifier === ODD_MODIFIER && min % 2 == 0))
		{
			min++;
		}
		
		for(i = min; i <= max; i+= this.step)
		{
			count++;
		}
		return count;
	}
	
	CountAuto()
	{
		return this.Count(this.min, this.max, this.modifier);
	}
}

function isValidModifier(modifier)
{
	return modifier === EVEN_MODIFIER || modifier === ODD_MODIFIER || modifier === NO_MODIFIER;
}

// Honestly could be using JQuery here, but it feels too overkill
function isValidInput(input)
{
	// Explicitly treating input as array because input[i] is apparently unsupported for some browsers
	var inputChars = input.split("");
	for(i = 0; i < inputChars.length; i++)
	{
		if(isNaN(parseInt(inputChars[i])))
		{
			return false;
		}
	}
	return true;
}

function parseRange(input)
{
	var delimitedInput = input.split("-");
	
	if(delimitedInput.length > 2)
	{
		throw InputMismatchException("Unexpected delimiters found");
	}
	else if(delimitedInput.length > 1)
	{
		var delimitedModifier = delimitedInput[1].split("_");
		var minStr = delimitedInput[0];
		var maxStr = delimitedModifier[0];
		
		if(!isValidInput(minStr) || !isValidInput(maxStr))
		{
			throw InputMismatchException("Unexpected characters found");
		}
		
		if(delimitedModifier.length > 1)
		{
			if(!isValidModifier(delimitedModifier[1]))
			{
				throw InputMismatchException("Unexpected modifier found")
			}
		}
		
		return new Range(parseInt(minStr), parseInt(maxStr), delimitedModifier[1]);
	}
	else
	{
		throw InputMismatchException("Range not defined")
	}
}

function countInput(input)
{
	var delimitedInput = input.split(",");
	var count = 0;
	
	for(i = 0; i < delimitedInput.length; i++)
	{
		if(delimitedInput[i].indexOf("-") != -1)
		{
			var range = parseRange(delimitedInput[i]);
			count += range.CountAuto();
		}
		else
		{
			count++;
		}
	}
	
	return count;
}

