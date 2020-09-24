//make sure to make new file!
import java.io.*;
import java.util.*;
//solves version 1
public class D671{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(array);
      
      int[] answer = new int[n];
      
      int i = 0;
      for(int k = 1; k < n; k+=2){
         answer[k] = array[i];
         i++;
      }
      for(int k = 0; k < n; k+=2){
         answer[k] = array[i];
         i++;
      }
      
      out.println((n-1)/2);
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}