//make sure to make new file!
import java.io.*;
import java.util.*;

public class C130{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] a = f.readLine().toCharArray();
         char[] b = f.readLine().toCharArray();
         
         int bp = 0;
         int cp = 0;
         
         boolean fail = false;
         for(int k = 0; k < n; k++){
            bp = Math.max(bp,k);
            cp = Math.max(cp,k);
            if(a[k] == b[k]) continue;
            
            if(b[k] == 'a'){
               fail = true;
               break;
            }
            
            if(b[k] == 'b'){
               while(bp < n && a[bp] == 'a') bp++;
               if(bp == n || a[bp] != 'b'){
                  fail = true;
                  break;
               }
               a[k] = 'b';
               a[bp] = 'a';
            }
            
            if(b[k] == 'c'){
               while(cp < n && a[cp] == 'b') cp++;
               if(cp == n || a[cp] != 'c'){
                  fail = true;
                  break;
               }
               a[k] = 'c';
               a[cp] = 'b';
            }
         
            
      
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}