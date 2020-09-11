import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = IntArray(1){0} + f.readLine().split(" ").map{it.toInt()}.toIntArray()

		val pairs = mutableListOf<Pair<Int,Int>>()
		for(j in 2..n){
			for(k in 1 until j){
				if(array[k] == array[j]) pairs.add(Pair(k,j))
			}
		}

		var answer = 0L

		val numfirst = LongArray(n+1){0L}
		val numsecond = LongArray(n+1){0L}

		var i = 0
		for(k in 2..n){
			val firstadd = HashSet<Int>()
			var pairsadded = 0L

			var psumfirst = 0L
			var psumsecond = 0L
			var firsti = 0
			var secondi = 0
			while(i < pairs.size && pairs[i].second == k){
				val p = pairs[i]

				while(firsti < p.first-1){
					psumfirst += numfirst[firsti+1]
					firsti++
				}

				while(secondi < p.first){
					psumsecond += numsecond[secondi+1]
					secondi++
				}
				answer += psumfirst - psumsecond
				//println("${p.first} ${p.second} $answer ${psumfirst(p.first-1)} ${psumsecond(p.first)}")
				firstadd.add(p.first)

				pairsadded++
				i++
			}

			for(x in firstadd){
				numfirst[x]++
			}

			numsecond[k] += pairsadded
		}

		println(answer)
	}
}
