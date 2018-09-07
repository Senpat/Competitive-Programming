//make sure to make new file!
import java.io.*;
import java.util.*;

public class BMC18{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      shuffleArray(array);
      Arrays.sort(array);
      
      long answer = 0L;
      
      long curmed = array[n/2];
      
      if(curmed < m){
         answer += m-curmed;
         for(int k = n/2+1; k < n; k++){
            answer+=Math.max(0,m-array[k]);
         }
      } else if(curmed > m){
         answer += curmed - m;
         for(int k = n/2-1; k>=0; k--){
            answer+=Math.max(0,array[k]-m);
         }
      } else {
         answer = 0;
      }
      
      out.println(answer);
      out.close();
   }
   
   public static void shuffleArray(long[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            long tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }   
   }
   
}