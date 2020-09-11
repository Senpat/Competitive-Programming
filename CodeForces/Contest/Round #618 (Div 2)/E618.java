//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest, wrong
public class E618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      
      }
      
      double[] answer = new double[n];
      Arrays.fill(answer,-1.0);
      int start = n-1;
      while(start>=0){
         
         int sum = array[start];
         int num = 1;
         
         int end = start-1;
         while(end >= 0 && (double)array[end] >= (1.0*(sum+array[end]))/(1.0*(num+1))){
            
            sum += array[end];
            num += 1;
            end--;
         }
         
         for(int k = end+1; k <= start; k++){
            answer[k] = (1.0*sum)/(1.0*num);
         }
         start = end;
      }
      /*
      if(answer[0] == -1.0){
         answer[0] = (double)array[0];
      }*/
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
         
         
         
         
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}