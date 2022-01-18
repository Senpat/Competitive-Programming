//make sure to make new file!
import java.io.*;
import java.util.*;

public class A732{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] par = new long[100005];
      long[] sortedpar = new long[100005]; 
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            par[array[k]] += (long)k;
         }
         
         shuffleArray(array);
         Arrays.sort(array);
         for(int k = 0; k < n; k++){
            sortedpar[array[k]] += (long)k;
         }
         
         boolean fail = false;
         for(int k = 0; k < n; k++){
            if((par[array[k]]%2L) != (sortedpar[array[k]]%2L)){
               fail = true;
               break;
            }
         }
         
         par = new long[100005];
         sortedpar = new long[100005]; 
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }

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