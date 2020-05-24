//make sure to make new file!
import java.io.*;
import java.util.*;

public class B633{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         Integer[] array = new Integer[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         Arrays.sort(array);
         int[] answer = new int[n];
         
         for(int k = 0; k < n; k++){
            if(k < n/2){
               answer[(n/2-k)*2-1] = array[k];
            } else {
               answer[(k-n/2)*2] = array[k];
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}