import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n1,n2,n3,n4) = f.readLine().split(" ").map{it.toInt()}

	val a = f.readLine().split(" ").map{it.toLong()}.toLongArray()
	val b = f.readLine().split(" ").map{it.toLong()}.toLongArray()
	val c = f.readLine().split(" ").map{it.toLong()}.toLongArray()
	val d = f.readLine().split(" ").map{it.toLong()}.toLongArray()

	val m1 = f.readLine().toInt()
	val bpairs = Array<MutableList<Int>>(n2){mutableListOf<Int>()}
	val m1s = Array<Pair<Int,Int>>(m1){
		var (p1,p2) = f.readLine().split(" ").map{it.toInt()}
		p1--
		p2--
		bpairs[p2].add(p1)
		Pair(p1,p2)
	}

	val m2 = f.readLine().toInt()
	val cpairs = Array<MutableList<Int>>(n3){mutableListOf<Int>()}
	val m2s = Array<Pair<Int,Int>>(m2){
		var (p1,p2) = f.readLine().split(" ").map{it.toInt()}
		p1--
		p2--
		cpairs[p2].add(p1)
		Pair(p1,p2)
	}

	val m3 = f.readLine().toInt()
	val dpairs = Array<MutableList<Int>>(n4){mutableListOf<Int>()}
	val m3s = Array<Pair<Int,Int>>(m3){
		var (p1,p2) = f.readLine().split(" ").map{it.toInt()}
		p1--
		p2--
		dpairs[p2].add(p1)
		Pair(p1,p2)
	}

	val aorder = (0 until n1).sortedBy { a[it] }

	val dpb = LongArray(n2){0L}

	for(k in 0 until n2){
		bpairs[k].sortWith(compareBy{a[it]})

		var i = 0
		while(i < bpairs[k].size && aorder[i] == bpairs[k][i]){
			i++
		}

		if(i < n1){
			dpb[k] = b[k] + a[aorder[i]]
		} else {
			dpb[k] = Long.MAX_VALUE
		}

	}

	//println(aorder.joinToString(" "))
	//println(dpb.joinToString(" "))

	val border = (0 until n2).sortedBy{dpb[it]}

	val dpc = LongArray(n3){0L}

	for(k in 0 until n3){
		cpairs[k].sortWith(compareBy{dpb[it]})

		var i = 0
		while(i < cpairs[k].size && border[i] == cpairs[k][i]){
			i++
		}

		if(i < n2 && dpb[border[i]] != Long.MAX_VALUE){
			dpc[k] = c[k] + dpb[border[i]]
		} else {
			dpc[k] = Long.MAX_VALUE
		}
	}

	val corder = (0 until n3).sortedBy{dpc[it]}

	val dpd = LongArray(n4){0L}

	for(k in 0 until n4){
		dpairs[k].sortWith(compareBy{dpc[it]})

		var i = 0
		while(i < dpairs[k].size && corder[i] == dpairs[k][i]){
			i++
		}

		if(i < n3 && dpc[corder[i]] != Long.MAX_VALUE){
			dpd[k] = d[k] + dpc[corder[i]]
		} else {
			dpd[k] = Long.MAX_VALUE
		}
	}

	var min = Long.MAX_VALUE
	for(k in 0 until n4){
		if(dpd[k] != Long.MAX_VALUE){
			min = min(min,dpd[k])
		}
	}

	if(min == Long.MAX_VALUE){
		println(-1)
	} else {
		println(min)
	}


}
