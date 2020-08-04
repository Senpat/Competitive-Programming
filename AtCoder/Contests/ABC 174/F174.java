//make sure to make new file!
import java.io.*;
import java.util.*;

public class F174{

   public static int BLOCK;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      BLOCK = (int)(Math.sqrt(n))+1;
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] answer = new int[q];
      
      Query[] queries = new Query[q];
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         queries[k] = new Query(l,r,k);
      }
      
      Arrays.sort(queries);
      
      HashMap<Integer,Integer> nums = new HashMap<Integer,Integer>();
      
      int moleft = 0;
      int moright = -1;
      
      for(int k = 0; k < q; k++){
         int l = queries[k].l;
         int r = queries[k].r;
         
         while(moright < r){
            moright++;
            if(nums.containsKey(array[moright])){
               nums.put(array[moright],nums.get(array[moright])+1);
            } else {
               nums.put(array[moright],1);
            }
         }
         
         while(moright > r){
            nums.put(array[moright],nums.get(array[moright])-1);
            if(nums.get(array[moright]) == 0) nums.remove(array[moright]);
            moright--;
         }
         
         while(moleft < l){
            nums.put(array[moleft],nums.get(array[moleft])-1);
            if(nums.get(array[moleft]) == 0) nums.remove(array[moleft]);
            moleft++;
         } 
         
         while(moleft > l){
            moleft--;
            if(nums.containsKey(array[moleft])){
               nums.put(array[moleft],nums.get(array[moleft])+1);
            } else {
               nums.put(array[moleft],1);
            }
         }
         
         answer[queries[k].i] = nums.size();
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < q; k++){
         sj.add("" + answer[k]);
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static class Query implements Comparable<Query>{
      int l;
      int r;
      int i;
      public Query(int a, int b, int c){
         l = a;
         r = b;
         i = c;
      }
      public int compareTo(Query q){
         if(l/BLOCK != q.l/BLOCK) return l/BLOCK-q.l/BLOCK;
         return r-q.r;
      }
   }
   
      
}