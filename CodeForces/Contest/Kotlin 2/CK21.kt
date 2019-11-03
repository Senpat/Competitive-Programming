//make sure to make new file!
import java.io.*
import java.util.*

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val f = BufferedReader(InputStreamReader(System.`in`))
        val out = PrintWriter(System.out)

        var st = StringTokenizer(f.readLine())

        val n = Integer.parseInt(st.nextToken())
        val m = Integer.parseInt(st.nextToken())

        val pq = PriorityQueue<Day>()

        var answer: Long = 0
        var total: Long = 0

        var maxicecream: Long = 0
        for (k in 0 until n) {
            st = StringTokenizer(f.readLine())

            val a = (Integer.parseInt(st.nextToken())).toLong()
            val b = (Integer.parseInt(st.nextToken())).toLong()
            val c = (Integer.parseInt(st.nextToken())).toLong()

            pq.add(Day(a, b, c))
            //out.println(a + " " + b + " " + c);


            answer += c * a
            total += a
            maxicecream += b
        }

        if (total > m || maxicecream < m) {
            out.println("-1")
            out.close()
            System.exit(0)
        }

        while (total != m.toLong()) {
            val d = pq.poll()
            val dispo = d.b - d.a

            if (dispo >= m - total) {
                answer += d.c * (m - total)
                total = m.toLong()
            } else {
                answer += d.c * dispo
                total += dispo
            }
        }

        out.println(answer)





        out.close()
    }

    class Day(internal var a: Long, internal var b: Long, internal var c: Long) : Comparable<Day> {

        override fun compareTo(d: Day): Int {
            if (c < d.c) return -1
            return if (c > d.c) 1 else 0
        }

        override fun toString(): String {
            return "$a $b $c"
        }
    }


