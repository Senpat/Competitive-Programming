//Planning the Expedition
import java.io.*;
import java.util.*;

public class B499{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[101];
      Arrays.fill(array,0);
      
      int max = 0;
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < m; k++){
         int i = Integer.parseInt(st.nextToken());
         array[i]++;
         max = Math.max(max,array[i]);
      }
      
      System.out.println(max);
   }
}