//make sure to make new file!
import java.io.*;
import java.util.*;

public class A189{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      
      if(check(array)){
         out.println("YES");
      } else {
         out.println("NO");
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(char[] array){
      for(int k = 0; k < array.length; k++){
         if(array[k] != '1' && array[k] != '4') return false;
      }
      
      if(array[0] != '1') return false;
      
      int fourstreak = 0;
      
      for(int k = 1; k < array.length; k++){
         if(array[k] == '4') fourstreak++;
         else fourstreak = 0;
         
         if(fourstreak > 2) return false;
      }
      
      return true;
   }
      
      
   
      
}