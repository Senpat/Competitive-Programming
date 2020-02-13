//make sure to make new file!
import java.io.*;
import java.util.*;

public class DGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      int numa = -1;
      int numb = -1;
      
      int a = 0;
      int b = 0;
      
      for(int k = 1; k <= m+1; k++){
         out.print("?");
         for(int j = 1; j <= m+1; j++){
            if(j == k) continue;
            out.print(" " + j);
         }
         out.println();
         out.flush();
         
         st = new StringTokenizer(f.readLine());
      
         int p = Integer.parseInt(st.nextToken());
         int v = Integer.parseInt(st.nextToken());
         
         if(numa == -1){
            numa = v;
            a++;
         } else if(numa == v){
            a++;
         } else if(numb == -1){
            numb = v;
            b++;
         } else {
            b++;
         }
      }
      
      
      if(numa > numb){
         out.println("! " + a);
      } else {
         out.println("! " + b);
      }
      

      
      
      
      
      
      out.close();
   }
   
      
}