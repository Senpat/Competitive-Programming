//Menorah
import java.io.*;
import java.util.*;
//fail
public class C1615{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] a = f.readLine().toCharArray();
         char[] b = f.readLine().toCharArray();
         
         int o = 0;
         boolean cano = false;
         int e = 0;
         boolean cane = false;
         
         int a1 = 0;
         int b1 = 0;
         
         for(int k = 0; k < n; k++){
            if(a[k] != b[k]){
               o++;
               if(a[k] == '1') cano = true;
            }
            else{
               e++;
               if(a[k] == '1') cane = true;
            }
            
            if(a[k] == '1') a1++;
            if(b[k] == '1') b1++;
         }
         
         if(e == n){
            out.println(0);
            continue;
         }
         
         if(a1!=b1 && a1 != n-b1+1){
            out.println(-1);
            continue;
         }
         
         int answer = n+5;
         if(o%2 == 0 && cano) answer = Math.min(answer,o);
         if(e%2 == 1 && cane) answer = Math.min(answer,e);
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}