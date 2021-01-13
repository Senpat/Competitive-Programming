//make sure to make new file!
import java.io.*;
import java.util.*;

public class F182{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[] maxes = new long[n];
      for(int k = 0; k < n-1; k++){
         maxes[k] = array[k+1]/array[k] -1;
      }
      
      long[] xdist = new long[n];
      
      long xcur = x;
      for(int k = n-1; k >= 0; k--){
         xdist[k] = xcur/array[k];
         xcur = xcur % array[k];
      }
      
      long answer = 1L;
      
      
      int last = -1;
      for(int k = 0; k < n; k++){
         if(xdist[k] == 0){
            if(k > last+1) answer *= calc(last+1,k-1,array,maxes,xdist);
            last = k;
         }
      }
      
      /*
      ArrayList<Integer> indeces = new ArrayList<Integer>();
      for(int k = 0; k < n; k++){
         if(xdist[k] != 0 && (k == 0 || xdist[k-1] == 0)) indeces.add(k);
      }
      
      for(int k = 0; k < indeces.size()-1; k++){
         answer *= calc(indeces.get(k),indeces.get(k+1)-2,array,maxes,xdist);
      }
      
      answer *= calc(indeces.get(indeces.size()-1),n-1,array,maxes,xdist);
      */
      if(last < n-1 && xdist[n-1] != 0){
         answer *= calc(last+1,n-1,array,maxes,xdist);
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   public static long calc(int l, int r, long[] array, long[] maxes, long[] xdist){
      long ret = 1L;
      if(r == array.length-1 && xdist[array.length-1] != 0) r--;
      
      int num0after = 0;
      for(int k = r+1; k < array.length; k++){
         if(xdist[k] == 0) num0after++;
         else break;
      }
      
      for(int k = r; k >= l; k--){
         for(int j = k; j >= l; j--){
            ret++;
            if(k == r) ret += Math.max(0,num0after-1);
            if(xdist[j] == maxes[j]) break;
         }
      }
      return ret;
   }
         
   
      
}