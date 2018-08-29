//Points in Segments
import java.io.*;
import java.util.*;

public class A501{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      boolean[] bool = new boolean[m];
      Arrays.fill(bool,false);
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         for(int j = a; j <= b; j++){
            bool[j-1] = true;
         }
      }
      
      int count = 0;
      String s = "";
      for(int k = 0; k < m; k++){
         if(bool[k] == false){
            count++;
            s += "" + (k+1) + " ";
         }
      }
      
      System.out.println(count);
      System.out.println(s);
   }
}