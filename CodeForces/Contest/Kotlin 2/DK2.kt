//make sure to make new file!
import java.io.*
import java.util.*

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val f = BufferedReader(InputStreamReader(System.`in`))
        val out = PrintWriter(System.out)

        var st = StringTokenizer(f.readLine())

        val n = Integer.parseInt(st.nextToken())
        val a = Integer.parseInt(st.nextToken())
        val b = Integer.parseInt(st.nextToken())
        val m = Integer.parseInt(st.nextToken())

        val array = IntArray(n)
        val alist = ArrayList<Int>()

        val freq = HashMap<Int, Int>()

        st = StringTokenizer(f.readLine())
        for (k in 0 until n) {
            array[k] = Integer.parseInt(st.nextToken())

            if (freq.containsKey(array[k])) {
                freq[array[k]] = freq[array[k]]!!+1
                //freq.put(array[k], (freq.get(array[k])?.inc())?.toInt())
            } else {
                alist.add(array[k])
                freq.put(array[k], 1)
            }
        }

        Collections.sort(alist)

        var answer = 0

        if(b>a) {
            for (k in alist.size - 1 downTo 0) {
                val i = alist.get(k)
                if (i % m == 0 && freq.containsKey(i.div(m))) {
                    val j = i.div(m)
                    val times = Math.min(freq.get(i)!!.div(b), freq.get(j)!!.div(a))

                    answer += times
                    freq[i] = freq[i]!! - times * b
                    freq[j] = freq[j]!! - times * a
                    //freq.put(i, freq.get(i) - times * b)
                    //freq.put(j, freq.get(j) - times * a)
                }
            }
        } else {
            for(k in 0 until alist.size){
                val i = alist.get(k)
                if(freq.containsKey(i*m)){
                    val j = i*m
                    val times = Math.min(freq[i]!!.div(a),freq[j]!!.div(b))

                    answer += times
                    freq[i] = freq[i]!! - times*a
                    freq[j] = freq[j]!! - times*b
                }
            }
        }


        out.println(answer)







        out.close()
    }


