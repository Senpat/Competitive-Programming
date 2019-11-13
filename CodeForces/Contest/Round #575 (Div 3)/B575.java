//make sure to make new file!
import java.io.*;
import java.util.*;

public class B575{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         
         int numodd = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] % 2 == 1) numodd++;
         }
         
         if(m > numodd || m%2 != numodd%2){
            out.println("NO");
            continue;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int o = 0;
         for(int k = 0; k < n && o < m; k++){
            if(array[k] % 2 == 1){
               answer.add(k+1);
               o++;
            }
         }
         
         answer.set(answer.size()-1,n);
         
         out.println("YES");
         for(int i : answer){
            out.print(i + " ");
         }
         out.println();
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}