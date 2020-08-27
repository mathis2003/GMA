import java.util.ArrayList;

public class Calculator {
	
	//calculate function variables
	int amountOfSums = 0;
	int numberLength;
	String codeContainer = "";
	String code = "";
	String startMatrix = "";	
	
	//codeSummation function variables
	boolean executeCalculation = true;
	String sum = "";
	String c;

	
	public String calculate(ArrayList<String> numbers, boolean showCalculations) { 
		
	
		numberLength = numbers.get(0).length();
		
		
		// for a matrix with rows of 5 numbers this would give "11111"
		String highestNumber = "";
		// for a matrix with rows of 5 numbers this would give "00000"
		String lowestNumber = "";
		for (int s = 0; s < numbers.size(); s++) {
			highestNumber += "1"; 
			lowestNumber += "0";
		} 
			 
		//for rows containing 3 characters this would give "000"
		for (int t = 0; t < numberLength; t++) startMatrix += "0";
		codeContainer = startMatrix;
		
		
		//loops through all possible combinations of sums of the rows
		for (String i = lowestNumber; isBigger(i, highestNumber) != 1; i = binarySummation(i, "1", numbers.size())) {
			
			
			ArrayList<String> calculatedNumbers = new ArrayList<String>();// only used when showCalculations
			
			//loops through all possible rows, info of whether they should be used is done by looping through the characters of i
			int j = 0;
			while(j < numbers.size()) {
				if (i.charAt(j) == '1') { 
					sum = "";//sum has to reset because otherwise it will keep the old numbers as well
					codeContainer = codeSummation(codeContainer, numbers.get(j));
					try {
						calculatedNumbers.add(numbers.get(j));
					} catch (Exception e) {}
					
				}
				
				j++;
			}
			
			if (showCalculations) {
				try {
					int k = 0;
					while ((k+1) < calculatedNumbers.size()) {
						code += calculatedNumbers.get(k);
						code += " + ";
						k++;
					}
					code += calculatedNumbers.get(k);
					code += " = ";
				} catch (Exception e) {}
				
			}
			try {
				calculatedNumbers.clear();
			} catch (Exception e) {}
			
			
			if (j > 0) {
				//adds the one of the possible summation results to the codes
				code += codeContainer;
				//separates the codes from each other
				code += "|";
			}
			
			//resets the codeContainer 
			codeContainer = startMatrix;
			
		}
		//resets startMatrix
		startMatrix = "";
		
		return code;
		
	}
	
	
	//makes the sum of the two linear codes
	public String codeSummation(String firstNumber, String nextNumber) {
		
		//checks if the two given linear codes have the same length
		if (firstNumber.length() == nextNumber.length()) executeCalculation = true;
		else {
			//lengths are different. The linear codes should have an equal amount of characters
			executeCalculation = false;
		}
		
		//makes the sum of the two linear codes
		for (int i = 0; i < firstNumber.length() && executeCalculation == true; i++){
		    char a = firstNumber.charAt(i); 
		    char b = nextNumber.charAt(i);
		    if (a == '1' || a == '0') {
		    	if (b == '1' || b == '0') {
		    		if (a == b) c = "0";
				    else c = "1";
			
				    sum += c;
				    
		    	}else {
		    		//b was a character different from 0 or 1
		    		executeCalculation = false;
		    	}
		    		
		    } else {
		    	//a was a character different from 0 or 1
		    	executeCalculation = false;
		    	
		    }
		}
		return sum;
		
	}
	
	
	
	
	//this will always return a string containing a binary number with as much digits as there are rows.
	public String binarySummation(String numberOne, String numberTwo, int length) {
			
		int i = Integer.parseInt(numberOne, 2);
		int j = Integer.parseInt(numberTwo, 2);
		
		int binarySum = i + j;
		String result = Integer.toBinaryString(binarySum);
			
		//makes sure the string will always have the length based on how many rows are in the matrix.
		while (result.length() < length) {
			result = "0" + result;
		}
			
		return result;
	}
		
		
	//0 means they're equal, 1 means the first one is bigger and 2 means the second one is bigger
	public int isBigger(String firstNumber, String secondNumber) {
			
		int i = Integer.parseInt(firstNumber, 2);
		int j = Integer.parseInt(secondNumber, 2);
			
		if (i>j) return 1;
		else if (i<j) return 2;
		else return 0;
			
	}

	
}
