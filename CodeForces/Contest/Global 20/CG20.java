//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG20{
   
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
         
         ArrayList<Integer> indeces = new ArrayList<Integer>();
         
         for(int k = 0; k < n-1; k++){
            if(array[k] == array[k+1]){
               indeces.add(k);
            }
         }
         
         if(indeces.size() <= 1){
            out.println(0);
         } else {
            int answer = Math.max(1,indeces.get(indeces.size()-1)-indeces.get(0)-1);
            out.println(answer);
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}