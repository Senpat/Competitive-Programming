//make sure to make new file!
import java.io.*;
import java.util.*;

public class B572{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      shuffleArray(array);
      Arrays.sort(array);
      if(array[n-1] >= array[n-2] + array[n-3]){
         out.println("NO");
         out.close();
         System.exit(0);
      }
      
      int[] answer = new int[n];
      for(int k = 0; k < n/2; k++){
         answer[k] = array[n-k*2-1];
         answer[n-1-k] = array[n-k*2-2];
      }
      if(n%2==1) answer[n/2] = array[0];
      
      out.println("YES");
      for(int k = 0; k < n; k++){
         out.print(answer[k] + " ");
      }
      

      
      
      
      
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