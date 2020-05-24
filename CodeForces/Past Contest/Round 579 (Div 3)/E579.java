//make sure to make new file!
import java.io.*;
import java.util.*;

public class E579{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      Integer[] array = new Integer[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(array);
      
      int answer = 0;
      int max = array[0];
      if(array[0] > 1){
         max--;
      }
      
      answer++;
      
      for(int k = 1; k < n; k++){
         if(array[k]-1 > max){
            max = array[k]-1;
            answer++;
         } else if(array[k] > max){
            max = array[k];
            answer++;
         } else if(array[k]+1 > max){
            max = array[k]+1;
            answer++;
         }
      }
      
      out.println(answer);
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}