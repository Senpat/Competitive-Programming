//Given Length and Sum of Digits...
import java.io.*;
import java.util.*;

public class C489{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if((m<1) && n>1 || n*9<m){
         out.println("-1 -1");
         out.close();
         System.exit(0);
      }
      
      int cura = m;
      int curi = m;
      
      StringBuilder max = new StringBuilder(m);
      StringBuilder min = new StringBuilder(m);
      
      for(int k = 0; k < n; k++){
         int a = Math.min(9,cura);
         int i = Math.max(0,curi-9*(n-k-1));
         
         
         if(k==0) {
            i = Math.max(1,i);
            a = Math.max(1,a);
         }
         
         if(cura==0) a = 0;
         if(curi==0) i = 0;
         
         curi-=i;
         cura-=a;
         max.append(a);
         min.append(i);
      }
      
      out.println(min + " " + max);
      
      
      out.close();
   }
   
}