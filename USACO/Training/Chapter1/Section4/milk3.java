/*
USER: pgz11901
TASK: milk3
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class milk3{
   
   public static boolean[][][] been;
   public static int a,b,c;
   public static int[] maxcap;
   public static boolean[] answers;
   //public static int[] array;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());
      maxcap = new int[3];
      maxcap[0] = a;
      maxcap[1] = b;
      maxcap[2] = c;
      
      answers = new boolean[c+1];
      answers[c] = true;
      
      bfs();
      
      for(int k = 0; k < answers.length-1; k++){
         if(answers[k]){
            System.out.print(k + " ");
            out.print(k+ " ");
         }
      }
      
      
      System.out.println(c);
      out.println(c);
      out.close();
      
   }
   
   public static void bfs(){
      been = new boolean[a+1][b+1][c+1];
      
      //been[0][0][c] = true;
      
      LinkedList<State> q = new LinkedList<State>();
      q.add(new State(0,0,c));
      
      while(!q.isEmpty()){
         State s = q.poll();
         System.out.println("\t" + s);
         if(!been[s.a][s.b][s.c]){
            
            been[s.a][s.b][s.c] = true;
            
            if(s.a == 0) answers[s.c] = true;
            
            for(int k = 0; k < 3; k++){
               for(int j = 0; j < 3; j++){
                  if(k!=j){                            //pour k into j
                     int[] array = new int[3];
                     for(int z = 0; z < 3; z++){
                        array[z] = s.array[z];
                     }
                     if(s.array[k] + s.array[j] <= maxcap[j]){
                        
                        array[j] += array[k];
                        array[k] = 0;
                        System.out.println(1 + " " + k + " " + j + "   " + new State(array));
                        q.offer(new State(array));
                     } else {
                        array[k] = (s.array[k] + s.array[j]) - maxcap[j];
                        array[j] = maxcap[j];
                        System.out.println(2 + " " + k + " " + j + "   " + new State(array));
                        q.offer(new State(array));
                     }
                  }
               }
             }
            
         }
      }
      
   }
   
   
   
   static class State{
      int a;
      int b;
      int c;
      int[] array;
      
      public State(int x, int y, int z){
         a = x;
         b = y;
         c = z;
         array = new int[3];
         array[0] = a;
         array[1] = b;
         array[2] = c;
      }
      
      public State(int[] i){
         array = i;
         a = array[0];
         b = array[1];
         c = array[2];
      }
      
      public boolean equals(State sta){
         return a == sta.a && b == sta.b && c == sta.c;
      }
      
      public String toString(){
         return a + " " + b + " " + c;
      }
   }
        
   
   
}