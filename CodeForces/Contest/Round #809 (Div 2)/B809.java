//make sure to make new file!
import java.io.*;
import java.util.*;

public class B809{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         ArrayList<ArrayList<Integer>> indeces = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) indeces.add(new ArrayList<Integer>());
         
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            indeces.get(array[k]).add(k);
         }
         
         int[] answer = new int[n+1];
         
         for(int k = 1; k <= n; k++){
            int lasteven = 0;
            int lastodd = 0;
            
            for(int i : indeces.get(k)){
               if(i % 2 == 0){
                  lasteven = lastodd+1;
               } else {
                  lastodd = lasteven+1;
               }
            }
            
            answer[k] = Math.max(lasteven,lastodd);
         }
         
         
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}