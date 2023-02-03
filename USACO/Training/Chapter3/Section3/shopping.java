/*
TASK: shopping
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class shopping{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
      
      StringTokenizer st;
      
      int s = Integer.parseInt(f.readLine());
      
      Offer[] offers = new Offer[s];
      for(int k = 0; k < s; k++){
         offers[k] = new Offer(f.readLine());
      }
      
      int n = Integer.parseInt(f.readLine());
      
      HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
      HashMap<Integer,Integer> costs = new HashMap<Integer,Integer>();
      
      ArrayList<Integer> items = new ArrayList<Integer>();
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         int c = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         int p = Integer.parseInt(st.nextToken());
         
         freq.put(c,x);
         costs.put(c,p);
         
         items.add(c);
      }
      
      //dummy values
      int curp = 1000;
      while(items.size() < 5){
         items.add(curp);
         freq.put(curp,0);
         costs.put(curp,0);
         curp++;
      }
      
      //for all offers, add dummy values
      for(int o = 0; o < s; o++){
         for(int k = 0; k < 5; k++){
            if(!offers[o].items.containsKey(items.get(k))){
               offers[o].items.put(items.get(k),0);
            }
         }
      }
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < n; k++){
         map.put(items.get(k),k);
      }
      
      int N = 5;
      int[][][][][] dp = new int[N+1][N+1][N+1][N+1][N+1];
      
      for(int k = 0; k <= N; k++){
         for(int j = 0; j <= N; j++){
            for(int h = 0; h <= N; h++){
               for(int g = 0; g <= N; g++){
                  for(int i = 0; i <= N; i++){
                     dp[k][j][h][g][i] = Integer.MAX_VALUE;
                  }
               }
            }
         }  
      }
      
      dp[freq.get(items.get(0))][freq.get(items.get(1))][freq.get(items.get(2))][freq.get(items.get(3))][freq.get(items.get(4))] = 0;
      
      for(int k = N; k >= 0; k--){
         for(int j = N; j >= 0; j--){
            for(int h = N; h >= 0; h--){
               for(int g = N; g >= 0; g--){
                  for(int i = N; i >= 0; i--){
                     int cur = dp[k][j][h][g][i];
                     if(cur == Integer.MAX_VALUE) continue;
                     
                     //buy 1
                     if(k > 0){
                        dp[k-1][j][h][g][i] = Math.min(dp[k-1][j][h][g][i],cur + costs.get(items.get(0)));
                     }
                     if(j > 0){
                        dp[k][j-1][h][g][i] = Math.min(dp[k][j-1][h][g][i],cur + costs.get(items.get(1)));
                     }
                     if(h > 0){
                        dp[k][j][h-1][g][i] = Math.min(dp[k][j][h-1][g][i],cur + costs.get(items.get(2)));
                     }
                     if(g > 0){
                        dp[k][j][h][g-1][i] = Math.min(dp[k][j][h][g-1][i],cur + costs.get(items.get(3)));
                     }
                     if(i > 0){
                        dp[k][j][h][g][i-1] = Math.min(dp[k][j][h][g][i-1],cur + costs.get(items.get(4)));
                     }
                     
                     //check offers
                     for(int o = 0; o < s; o++){
                        boolean check = true;
                        int i0 = offers[o].items.get(items.get(0));
                        int i1 = offers[o].items.get(items.get(1));
                        int i2 = offers[o].items.get(items.get(2));
                        int i3 = offers[o].items.get(items.get(3));
                        int i4 = offers[o].items.get(items.get(4));
                        check &= k >= i0;
                        check &= j >= i1;
                        check &= h >= i2;
                        check &= g >= i3;
                        check &= i >= i4;
                        
                        if(check){
                           dp[k-i0][j-i1][h-i2][g-i3][i-i4] = Math.min(dp[k-i0][j-i1][h-i2][g-i3][i-i4], cur + offers[o].cost);
                        }
                     }
                     
                  }
               }
            }
         }  
      }
      
      out.println(dp[0][0][0][0][0]);
      
        
      out.close();
   }
   
   public static class Offer{
      HashMap<Integer,Integer> items;
      int cost;
      
      public Offer(String s){
         StringTokenizer st = new StringTokenizer(s);
         
         int i = Integer.parseInt(st.nextToken());
         
         items = new HashMap<Integer,Integer>();
         
         for(int k = 0; k < i; k++){
            int item = Integer.parseInt(st.nextToken());
            int freq = Integer.parseInt(st.nextToken());
            
            items.put(item,freq);
         }
         
         cost = Integer.parseInt(st.nextToken());
         
      }
   }
}