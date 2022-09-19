//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG21{
   
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
         
         int[] ptr = new int[n];
         Arrays.fill(ptr,-1);
         
         int gold = n-1;
         int max = -1;
         for(int k = n-2; k >= 0; k--){
            if(array[k] < array[gold]){
               gold = k;
            } else if(array[k] > array[gold]){
               if(array[k] > max){
                  ptr[k] = gold;
                  max = array[k];
               } else {
                  gold = k;
               }
            }
         }
         
         gold = n-1;
         int min = n+1;
         for(int k = n-2; k >= 0; k--){
            if(array[k] > array[gold]){
               gold = k;
            } else if(array[k] < array[gold]){
               if(array[k] < min){
                  ptr[k] = gold;
                  min = array[k];
               } else {
                  gold = k;
               }
            }
         }
         
         int answer = 0;
         int i = 0;
         while(i < n-1){
            answer++;
            i = ptr[i];
         }
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}