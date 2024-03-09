//make sure to make new file!
import java.io.*;
import java.util.*;
//greedy
public class E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         
         int[] answer = new int[n];
         
         int mid = (n+1)/2;
         for(int k = 0; k < n; k++){
            if(array[k] == mid){
               answer[k] = 1;
               continue;
            }
            boolean[] b = new boolean[n];
            for(int j = 0; j < n; j++){
               if((array[j] > array[k]) != (array[k] > mid)) b[j] = true;
               else b[j] = false;
            }
            
            int thresh = Math.abs(array[k]-mid);
            
            int merge = 0;
            int i = 2;
            while(i < k){
               if(b[i-2] && b[i-1]){
                  if(b[i]) merge++;
                  b[i] = true;
                  i += 2;
               } else {
                  i++;
               }
            }
            
            i = k+3;
            while(i < n){
               if(b[i-2] && b[i-1]){
                  if(b[i]) merge++;
                  b[i] = true;
                  i += 2;
               } else {
                  i++;
               }
            }
            
            if(merge >= thresh){
               answer[k] = 1;
            } else {
               answer[k] = 0;
            }
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int i : answer) sj.add("" + i);
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}