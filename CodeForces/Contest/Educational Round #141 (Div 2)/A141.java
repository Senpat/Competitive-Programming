//make sure to make new file!
import java.io.*;
import java.util.*;

public class A141{
   
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
         
         Arrays.sort(array);
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         ArrayList<Integer> addlater = new ArrayList<Integer>();
         for(int k = n-1; k >= 0; k--){
            if(k != n-1 && array[k] == array[n-1]){
               addlater.add(array[k]);
            } else {
               answer.add(array[k]);
            }
         }
         
         if(answer.size() == 1 && addlater.size() > 0){
            out.println("NO");
         } else {
            for(int i : addlater){
               answer.add(i);
            }
            out.println("YES");
            for(int i : answer){
               out.print(i + " ");
            }
            out.println();
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}