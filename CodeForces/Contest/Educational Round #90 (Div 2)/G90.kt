import java.io.*
import java.util.*
import kotlin.math.*
//semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,q) = f.readLine().split(" ").map{it.toInt()}

	val UPPER = 2*n+5
	val lst = LazySegmentTree(1,UPPER)

	for(k in 0 until UPPER){
		lst.update(k+1,k+1,k.toLong())
	}

	val pawns = HashSet<Pair<Int,Int>>()

	val freq = TreeMap<Int,Int>()

	for(k in 0 until q){
		val (x,y) = f.readLine().split(" ").map{it.toInt()}
		val pawn = Pair(x,y)
		val min = y+abs(x-m)
		if(pawns.contains(pawn)){
			//remove
			lst.update(1,min,-1)
			freq[min] = freq[min]!!-1
			if(freq[min] == 0) freq.remove(min)
			pawns.remove(pawn)
		} else {
			//add
			lst.update(1,min,1)
			freq[min] = (freq[min] ?: 0) + 1
			pawns.add(pawn)
		}

		if(freq.isEmpty()) println(0)
		else{
			val answer = lst.get(1,freq.lastKey())
			//println("$k $min $answer")
			println(max(0,answer-n))
		}
	}
}

//from danny
class LazySegmentTree(val treeFrom: Int, val treeTo: Int) {
    val value: LongArray
    val lazy: LongArray

    init {
        val length = treeTo - treeFrom + 1
        var e = 0
        while (1 shl e < length) {
            e++
        }
        value = LongArray(1 shl (e + 1))
        lazy = LongArray(1 shl (e + 1))
    }

    fun update(from: Int, to: Int, delta: Long) {
        update(from, to, treeFrom, treeTo, 1, delta)
    }

    fun update(from: Int, to: Int, segFrom: Int, segTo: Int, node: Int, delta: Long): Long {
        if (from > segTo || to < segFrom) {

        } else if (from <= segFrom && to >= segTo) {
            value[node] += delta
            lazy[node] += delta
        } else {
            val mid = (segFrom + segTo) / 2
            value[node] = lazy[node] + max(
                    update(from, to, segFrom, mid, 2 * node, delta),
                    update(from, to, mid + 1, segTo, (2 * node) + 1, delta)
            )
        }
        return value[node]
    }

    operator fun get(from: Int, to: Int) = query(from, to, treeFrom, treeTo, 1)

    fun query(from: Int, to: Int, segFrom: Int, segTo: Int, node: Int): Long {
        if (from > segTo || to < segFrom) {
            return 0L
        } else if (from <= segFrom && to >= segTo) {
            return value[node]
        } else {
            val mid = (segFrom + segTo) / 2
            return lazy[node] + max(
                    query(from, to, segFrom, mid, 2 * node),
                    query(from, to, mid + 1, segTo, (2 * node) + 1)
            )
        }
    }
}
