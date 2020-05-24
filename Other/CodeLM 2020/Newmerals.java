import java.util.Scanner;
//make sure to make new file!
import java.io.*;
import java.util.*;
public class Newmerals {

    static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		String newmeralA = s.next();              // The first newmeral.
		String newmeralB = s.next();              // The second newmeral.
		char operator = s.next().charAt(0);       // The operator, ':', '^' or '%'.

		// code to solve the problem.  You can write and call other methods as well.
		
      StringJoiner sj = new StringJoiner("");
      String a = newmeralA;
      String b = newmeralB;
      if(operator == ':'){
         if(a.length() == b.length()){
            for(int k = 0; k < a.length(); k++){
               sj.add("" + a.charAt(k));
               sj.add("" + b.charAt(k));
            }
         } else if(a.length() < b.length()){
            int i = b.length()/a.length();
            
            for(int k = 0; k < a.length(); k++){
               sj.add("" + a.charAt(k));
               for(int j = 0; j < i; j++){
                  sj.add("" + b.charAt(k*i+j));
               }
            }
         } else {
            int i = a.length()/b.length();
            
            for(int k = 0; k < b.length(); k++){
               for(int j = 0; j < i; j++){
                  sj.add("" + a.charAt(k*i+j));
               }
               sj.add("" + b.charAt(k));
               
            }
         }
      
      
      } else if(operator == '^'){
         for(int k = 0; k < Math.max(a.length(),b.length()); k++){
            if(k < a.length() && k < b.length()){
               if(a.charAt(k) == b.charAt(k)){
                  sj.add("" + promote(a.charAt(k)));
               } else {
                  sj.add("" + (char)Math.max(a.charAt(k),b.charAt(k)));
               }
               
            
            } else if(k < a.length()){
               sj.add("" + a.charAt(k));
            } else if(k < b.length()){
               sj.add("" + b.charAt(k));
            }
         }
      
      
      } else if(operator == '%'){
         
         for(int k = newmeralB.length()-1; k >= 0; k--){
            sj.add("" + newmeralB.charAt(k));
         }
         sj.add(newmeralA);
         sj.add(newmeralB);
      
      
      }
      
      System.out.println(sj.toString());
	}
   
   public static char promote(char c){
      if(c == 'D') return 'A';
      return (char)(c+1);
   }

}
