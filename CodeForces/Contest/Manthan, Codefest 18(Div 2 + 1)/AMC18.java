//Packets
import java.io.*;
import java.util.*;

public class AMC18{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      out.println(dothing(n));
      
      out.close();
   }
   
   public static int dothing(int i){
      if(i == 0) return 0;
      if(i == 1) return 1;
      if(i <= 3) return 2;
      
      int index = 2;
      int cur = 3;
      
      while(cur < i){
         cur = cur*2+1;
         index++;
      }
      
      return index;
   }
      
   
}