import java.io.*
import java.util.*
import kotlin.math.*
//semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = IntArray(1){0} + f.readLine().split(" ").map{it.toInt()}.toIntArray()

	val indeces = Array<MutableList<Int>>(n+1){mutableListOf<Int>()}
	for(k in 1..n){
		indeces[array[k]].add(k)
	}

	var lstmin = LazySegmentTreeMin(0,n)
	var lstmax = LazySegmentTreeMax(0,n)

	for(k in 0..n){
		lstmin.update(k,k,-k.toLong())
		lstmax.update(k,k,-k.toLong())
	}

	val above = LongArray(n+1){0L}

	for(k in 1..n){
		for(index in indeces[k]){
			lstmin.update(index,n,2L)
			lstmax.update(index,n,2L)
		}

		for(index in indeces[k]){
			above[index] = (lstmax.get(index,n)-lstmin.get(0,index-1)-1)/2L
		}
	}

	val below = LongArray(n+1){0L}
	//reset lazy segtrees

	lstmin = LazySegmentTreeMin(0,n)
	lstmax = LazySegmentTreeMax(0,n)

	for(k in 0..n){
		lstmin.update(k,k,-k.toLong())
		lstmax.update(k,k,-k.toLong())
	}

	for(k in n downTo 1){
		for(index in indeces[k]){
			lstmin.update(index,n,2L)
			lstmax.update(index,n,2L)
		}

		for(index in indeces[k]){
			below[index] = (lstmax.get(index,n)-lstmin.get(0,index-1))/2L
		}
	}
	val answer = LongArray(n){j -> max(above[j+1],below[j+1])}
	println(answer.joinToString(" "))
}

//from danny
class LazySegmentTreeMin(val treeFrom: Int, val treeTo: Int) {
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
            value[node] = lazy[node] + min(
                    update(from, to, segFrom, mid, 2 * node, delta),
                    update(from, to, mid + 1, segTo, (2 * node) + 1, delta)
            )
        }
        return value[node]
    }

    operator fun get(from: Int, to: Int) = query(from, to, treeFrom, treeTo, 1)

    fun query(from: Int, to: Int, segFrom: Int, segTo: Int, node: Int): Long {
        if (from > segTo || to < segFrom) {
            return 1000000000L
        } else if (from <= segFrom && to >= segTo) {
            return value[node]
        } else {
            val mid = (segFrom + segTo) / 2
            return lazy[node] + min(
                    query(from, to, segFrom, mid, 2 * node),
                    query(from, to, mid + 1, segTo, (2 * node) + 1)
            )
        }
    }
}

class LazySegmentTreeMax(val treeFrom: Int, val treeTo: Int) {
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
            return -1000000000L
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
