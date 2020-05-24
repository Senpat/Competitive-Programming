//make sure to make new file!
import java.io.*;
import java.util.*;

public class D630{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int k = Integer.parseInt(f.readLine());
      
      int N = 3;
      
      out.println(N + " " + N);
      
      int bb = biggestbit(k)/2;
      out.println((k+bb*2) + " " + (bb*2) + " 0");
      out.println(k + " " + (k+bb*2) + " 0");
      out.println("0 " + k + " " + k); 
      
   
      
      
      
      
      
      out.close();
   }
   
   public static int biggestbit(int i){
      if (i == 0) 
         return 0; 
   
      int msb = 0; 
      while (i != 0) 
      { 
         i = i / 2; 
         msb++; 
      } 
   
      return (1 << msb); 
   }
      
}