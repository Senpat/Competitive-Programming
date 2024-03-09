import java.io.*;
import java.util.*;

class C2{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("c2_full.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("c2_full_out.txt")));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         int n = Integer.parseInt(f.readLine());
         
         String s = f.readLine();
         int[] array = new int[n+1];
         for(int k = 0; k < n; k++){
            array[k+1] = Character.getNumericValue(s.charAt(k));
         }
         
         boolean[] curchange = new boolean[n+1];
         long cur = 0L;
         
         for(int k = 1; k <= n; k++){
            if(array[k] == 1){
               curchange[k] = true;
               cur++;
               for(int j = k; j <= n; j+=k){
                  array[j] = 1-array[j];
               }  
            }
         }
         
         long answer = 0L;
         int c = Integer.parseInt(f.readLine());
         for(int k = 0; k < c; k++){
            int i = Integer.parseInt(f.readLine());
            if(curchange[i]){
               cur--;
            } else {
               cur++;
            }
            curchange[i] = !curchange[i];
            answer += cur;
         }
         
         
         out.println("Case #" + q + ": " + answer);
      }
        
        
      out.close();
   }
      
}