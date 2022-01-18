//make sure to make new file!
import java.io.*;
import java.util.*;

public class DHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] a = f.readLine().toCharArray();
         char[] b = f.readLine().toCharArray();
         
         int na = a.length;
         int nb = b.length;
         
         if(nb > na){
            out.println("NO");
            continue;
         }
         
         int par = (na-nb)%2;
         
         int start = -1;
         for(int k = par; k < na; k += 2){
            if(a[k] == b[0]){
               start = k;
               break;
            }
         }
         
         if(start == -1){
            out.println("NO");
            continue;
         }
         
         boolean fail = false;
         int i = start;
         for(int k = 1; k < nb; k++){
            i++;
            while(i < na && a[i] != b[k]){
               i+=2;
            }
            
            if(i >= na){
               fail = true;
               break;
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