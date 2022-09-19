//make sure to make new file!
import java.io.*;
import java.util.*;

public class B789{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int N = n*m;
      
         String s = f.readLine();
         int[] array = new int[N];
         for(int k = 0; k < N; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
         }
         
         //calculate numcols
         int[] numcols = new int[N];
         int curcols = 0;
         int[] colhasbit = new int[m];
         for(int k = 0; k < N; k++){
            if(array[k] == 1){
               if(colhasbit[k%m] == 0){
                  curcols++;
                  colhasbit[k%m]++;
               }
            }
            numcols[k] = curcols;
         }
         
         //calculate numrows
         int[] numrows = new int[N];
         int[] start = new int[N];
         for(int k = 0; k < N; k++){
            start[k] = k/m + 1;
         }
         
         int[] sub = new int[N];
         int cursum = 0;
         for(int k = 0; k < N; k++){
            cursum += array[k];
            if(k >= m){
               cursum -= array[k-m];
            }
            
            if(cursum == 0){
               sub[k]++;
            }
         }
         
         int[] submod = new int[m];
         for(int k = 0; k < N; k++){
            submod[k%m] += sub[k];
            numrows[k] = start[k]-submod[k%m];
         }
         
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < N; k++){
            sj.add("" + (numcols[k] + numrows[k]));
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}