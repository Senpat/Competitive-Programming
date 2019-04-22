//make sure to make new file!
import java.io.*;
import java.util.*;

public class B549{
   
   public static int[] digits;
   public static int[] pow9;
   public static int len;
   public static int[] pow10;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int n = Integer.parseInt(s);
      len = s.length();
      
      digits = new int[s.length()];
      for(int k = 0; k < s.length(); k++){
         digits[k] = Character.getNumericValue(s.charAt(k));
      }
         
      
      pow9 = new int[11];
      pow10 = new int[11];
      pow9[0] = 1;
      pow10[0] = 1;
      for(int k = 1; k < pow9.length; k++){
         pow9[k] = pow9[k-1] * 9;
         pow10[k] = pow10[k-1] * 10;
      }
      
      int answer = solve(n);
      out.println(answer);

      
      
      
      
      out.close();
   }
   
   public static int solve(int num){
      if(num <= 9){
         return num;
      }
      
      int numdigits = (int)(Math.log10(num)+1);
      int firstdigit = num/pow10[numdigits-1];
      if(num < 100){
         return Math.max((firstdigit-1)*9,firstdigit*num%10);
      }
      System.out.println((firstdigit-1)*pow9[numdigits-1] + " " + num + " " + firstdigit + " " + numdigits + " " + ((firstdigit)*pow10[(numdigits-1)]));
      return Math.max((firstdigit-1)*pow9[numdigits-1],firstdigit*solve(num-(firstdigit)*pow10[(numdigits-1)]));
   }
   
      
}