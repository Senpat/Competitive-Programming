//make sure to make new file!
import java.io.*;
import java.util.*;

public class A553{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      int min = Integer.MAX_VALUE;
      for(int k = 0; k <= n-4; k++){
         int sum = 0;
         sum += dif(ctoi('A'),ctoi(s.charAt(k)));
         sum += dif(ctoi('C'),ctoi(s.charAt(k+1)));
         sum += dif(ctoi('T'),ctoi(s.charAt(k+2)));
         sum += dif(ctoi('G'),ctoi(s.charAt(k+3)));
         min = Math.min(sum,min);
      }
      out.println(min);
      
      
      
      out.close();
   }
   
   public static int ctoi(char c){
      return (int)c - 65;
   }
   
   public static int dif(int c1, int c2){
      return Math.min(Math.abs(c1-c2),26-Math.abs(c1-c2));
   }
   
      
}