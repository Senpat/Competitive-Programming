import java.io.*;
import java.util.*;

class rental3{

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
      
      int total = 0;
      while(!stablecow.isEmpty()){
         
         
         int gallons = stablecow.poll();
         System.out.println(gallons);
         if(stablestore.isEmpty()){
            total+=stableneigh.poll();
         } 
         else {
            if(stableneigh.isEmpty() || calc(gallons,stablestore) > stableneigh.peek()){
               while(gallons > 0 && !stablestore.isEmpty()){
                  Store store = stablestore.poll();
                  if(gallons >= store.getmaxbuy()){
                     total += store.getmaxbuy() * store.getprice();
                     gallons -= store.getmaxbuy();
                  } 
                  else {
                     total += gallons * store.getprice();
                     store.reduce(gallons);
                     stablestore.add(store);
                     gallons = 0;
                  }
               }
            } 
            else {
               total += stableneigh.poll();
            }
         }
      }
      
      System.out.println(total);
      out.println(total);
      out.close();
   }
   
   public static int calc(int gallons, PriorityQueue<Store> pq){
      PriorityQueue<Store> stores = new PriorityQueue<Store>(pq);
      
      
      int total = 0;
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
   
   public void reduce(int v){
      maxbuy -= v;
   }
   
}