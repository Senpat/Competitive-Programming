//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1092{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n % 4 == 1 || n % 4 == 2){
         out.println("NO");      
      } else {
         
         ArrayList<Integer> a1 = new ArrayList<Integer>();
         ArrayList<Integer> a2 = new ArrayList<Integer>();
         if(n % 4 == 0){   
            for(int k = 1; k < n/2; k+=2){
               a1.add(k);
               a1.add(n-k+1);
               a2.add(k+1);
               a2.add(n-(k+1)+1);
            }
         } else {
            //n % 4 == 3
            
            a1.add(1);
            a1.add(2);
            a2.add(3);
            
            for(int k = 1; k < (n-3)/2; k+=2){
               a1.add(k + 3);
               a1.add(n-k+1);
               a2.add(k+1 + 3);
               a2.add(n-(k+1)+1);
            }
            
                     
         }
         out.println("YES");
         out.println(a1.size());
         for(int i : a1){
            out.print(i + " ");
         }
         out.println();
         out.println(a2.size());
         for(int i : a2){
            out.print(i + " ");
         }
         out.println();
      } 
      
      
      
      
      
      out.close();
   }
   
      
}