//Ali Iftakhar
//CS 145
//Assignment 4.


import java.util.*;
   
   /**
    * This class is responsible for managing and taking care of the backend of the grammar solving BNF. 
    * It ensures that the program gives the correct input for every non-terminal or terminal input.
    *
    * @author: Ali Iftakhar
    * @version: 5/6/2019
    */


public class GrammarSolver { 
   private List<String> rules = new ArrayList<>();
   private Map<String, ArrayList<String>> theMap = new TreeMap<>();
   
   
   /**
    * This is our constructor. It takes in a list of rules and saves them in a map. It also 
    * checks for various exceptions.
    *
    *@param List<String> givenRules are the set of rules that are being passed into the program.
    *@throw IllegalArugmentException if the list being passed is completely empty.
    *@throw IllegalArgumentException if the same non-terminal exists twice in the list.
    */
    
   public GrammarSolver(List<String> givenRules) {
      if(givenRules.size() == 0) {
         throw new IllegalArgumentException();
      }
      //Add the provided rule lines to the array of rules.
      rules.addAll(givenRules);
      
      //We want to break the string first into 2. First part is key and second are the values.
       for(int i = 0; i < rules.size(); i++) {
         String s1 = rules.get(i);
         String[] keyValuesAt0 = s1.split("::=", 2);
         String s2 = keyValuesAt0[1];
         String[] parts2 = s2.split("[|]");
         if(theMap.containsKey(keyValuesAt0[0])) {
            throw new IllegalArgumentException();
         }
         
         //Now we break it into an array of values each individual from one another.
         //String valuesSplit[] = keyValuesAt0[1].split("|");
         

         //Create an arraylist and save every value inside of it.
         ArrayList<String> t = new ArrayList<>();
         for(String x: parts2) {
            x = x.trim();
            t.add(x);
         }
         
         //Then put the values in a specific key.
         theMap.put(keyValuesAt0[0].trim(), t);
            
      }
      
      System.out.println(theMap);
   
   }
   
   /**
    * This program checks to see if the symbol is a non-terminal or a terminal. 
    *
    *@param String symbol is the symbol that is currently being checked.
    *@return boolean depending on if the Map has that key. 
    *@throw IllegalArgument exception if symbol size is less than 1 or if its null.
    */
    
   public boolean contains(String symbol) {
      if(symbol.length() < 1 || symbol.equals(null)) {
         throw new IllegalArgumentException();
      }
      if(theMap.containsKey(symbol)) {
         return true; 
      }  else {
         return false;
      }
      
   }
   
   /**
    * getSymbols() is responsible for retreiving all the non-terminals.
    * It tells what symbols can be pursued. 
    * @return a Set<String> of all the keys in the map.
    */
    
   public Set<String> getSymbols() {
      Set<String> returnSet = new TreeSet<>();
      for(String x: theMap.keySet()) {
        returnSet.add(x);
      }
      
      return returnSet;
   }
   
   /** 
    * generate is responsible for creating a string and attaching it to the final input. 
    * generate will check if the symbol is a terminal or non-terminal. If it is a terminal
    * it goes ahead and returns it. If its a non-terminal, it does recursive runs.
    * @param String symbol for what is currently being observed.
    * @return String which will be the output of the program.
    */
    
   public String generate(String symbol) {
      Random rand = new Random();
      String actualAnswer = "";
      if(theMap.containsKey(symbol)) {
         List<String> rule = new ArrayList<>();
         rule.addAll(theMap.get(symbol));
         int number = rand.nextInt(rule.size());
         String[] noOfOccur = rule.get(number).split("[ \t]+");
         String[] answers = new String[noOfOccur.length];
         for(int i = 0; i < noOfOccur.length; i++) {
            answers[i] = generate(noOfOccur[i]);
            if(!answers[i].contains(" ")) {
               actualAnswer += answers[i] + " ";
            } else {
               actualAnswer += answers[i];
            }
            
         }
         
         return actualAnswer;
      } else {         
         return symbol;     
      }
  
   }

}
