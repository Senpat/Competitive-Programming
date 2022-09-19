//make sure to make new file!
import java.io.*;
import java.util.*;

public class B782{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] input = f.readLine().toCharArray();
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(input[k]);
         }
         
         int[] answer = new int[n];
         int[] result = new int[n];
         int added = 0;
         if(m%2 == 0){
            //maximize
            for(int k = 0; k < n; k++){
               result[k] = array[k];
               if(added < m){
                  if(array[k] == 0){
                     answer[k]++;
                     added++;
                     result[k] = 1;
                  }
               }
            }
         } else {
            //minimize but add 1-array to result
            for(int k = 0; k < n; k++){
               result[k] = 1-array[k];
               if(added < m){
                  if(array[k] == 1){
                     answer[k]++;
                     added++;
                     result[k] = 1;
                  }
               }
            }
         }
         
         if((m-added)%2 == 1){
            result[n-1] = 1-result[n-1];
         }
         answer[n-1] += (m-added);
         
         StringJoiner sj = new StringJoiner("");
         StringJoiner sj2 = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + result[k]);
            sj2.add("" + answer[k]);
         }
         
         out.println(sj.toString());
         out.println(sj2.toString());
         
            
            
      

      }
      
      
      
      
      out.close();
   }
   
      
}