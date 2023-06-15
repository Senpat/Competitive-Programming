//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1090{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      shuffleArray(array);
      Arrays.sort(array);
      
      int answer = 0;
      int l = 0;
      for(int k = n-1; k >= 0; k--){
         if(k < l) break;
         
         answer++;
         if(l < k && array[k] + array[l] <= x){
            l++;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void shuffleArray(int[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            int tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }   
   }
   
      
}