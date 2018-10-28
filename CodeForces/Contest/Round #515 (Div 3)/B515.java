//make sure to make new file!
import java.io.*;
import java.util.*;

public class B515{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      int[] freq = new int[n];
      int total = 0;
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         freq[k] = array[k];
      }
      
      for(int k = 0; k < n; k++){
         if(array[k] == 1){
            for(int j = 1; j <= m-1; j++){
               if(k-j>=0){
                  freq[k-j]++;
               }
               if(k+j < n){
                  freq[k+j]++;
               }
            }
            total++;
         }
      }
      for(int k = 0; k < n; k++){
         if(freq[k] == 0){
            out.println("-1");
            out.close();
            System.exit(0);
         }
            
         if(array[k]==1 && check(array,n,m,freq,k)){
            total--;
            freq[k]--;
            for(int j = 1; j <= m-1; j++){
               if(k-j>=0){
                  freq[k-j]--;
               }
               if(k+j < n){
                  freq[k+j]--;
               }
            }
         }
      }
      
      out.println(total);
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int n, int m, int[] freq,int k){
      if(freq[k]<=1) return false;
      for(int j = 1; j <= m-1; j++){
         if(k-j>=0){
            if(freq[k-j]<=1) 
               return false;
         }
         if(k+j < n){
            if(freq[k+j]<=1) 
               return false;
         }
      }
         
      
      
      return true;
   }
   
}