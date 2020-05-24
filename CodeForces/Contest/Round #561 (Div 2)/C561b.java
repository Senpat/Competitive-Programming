//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, manual binary search
public class C561b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Math.abs(Integer.parseInt(st.nextToken()));
      }
      
      shuffleArray(array);
      Arrays.sort(array);
      
      long answer = 0L;
      for(int k = 0; k < n-1; k++){
         answer += 0L + bsearch(array,array[k]*2)-k;
      }
      
      out.println(answer);         

      
      
      
      
      out.close();
   }
   
   public static int bsearch(int[] array, int i){
      
      int l = -1;
      int r = array.length;
      int mid;
      while(r-l>1){
         mid = (l+r)/2;
         
         if(array[mid] >= i){
            l = mid;
         } else {
            r = mid;
         }
      }
      
      return l;
      
      
      
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