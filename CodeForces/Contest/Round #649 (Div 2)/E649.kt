import java.io.*
import java.util.*
import kotlin.math.*
import kotlin.random.*
//upsolve, danny
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val random = Random(9)
	val shuffled = (0 until n).toMutableList().shuffled(random)

	val FIRST = 7
	val FIRSTQS = 10
	//7 numbers using 10 queries
	val first2 = mutableListOf<Pair<Int,Int>>()
	for(k in 0 until min(FIRST,n)){
		//find number at ith index using FIRSTQS queries
		val i = shuffled[k]

		var curshuffled = (0 until n).toMutableList()
		curshuffled.remove(i)
		curshuffled = curshuffled.shuffled(random)

		var min = 2047
		val removed = HashSet<Int>()
		for(j in 0 until min(FIRSTQS,n-1)){
			println("? ${i+1} ${curshuffled[j]+1}")
			System.out.flush()

			var v = f.readLine().toInt()
			var i2 = 0
			for(i2 in 0 until 11){
				if(v and 1 == 0 && !removed.contains(i2)){
					min -= 1 shl i2
					removed.add(i2)
				}
				v = v shr 1
			}

		}

		first2.add(Pair(min,i))

	}

	var f1 = Pair(-1,-1)
	var f2 = Pair(-1,-1)

	for(k in 0 until first2.size){
		for(j in k+1 until first2.size){
			if(first2[k].first and first2[j].first == 0){
				f1 = first2[k]
				f2 = first2[j]
				break
			}
		}
	}

	if(f1.first == -1){
		println("! fail")
		return
	}

	//figure out which bits f1 contributes to, rest f2 contributes
	val f1cont = HashSet<Int>()
	//the bits that f2 equals to 1, f1 contributes.

	var i = f2.first
	var i2 = 0
	while(i > 0){
		if(i and 1 == 1){
			f1cont.add(i2)
		}
		i = i shr 1
		i2++
	}

	val answer = IntArray(n)
	for(k in 0 until n){
		if(k == f1.second){
			answer[k] = f1.first
		} else if(k == f2.second){
			answer[k] = f2.first
		} else {
			println("? ${f1.second+1} ${k+1}")
			System.out.flush()

			var q1 = f.readLine().toInt()

			println("? ${f2.second+1} ${k+1}")
			System.out.flush()

			var q2 = f.readLine().toInt()

			var inc = 0

			var cur = 0
			while(1 shr inc < n){
				if(f1cont.contains(inc)){
					cur += q1 and (1 shr inc)
				} else {
					cur += q2 and (1 shr inc)
				}
				inc++
			}

			answer[k] = cur
		}
	}

	println("! " + answer.joinToString(" "))

}
