import java.io.*
import java.util.*
import kotlin.math.*

//works for C2 hopefully
//based on C723b2.kt, uses danny segtree
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = LongArray(1){-1L} + f.readLine().split(" ").map{it.toLong()}.toLongArray()

	//1 indexed
	val segtree = LazySegmentTree(0,n)

	//Pairs store (value,index). Sort by bigger value, then bigger index
	val pq = PriorityQueue<Pair<Long,Int>>(compareBy({-it.first},{-it.second}))

	var curanswer = 0
	val pospsum = LongArray(n+1){0}
	for(k in 1..n){
		if(array[k] >= 0){
			curanswer++

			pospsum[k] = pospsum[k-1] + array[k]
		} else {
			pospsum[k] = pospsum[k-1]

			pq.add(Pair(array[k],k))
		}

		segtree.update(k,k,pospsum[k])
	}

	while(!pq.isEmpty()){
		val p = pq.poll()

		//check if you can add it
		val curmin = segtree.get(p.second,n)

		if(curmin >= -p.first){
			//you can add it
			curanswer++

			segtree.update(p.second,n,array[p.second])
		}

		/*
		println("${p.first} ${p.second} $curmin")
		for(k in 0..n){
			println(segtree.get(k,k))
		}*/
	}

	println(curanswer)



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
            return Long.MAX_VALUE
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
