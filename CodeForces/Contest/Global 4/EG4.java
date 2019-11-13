//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, semi-t 
public class EG4{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      int n = s.length;
      
      ArrayList<Character> answer = new ArrayList<Character>();
      char middle = 'd';
      for(int k = 0; k < n/2; k+=2){
         int a = k;
         int b = k+1;
         int c = n-k-2;
         int d = n-k-1;
         if(a==c){
            middle = s[a];
            break;
         } else if(b==c){
            middle = s[b];
            break;
         } else {
            answer.add(find(s[a],s[b],s[c],s[d]));
         }
      }
      
      for(int k = 0; k < answer.size(); k++){
         out.print(answer.get(k));
      }
      if(middle != 'd') out.print(middle);
      for(int k = answer.size()-1; k >= 0; k--){
         out.print(answer.get(k));
      }
      
      
      
      
      out.close();
   }
   
   public static char find(char a, char b, char c, char d){
      HashSet<Character> hs = new HashSet<Character>();
      hs.add(a);
      hs.add(b);
      if(hs.contains(c)) return c;
      if(hs.contains(d)) return d;
      return '|';
   }
   
      
}