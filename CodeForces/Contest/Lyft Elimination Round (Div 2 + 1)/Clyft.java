//make sure to make new file!
import java.io.*;
import java.util.*;

public class Clyft{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      int[] indexof = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         indexof[array[k]] = k;
      }
      
      ArrayList<HashSet<Integer>> next = new ArrayList<HashSet<Integer>>();
      ArrayList<HashSet<Integer>> prev = new ArrayList<HashSet<Integer>>();
      
      for(int k = 0; k <= n; k++){
         next.add(new HashSet<Integer>());
         prev.add(new HashSet<Integer>());
           
      }
      
      //construct adj lists
      for(int k = 1; k <= n; k++){
         //check pos
         for(int j = k+array[k]; j <= n; j+=array[k]){
            if(array[j]>array[k]){
               next.get(array[k]).add(array[j]);
               prev.get(array[j]).add(array[k]);
            }
         }
         //check neg
         for(int j = k-array[k]; j >= 0; j-=array[k]){
            if(array[j]>array[k]){
               next.get(array[k]).add(array[j]);
               prev.get(array[j]).add(array[k]);
            }
         }
      
      }
      
      char[] answers = new char[n+1];
      
      Arrays.fill(answers, ' ');
      
      Stack<Integer> q = new Stack<Integer>();
      //Queue<Integer> q = new LinkedList<Integer>(
            
      for(int k = 1; k <= n; k++){
         if(next.get(array[k]).size()==0){
            answers[k] = 'B';
            q.add(k);
         }
      }
      
      HashSet<Integer> seen = new HashSet<Integer>();
      
      while(!q.empty()){
         int cur = q.pop();
         int num = array[cur];
         
         if(!seen.contains(cur)){
            out.println(cur);
            seen.add(cur);
            for(int nei : prev.get(num)){
               int k = indexof[nei];
               if(answers[k] != 'A'){
                  if(answers[cur] == 'B') answers[k] = 'A';
                  else answers[k] = 'B';
               }
               q.push(k);
               
            }
         }
         
      }
      
      for(int k = 1; k <= n; k++){
         out.print(answers[k]);
      }
      
      
      
      out.close();
   }
   
}