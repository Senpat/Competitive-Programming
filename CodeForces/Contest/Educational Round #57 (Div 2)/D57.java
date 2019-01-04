//make sure to make new file!
import java.io.*;
import java.util.*;
//stupid
public class D57{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      ArrayList<Word> words = new ArrayList<Word>();
      
      for(int k = 0; k < n; k++){
         long i = Long.parseLong(st.nextToken());
         if(s.charAt(k) != 'h' && s.charAt(k) != 'a' && s.charAt(k) != 'r' && s.charAt(k) != 'd') continue;
         
         words.add(new Word(s.charAt(k),i));
      }
      
      //calculate critical indeces
      
      HashMap<Character,Boolean> map = new HashMap<Character,Boolean>();
      
      map.put('h',false);
      map.put('a',false);
      map.put('r',false);
      map.put('d',false);
      
      ArrayList<Word> words1 = new ArrayList<Word>();
      
      
      
      for(int k = 0; k < words.size(); k++){
         char c = words.get(k).c;
         
         if(c == 'a' && !map.get('h')) continue;
         if(c == 'r' && (!map.get('h') || !map.get('a') )) continue;
         if(c == 'd' && (!map.get('h') || !map.get('a') || !map.get('r') ))continue;
         
         map.put(c,true);
         words1.add(words.get(k));
      }
      
      if(!map.get('h') || !map.get('a') || !map.get('r') || !map.get('d')){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      //check going backward
         
      map.put('h',false);
      map.put('a',false);
      map.put('r',false);
      map.put('d',false);
      
      ArrayList<Word> words2 = new ArrayList<Word>();
      
      for(int k = words1.size()-1; k >= 0; k--){
         char c = words1.get(k).c;
         
         if(c == 'r' && !map.get('d')) continue;
         if(c == 'a' && (!map.get('r') || !map.get('d')) ) continue;
         if(c == 'h' && (!map.get('a') || !map.get('r') || !map.get('d'))) continue;
         
         map.put(c,true);
         words2.add(words1.get(k));
      }
      
      if(!map.get('h') || !map.get('a') || !map.get('r') || !map.get('d')){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      
      
      long h = 0L;
      long a = 0L;
      long r = 0L;
      long d = 0L;
      
      for(Word w : words2){
         if(w.c == 'h') h+=w.x;
         if(w.c == 'a') a+=w.x;
         if(w.c == 'r') r+=w.x;
         if(w.c == 'd') d+=w.x;
      }
      
      long min = h;
      min = Math.min(min,a);
      min = Math.min(min,r);
      min = Math.min(min,d);
      
      
      out.println(min);
      
      
      
      out.close();
   }
   
   public static class Word{
      char c;
      long x;
      public Word(char a,long b){
         c = a;
         x = b;
      }
      public String toString(){
         return "" + c + " " + x;
      }
   }
   
}