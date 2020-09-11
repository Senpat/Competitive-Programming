//Geometers Anonymous Club
import java.io.*;
import java.util.*;
//minkowski
//wikipedia
//got problem from https://usaco-guide.vercel.app/plat/convex-hull
public class F1195{

   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      ArrayList<ArrayList<Point>> alist = new ArrayList<ArrayList<Point>>(n);
      
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(f.readLine());
         alist.add(new ArrayList<Point>());
         for(int j = 0; j < i; j++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            alist.get(k).add(new Point(a,b));
         }
      }
      
      ArrayList<ArrayList<Slope>> slopes = new ArrayList<ArrayList<Slope>>(n);
      
      for(int k = 0; k < n; k++){
         slopes.add(new ArrayList<Slope>());
         for(int j = 0; j < alist.get(k).size()-1; j++){
            slopes.get(k).add(getslope(alist.get(k).get(j),alist.get(k).get(j+1)));
         }
         slopes.get(k).add(getslope(alist.get(k).get(alist.get(k).size()-1),alist.get(k).get(0)));
      }
      
      HashMap<Slope,Integer> last = new HashMap<Slope,Integer>();
      ArrayList<HashMap<Slope,Integer>> next = new ArrayList<HashMap<Slope,Integer>>();
      
      bits = new int[n+1];             //1-indexed!!!
      
      for(int k = 0; k < n; k++){
         next.add(new HashMap<Slope,Integer>());
         for(int j = 0; j < slopes.get(k).size(); j++){
            Slope s = slopes.get(k).get(j);
            if(last.containsKey(s)){
               next.get(last.get(s)).put(s,k);
               last.put(s,k);
            } else {
               update(k+1,1);
               last.put(s,k);
            }
         }
      }  
      
      PriorityQueue<Query> queries = new PriorityQueue<Query>();
      int q = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < q; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         queries.add(new Query(k,l,r));
      }
      
      int[] answer = new int[q];
      
      for(int k = 0; k < n; k++){
         while(!queries.isEmpty() && queries.peek().l == k){
            Query qu = queries.poll();
            answer[qu.i] = psum(qu.r+1);
         }
         
         for(int j = 0; j < slopes.get(k).size(); j++){
            Slope s = slopes.get(k).get(j);
            
            update(k+1,-1);
            if(next.get(k).containsKey(s)){
               update(next.get(k).get(s)+1,1);
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < q; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj);
      
      
      
      out.close();
   }
   
   
   
   public static int gcd(int a, int b){
      if(a % b == 0) return b;
      if(b % a == 0) return a;
      if(a > b){
         return gcd(b,a%b);
      } else {
         return gcd(a,b%a);
      }
   }
      
   
   public static Slope getslope(Point a, Point b){
      int dx = a.x-b.x;
      int dy = a.y-b.y;
      
      if(dx == 0){
         return new Slope(dx,dy/Math.abs(dy));
      }
      
      if(dy == 0){
         return new Slope(dx/Math.abs(dx),dy);
      }
      
      int gcd = gcd(Math.abs(dx),Math.abs(dy));
      
      dx /= gcd;
      dy /= gcd;
      
      return new Slope(dx,dy);
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
   }
   
   public static class Slope{
      int dx;
      int dy;
      
      public Slope(int a, int b){
         dx = a;
         dy = b;
      }
      
      public int hashCode(){
         return toString().hashCode();
      }
      
      public boolean equals(Object o){
         Slope p = (Slope)o;
         return dx == p.dx && dy == p.dy;
      }
      
      public String toString(){
         return dx + " " + dy;
      }
   }
   
   public static class Query implements Comparable<Query>{
      int i;
      int l;
      int r;
      
      public Query(int a, int b, int c){
         i = a;
         l = b;
         r = c;
      }
      public int compareTo(Query q){
         //sort by l
         return l-q.l;
      }
   }
   
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }
      
}