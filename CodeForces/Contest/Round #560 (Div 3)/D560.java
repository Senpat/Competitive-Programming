//make sure to make new file!
import java.io.*;
import java.util.*;

public class D560{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      factor = new int[MAX];
      initsieve();
      
         
      
      
      for(int q = 0; q < t; q++){
         
         int n = Integer.parseInt(f.readLine());
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         
         int N = array[0] * array[n-1];
         boolean stop = false;
         for(int k = 1; k < n/2; k++){
            if(array[k]*array[n-k-1] != N){
               stop = true;
               break;
            }
         }
            
         if(n%2==1){
            if(array[n/2]*array[n/2] != N){
               stop = true;
            }
         }
            
         if(countfactors(N) != n+2) stop = true;
            
         int answer = stop ? -1 : N;
         out.println(answer);
         
      
         
      
      }
      
      
      
      
      out.close();
   }
   //from gfg
   public static int MAX = 1000005; 
   public static int factor[]; 
  
    // function to generate all prime 
    // factors of numbers from 1 to 10^6 
   public static void initsieve() 
   { 
   
      for (int i = 2; i < MAX; i++) 
         factor[i] = i; 
         
      for (int i = 4; i < MAX; i += 2) 
         factor[i] = 2; 
         
      for (int i = 3; i * i < MAX; i++) { 
         if (factor[i] == i) { 
            for (int j = i * i; j < MAX; j += i) { 
               if (factor[j] == j) 
                  factor[j] = i; 
            } 
         } 
      } 
   } 
  
   public static int countfactors(int n) 
   { 
      if (n == 1) 
         return 1; 
   
      int ans = 1; 
   
      int dup = factor[n]; 
   
      int c = 1; 
   
      int j = n / factor[n]; 
   
      while (j != 1) { 
         if (factor[j] == dup) 
            c += 1; 
         
         else { 
            dup = factor[j]; 
            ans = ans * (c + 1); 
            c = 1; 
         } 
         
         j = j / factor[j]; 
      } 
   
      ans = ans * (c + 1); 
   
      return ans; 
   } 
      
}