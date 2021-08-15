//Eve
import java.io.*;
import java.util.*;

public class Eve{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<Character> genders = new ArrayList<Character>(n+1);
      ArrayList<Integer> moms = new ArrayList<Integer>(n+1);
      
      //make 1-indexed
      genders.add('?');
      moms.add(-1);
      
      for(int k = 1; k <= n; k++){
         char c = f.readLine().charAt(0);
         genders.add(c);
         moms.add(k);
      }
      
      int m = Integer.parseInt(f.readLine());
      
      HashSet<Integer> dead = new HashSet<Integer>();
      
      for(int q = 0; q < m; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         if(a < 0){
            dead.add(a*-1);
         } else {
            int b = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            
            genders.add(c);
            //int mom = genders.get(a) == 'F' ? a : b;
            int mom = b;
            
            moms.add(moms.get(mom));
         }
      }
      
      int x = Integer.parseInt(f.readLine());
      
      //get all moms of people who aren't dead
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();            //stores id of mom -> dna of mom (-1 if unknown)
      for(int k = 1; k < genders.size(); k++){
         if(dead.contains(k)) 
            continue;
         
         if(!hmap.containsKey(moms.get(k))) hmap.put(moms.get(k),-1);
      }         
      
      for(int k = 0; k < x; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int id = Integer.parseInt(st.nextToken());
         int dna = Integer.parseInt(st.nextToken());
         
         if(hmap.containsKey(moms.get(id))) hmap.put(moms.get(id),dna);
      }
      
      if(hmap.size() == 0) out.println("YES");
      if(hmap.size() == 1) out.println("YES");
      else {
         int seendna = -1;
         boolean founddif = false;
         boolean foundunknown = false;
         
         for(int mom : hmap.keySet()){
            int i = hmap.get(mom);
            if(i == -1){
               foundunknown = true;
            } else {
               if(seendna == -1){
                  seendna = i;
               } else if(seendna != i){
                  founddif = true;
                  break;
               }
            }
         }
         
         if(founddif){
            out.println("NO");
         } else if(foundunknown){
            out.println("POSSIBLY");
         } else {
            out.println("YES");
         }
      
         
         
      }  
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}