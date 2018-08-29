/*
USER: pgz11901
TASK: holstein
LANG: JAVA
*/

import java.util.*;
import java.io.*;

class holstein{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
      
      int v = Integer.parseInt(f.readLine());
      
      int[] vitamins = new int[v];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < v; k++){
         vitamins[k] = Integer.parseInt(st.nextToken());
      }
      
      int g = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[g][v];
      
      for(int k = 0; k < g; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < v; j++){
            matrix[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      ArrayList<ArrayList<State>> dp = new ArrayList<ArrayList<State>>();
      
      for(int k = 0; k < g+1; k++){
         dp.add(new ArrayList<State>());
      }
      
      dp.get(0).add(new State(vitamins,0,new ArrayList<Integer>()));
      
      int min = v+1;
      State minstate = null;
      
      for(int k = 1; k <= g; k++){
         System.out.println(k + " " + dp.get(k-1).size());
         for(int p = 0; p < dp.get(k-1).size(); p++){
            //System.out.println(p + " " + dp.get(k-1).size());
            State s = dp.get(k-1).get(p);
            //find yes
            int[] newarray = new int[v];
            for(int j = 0; j < v; j++){
               newarray[j] = Math.max(0,s.array[j]-matrix[k-1][j]);
            }
            
            
            State sta = new State(newarray,s.numused+1,(ArrayList<Integer>)s.added.clone());
            sta.added.add(k);
            if(sta.check()){
               if(sta.numused<min){
                  min = sta.numused;
                  minstate = sta;
               }
            } 
            else {
               dp.get(k).add(sta);
            }
            //find no
            dp.get(k).add(s);
         }
      }
      
      String str = "" + min;
      for(int i : minstate.added){
         str += " " + i;
      }
      
      
      System.out.println(str);
      out.println(str);
      out.close();
       
      
      
   }
   
   public static class State{
      int[] array;
      
      int numused;
      
      ArrayList<Integer> added;
      
      public State(int[] a, int i, ArrayList<Integer> al){
         array = a;
         numused = i;
         added = al;
      }
      
      public boolean check(){
         for(int k = 0; k < array.length; k++){
            if(array[k] > 0){
               return false;
            }
         }
         return true;
      }
      /* 
      public State clone(){
         State s = new State(array.clone(),numused,added.clone());
         return s;
      }*/
   }  
   
   
}