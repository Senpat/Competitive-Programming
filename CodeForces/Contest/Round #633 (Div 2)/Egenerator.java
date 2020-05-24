//make sure to make new file!
import java.io.*;
import java.util.*;

public class Egenerator{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(new FileWriter("Egenerator2.out"));
      
      int n = 1000;
      
      HashSet<Integer> hset = new HashSet<Integer>();
      
      int i = 0;
      int k = 1;
      
      while(i < n){
         while(hset.contains(k)) k++;
         
         int b = k+1;
         while(hset.contains(b) || hset.contains(k^b)) b++;
         
         out.println(k + "\t\t\t\t" + s(8-Integer.toBinaryString(k).length()) + Integer.toBinaryString(k));
         out.println(b + "\t\t\t\t" + s(8-Integer.toBinaryString(b).length())+ Integer.toBinaryString(b));
         out.println((k^b) + "\t\t\t\t" + s(8-Integer.toBinaryString(k^b).length())+ Integer.toBinaryString(k^b));
         
         hset.add(k);
         hset.add(b);
         hset.add(k^b);
         i++;
      }
      
      

      
      
      
      
      
      out.close();
   }
   
   public static String s(int i){
      String s = "";
      for(int k = 0; k < i; k++) s += " ";
      return s;
   }
      
}