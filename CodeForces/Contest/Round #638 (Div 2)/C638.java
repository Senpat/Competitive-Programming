//make sure to make new file!
import java.io.*;
import java.util.*;
//WRONG
public class C638{
   
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
         
         int[] freq = new int[26];
         for(int k = 0; k < n; k++){
            freq[array[k]-'a']++;
         }
         
         ArrayList<ArrayList<Character>> answer = new ArrayList<ArrayList<Character>>(m);
         for(int k = 0; k < m; k++) answer.add(new ArrayList<Character>());
         
         if(array[m-1] > array[0]){
            out.println(array[m-1]);
         } else {
            
            int top = m;
            for(int k = 0; k < 26; k++){
               if(freq[k] == 0) 
                  continue;
               
               for(int j = 0; j < freq[k]/top; j++){
                  for(int h = 0; h < top; h++){
                     answer.get(h).add((char)('a'+k));
                  }
               }
               
               for(int j = 0; j < freq[k]%top; j++){
                  answer.get(j).add((char)('a'+k));
               }
               
               if(freq[k]%top != 0) top = freq[k]%top;
            }
            StringJoiner sj = new StringJoiner("");
            for(char c : answer.get(0)){
               sj.add("" + c);
            }
          
            out.println(sj.toString());
         }
          
         
                  
            
            
         
         
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