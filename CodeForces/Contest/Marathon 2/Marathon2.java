//Colored Path

import java.io.*;
import java.util.*;

public class Marathon2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());       //num colors
      int h = Integer.parseInt(st.nextToken());       //num bottles in hand
      int u = Integer.parseInt(st.nextToken());       //num chameleons
      
      char[] strip = f.readLine().toCharArray();
      
      String curstring = f.readLine();
      ArrayList<Character> bottles = new ArrayList<Character>(h);
      for(int k = 0; k < h; k++){
         bottles.add(curstring.charAt(k));
      }
      Queue<Character> backpack = new LinkedList<Character>();
      for(int k = h; k < h+s; k++){
         backpack.add(curstring.charAt(k));
      }
      
      TreeMap<Integer, Integer> chams = new TreeMap<Integer,Integer>();          //index on strip,chameleon#
      for(int k = 0; k < u; k++){
         chams.put(k,k);
      }
      ArrayList<Boolean> have = new ArrayList<Boolean>(c);
      for(int k = 0; k < c; k++) have.add(true);
      for(int k = 0; k < s; k++){
         char answer = '?';
         int cham = chams.get(chams.firstKey());
         int index = chams.firstKey()+1;
         
            
         for(char ch : bottles) have.set(ctoi(ch),false);
         
         while(answer == '?'){
            if(!chams.containsKey(index)){
               have.set(ctoi(strip[index]),true);
               if(!have.contains(false)){             //all true
                  answer = strip[index];
                  chams.put(index,cham);
                  chams.remove(chams.firstKey());
                  if(!backpack.isEmpty()) bottles.add(backpack.poll());
                  bottles.remove(new Character(answer));
                  
               }
            }
            index++;
         }
         
         System.out.println(cham + " " + answer);
         
         have = new ArrayList<Boolean>();
         for(int j = 0; j < c; j++) have.add(true);
         
      }
      //System.out.println(chams.firstKey());
      
      
      
      
   }

   
   
   public static int ctoi(char c){
      if(c==0) System.out.println(true);
      return (int)c - 65;
   }  
   
}
      