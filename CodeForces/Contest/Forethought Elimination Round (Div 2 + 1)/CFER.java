//make sure to make new file!
import java.io.*;
import java.util.*;

public class CFER{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      
      for(int q = 0; q < t; q++){
         int n = Integer.parseInt(f.readLine());
         
         
         int mod = n;
         int max = 0;
         while(mod > 1){
            
            HashSet<Integer> a = new HashSet<Integer>();
            HashSet<Integer> b = new HashSet<Integer>();
            
            for(int k = 1; k <= n; k++){
               int curmod  = k % mod;
               int nmod = mod/2;
               if(mod%2 == 1) nmod++;
               if(curmod != 0 && curmod <= nmod){
               
                  a.add(k);
               } else {
                  b.add(k);
               }
            }
            
            out.print(a.size() + " " + b.size() + " ");
            for(int i : a){
               out.print(i + " ");
            }
            for(int i : b){
               out.print(i + " ");
            }
            out.println();
            out.flush();
            
            int in = Integer.parseInt(f.readLine());
            if(in == -1) while(true);
            max = Math.max(max,in);
            
            if(mod % 2 == 1) mod = mod/2 + 1;
            else mod = mod/2;
            
            //out.println(mod);
         }
         
         out.println("-1 " + max);
         out.flush();
      }
      
      
      out.close();
   }
   
      
}