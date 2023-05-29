package SavingsCalculator;

import java.util.HashMap;

public class Savings {
    
    public static final int numberOfYears = 30;

    public static HashMap<Integer, Double> calculateOnlySavings(int monthlySavings) {
        HashMap<Integer, Double> savingsTotal = new HashMap<>();
        double currentAmmount = 0;
        for(int i = 0; i <= numberOfYears; i++) {            
            savingsTotal.put(i, currentAmmount);
            currentAmmount += (monthlySavings * 12) ;
        } 
        return savingsTotal;
    }
    public static HashMap<Integer, Double> calculateSavings(int monthlySavings, double interestRate) {
        HashMap<Integer, Double> savingsTotal = new HashMap<>();
        double currentAmmount = 0;
        for(int i = 0; i <= numberOfYears; i++) {            
            savingsTotal.put(i, currentAmmount);
            currentAmmount += (monthlySavings * 12) + ((currentAmmount *(interestRate / 100.0)));
        } 
        return savingsTotal;
    }


}
