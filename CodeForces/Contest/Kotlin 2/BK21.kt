//make sure to make new file!
import java.io.*
import java.util.*

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val f = BufferedReader(InputStreamReader(System.`in`))
        val out = PrintWriter(System.out)

        val n = Integer.parseInt(f.readLine())

        val st = StringTokenizer(f.readLine())

        val array = LongArray(n)

        var max: Long = 0
        var maxindex: Long = -1

        for (k in 0 until n) {
            array[k] = (Integer.parseInt(st.nextToken())).toLong()

            if (array[k] >= max) {
                max = array[k]
                maxindex = (k + 1).toLong()
            }


        }

        var answer = n * (max - 1) + maxindex
        if (max == 1L) answer = maxindex
        if (max == 0L) answer = 0


        out.println(answer)







        out.close()
    }


