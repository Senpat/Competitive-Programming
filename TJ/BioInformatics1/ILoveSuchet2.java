//make sure to make new file!
import java.io.*;
import java.util.*;

public class ILoveSuchet2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String suchet = f.readLine();
      
      
      if(check0(suchet)){
            out.println("1");
            out.close();
            return;
         }
      
      for(int k = 1; k <= (n)/2; k++){
         if(checkifsuchetiscool(suchet,k)){
            out.println("1");
            out.close();
            return;
         }
      }
      
      out.println("0");

      
      
      
      
      
      out.close();
   }
   
   public static boolean check0(String s){
      for(int k = 1; k < s.length(); k++){
         if(s.charAt(k) != s.charAt(0)){
            return false;
         }
      }
       return true;
    } 
   
   public static boolean checkifsuchetiscool(String s, int i){
      for(int k = 1; k < s.length(); k++){
         if(s.charAt(k%i) != s.charAt(k)){
            return false;
         }
      }
      return true;
   }
   
      
}