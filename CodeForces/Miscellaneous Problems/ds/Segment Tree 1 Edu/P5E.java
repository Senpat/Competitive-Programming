//make sure to make new file!
import java.io.*;
import java.util.*;
//fail, doesn't account for earthquake destroying buildings
public class P5E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Segtree segtree= new Segtree(n);
      segtree.fill(0,0,n-1);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         if(i == 1){
            int index = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            
            segtree.set(0,0,n-1,index,h);
         } else {
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken())-1;
            int p = Integer.parseInt(st.nextToken());
            
            out.println(segtree.earthquake(0,0,n-1,l,r,p));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
   
      int[] strengths;
      ArrayList<TreeSet<Cell>> a;
      
      public Segtree(int size){
         a = new ArrayList<>(4*size);
         for(int k = 0; k < 4*size; k++){
            a.add(new TreeSet<Cell>());
         }
         
         strengths = new int[size];
         Arrays.fill(strengths,Integer.MAX_VALUE);
      }
      
      //fill with max (impossible to destroy buildings that don't exist)
      public void fill(int v, int l, int r){
         
         for(int k = l; k <= r; k++){
            a.get(v).add(new Cell(Integer.MAX_VALUE,k));
         }
         
         if(l != r){
            int mid = (l+r)/2;
            fill(2*v+1,l,mid);
            fill(2*v+2,mid+1,r);
         }  
      }
      
      public void set(int v, int l, int r, int i, int x){
         a.get(v).remove(new Cell(strengths[i],i));
         a.get(v).add(new Cell(x,i));
         
         if(l == r){
            strengths[i] = x;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               set(2*v+1,l,mid,i,x);
            } else {
               set(2*v+2,mid+1,r,i,x);
            }
         }
      }
      
      public int earthquake(int v, int l, int r, int ql, int qr, int p){
         if(l >= ql && r <= qr){
            Cell c = new Cell(p,Integer.MAX_VALUE);         // will be greater than all cells with strength <= p
            System.out.println(l + " " + r + " " + ql + " " + qr + " " + p + " " + a.get(v).headSet(c).size());
            for(Cell cell : a.get(v)){
               System.out.println(cell);
            }
            return a.get(v).headSet(c).size();
         } else if(r < ql || l > qr){
            return 0;
         } else {
            int mid = (l+r)/2;
            
            return earthquake(2*v+1,l,mid,ql,qr,p) + earthquake(2*v+2,mid+1,r,ql,qr,p);
         }
      }
      
      
   }
   
   
   
   
   public static class Cell implements Comparable<Cell>{
      int strength;
      int index;
      public Cell(int a, int b){
         strength = a;
         index = b;
      }
      
      public int compareTo(Cell c){
         if(strength == c.strength) return index-c.index;
         return strength-c.strength;
      }
      public boolean equals(Object o){
         Cell c = (Cell)o;
         return strength == c.strength && index == c.index;
      }
      public String toString(){
         return index + " -> " + strength;
      }
   }
   
      
}