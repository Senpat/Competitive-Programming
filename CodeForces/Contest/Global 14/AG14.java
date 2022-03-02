//make sure to make new file!
import java.io.*;
import java.util.*;

public class AG14{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int totsum = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            totsum += array[k];
         }
         
         if(totsum == x){
            out.println("NO");
            continue;
         }
         
         Arrays.sort(array);
         
         int sum = 0;
         for(int k = 0; k < n; k++){
            sum += array[k];
            if(sum > x) break;
            if(sum == x){
               //swap k and k+1
               int temp = array[k];
               array[k] = array[k+1];
               array[k+1] = temp;
               break;
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + array[k]);
         }
         
         out.println("YES");
         out.println(sj.toString());
         

      }
      
      
      
      
      out.close();
   }
   
      
}