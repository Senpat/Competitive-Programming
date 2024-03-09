//make sure to make new file!
import java.io.*;
import java.util.*;

public class A910{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         int b = 0;
         for(int k = 0; k < n; k++){
            if(array[k] == 'B') b++;
         }
         
         if(b == m){
            out.println("0");
            continue;
         }
         
         int answer = -1;
         out.println("1");
         if(b < m){
            for(int k = 0; k < n; k++){
               if(array[k] != 'B') b++;
               if(b == m){
                  answer = k+1;
                  break;
               }
            }
            out.println(answer + " B");
         } else {
            for(int k = 0; k < n; k++){
               if(array[k] == 'B') b--;
               if(b == m){
                  answer = k+1;
                  break;
               }
            }
            out.println(answer + " A");
         }
         

      }
      
      
      
      
      out.close();
   }
   
      
}