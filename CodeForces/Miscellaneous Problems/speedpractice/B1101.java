//make sure to make new file!
import java.io.*;
import java.util.*;

public class B1101{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int count = 0;
      
      //go until first :
      boolean seenob = false;
      
      int firstcindex = -1;
      
      for(int k = 0; k < s.length() && firstcindex != -1; k++){
         if(seenob){
            if(s.charAt(k) == ':'){
               firstcindex = k;
               
      

      
      
      
      
      out.close();
   }
   
      
}