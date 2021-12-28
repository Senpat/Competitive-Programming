//make sure to make new file!
import java.io.*;
import java.util.*;
//hint
public class EG17{
   
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
         
         //longest subsequence 
         int answer = 2;
         
         for(int k = 0; k < n; k++){
            if(answer >= n-k) break;
            
            int curanswer = 2;
            int last = array[k+1];
            
            for(int j = k+2; j < n; j++){
               if(array[j] >= 2*last-array[k]){
                  curanswer++;
                  last = array[j];
               }
            }
            
            answer = Math.max(answer,curanswer);
            
         }
         
         out.println(n-answer);

      }
      
      
      
      
      out.close();
   }
   
      
}