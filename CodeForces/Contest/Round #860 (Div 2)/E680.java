//make sure to make new file!
import java.io.*;
import java.util.*;

public class E680{
   
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
         
         int[] testchange = new int[n];         //testchange[x] == 1 means set of tests starting at x isn't valid, 0 means valid
         int[] numtests = new int[n];           //numtests[x] = # of tests in set of tests starting at x
         int[] max1 = new int[n];               //max1[x] = max # of tests if you change x
         int[] max2 = new int[n];               //max2[x] = max # of tests if you change something after x
         
         testchange[n-1] = 1;
         numtests[n-1] = 0;
         
         max2[n-1] = 1;
         
         int suffmax1 = 0;                      //suffix max of max num tests if you don't change anything
         
         for(int k = n-2; k >= 0; k--){
            int next = k + array[k] + 1;
            
            if(next == n || (next < n && testchange[next] == 0)){
               testchange[k] = 0;
               if(next == n) numtests[k] = 1;
               else numtests[k] = numtests[next]+1;
            } else {
               testchange[k] = 1;
            }
            
            max1[k] = suffmax1+1;
            max2[k] = max1[k];
            if(next < n){
               max2[k] = Math.max(max1[k], max2[next]+1);
            }
            if(testchange[k] == 0){
               suffmax1 = Math.max(suffmax1,numtests[k]);
            }
            
            
         }
         
         int[] answer = new int[n-1];
         
         for(int k = n-2; k >= 0; k--){
            //check 0
            if(testchange[k+1] == 0 && numtests[k+1] == array[k]){
               answer[k] = 0;
               continue;
            }
            
            //check 1: change multitest
            if(testchange[k+1] == 0){
               answer[k] = 1;
               continue;
            }
            
            //check 1: change a test size
            if(array[k] <= max2[k+1]){
               answer[k] = 1;
               continue;
            }
            
            answer[k] = 2;
            
         }
         
         if(array[n-2] == 1) answer[n-2] = 1;
         else answer[n-2] = 2;
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n-1; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
         
      
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}