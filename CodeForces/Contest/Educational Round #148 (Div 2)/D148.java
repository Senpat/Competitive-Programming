//make sure to make new file!
import java.io.*;
import java.util.*;
//first subtask
public class D148{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Integer[] array = new Integer[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(array);
      
      st = new StringTokenizer(f.readLine());
      StringJoiner sj = new StringJoiner(" ");
      for(int t = 0; t < q; t++){
         int x = Integer.parseInt(st.nextToken());
         
         int x2 = x % 2 == n % 2 ? n : n-1;
         int add = Math.min(x,x2);
         int decs = (x-add)/2;            //# of times you decrease
         
         int[] curarray = new int[n];
         for(int k = 0; k < n; k++) curarray[k] = array[k];
         
         for(int k = 0; k < add; k++){
            curarray[k] += x-k;
         }
         
         Arrays.sort(curarray);
         long curdif = 0L;
         for(int k = 1; k < n; k++){
            curdif += (long)(curarray[k]-curarray[0]);
         }
         
         long answer = -1;
         if(curdif >= decs){
            answer = curarray[0];
         } else {
            //how much lower than curarray[0] the answer must be
            long diff = (long)decs-curdif;
            answer = curarray[0] - (diff + n-1)/n;
         }
         sj.add("" + answer);
                  
      } 
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}