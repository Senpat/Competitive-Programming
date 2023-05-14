//make sure to make new file!
import java.io.*;
import java.util.*;
//wa tc 10 somehow
public class CTONb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         Integer[] array = new Integer[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         long numadd = 0;
         long numrem = 0;
         long answer = c * (long)n + d;            //remove everything and add 1
         for(int k = 0; k < n; k++){
            //make a permutation with array[k] as the biggest value
            if(k == 0){
               numadd = array[0]-1;
            } else {
               if(array[k] == array[k-1]) numrem++;
               else numadd += array[k]-array[k-1]-1;
            }
            //out.println(k + " " + numadd + " " + numrem + " " + (n-k-1));
            answer = Math.min(answer,d * numadd + c * (numrem + (long)(n-k-1)));

         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}