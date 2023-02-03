/*
TASK: fracdec
LANG: JAVA
*/
//also tle
import java.io.*;
import java.util.*;
import java.math.*;

public class fracdecc{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int integer = n/m;
      
      n = n%m;
      
      if(n == 0){
         out.println("" + integer + ".0");
         out.close();
         return;
      }
      
      BigInteger bign = new BigInteger(""+n);
      BigInteger bigm = new BigInteger(""+m);
      
      
      //count 2s
      int num2 = 0;
      int i = m;
      while(i % 2 == 0){
         i >>= 1;
         num2++;
      }
      
      int num5 = 0;
      
      while(i % 5 == 0){
         i /= 5;
         num5++;
      }
      
      int zeros = Math.max(num2,num5);
      BigInteger fixed = new BigInteger("1" + repeat("0",zeros));
      
      if(i == 1){
         //no repeating part
         BigInteger numer = fixed.multiply(bign).divide(bigm);
         String dec = numer.toString();
         
         String answer = "" + integer + "." + repeat("0",zeros-dec.length()) + dec;
         
         out.println(answer);
         out.close();
         return;
      }
      
      int num9s = i-1;
      
      BigInteger bigi = new BigInteger(""+i);
      
      //example: 1/24
      //fixed part is 1000, repeating is 9 -> 9000
      
      String s1 = repeat("9",num9s);
      
      BigInteger denom = new BigInteger(s1 + repeat("0",zeros));
      
      
      BigInteger numer = denom.multiply(bign).divide(bigm);
      
      //1/24 = 375/9000 = d
      //375/9 = 1000d
      
      //41 + 6/9 = 1000d
      BigInteger only9s = denom.divide(new BigInteger("1"+repeat("0",zeros)));
      System.out.println(only9s);
      BigInteger[] div = numer.divideAndRemainder(only9s);
      
      String fixeddec = div[0].toString();
      String repeatdec = div[1].toString();
      repeatdec = repeat("0",num9s-repeatdec.length()) + repeatdec;
      char[] repeatarray = repeatdec.toCharArray();
      int shortestcycle = repeatdec.length();
      
      int REP = 200;
      for(int k = 1; k*2 <= repeatdec.length(); k++){
         if(repeatdec.length() % k != 0) continue;
         //check if it is a cycle of length k
         boolean fail = false;
         for(int rep = 0; rep < REP; rep++){
            int x = (int)(Math.random()*(repeatdec.length()-k))+k;
            if(repeatarray[x] != repeatarray[x-k]) {
               fail = true;
               break;
            }
         }
         
         if(!fail){
            shortestcycle = k;
            break;
         }
      }
      
      repeatdec = repeatdec.substring(0,shortestcycle);
      
      if(fixeddec == "0") fixeddec = "";
      
      String answer = "" + integer + "." + 
                           repeat("0",zeros-fixeddec.length()) + fixeddec + 
                           "(" + repeatdec + ")";
      
      StringJoiner sj = new StringJoiner("");
      for(int k = 0; k < answer.length(); k++){
         if(k > 0 && k%76 == 0){
            sj.add("\n");
         }
         sj.add("" + answer.charAt(k));
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   public static String repeat(String s, int x){
      StringBuilder sj = new StringBuilder();
      for(int k = 0; k < x; k++) sj.append(s);
      return sj.toString();
   }
      
}