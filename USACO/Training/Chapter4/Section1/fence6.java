/*
TASK: fence6
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class fence6{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("fence6.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] node = new int[n+1][2];        //node[x][0] is node id of left endpoint of xth segment, node[x][1] is right endpoint
      for(int k = 1; k <= n; k++){
         node[k][0] = -1;
         node[k][1] = -1;
      }
      int[] weight = new int[n+1];
      
      int nodei = 1;
      for(int k = 1; k <= n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         int n1 = Integer.parseInt(st.nextToken());
         int n2 = Integer.parseInt(st.nextToken());     
         
         weight[i] = w;
         
         int[] a1 = new int[n1];
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n1; j++){
            a1[j] = Integer.parseInt(st.nextToken());
         }
         int[] a2 = new int[n2];
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n2; j++){
            a2[j] = Integer.parseInt(st.nextToken());
         }
         
         if(node[i][0] != -1 && node[i][1] != -1) continue;
         
         if(node[i][0] != -1 || node[i][1] != -1){
            //endpoint to fill
            int unfilled = node[i][0] == -1 ? 0 : 1;
            int filled = 1-unfilled;
            //check to fill with n1 or n2
            boolean filln1 = true;
            for(int j = 0; j < n1; j++){
               if(node[a1[j]][0] == node[i][filled] || node[a1[j]][1] == node[i][filled]){
                  filln1 = false;
                  break;
               }
            }
            
            node[i][unfilled] = nodei;
            if(filln1){
               for(int j = 0; j < n1; j++){
                  if(node[a1[j]][0] == -1) node[a1[j]][0] = nodei;
                  else if(node[a1[j]][1] == -1) node[a1[j]][1] = nodei;
               }
            } else {
               for(int j = 0; j < n2; j++){
                  if(node[a2[j]][0] == -1) node[a2[j]][0] = nodei;
                  else if(node[a2[j]][1] == -1) node[a2[j]][1] = nodei;
               }
            }
            
            nodei++;
         } else{
            //fill both
            node[i][0] = nodei;
            node[i][1] = nodei+1;
            
            for(int j = 0; j < n1; j++){
               if(node[a1[j]][0] == -1) node[a1[j]][0] = node[i][0];
               else if(node[a1[j]][1] == -1) node[a1[j]][1] = node[i][0];
            }
            
            for(int j = 0; j < n2; j++){
               if(node[a2[j]][0] == -1) node[a2[j]][0] = node[i][1];
               else if(node[a2[j]][1] == -1) node[a2[j]][1] = node[i][1];
            }
            
            nodei+=2;
         }
         
         
      }
        
      int[][] adj = new int[nodei][nodei];
      for(int k = 0; k < nodei; k++) Arrays.fill(adj[k],Integer.MAX_VALUE);
      
      for(int k = 1; k <= n; k++){
         adj[node[k][0]][node[k][1]] = weight[k];
         adj[node[k][1]][node[k][0]] = weight[k];
      }
      
      //get smallest cycle
      int answer = Integer.MAX_VALUE;
      for(int start = 1; start < nodei; start++){
         PriorityQueue<State> pq = new PriorityQueue<State>();
         pq.add(new State(start,-1,0));
         
         int[] dist = new int[nodei];
         Arrays.fill(dist,Integer.MAX_VALUE);
         dist[start] = 0;
         
         while(!pq.isEmpty()){
            State s = pq.poll();
            
            if(dist[s.v] != s.d) continue;
            
            for(int nei = 1; nei < nodei; nei++){
               
               if(adj[s.v][nei] == Integer.MAX_VALUE) continue;
               if(nei == s.p) continue;
               if(nei != s.p && dist[nei] != Integer.MAX_VALUE){
                  answer = Math.min(answer,dist[s.v] + dist[nei] + adj[s.v][nei]);
               }
               
               int newd = s.d + adj[s.v][nei];
               if(newd < dist[nei]){
                  dist[nei] = newd;
                  pq.add(new State(nei,s.v,newd));
               }
            }
         }
      }
      
      
      System.out.println(answer);
      out.println(answer);
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;
      int p;
      int d;
      public State(int a, int b, int c){
         v = a;
         p = b;
         d = c;
      }
      public int compareTo(State s){
         return d-s.d;
      }
   }
      
}