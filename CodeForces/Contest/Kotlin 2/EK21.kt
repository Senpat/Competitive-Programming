//make sure to make new file!
import java.io.*
import java.util.*

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val f = BufferedReader(InputStreamReader(System.`in`))
        val out = PrintWriter(System.out)

        val n = Integer.parseInt(f.readLine())

        val st = StringTokenizer(f.readLine())

        val array = IntArray(n)

        val MAX = 200005
        val freq = IntArray(MAX)
        for (k in 0 until n) {
            array[k] = Integer.parseInt(st.nextToken())

            freq[array[k]]++
        }

        //find min without exactly 2
        var min = -1
        for (k in 1 until MAX) {
            if (freq[k] != 2) {
                min = k
                break
            }
        }


        var l = 1
        var r = min
        var mid: Int


        var answer = -1
        while (l <= r) {
            mid = (l + r + 1) / 2
            if (check(array, mid)) {
                //if(mid*a+(n-mid)*b < c){
                l = mid + 1
                answer = mid
            } else {
                r = mid - 1
            }
        }

        min = answer
        if (check(array, min + 1)) min++

        //out.println(min)


        val bool = BooleanArray(MAX)
        for (k in 0 until MAX) bool[k] = false

        for (k in 0 until n) {
            if (array[k] >= min)
                out.print("B")
            else if (bool[array[k]])
                out.print("G")
            else {
                out.print("R")
                bool[array[k]] = true
            }
        }








        out.close()
    }

    fun check(array: IntArray, i: Int): Boolean {
        val q = LinkedList<Int>()
        val hset = HashSet<Int>()

        for (k in array.indices) {
            if (array[k] >= i) continue
            if (hset.contains(array[k])) {
                if (q.peek() !== array[k]) return false
                q.poll()
            } else {
                hset.add(array[k])
                q.add(array[k])
            }
        }
        return true
    }


