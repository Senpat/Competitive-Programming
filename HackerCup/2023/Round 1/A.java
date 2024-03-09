import java.io.*;
import java.util.*;

class A{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("a_full.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("a_full_out.txt")));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         Double[] array = new Double[n];
         for(int k = 0; k < n; k++){
            array[k] = Double.parseDouble(st.nextToken());
         }
         
         Arrays.sort(array);
         
         if(n == 5){
            //either first 3 + last 2 or first 2 + last 3
            double a1 = (array[3] + array[4])/2.0 - (array[0]+array[2])/2.0;
            double a2 = (array[2] + array[4])/2.0 - (array[0]+array[1])/2.0;
            
            out.printf("Case #%d: %f\n",q,Math.max(a1,a2));
         } else {
            double answer = (array[n-2] + array[n-1])/2.0 - (array[0]+array[1])/2.0;
            out.printf("Case #%d: %f\n",q,answer);
         }
      

      }
      
        
      out.close();
   }
      
}