//make sure to make new file!
import java.io.*;
import java.util.*;

public class NOCHANGE{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         
         st = new StringTokenizer(f.readLine());
         int index = -1;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
            if(array[k] > m || m%array[k] != 0){
               index = k;
            }
         }
         
         if(index == -1){
            out.println("NO");
         } else {
            out.print("YES ");
            StringJoiner sj = new StringJoiner(" ");
            for(int k = 0; k < n; k++){
               if(k == index){
                  int i = m/array[index]+1;
                  sj.add("" + i);
               } else {
                  sj.add("0");
               }
            }
            out.println(sj.toString());
         }
      
      }
      
   
      
      
      
      
      
      out.close();
   }
   
      
}