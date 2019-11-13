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

        val hs = HashSet<Int>()
        var min = Integer.MAX_VALUE
        var minindex = -1
        var max = 0
        var maxindex = -1
        for (k in 0 until n) {
            array[k] = Integer.parseInt(st.nextToken())
            hs.add(array[k])

            if (array[k] <= min) {
                minindex = k
                min = array[k]
            }

            if (array[k] >= max) {
                maxindex = k
                max = array[k]
            }
        }

        if (hs.size < 3) {
            out.println("-1 -1 -1")
        } else {
            var middle = -1
            for (k in 0 until n) {
                if (array[k] != min && array[k] != max) {
                    middle = k
                }
            }
            minindex++
            middle++
            maxindex++
            out.println("$minindex $middle $maxindex")
        }








        out.close()
    }


