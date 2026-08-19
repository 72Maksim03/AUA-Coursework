package bank;
/**
 * Utility class for generating and validating bank account numbers using the Luhn algorithm.
 */

public class AccNumber {
    /**
     * Validates a bank account number using the Luhn algorithm.
     *
     * @param card The bank account number to validate.
     * @return True if the bank account number is valid, false otherwise.
     */
    public static boolean luhn(String card){
        if(card.charAt(0) != '4' && card.charAt(0) != '5'){
            return false;
        }
        boolean ignore = true;
        int sum = 0;
        for(int i = card.length() - 1; i >= 0; i--){
            int digit = card.charAt(i) - '0';
            if(!ignore){
                digit *= 2;
                if(digit > 9){
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            ignore = !ignore;
        }
        return sum % 10 == 0;
    }

    /**
     * Generates a random bank account number that satisfies the Luhn algorithm.
     *
     * @return A randomly generated bank account number.
     */
    public static String createRandomNumber(){
        boolean ch = true;
        String result = "";
        while(ch){
            result = "";
            for(int i = 0; i < 16; i++){
                int digit = (int) (Math.random() * 10);
                result = result + digit;
            }
            if(luhn(result)){
                ch = false;
            }
        }
        return result;
    }
}