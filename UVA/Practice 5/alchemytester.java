//make sure to make new file!
import java.io.*;
import java.util.*;

public class alchemytester{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static HashSet<Integer> seen;
   
   public static int answer;
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      for(int test = 1; test <= 101; test++){
         BufferedReader f = new BufferedReader(new FileReader("alchemyin/alchemy.in" + test));
      
         int n = Integer.parseInt(f.readLine());
      
         Circle[] circles = new Circle[n];
      
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
         
            circles[k] = new Circle(x,y,r,a,b,k+1);
         }
      
         Arrays.sort(circles);
      
         int[] indexof = new int[n+1];
         for(int k = 0; k < n; k++){
            indexof[circles[k].i] = k;
         }
      
      //stores edges of bigger to smaller circles
         adj = new ArrayList<ArrayList<Integer>>();
      
         for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
      
         for(int k = 0; k < n; k++){
            for(int j = k+1; j < n; j++){
               if(circles[k].ischild(circles[j])){
                  adj.get(j).add(k);
                  break;
               }
            }
         }
      
      //add the actual index (not index in sorted array)
         ArrayList<ArrayList<Integer>> order = new ArrayList<ArrayList<Integer>>();
      
         HashSet<Integer> seen = new HashSet<Integer>();
      
         int answer = 0;
         for(int k = n-1; k >= 0; k--){
            if(seen.contains(k)) 
               continue;
         
            ArrayList<Integer> curorder = new ArrayList<Integer>();
         
            Queue<State> q = new LinkedList<State>();
            q.add(new State(k,0));
         
            while(!q.isEmpty()){
               State s = q.poll();
               seen.add(s.v);
            
               int max = circles[s.v].getmax(s.depth);
            
               answer += max;
            
               if(curorder.size() == 0) curorder.add(circles[s.v].i);
               else {
                  int insert = -1;
                  int greater = s.depth;
                  for(int j = 0; j < curorder.size(); j++){
                  //check to insert at k
                     if(circles[s.v].calc(greater) == max && curorder.get(j) > circles[s.v].i){
                        insert = j;
                        break;
                     }
                  
                     if(circles[s.v].ischild(circles[indexof[curorder.get(j)]])) greater--;
                  }
               
                  if(insert == -1){
                     if(circles[s.v].calc(0) == max) insert = curorder.size();
                     else {
                        greater = 0;
                        for(int j = curorder.size()-1; j >= 0; j--){
                           if(circles[s.v].ischild(circles[indexof[curorder.get(j)]])) greater++;
                        
                           if(circles[s.v].calc(greater) == max){
                              insert = j;
                              break;
                           }
                        }
                     }
                  }
                
                  curorder.add(insert,circles[s.v].i);
               }
            
               for(int nei : adj.get(s.v)){
                  q.add(new State(nei,s.depth+1));
               }
            }
         
            order.add(curorder);
         }
      
         
      
      
      //merge all curorders
         int[] pointers = new int[order.size()];
         PriorityQueue<Mergestate> pq = new PriorityQueue<Mergestate>();
         for(int k = 0; k < order.size(); k++){
            pointers[k] = 1;
            pq.add(new Mergestate(order.get(k).get(0),k));
         }
      
         StringJoiner sj = new StringJoiner(" ");
         while(!pq.isEmpty()){
            Mergestate ms = pq.poll();
            sj.add("" + ms.i);
            if(pointers[ms.index] < order.get(ms.index).size()){
               pq.add(new Mergestate(order.get(ms.index).get(pointers[ms.index]),ms.index));
               pointers[ms.index]++;
            }
         }
      
         BufferedReader fanswer = new BufferedReader(new FileReader("alchemyout/alchemy.out"+test));
         System.out.println(test);
         if(answer != Integer.parseInt(fanswer.readLine())) System.out.println("WRONG ANSWER");
         if(!sj.toString().equals(fanswer.readLine())) System.out.println("WRONG ORDER");
            
         
      
      
      
      }
   }
   
   public static class Mergestate implements Comparable<Mergestate>{
      int i;
      int index;
      
      public Mergestate(int a, int b){
         i = a;
         index = b;
      }
      
      public int compareTo(Mergestate m){
         return i-m.i;
      }
   }
   
   
   public static class State{
      int v;
      int depth;
      
      public State(int a, int b){
         v = a;
         depth = b;
      }
   }
   
   public static class Circle implements Comparable<Circle>{
      int x;
      int y;
      int r;
      int a;
      int b;
      int i;
      public Circle(int q, int w, int e, int s, int d, int index){
         x = q;
         y = w;
         r = e;
         a = s;
         b = d;
         i = index;
      }
      
      public int calc(int x){
         return (a+b)*(x/2) + a*(x%2);
      }
      
      public int getmax(int upper){
         int max = 0;
         int sum = 0;
         for(int k = 0; k < upper; k++){
            if(k%2 == 0) sum += a;
            else sum += b;
            if(max < sum){
               max = sum;
            }
         }
         return max;
      }
      
      public boolean ischild(Circle c){
         return (x-c.x)*(x-c.x) + (y-c.y)*(y-c.y) <= c.r*c.r;
      }
      
      public int compareTo(Circle c){
         return r-c.r;
      }
      
      public String toString(){
         return "(" + x + ", " + y + ") " + r;
      }
   }
      
}