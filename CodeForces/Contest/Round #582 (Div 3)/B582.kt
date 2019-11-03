//make sure to make new file!
import java.io.*
import java.util.*

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val f = BufferedReader(InputStreamReader(System.`in`))
        val out = PrintWriter(System.out)

        val t = Integer.parseInt(f.readLine())

        for (q in 1..t) {

            val n = Integer.parseInt(f.readLine())

            val st = StringTokenizer(f.readLine())

            val array = IntArray(n)
            for (k in 0 until n) {
                array[k] = Integer.parseInt(st.nextToken())
            }

            var answer = 0
            var min = Integer.MAX_VALUE
            for (k in n - 1 downTo 0) {
                if (array[k] > min) {
                    answer++
                } else {
                    min = array[k]
                }
            }

            out.println(answer)
        }




        out.close()
    }


