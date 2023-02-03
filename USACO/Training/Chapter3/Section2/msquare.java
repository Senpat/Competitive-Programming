/*
TASK: msquare
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class msquare{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
      
      String[] splitted = f.readLine().split(" ");
      String target = splitted[0] + splitted[1] + splitted[2] + splitted[3] + splitted[7] + splitted[6] + splitted[5] + splitted[4];
      
      Queue<String> q = new LinkedList<String>();
      q.add("12348765");
      HashMap<String,State> hmap = new HashMap<String,State>();
      hmap.put("12348765",new State("",'?'));
      
      while(!q.isEmpty()){
         String s = q.poll();
         
         if(s.equals(target)){
            break;
         }
         
         String sa = doA(s);
         String sb = doB(s);
         String sc = doC(s);
         
         if(!hmap.containsKey(sa)){
            hmap.put(sa,new State(s,'A'));
            q.add(sa);
         }
         if(!hmap.containsKey(sb)){
            hmap.put(sb,new State(s,'B'));
            q.add(sb);
         }
         if(!hmap.containsKey(sc)){
            hmap.put(sc,new State(s,'C'));
            q.add(sc);
         }
         
         
      }
      
      Stack<Character> stack = new Stack<Character>();
      
      String cur = target;
      while(cur.length() > 0){
         State state = hmap.get(cur);
         if(state.move == '?') break;
         stack.push(state.move);
         cur = state.parent;
      }  
      
      out.println(stack.size());
      
      while(!stack.isEmpty()){
         out.print(stack.pop());
      }
      out.println();
      
        
        
      out.close();
   }
   
   public static class State{
      String parent;
      char move;
      public State(String a, char b){
         parent = a;
         move = b;
      }
   }
   
   public static String doA(String s){
      return s.substring(4) + s.substring(0,4);
   }
   
   public static String doB(String s){
      return "" + s.charAt(3) + s.substring(0,3) + s.charAt(7) + s.substring(4,7);
   }
   
   public static String doC(String s){
      return "" + s.charAt(0) + s.charAt(5) + s.charAt(1) + s.charAt(3) + s.charAt(4) + s.charAt(6) + s.charAt(2) + s.charAt(7);
   }
   
      
}