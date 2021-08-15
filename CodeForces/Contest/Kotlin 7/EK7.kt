import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = FastScanner()

	val INF = 10000000000L

	val n = f.nextInt()
	val a = LongArray(1){INF} + LongArray(n){f.nextLong()}.sorted()
	val b = LongArray(n+1){f.nextLong()}.sorted()

	val m = f.nextInt()
	val cin = LongArray(m){f.nextLong()}
	val c = Array<Pair<Long,Int>>(m){j -> Pair(cin[j],j)}
	c.sortBy({it.first})

	val answer = LongArray(m){0L}

	/*
	val tset = TreeSet<Int>(){compareBy{b[it]-a[it]}}
	for(k in 0..n){
		tset.add(k)
	}*/

	val tset = (1..n).toSortedSet(compareBy({b[it]-a[it]},{it}))

	var i = 0
	for(k in 0 until m){
		//calculate answer for mth query
		while(i < n && c[k].first > a[i+1]){
			tset.remove(i+1)
			a[i] = a[i+1]
			a[i+1] = INF
			tset.add(i)
			i++
		}

		a[i] = c[k].first
		tset.add(i)

		val last = tset.last()
		answer[c[k].second] = b[last]-a[last]

		tset.remove(i)
		//println("$k $i $last")
		//println(tset.joinToString(" "))
	}

	println(answer.joinToString(" "))
}

class FastScanner {
    private val BS = 1 shl 16
    private val NC = 0.toChar()
    private val buf = ByteArray(BS)
    private var bId = 0
    private var size = 0
    private var c = NC
    private var `in`: BufferedInputStream? = null

    constructor() {
        `in` = BufferedInputStream(System.`in`, BS)
    }

    private val char: Char
        private get() {
            while (bId == size) {
                size = try {
                    `in`!!.read(buf)
                } catch (e: Exception) {
                    return NC
                }
                if (size == -1) return NC
                bId = 0
            }
            return buf[bId++].toChar()
        }

    fun nextInt(): Int {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            c = char
        }
        var res = 0
        while (c >= '0' && c <= '9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }

	 fun nextLong(): Long {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            c = char
        }
        var res = 0L
        while (c >= '0' && c <= '9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }
}
