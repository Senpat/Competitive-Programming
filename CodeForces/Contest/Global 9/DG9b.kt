import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun checksorted(array : IntArray, n : Int) : Boolean{
		for(k in 1 until n){
			if(array[k] < array[k-1]) return false
		}
		return true
	}

	fun getmex(array : IntArray, n : Int) : Int{
		val hset = HashSet<Int>()
		for(k in 0 until n){
			hset.add(array[k])
		}
		for(k in 0..n){
			if(!hset.contains(k)) return k
		}
		return n
	}


	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		val answer = mutableListOf<Int>()
		var i = 0
		while(!checksorted(array,n)){
			//println(array.joinToString(" "))
			if(array[i] == i){
				i++
			}
			val mex = getmex(array,n)
			if(mex != i){
				//find an index with i and set it to the getmex
				for(k in i until n){
					if(array[k] == i){
						answer.add(k+1)
						array[k] = mex
						break
					}
				}
			} else {
				array[i] = mex
				answer.add(i+1)
				i++
			}

		}

		println(answer.size)
		println(answer.joinToString(" "))
	}
}
