//make sure to make new file!
import java.io.*;
import java.util.*;

public class A866{
   
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
         
         //get initial mex
         int N = n+5;
         int[] freq = new int[N];
         for(int k = 0; k < n; k++){
            if(array[k] < N) freq[array[k]]++;
         }
         
         int mex = -1;
         for(int k = 0; k < N; k++){
            if(freq[k] == 0){
               mex = k;
               break;
            }
         }
         
         if(mex == n){
            out.println("No");
            continue;
         }
         
         //replace all instances of mex+1 wth mex
         
         //replace everything between first mex+1 and last mex+1
         if(freq[mex+1] == 0){
            out.println("Yes");
            continue;
         }
         
         int first = -1;
         int last = -1;
         for(int k = 0; k < n; k++){
            if(array[k] == mex+1){
               last = k;
               if(first == -1) first = k;
            }
         }
         
         boolean fail = false;
         for(int k = first; k <= last; k++){
            if(array[k] < mex){
               freq[array[k]]--;
               if(freq[array[k]] == 0){
                  fail = true;
               }
            }
         }
       
         
         
         if(fail){
            out.println("No");
         } else {
            out.println("Yes");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}