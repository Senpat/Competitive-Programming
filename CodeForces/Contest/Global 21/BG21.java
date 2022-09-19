//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG21{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         ArrayList<Integer> non0 = new ArrayList<Integer>();
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] != 0) non0.add(k);
         }
         
         if(non0.size() == 0){
            out.println(0);
         } else {
            boolean fail = false;
            for(int i = 0; i < non0.size()-1; i++){
               if(non0.get(i+1)-non0.get(i) > 1){
                  fail = true;
                  break;
               }
            }
            
            if(fail){
               out.println(2);
            } else {
               out.println(1);
            }
         }
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}