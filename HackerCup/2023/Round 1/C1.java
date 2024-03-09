import java.io.*;
import java.util.*;

class C1{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("c1_full.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("c1_full_out.txt")));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         int n = Integer.parseInt(f.readLine());
         
         String s = f.readLine();
         int[] array = new int[n+1];
         for(int k = 0; k < n; k++){
            array[k+1] = Character.getNumericValue(s.charAt(k));
         }
         
         boolean[] changes = new boolean[n+1];
         int c = Integer.parseInt(f.readLine());
         for(int k = 0; k < c; k++){
            int i = Integer.parseInt(f.readLine());
            changes[i] = !changes[i];
         }
         
         for(int k = 1; k <= n; k++){
            if(changes[k]){
               for(int j = k; j <= n; j += k){
                  array[j] = 1-array[j];
               }
            }
         }
         
         int answer = 0;
         for(int k = 1; k <= n; k++){
            if(array[k] == 1){
               answer++;
               for(int j = k; j <= n; j+=k){
                  array[j] = 1-array[j];
               }  
            }
         }
         
         out.println("Case #" + q + ": " + answer);
      }
        
        
      out.close();
   }
      
}