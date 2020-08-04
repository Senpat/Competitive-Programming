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

	fun find(array : IntArray, n : Int, i : Int) : Int{
		for(k in n-1 downTo 0){
			if(i > array[k]) return k
		}

		return 0
	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		val answer = mutableListOf<Int>()
		while(!checksorted(array,n)){
			val i = getmex(array,n)
			val ind = find(array,n,i)
			array[ind] = i
			answer.add(ind+1)

		}

		println(answer.size)
		println(answer.joinToString(" "))
	}
}
