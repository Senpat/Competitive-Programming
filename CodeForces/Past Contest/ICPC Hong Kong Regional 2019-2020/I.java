//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial, cool alarms trick
public class I{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int q = fs.nextInt();
      
      int last = 0;
      
      ArrayList<Member> members = new ArrayList<Member>();
      int mid = 0;
      
      ArrayList<Obs> obs = new ArrayList<Obs>(n+1);
      for(int k = 0; k <= n; k++) obs.add(new Obs());
      
      for(int t = 0; t < q; t++){
         int qt = fs.nextInt();
         
         if(qt == 1){
            long y = (long)(fs.nextInt() ^ last);
            int on = fs.nextInt();
            
            int[] qi = new int[on];
            for(int k = 0; k < on; k++){
               qi[k] = fs.nextInt() ^ last;
            }
            
            Member member = new Member(mid,y,qi);
            
            //add alarms
            for(int k = 0; k < on; k++){
               long tot = obs.get(qi[k]).totaladded;
               Alarm alarm = new Alarm(mid,tot + (y+on-1)/on,tot,0);
               obs.get(qi[k]).pq.add(alarm);
               member.alarmstarts[k] = tot;
            }
            
            members.add(member);
            
            mid++;
            
         } else if(qt == 2){
            int x = fs.nextInt() ^ last;
            long y = (long)(fs.nextInt() ^ last);
            
            obs.get(x).totaladded += y;
            
            ArrayList<Integer> answer = new ArrayList<Integer>();
            
            while(!obs.get(x).pq.isEmpty() && obs.get(x).pq.peek().target <= obs.get(x).totaladded){
               Alarm alarm = obs.get(x).pq.poll();
               int id = alarm.id;
               
               if(alarm.level != members.get(id).alarmlevel) continue;
               members.get(id).alarmlevel++;
               
               //see how many were used in the other observatories
               for(int k = 0; k < members.get(id).on; k++){
                  long used = obs.get(members.get(id).obs[k]).totaladded - members.get(id).alarmstarts[k];
                  members.get(id).y -= used;
               }
               
               if(members.get(id).y <= 0){
                  answer.add(id);
                  continue;
               }
               
               //add new alarms
               int mon = members.get(id).on;
               for(int k = 0; k < mon; k++){
                  long tot = obs.get(members.get(id).obs[k]).totaladded;
                  Alarm newalarm = new Alarm(id,tot + (members.get(id).y + mon-1)/mon,tot,members.get(id).alarmlevel);
                  obs.get(members.get(id).obs[k]).pq.add(newalarm);
                  members.get(id).alarmstarts[k] = tot;
               }
               
            }
            
            last = answer.size();
            Collections.sort(answer);
            StringJoiner sj = new StringJoiner(" ");
            sj.add("" + answer.size());
            for(int i : answer){
               sj.add("" + (i+1));
            }
            out.println(sj.toString());  
         }
         
      }
      
      
      out.close();
   }
   
   
   public static class Alarm implements Comparable<Alarm>{
      int id;
      long target;
      long start;       //how many the observatory had when the alarm was placed
      
      int level;        //used to see if that alarm has been replaced
      
      public Alarm(int a, long b, long c, int d){
         id = a;
         target = b;
         start = c;
         level = d;
      }
      
      public int compareTo(Alarm a){
         if(target < a.target) return -1;
         if(target > a.target) return 1;
         return 0;
      }
   }
   
   public static class Obs{
      PriorityQueue<Alarm> pq;
      long totaladded;
      
      public Obs(){
         pq = new PriorityQueue<Alarm>();
         totaladded = 0L;
      }
   }
   
   public static class Member{
      int id;
      long y;
      int[] obs;
      int on;
      
      int alarmlevel;
      long[] alarmstarts;
      public Member(int a, long b, int[] c){
         id = a;
         y = b;
         obs = c;
         on = obs.length;
         
         alarmlevel = 0;
         alarmstarts = new long[3];
      }
   }
   
      
}


class FastScanner
{
    //I don't understand how this works lmao
    private int BS = 1 << 16;
    private char NC = (char) 0;
    private byte[] buf = new byte[BS];
    private int bId = 0, size = 0;
    private char c = NC;
    private double cnt = 1;
    private BufferedInputStream in;
 
    public FastScanner() {
        in = new BufferedInputStream(System.in, BS);
    }
 
    public FastScanner(String s) {
        try {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        } catch (Exception e) {
            in = new BufferedInputStream(System.in, BS);
        }
    }
 
    private char getChar() {
        while (bId == size) {
            try {
                size = in.read(buf);
            } catch (Exception e) {
                return NC;
            }
            if (size == -1) return NC;
            bId = 0;
        }
        return (char) buf[bId++];
    }
 
    public int nextInt() {
        return (int) nextLong();
    }
 
    public int[] nextInts(int N) {
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = (int) nextLong();
        }
        return res;
    }
 
    public long[] nextLongs(int N) {
        long[] res = new long[N];
        for (int i = 0; i < N; i++) {
            res[i] = nextLong();
        }
        return res;
    }
 
    public long nextLong() {
        cnt = 1;
        boolean neg = false;
        if (c == NC) c = getChar();
        for (; (c < '0' || c > '9'); c = getChar()) {
            if (c == '-') neg = true;
        }
        long res = 0;
        for (; c >= '0' && c <= '9'; c = getChar()) {
            res = (res << 3) + (res << 1) + c - '0';
            cnt *= 10;
        }
        return neg ? -res : res;
    }
 
    public double nextDouble() {
        double cur = nextLong();
        return c != '.' ? cur : cur + nextLong() / cnt;
    }
 
    public double[] nextDoubles(int N) {
        double[] res = new double[N];
        for (int i = 0; i < N; i++) {
            res[i] = nextDouble();
        }
        return res;
    }
 
    public String next() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c > 32) {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }
 
    public String nextLine() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c != '\n') {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }
 
    public boolean hasNext() {
        if (c > 32) return true;
        while (true) {
            c = getChar();
            if (c == NC) return false;
            else if (c > 32) return true;
        }
    }
}