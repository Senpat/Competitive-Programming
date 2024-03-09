//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      HashSet<String> seen = new HashSet<String>();
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         boolean found = false;
         for(int j = 0; j < n; j++){
            String s = f.readLine();
            if(seen.contains(s)) continue;
            
            //see if bie is in it
            for(int k = 2; k < s.length(); k++){
               if(s.charAt(k-2) == 'b' && s.charAt(k-1) == 'i' && s.charAt(k) == 'e'){
                  seen.add(s);
                  out.println(s);
                  found = true;
                  break;
               }
            }
         }
         
         if(!found){
            out.println("Time to play Genshin Impact, Teacher Rice!");
         }
         

      }
      
      
      
      
      out.close();
   }
   
      
}