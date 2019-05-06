import java.util.*;

//For now this class is saving the keys and the values inside of the map. I think the algorithm on it 
// is correct however you can never be too sure of it LUL. Tomorrow let's pick up from here and try to 
//move on from here.
public class GrammarSolver { 
   private List<String> rules = new ArrayList<>();
   private Map<String, ArrayList<String>> theMap = new TreeMap<>();
   
   
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
            x.trim();
            t.add(x);
         }
         
         //Then put the values in a specific key.
         theMap.put(keyValuesAt0[0].trim(), t);
            
      }
      
      System.out.println(theMap);
   
   }
   
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
   
   public Set<String> getSymbols() {
      Set<String> returnSet = new TreeSet<>();
      for(String x: theMap.keySet()) {
        returnSet.add(x);
      }
      
      return returnSet;
   }
   
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
            actualAnswer += answers[i];
         }
         
         return actualAnswer;
      } else {         
         return symbol;     
      }
  
   }

}