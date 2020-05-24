//make sure to make new file!
import java.io.*;
import java.util.*;

public class D620{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         int n = Integer.parseInt(st.nextToken());
         
         char[] signs = st.nextToken().toCharArray();
         
         Integer[] minans = new Integer[n];
         Arrays.fill(minans,0);
         int[] maxans = new int[n];
         
         int mini = n;
         for(int k = 0; k < n-1; k++){
            if(signs[k] == '<'){
               minans[k+1] = mini;
               mini--;
            }
         }
         for(int k = 0; k < n; k++){
            if(minans[k] == 0){
               minans[k] = mini;
               mini--;
            }
         }
               
         //sort subarrays
         int start = -1;
         
         for(int k = 0; k < n-1; k++){
            if(start == -1){
               if(signs[k] == '<'){
                  start = k;
               }
            } else {
               if(signs[k] != '<'){
                  Arrays.sort(minans,start+1,k+1);
                  start = -1;
               }
            }
         
         
         }
         if(start != -1){
            Arrays.sort(minans,start+1,n);
         } 
         
         
         int maxi = n;
         for(int k = n-2; k >= 0; k--){
            if(signs[k] == '<'){
               maxans[k+1] = maxi;
               maxi--;
            }
         }
         for(int k = 0; k < n; k++){
            if(maxans[k] == 0){
               maxans[k] = maxi;
               maxi--;
            }
         }
         
         
         StringJoiner minsj = new StringJoiner(" ");
         StringJoiner maxsj = new StringJoiner(" ");
         
         for(int k = 0; k < n; k++){
            minsj.add(""+minans[k]);
            maxsj.add(""+maxans[k]);
         }
         
         out.println(minsj.toString());
         out.println(maxsj.toString());
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}