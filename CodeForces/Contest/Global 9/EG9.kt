import java.io.*
import java.util.*
import kotlin.math.*
//upsolve - wrong greedy
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = IntArray(1) + f.readLine().split(" ").map{it.toInt()}.toIntArray()
	val sorted = IntArray(n+1){j -> array[j]}.sorted()

	val pairs = Array(n+1){mutableListOf<Pair<Int,Int>>()}

	for(k in 1..n){
		for(j in 1 until k){
			if(array[j] > array[k]){
				pairs[k].add(Pair(j,k))
			}
		}
	}


	val answer = mutableListOf<Pair<Int,Int>>()

	//gets first index of x before i
	fun getindex(x : Int, i : Int) : Int{
		for(k in 1..n) if(array[k] == x) return k
		//shouldn't happen
		return -1
	}

	fun swap(a : Int, b : Int){
		val temp = array[a]
		array[a] = array[b]
		array[b] = temp
	}

	for(k in n downTo 1){
		if(sorted[k] == array[k]) continue
		val i = getindex(sorted[k],k)

		if(i != k){
			for(j in 0 until pairs[k].size){
				if(pairs[k][j].first != i){
					answer.add(pairs[k][j])
					swap(pairs[k][j].first,pairs[k][j].second)
				}
			}
			answer.add(Pair(i,k))
			swap(i,k)
		}
	}

	println(answer.size)
	for(k in 0 until answer.size){
		println("${answer[k].first} ${answer[k].second}")
	}
}
