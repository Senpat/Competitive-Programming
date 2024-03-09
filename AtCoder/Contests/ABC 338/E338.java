//make sure to make new file!
import java.io.*;
import java.util.*;

public class E338{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      int[] index = new int[2*n+1];
      boolean[] start = new boolean[2*n+1];         //start or end of range
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         int x1 = Math.min(a,b);
         int x2 = Math.max(a,b);
         
         index[x1] = k;
         start[x1] = true;
         index[x2] = k;
         start[x2] = false;
      }
      
      Stack<Integer> stack = new Stack<Integer>();
      boolean fail = false;
      for(int k = 1; k <= 2*n; k++){
         if(start[k]) stack.push(index[k]);
         else{
            if(stack.pop() != index[k]){
               fail = true;
               break;
            }
         }
      }
      
      if(fail){
         out.println("Yes");
      } else {
         out.println("No");
      }
      
      
      
      
      
      out.close();
   }
   
   /*
   public static class Range implements Comparable<Range>{
      int l;
      int r;
      int i;
      
      public Range(int a, int b, int c){
         l = Math.min(a,b);
         r = Math.max(a,b);
         i = c;
      }
      
      //sort by l, r doesn't matter
      public int compareTo(Range range){
         return l-range.l;
      }
   }*/
      
}