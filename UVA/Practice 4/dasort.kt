import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = FastScanner()

	for(q in 1..f.nextInt()){
		val useless = f.nextInt()
		val n = f.nextInt()
		val array = IntArray(n){f.nextInt()}
		val sorted = IntArray(n){j -> array[j]}.sorted()
		var i = 0
		for(k in 0 until n){
			if(array[k] == sorted[i]) i++
		}

		println("$q ${n-i}")
	}
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
}
