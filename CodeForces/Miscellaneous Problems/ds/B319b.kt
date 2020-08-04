import java.io.*
import java.util.*
import kotlin.math.*
//danny
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	val left = IntArray(1){-1} + (0 until n).toMutableList().toIntArray()
	val right = (1 until n).toMutableList().toIntArray() + IntArray(1){-1}
	/*
	val indexof = IntArray(n+1)
	for(k in 0 until n){
		indexof[array[k]] = k
	}*/
	//val seen = HashSet<Int>()
	var check = (0 until n).toHashSet<Int>()
	var answer = 0
	while(true){
		val remove = mutableListOf<Int>()
		for(k in check){
			if(left[k] != -1 && array[left[k]] > array[k]){
				remove.add(k)

			}
		}

		//println(remove)

		if(remove.isEmpty()) break

		check = HashSet<Int>()

		for(k in remove.size-1 downTo 0){
			if(right[remove[k]] != -1) left[right[remove[k]]] = left[remove[k]]
			if(left[remove[k]] != -1) right[left[remove[k]]] = right[remove[k]]

			if(right[remove[k]] != -1 && !(k < remove.size-1 && remove[k+1] == right[remove[k]])) {
				check.add(right[remove[k]])
				//seen.add(right[remove[k]])
			}
		}

		answer++
	}

	println(answer)
}
