//make sure to make new file!
import java.io.*;
import java.util.*;

public class B618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         Integer[] array = new Integer[2*n];
         
         for(int k = 0; k < 2*n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         int answer;
            answer = array[n]-array[n-1];
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}