import java.io.*;
import java.util.*;

class rental{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("rental.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int numcows = Integer.parseInt(st.nextToken());
      int numstores = Integer.parseInt(st.nextToken());
      int numneigh = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Integer> stablecow = new PriorityQueue<Integer>(numcows,Collections.reverseOrder());
      
      for(int k = 0; k < numcows; k++){
         stablecow.add(Integer.parseInt(f.readLine()));
      }
      
      PriorityQueue<Store> stablestore = new PriorityQueue<Store>(numstores,Collections.reverseOrder());
      
      for(int k = 0; k < numstores; k++){
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         stablestore.add(new Store(Integer.parseInt(st1.nextToken()),Integer.parseInt(st1.nextToken())));
      }
      
      PriorityQueue<Integer> stableneigh = new PriorityQueue<Integer>(numneigh,Collections.reverseOrder());
      
      for(int k = 0; k < numneigh; k++){
         stableneigh.add(Integer.parseInt(f.readLine()));
      }
      
      long previous = -1L;
      long current = 0L;
      
      int initial = Math.max(0,numcows - numneigh);
      
      //int max = 0;
      
      while(current>=previous){
         //System.out.println("hi");
         previous = current;
         PriorityQueue<Integer> cow = new PriorityQueue<Integer>(stablecow);
         PriorityQueue<Store> stores = new PriorityQueue<Store>(stablestore);
         PriorityQueue<Integer> neigh = new PriorityQueue<Integer>(stableneigh);
         
         current = calculate(cow,stores,neigh,initial);
         //System.out.println(previous + " " + current);
         initial++;
         
         
         
      }
      
      System.out.println(previous);
      out.println(previous);
      out.close();
   }
   
   public static long calculate(PriorityQueue<Integer> cow, PriorityQueue<Store> stores, PriorityQueue<Integer> neigh, int i){
      long total = 0L;
      
      long gallons = 0L;
      for(int k = 0; k < i; k++){
         gallons += cow.poll();
      }
      
      
      while(gallons > 0 && !stores.isEmpty()){
         Store store = stores.poll();
         if(gallons >= store.getmaxbuy()){
            total += store.getmaxbuy() * store.getprice();
            gallons -= store.getmaxbuy();
         } 
         else {
            total += gallons * store.getprice();
            
            gallons = 0;
         }
      }
      
      while(!cow.isEmpty() && !neigh.isEmpty()){
         cow.poll();
         total += neigh.poll();
      }
      //System.out.println(total);
      return total; 
      
   }
   
}
         
      
      
      
      
      
            
   


class Store implements Comparable<Store>{
   
   private int maxbuy,price;
   
   public Store(int m, int p){
      maxbuy = m;
      price = p;
      
   }
   
   public int getmaxbuy(){
      return maxbuy;
   }
   
   public int getprice(){
      return price;
   }
   
   public int compareTo(Store s){
      return price - s.getprice();
   }
   
   public String toString(){
      return maxbuy + " " + price;
   }
   
   /*public void reduce(long v){
      maxbuy-=v;
   }*/
   
}