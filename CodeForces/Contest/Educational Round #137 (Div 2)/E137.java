//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt
public class E137{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int p1 = Integer.parseInt(st.nextToken());
      long t1 = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int p2 = Integer.parseInt(st.nextToken());
      long t2 = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int h = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      
      //when refill1 == 0 and refill2 == 0
      int maxdamage = 0;
      
      HashSet<State> seen = new HashSet<State>();
      PriorityQueue<State> pq = new PriorityQueue<State>();
      seen.add(new State(0,0L,0L,0L));
      pq.add(new State(0,0L,0L,0L));
      
      int loops = 0;
      
      long answer = -1L;
      while(!pq.isEmpty()){
         State state = pq.poll();
         //out.println(state.damage + " " + state.time + " " + state.refill1 + " " + state.refill2);
         loops++;
         if(loops % 1000000 == 0){
            out.println(loops + " " + state.damage);
            out.flush();
         }
         if(state.damage >= h){
            answer = state.time;
            break;
         }
         
         if(state.refill1 == 0 && state.refill2 == 0){
            if(state.damage < maxdamage) continue;
            maxdamage = state.damage;
         } else {
            if(state.damage + p1+p2-s <= maxdamage) continue;
         }
         
         //shoot when next player is ready
         long player1 = t1-state.refill1;
         long player2 = t2-state.refill2;
         
         if(player1 < player2){
            State newState = new State(state.damage+p1-s,state.time+player1,0L,state.refill2+player1);
            if(!seen.contains(newState)){
               pq.add(newState);
               seen.add(newState);
            }
         } else if(player1 > player2){
            State newState = new State(state.damage+p2-s,state.time+player2,state.refill1+player2,0L);
            if(!seen.contains(newState)){
               pq.add(newState);
               seen.add(newState);
            }
         }
         
         //shoot when both players are ready
         int newdamage = state.damage+p1+p2-s;
         if(newdamage > maxdamage){
            State newState = new State(newdamage,state.time+Math.max(player1,player2),0L,0L);
            if(!seen.contains(newState)){
               pq.add(newState);
               seen.add(newState);
            }
            //out.println("double: " + newdamage + " " + (state.time+Math.max(player1,player2)));
         }
         
      }
      
      
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int damage;
      long time;
      long refill1;
      long refill2;
      
      int hash;
      
      public State(int a, Long b, Long c, Long d){
         damage = a;
         time = b;
         refill1 = c;
         refill2 = d;
         
         hash = damage ^ b.hashCode() ^ c.hashCode() ^ d.hashCode();
      }
      
      public int compareTo(State s){
         if(time == s.time) return 0;
         if(time < s.time) return -1;
         return 1;
      }
      
      public String toString(){
         return damage + " " + time + " " + refill1 + " " + refill2;
      }
      
      public int hashCode(){
         return hash;
      }
   }
      
   
      
}