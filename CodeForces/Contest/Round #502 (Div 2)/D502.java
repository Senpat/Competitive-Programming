//The Wu
import java.io.*;
import java.util.*;

public class D502{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] wu = new int[n];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         wu[k] = Integer.parseInt(st.nextToken());
      }
      
      String[] strings = new String[m];
      
      for(int k = 0; k < m; k++){
         strings[k] = f.readLine();
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         String t = st.nextToken();
         int i = Integer.parseInt(st.nextToken());
         int count = 0;
         
         for(int j = 0; j < m; j++){
            int curq = 0;
            for(int h = 0; h < n; h++){
               if(t.charAt(h)==strings[j].charAt(h)){
                  curq+=wu[h];
               }
               if(curq>i) break;
            }
            if(curq <= i) count++;
         }
         
         out.println(count);
      }
      
      
      
      out.close();
   }
   
}