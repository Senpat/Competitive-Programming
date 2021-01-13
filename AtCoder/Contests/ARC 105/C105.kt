import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

	val maxcamel = array.max()!!
	var minweight = Long.MAX_VALUE
	val parts = Array<Pair<Long,Long>>(m){
		val (a,b) = f.readLine().split(" ").map{it.toLong()}
		minweight = min(minweight,b)
		Pair(a,b)
	}

	if(minweight < maxcamel){
		println(-1)
		return
	}

	//get min number of groups that sum <= minweight
	val perms = mutableListOf<MutableList<Int>>()

	val taken = BooleanArray(n){false}
	fun getperm(curperm : MutableList<Int>){
		if(curperm.size == n) perms.add(curperm.toMutableList())
		else{
			for(k in 0 until n){
				if(!taken[k]){
					taken[k] = true
					curperm.add(k)
					getperm(curperm)
					curperm.removeAt(curperm.size-1)
					taken[k] = false
				}
			}
		}
	}

	getperm(mutableListOf<Int>())

	var mingroups = Long.MAX_VALUE
	for(perm in perms){
		var cursum : Long = 0L
		var numgroups : Long = 1L
		for(i in perm){
			if(cursum + array[i] > minweight){
				cursum = array[i]
				numgroups++
			} else {
				cursum += array[i]
			}
		}
		mingroups = min(mingroups,numgroups)
	}
	println(mingroups)
	var answer = 0L

	for(k in 0 until m){
		answer = max(answer,parts[k].first/(parts[k].second/minweight))
	}

	println(answer*(mingroups-1))



}
