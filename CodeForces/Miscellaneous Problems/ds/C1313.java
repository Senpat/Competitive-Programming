//make sure to make new file!
import java.io.*;
import java.util.*;
//should work on C1 and C2
public class C1313{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      int[] lastmin = new int[n];
      Arrays.fill(lastmin,-1);
      
      Stack<Pair> stk = new Stack<Pair>();
      stk.add(new Pair(array[0],0));
      
      for(int k = 1; k < n; k++){
         
         while(!stk.isEmpty() && stk.peek().v > array[k]){
            stk.pop();
         }
         
         if(stk.isEmpty()){
            lastmin[k] = -1;
         } else {
            lastmin[k] = stk.peek().i;
         }
         
         stk.add(new Pair(array[k],k));
      }
      
      long[] dpleft = new long[n];
      dpleft[0] = array[0];
      
      for(int k = 1; k < n; k++){
         if(lastmin[k] == -1){
            dpleft[k] = array[k]*(k+1);
         } else {
            dpleft[k] = dpleft[lastmin[k]] + array[k]*(k-lastmin[k]);
         }
         //out.println(dpleft[k]);
      }
      
      
      //same as before but backward
      int[] firstmin = new int[n];
      Arrays.fill(firstmin,-1);
      
      stk = new Stack<Pair>();
      stk.add(new Pair(array[n-1],n-1));
      
      for(int k = n-2; k >= 0; k--){
         
         while(!stk.isEmpty() && stk.peek().v > array[k]){
            stk.pop();
         }
         
         if(stk.isEmpty()){
            firstmin[k] = -1;
         } else {
            firstmin[k] = stk.peek().i;
         }
         
         stk.add(new Pair(array[k],k));
      }
      
      
      long[] dpright = new long[n];
      dpright[n-1] = array[n-1];
      
      for(int k = n-2; k >= 0; k--){
         if(firstmin[k] == -1){
            dpright[k] = array[k]*(n-k);
         } else {
            dpright[k] = dpright[firstmin[k]] + array[k]*(firstmin[k]-k);
         }
         //out.println(dpright[k]);
      }
      
      long max = 0;
      int maxindex = 0;
      
      for(int k = 0; k < n; k++){
         if(dpleft[k] + dpright[k]-array[k] >= max){
            max = dpleft[k] + dpright[k] - array[k];
            maxindex = k;
         }
      }
      
      ArrayList<Long> left = new ArrayList<Long>();
      ArrayList<Long> right = new ArrayList<Long>();
      
      left.add(array[maxindex]);
      
      for(int k = maxindex-1; k >= 0; k--){
         left.add(Math.min(array[k],left.get(left.size()-1)));
      }
      
      right.add(array[maxindex]);
      for(int k = maxindex+1; k < n; k++){
         right.add(Math.min(array[k],right.get(right.size()-1)));
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = left.size()-1; k >= 0; k--){
         sj.add("" + left.get(k));
      }
      for(int k = 1; k < right.size(); k++){
         sj.add("" + right.get(k));
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      long v;
      int i;
      public Pair(long a, int b){
         v = a;
         i = b;
      }
   }
      
      
}