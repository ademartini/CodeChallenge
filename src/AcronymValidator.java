import java.util.List;


public class AcronymValidator {

	/**
	 * Validates acronyms.  Assume that params are not null and the list does not
	 * contain null values.
	 * @param acronym The string acronym to try
	 * @param productName The product name tokenized into a list
	 * @return If acronym is valid
	 */
	public static boolean Validate(String acronym, List<String> productName){
		
		return Validate(acronym,productName,0,0,0);
	}
	
	/**
	 * Recursive helper method for public Validate
	 * @param acronym The string acronym to try
	 * @param productName The product name tokenized into a list
	 * @param acronymIndex The index of the current letter in the acronym
	 * @param wordIndex The index of the current word
	 * @param letterIndex The letter index within the current word
	 * @return If acronym is valid from the given starting indices
	 */
	private static boolean Validate(String acronym, 
									List<String> productName, 
									int acronymIndex, 
									int wordIndex, 
									int letterIndex){
		
		//We have reached the end of the acronym
		if(acronymIndex >= acronym.length()){
			
			//We need to have satisfied all words otherwise this is a failure path
			return (wordIndex >= productName.size()) ||
					((wordIndex == productName.size() - 1) && (letterIndex > 0));
		}
		
		//We have not satisfied all acronym letters, but have run out of words
		if(wordIndex == productName.size()){
			
			return false;
		}
		
		//In this function call we are working with the following acronym letter
		char currentChar = acronym.toLowerCase().charAt(acronymIndex);
		
		String currentWord = productName.get(wordIndex).toLowerCase();
		
		int index = currentWord.indexOf(currentChar,letterIndex);
		
		//Iterate over all matches in the current word until a successful
		//recursive path is found or we don't find a successful path
		while(index >= 0){
		
			boolean success = Validate(acronym,productName,acronymIndex + 1,wordIndex,index + 1);
			
			if(success){
				
				return true;
			}
			
			index = currentWord.indexOf(currentChar, index + 1);
		}
		
		//We never found anything in this word or we ran out of words
		if(letterIndex == 0 || wordIndex == productName.size() - 1){
			
			return false;
		}
		
		//Move on to the next word
		String nextWord = productName.get(wordIndex + 1).toLowerCase();
		
		index = nextWord.indexOf(currentChar,0);
		
		while(index >= 0){
			
			boolean success = Validate(acronym,productName,acronymIndex + 1,wordIndex + 1,index + 1);
			
			if(success){
				
				return true;
			}
			
			index = nextWord.indexOf(currentChar,index + 1);
		}
		
		return false;
	}
}
