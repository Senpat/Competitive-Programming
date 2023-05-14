//make sure to make new file!
import java.io.*;
import java.util.*;

public class B144{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         String a = f.readLine();
         String b = f.readLine();
         
         int an = a.length();
         int bn = b.length();
         
         if(a.charAt(0) == b.charAt(0)){
            out.println("YES");
            out.println("" + a.charAt(0) + "*");
            continue;
         }
         if(a.charAt(an-1) == b.charAt(bn-1)){
            out.println("YES");
            out.println("*" + a.charAt(an-1));
            continue;
         }
         
         boolean found = false;
         for(int k = 0; k < an-1; k++){
            for(int j = 0; j < bn-1; j++){
               if(a.charAt(k) == b.charAt(j) && a.charAt(k+1) == b.charAt(j+1)){
                  out.println("YES");
                  out.println("*" + a.charAt(k) + "" + a.charAt(k+1) + "*");
                  found = true;
                  break;
               }
            }
            if(found) break;
         }
         
         
         if(!found){
            out.println("NO");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}