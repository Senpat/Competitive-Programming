//make sure to make new file!
import java.io.*;
import java.util.*;

public class C638b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         char[] array = f.readLine().toCharArray();
         shuffleArray(array);
         Arrays.sort(array);
         
         HashSet<Character> hset = new HashSet<Character>();
         int[] freq = new int[26];
         for(int k = 0; k < n; k++){
            freq[array[k]-'a']++;
            hset.add(array[k]);
         }
         
         StringJoiner sj = new StringJoiner("");
         
         if(array[m-1] > array[0]){
            sj.add(""+array[m-1]);
         //one letter
         }else if(array[0] == array[n-1]){
            for(int k = 0; k < n/m; k++){
               sj.add(""+array[0]);
            }
            if(n%m != 0) sj.add("" + array[0]);
         } else if(hset.size() == 2 && freq[array[0]-'a'] == m){
            char a = ' ';
            char b = ' ';
            for(char c : hset){
               if(a == ' ') a = c;
               else b = c;
            }
            
            if(a > b){
               char temp = a;
               a = b;
               b = temp;
            }
            
            for(int k = 0; k < freq[a-'a']/m; k++){
               sj.add("" + a);
            }
            if(freq[a-'a']%m != 0)sj.add("" + a);
            
            int m2 = m;
            if(freq[a-'a']%m != 0){
               m2 = freq[a-'a']%m;
            }
            for(int k = 0; k < freq[b-'a']/m2; k++){
               sj.add("" + b);
            }
            if(freq[b-'a']%m2 != 0) sj.add("" + b);
         }
         
         else {
            sj.add(""+array[0]);
            for(int k = m; k < n; k++){
               sj.add("" + array[k]);
            }
         }
         
         out.println(sj.toString());
                  
            
            
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static void shuffleArray(char[] arr){
      int n = arr.length;
      Random rnd = new Random();
      for(int i=0; i<n; ++i){
         char tmp = arr[i];
         int randomPos = i + rnd.nextInt(n-i);
         arr[i] = arr[randomPos];
         arr[randomPos] = tmp;
      }   
   }

   
      
}