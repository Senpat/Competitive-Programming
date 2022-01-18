//make sure to make new file!
import java.io.*;
import java.util.*;

public class E733{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         String s = f.readLine();
         char[] array = s.toCharArray();
         int n = array.length;
         
         int[] freq = new int[26];
         for(int k = 0; k < n; k++){
            freq[array[k]-'a']++;
         }
         
         ArrayList<Letter> letters = new ArrayList<Letter>();
         int unique = -1;
         for(int k = 0; k < 26; k++){
            if(freq[k] > 0){
               letters.add(new Letter((char)('a'+k),freq[k]));
               if(freq[k] == 1 && unique == -1){
                  unique = letters.size()-1;
               }
            }
         }
         
         if(letters.size() == 1){
            out.println(s);
            continue;
         }
         
         if(unique != -1){
            letters.get(unique).freq--;
            StringBuilder sb = new StringBuilder("");
            sb.append(letters.get(unique).c);
            for(Letter l : letters){
               for(int k = 0; k < l.freq; k++){
                  sb.append(l.c);
               }
            }
            out.println(sb.toString());
            continue;
         }
         
         //either aa_a_a_... or abbbbb..______ where _ are sorted
         int afreq = letters.get(0).freq;
         char ach = letters.get(0).c;
         if((afreq-2) <= n-afreq){
            //aa_a_a_...
            char[] answer = new char[n];
            Arrays.fill(answer,'?');
            //going to be at leat 2
            answer[0] = ach;
            answer[1] = ach;
            int i = 3;
            for(int k = 0; k < afreq-2; k++){
               answer[i] = ach;
               i+=2;
            }
            
            i = 2;
            for(int k = 1; k < letters.size(); k++){
               for(int j = 0; j < letters.get(k).freq; j++){
                  answer[i] = letters.get(k).c;
                  while(i < n && answer[i] != '?') i++;
               }
            }
            
            out.println(new String(answer));
         } else {
            StringBuilder sb = new StringBuilder("");
            /*
            sb.append(letters.get(1).c);
            letters.get(1).freq--;
            for(Letter l : letters){
               for(int k = 0; k < l.freq; k++){
                  sb.append(l.c);
               }
            }
            out.println(sb.toString());
            */
            if(letters.size() == 2){
               sb.append(letters.get(0).c);
               for(int k = 0; k < letters.get(1).freq; k++){
                  sb.append(letters.get(1).c);
               }
               for(int k = 0; k < letters.get(0).freq-1; k++){
                  sb.append(letters.get(0).c);
               }
            } else {
               //abaaaaaaaaaaac__________
               sb.append(letters.get(0).c);
               sb.append(letters.get(1).c);
               for(int k = 0; k < letters.get(0).freq-1; k++){
                  sb.append(letters.get(0).c);
               }
               sb.append(letters.get(2).c);
               letters.get(0).freq = 0;
               letters.get(1).freq--;
               letters.get(2).freq--;
               for(Letter l : letters){
                  for(int k = 0; k < l.freq; k++){
                     sb.append(l.c);
                  }
               }
            }
               
            
            
            out.println(sb.toString());
         }
         
         
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static class Letter{
      char c;
      int freq;
      public Letter(char a, int b){
         c = a;
         freq = b;
      }
   }
      
}