//make sure to make new file!
import java.io.*;
import java.util.*;

public class B599b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
   
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         char[] array1 = f.readLine().toCharArray();
         char[] array2 = f.readLine().toCharArray();
         
         int[] freq1 = new int[26];
         int[] freq2 = new int[26];
         for(int k = 0; k < n; k++){
            freq1[array1[k]-'a']++;
            freq2[array2[k]-'a']++;
         }
         
         boolean fail = false;
         ArrayList<Character> sort = new ArrayList<Character>(n);
         for(int k = 0; k < 26; k++){
            if((freq1[k] + freq2[k])%2 != 0){
               fail = true;
               break;
            } else {
               char c = k+'a';
               for(int j = 0; j < (freq1[k]+freq2[k])/2; j++){
                  sort.add(c);
               }
            }
         }
         
         if(fail){
            out.println("No");
         }
         
         
         
         
         ArrayList<PriorityQueue<Integer>> indexof1 = new ArrayList<PriorityQueue<Integer>>(26);
         ArrayList<PriorityQueue<Integer>> indexof2 = new ArrayList<PriorityQueue<Integer>>(26);

         for(int k = 0; k < 26; k++){
            indexof1.add(new PriorityQueue<Integer>());
            indexof2.add(new PriorityQueue<Integer>());
         }
         
         for(int k = 0; k < n; k++){
            }
      
      }
      
   
      
      
      
      
      out.close();
   }
   
   public static boolean check(String s1, String s2){
      char a = '?';
      char b = '?';
      boolean secondused = false;
      
      int n = s1.length();
      for(int k = 0; k < n; k++){
         if(s1.charAt(k) == s2.charAt(k)) continue;
         if(secondused) return false;
         if(a != '?'){
            if(s1.charAt(k) != a || s2.charAt(k) != b){
               return false;
            }
            secondused = true;
         
         } else {
            a = s1.charAt(k);
            b = s2.charAt(k);
         }
      }
      
      return (a != '?' && secondused);
   }
      
      
}