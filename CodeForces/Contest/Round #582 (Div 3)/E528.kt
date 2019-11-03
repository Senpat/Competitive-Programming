//make sure to make new file!
import java.io.*
import java.util.*

object E528 {

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val f = BufferedReader(InputStreamReader(System.`in`))
        val out = PrintWriter(System.out)

        val n = Integer.parseInt(f.readLine())

        var a = f.readLine()
        var b = f.readLine()

        val each = HashSet<Character>()
        each.add('a')
        each.add('b')
        each.add('c')

        val doubles = HashSet<String>()
        doubles.add("aa")
        doubles.add("bb")
        doubles.add("cc")

        if (doubles.contains(b)) {
            val temp = b
            b = a
            a = temp
        }

        val sb = StringBuilder("")

        out.println("YES")
        if (n == 1) {
            out.println(find1(a, b))
        } else if (doubles.contains(b)) {
            //both are doubles
            //ex: aa, bb -> abababccc
            if (a.equals(b)) {
                val next = if (a.charAt(0) === 'a') 'b' else 'a'
                b = "" + next + next
            }
            for (k in 0 until n) {
                sb.append(a.charAt(0))
                sb.append(b.charAt(0))
            }
            each.remove(a.charAt(0))
            each.remove(b.charAt(0))
            val last = get(each)
            for (k in 0 until n) {
                sb.append(last)
            }

            out.println(sb.toString())
        } else if (doubles.contains(a)) {
            //one (a) is double

            if (b.charAt(0) === a.charAt(0) || b.charAt(1) === a.charAt(0)) {
                //ex: aa, ab -> acacacbbb
                val last = if (b.charAt(0) === a.charAt(0)) b.charAt(1) else b.charAt(0)
                each.remove(a.charAt(0))
                each.remove(last)
                val second = get(each)
                for (k in 0 until n) {
                    sb.append(a.charAt(0))
                    sb.append(second)
                }
                for (k in 0 until n) {
                    sb.append(last)
                }

                out.println(sb.toString())
            } else {
                //ex: aa, bc -> acacacbbb

                for (k in 0 until n) {
                    sb.append(a.charAt(0))
                    sb.append(b.charAt(1))
                }
                for (k in 0 until n) {
                    sb.append(b.charAt(0))
                }

                out.println(sb.toString())
            }
        } else {
            //no doubles
            //ex: ab, bc -> b,a,c -> bbbaaaccc

            val f1 = find1(a, b)

            for (k in 0..2) for (j in 0 until n) sb.append(f1.charAt(k))
            out.println(sb.toString())


        }






        out.close()
    }

    operator fun get(hs: HashSet<Character>): Char {
        for (c in hs) return c
        return 'a'
    }

    fun find1(a: String, b: String): String {
        for (k in 97..99) {
            for (j in 97..99) {
                for (h in 97..99) {
                    if (k == j || j == h || h == k)
                        continue
                    val s1 = "" + k.toChar() + j.toChar()
                    val s2 = "" + j.toChar() + h.toChar()

                    if (s1.equals(a) || s1.equals(b))
                        continue
                    if (s2.equals(a) || s2.equals(b))
                        continue

                    return s1 + h.toChar()
                }
            }
        }

        while (true);
        //return "big sad";
    }


}