//make sure to make new file!
import java.io.*;
import java.util.*;
//greedy
/*
fails on case 
3 3 7
10 100
9 1
8 1
7 7
1 8
1 9
1 10
(7 7 is not in initial allocation but it is in final allocation)
*/
public class P2426{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      Pair[] pairs = new Pair[n];
      
      //get biggest values of a
      PriorityQueue<Pair> pq1 = new PriorityQueue<Pair>((p1,p2) -> comp(p2.a,p1.a));
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         int ai = Integer.parseInt(st.nextToken());
         int bi = Integer.parseInt(st.nextToken());
         
         pairs[k] = new Pair(ai,bi);
         pq1.add(new Pair(ai,bi));
      }
      
      //get biggest value of b-a (biggest gain of switch from a to b)
      PriorityQueue<Pair> pqa = new PriorityQueue<Pair>((p1,p2) -> comp((p2.b-p2.a),(p1.b-p1.a)));
      for(int k = 0; k < a; k++) pqa.add(pq1.poll());
      
      //get biggest value of b
      PriorityQueue<Pair> pq2 = new PriorityQueue<Pair>((p1,p2) -> comp(p2.b,p1.b));
      while(!pq1.isEmpty()) pq2.add(pq1.poll());
      
      //get biggest value of a-b
      PriorityQueue<Pair> pqb = new PriorityQueue<Pair>((p1,p2) -> comp((p2.a-p2.b),(p1.a-p1.b)));
      for(int k = 0; k < b; k++) pqb.add(pq2.poll());
      
      if(a > 0 && b > 0){
         while(true){
            Pair pa = pqa.peek();
            Pair pb = pqb.peek();
            
            if(pa.b + pb.a > pa.a + pb.b){
               pqa.poll();
               pqb.poll();
               pqa.add(pb);
               pqb.add(pa);  
            } else {
               break;
            }
         }
      }
      
      long answer = 0L;
      while(!pqa.isEmpty()){
         answer += (long)pqa.poll().a;
      }
      while(!pqb.isEmpty()){
         answer += (long)pqb.poll().b;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int comp(int x, int y){
      if(x > y) return 1;
      if(x < y) return -1;
      return 0;
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
      
   }
   
      
}