//make sure to make new file!
import java.io.*;
import java.util.*;

public class A632{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char c0;
         char c1;
         
         if(n%2 == 0 && m%2 == 0){
            c0 = 'W';
            c1 = 'B';
         } else {
            c0 = 'B';
            c1 = 'W';
         }
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               if((n*m%2) == 0 && k==n-1 && j == m-1) out.print('B');
               else if((k+j)%2 == 0) out.print(c0);
               else out.print(c1);
            }
            out.println();
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}