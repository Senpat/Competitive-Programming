/*
bts songs to dance to:
I need U
Run
ON
Filter
I'm fine
 */
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import static java.lang.System.out;
import java.util.*;
import java.io.*;
import java.math.*;

public class Bray
{
    static final int SQRT = 420;
    static final long MOD = 998244353L;
    static final long BASE = 37L;
    public static void main(String hi[]) throws Exception
    {
        long[] base = new long[200001];
        long[] invBase = new long[200001];
        base[0] = invBase[0] =  1L;
        long boof = power(BASE, MOD-2, MOD);
        for(int i=1; i <= 200000; i++)
        {
            base[i] = (base[i-1]*BASE)%MOD;
            invBase[i] = (invBase[i-1]*boof)%MOD;
        }
        FastScanner infile = new FastScanner();
        char[] arr = infile.nextLine().toCharArray();
        int N = arr.length;
        //compute prefix hashes for arr
        long[] prefix = new long[N];
        prefix[0] = arr[0];
        for(int i=1; i < N; i++)
        {
            prefix[i] = prefix[i-1]+(arr[i]*base[i])%MOD;
            if(prefix[i] >= MOD)
                prefix[i] -= MOD;
        }
        //query shit
        int Q = infile.nextInt();
        String[] qstrings = new String[Q];
        int[] kth = new int[Q];
        for(int q=0; q < Q; q++)
        {
            qstrings[q] = infile.next();
            kth[q] = infile.nextInt();
        }
        //process qstrings with length <= SQRT
        long[] qhashes = new long[Q];
        HashSet<Long> smalls = new HashSet<Long>();
        for(int qq=0; qq < Q; qq++)
        {
            String s = qstrings[qq];
            char[] temp = s.toCharArray();
            int M = temp.length;
            long hash = temp[0];
            for(int i=1; i < M; i++)
            {
                hash += (temp[i]*base[i])%MOD;
                if(hash >= MOD)
                    hash -= MOD;
            }
            qhashes[qq] = hash;
            if(M <= SQRT)
                smalls.add(hash);
        }
        //for qstrings with length <= SQRT
        HashMap<Long, ArrayList<Integer>> map = new HashMap<Long, ArrayList<Integer>>();
        for(int length=1; length <= SQRT; length++)
            for(int a=0; a < N-length+1; a++)
            {
                int b = a+length-1;
                long hash = prefix[b];
                if(length==1) System.out.println(hash);
                if(a > 0)
                {
                    hash -= prefix[a-1];
                    if(hash < 0)
                        hash += MOD;
                    hash = (hash*invBase[a])%MOD;
                }
                if(length == 1) System.out.println(hash);
                if(smalls.contains(hash))
                {
                    if(!map.containsKey(hash))
                        map.put(hash, new ArrayList<Integer>());
                    map.get(hash).add(a+1);
                    System.out.println(a+1);
                }
            }
        StringBuilder sb = new StringBuilder();
        outer:for(int qq=0; qq < Q; qq++)
        {
            int M = qstrings[qq].length();
            if(M <= SQRT)
            {
                if(map.containsKey(qhashes[qq]))
                {
                    ArrayList<Integer> ls = map.get(qhashes[qq]);
                    if(kth[qq] <= ls.size())
                        sb.append(ls.get(kth[qq]-1)+"\n");
                    else
                        sb.append("-1\n");
                }
                else
                    sb.append("-1\n");
            }
            else
            {
                int cnt = 0;
                for(int i=0; i < N-M+1; i++)
                {
                    long hash = prefix[i+M-1];
                    if(i > 0)
                    {
                        hash -= prefix[i-1];
                        if(hash < 0)
                            hash += MOD;
                        hash = (hash*invBase[i])%MOD;
                    }
                    if(hash == qhashes[qq])
                    {
                        cnt++;
                        if(cnt == M)
                        {
                            sb.append((i+1)+"\n");
                            continue outer;
                        }
                    }
                }
                sb.append("-1\n");
            }
        }
        System.out.print(sb);
    }
    //static boolean debug = false;
    public static long power(long x, long y, long p)
    {
        //0^0 = 1
        long res = 1L;
        x = x%p;
        while(y > 0)
        {
            if((y&1)==1)
                res = (res*x)%p;
            y >>= 1;
            x = (x*x)%p;
        }
        return res;
    }
}
class FastScanner
{
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