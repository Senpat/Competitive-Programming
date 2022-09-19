
//make sure to make new file!
import java.io.*;
import java.util.*;

public class B126{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 32768;
      
      int[] answer = new int[N];
      Arrays.fill(answer,15);
      answer[0] = 0;
      
      Queue<Pair> q = new LinkedList<Pair>();
      q.add(new Pair(0,0));
      
      while(!q.isEmpty()){
         Pair p = q.poll();
         
         if(answer[p.x] < p.t) continue;
         
         int next;
         //divide by 2
         if(p.x % 2 == 0){
            next = p.x/2;
            if(answer[next] > p.t+1){
               answer[next] = p.t+1;
               q.add(new Pair(next,p.t+1));
            }
            
            next = p.x + (N-p.x)/2;
            if(answer[next] > p.t+1){
               answer[next] = p.t+1;
               q.add(new Pair(next,p.t+1));
            }
         }
         
         //subtract 1
         
         next = p.x-1;
         if(next < 0) next += N;
         
         if(answer[next] > p.t+1){
            answer[next] = p.t+1;
            q.add(new Pair(next,p.t+1));
         }
         
      }
      
      
      
      
      
      
      
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[array[k]]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      int x;
      int t;
      public Pair(int a, int b){
         x = a;
         t = b;
      }
   }
   
      
}