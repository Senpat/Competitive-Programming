//make sure to make new file!
import java.io.*;
import java.util.*;

public class B69{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      int maxindex = 0;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[maxindex] < array[k]){
            maxindex = k;
         }
      }
      
      int answer = 0;
      for(int k = 0; k < n; k++){
         boolean left = true;
         boolean right = true;
         if(k > 0 && array[k-1] > array[k]){
            left = false;
         }
         if(k < n-1 && array[k+1] > array[k]){
            left = false;
         }
         if(left && right)  answer++;
      }
      
      if(answer == 1){
         out.println("YES");
      } else { 
         out.println("NO");
      }
      
      

      
      
      
      
      out.close();
   }
   
   
}