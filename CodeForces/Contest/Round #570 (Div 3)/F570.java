//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, semi-t
public class F570{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int[] array = new int[n];
         HashSet<Integer> nums = new HashSet<Integer>();
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            nums.add(array[k]);
         }
         
         shuffleArray(array);
         Arrays.sort(array);
         
         HashSet<Integer> hset = new HashSet<Integer>();
         
         int added = 0;
         int answer = 0;
         for(int k = n-1; k >= 0 && added<3; k--){
            if(!hset.contains(array[k])){
               added++;
               answer+=array[k];
               finddiv(array[k],hset);
            }
         }
         
         if(array[n-1]%30 == 0 && nums.contains(array[n-1]/2) && nums.contains(array[n-1]/3) && nums.contains(array[n-1]/5)){
            answer = Math.max(answer,array[n-1]/2 + array[n-1]/3 + array[n-1]/5);
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   public static void finddiv(int i, HashSet<Integer> hset){
      
      for(int k = 1; k <= (int)Math.sqrt(i); k++){
         if(i%k == 0){
            hset.add(k);
            hset.add(i/k);
         }
      }
   }
   public static void shuffleArray(int[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            int tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }   
   }
   
      
}