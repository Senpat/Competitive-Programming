import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()*2
		val array = f.readLine().split(" ").map{it.toInt()}.sorted()

		val freqs = HashMap<Int,Int>()
		for(k in 0 until n){
			if(freqs.containsKey(array[k])){
				freqs.put(array[k],freqs[array[k]]!!+1)
			} else {
				freqs.put(array[k],1)
			}
		}

		//pick second number, out of 0 to n-1
		var found = false
		for(k in 0 until n-1){
			val curfreqs = HashMap<Int,Int>()
			for((key,value) in freqs){
				curfreqs.put(key,0)
			}
			//array[k] is the 2nd number
			val answer = mutableListOf<Pair<Int,Int>>()
			answer.add(Pair(array[k],array[n-1]))
			curfreqs.put(array[k],curfreqs[array[k]]!!+1)
			curfreqs.put(array[n-1],curfreqs[array[n-1]]!!+1)

			var cur = array[n-1]
			for(j in n-2 downTo 0){
				if(curfreqs[array[j]]!! < freqs[array[j]]!!){
					curfreqs.put(array[j],curfreqs[array[j]]!!+1)
					if(curfreqs.containsKey(cur-array[j]) && curfreqs[cur-array[j]]!! < freqs[cur-array[j]]!!){
						curfreqs.put(cur-array[j],curfreqs[cur-array[j]]!!+1)
						answer.add(Pair(array[j],cur-array[j]))
						cur = array[j]
					} else {
						break
					}
				}
			}

			if(answer.size == n/2){
				found = true
				println("YES")
				println(answer[0].first+answer[0].second)
				val sj = StringJoiner("\n")
				for(i in 0 until answer.size){
					sj.add("${answer[i].first} ${answer[i].second}")
				}
				println(sj.toString())
				break
			}
		}
		if(!found){
			println("NO")
		}
	}
}
