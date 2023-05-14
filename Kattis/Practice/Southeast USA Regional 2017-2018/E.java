//make sure to make new file!
import java.io.*;
import java.util.*;

public class E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      ArrayList<String> q1 = new ArrayList<String>();
      while(true){
         String s = f.readLine();
         if(s.equals("E")) break;
         q1.add(s);
      }
      ArrayList<String> q2 = new ArrayList<String>();
      while(true){
         String s = f.readLine();
         if(s.equals("E")) break;
         q2.add(s);
      }
      
      Output o1 = calc(q1);
      Output o2 = calc(q2);
      
      if(o1.equals(o2)){
         out.println(0);
      } else {
         out.println(1);
      }
      
      
      
      
      
      out.close();
   }
   
   public static Output calc(ArrayList<String> q){
      int n = q.size();
      
      //index in final string of added letters
      ArrayList<Letter> letters = new ArrayList<Letter>();
      //index of initial string that got deleted
      ArrayList<Long> deleted = new ArrayList<Long>();
      
      long[][] queries = new long[n][2];              //first element: 0 means i, 1 means d. second number: index
      
      long[] qd = new long[n];         //indeces of d queries
      Arrays.fill(qd,-1);
      
      boolean[] deleteletter = new boolean[n];           //did that delete operation delete an insert operation?
      //update letters
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(q.get(k));
         
         ArrayList<Letter> newletters = new ArrayList<Letter>();
         
         char qt = st.nextToken().charAt(0);
         if(qt == 'I'){
            long i = Long.parseLong(st.nextToken());
            char c = st.nextToken().charAt(0);
            
            for(Letter l : letters){
               if(l.i < i) newletters.add(l);
               if(l.i >= i) newletters.add(new Letter(l.i+1,l.c));
            }
            newletters.add(new Letter(i,c));
            
            letters = newletters;
            
            queries[k][0] = 0;
            queries[k][1] = i;
         } else {
            //c == 'D'
            long i = Long.parseLong(st.nextToken());
            
            for(Letter l : letters){
               if(l.i < i) newletters.add(l);
               if(l.i == i) deleteletter[k] = true;
               if(l.i > i) newletters.add(new Letter(l.i-1,l.c));
            }
            
            letters = newletters;
            
            queries[k][0] = 1;
            queries[k][1] = i;
            
            qd[k] = i;  
         }
      }
      
      //update deleted
      for(int k = 0; k < n; k++){
         if(queries[k][0] == 1 && !deleteletter[k]){
            //see where queries[k][1] started
            long i = queries[k][1];
            for(int j = k-1; j >= 0; j--){
               if(queries[j][0] == 1 && queries[j][1] <= i) i++;
               else if(queries[j][0] == 0 && queries[j][1] <= i) i--;
            }
            deleted.add(i);
         }
         
      }
      
      return new Output(letters,deleted);
      
   }
   
   
   
   public static class Output{
      ArrayList<Letter> letters;
      ArrayList<Long> deleted;
      
      public Output(ArrayList<Letter> a, ArrayList<Long> b){
         letters = a;
         deleted = b;
         Collections.sort(letters);
         Collections.sort(deleted);
         
         /*
         System.out.println("Output: ");
         System.out.println("letters: ");
         for(Letter l : letters){
            System.out.println(l.i + " " + l.c);
         }
         System.out.println("deleted: ");
         for(long l : deleted){
            System.out.println(l);
         }*/
      }
      
      public boolean equals(Output o){
      
         if(letters.size() != o.letters.size()) return false;
         for(int k = 0; k < letters.size(); k++){
            if(!letters.get(k).equals(o.letters.get(k))) return false;
         }
         if(deleted.size() != o.deleted.size()) return false;
         for(int k = 0; k < deleted.size(); k++){
            //System.out.println(deleted.get(k) + " " + o.deleted.get(k));
            if((long)deleted.get(k) != (long)o.deleted.get(k)) return false;
         }
         return true;
      }
   }
         
   
   
   public static class Letter implements Comparable<Letter>{
      long i;
      char c;
      public Letter(long a, char b){
         i = a;
         c = b;
      }
      
      public int compareTo(Letter l){
         if(i > l.i) return 1;
         if(i < l.i) return -1;
         return 0;
      }
      
      public boolean equals(Letter l){
         return i == l.i && c == l.c;
      }
   }
}