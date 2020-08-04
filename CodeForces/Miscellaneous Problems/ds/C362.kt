import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()+1}

	val bits = IntArray(n+1)

	fun update(ii : Int, x : Int){
		var i = ii
		while(i <= n){
			bits[i] += x
			i += (i and -i)
		}
	}

	fun psum(ii : Int): Int{
		var i = ii
		var sum : Int = 0
		while(i > 0){
			sum += bits[i]
			i -= (i and -i)
		}
		return sum
	}

	//get initial inversion count
	var initial = 0
	for(k in 0 until n){
		for(j in 0 until k){
			if(array[j] > array[k]) initial++
		}
	}
	//println(initial)
	val freq = IntArray(initial+1)
	var min = initial

	//find maximum decrease

	for(k in 0 until n){
		bits.fill(0)
		update(array[k],1)
		for(j in k+1 until n){
			if(array[j] < array[k]){
				//consider swapping
				var i = initial
				//add number > array[k] in the range
				i += psum(n) - psum(array[k])
				//subtract number less than array[j] in range
				i -= psum(n) - psum(array[j])
				i -= psum(array[k]-1)-psum(array[j])

				//println("$k $j ${psum(n) - psum(array[k])} ${psum(n) - psum(array[j])} ${psum(array[k]-1)-psum(array[j])}")
				if(i <= initial){
					freq[i]++
					min = min(min,i)
				}
			}
			update(array[j],1)
		}
	}

	println("${min} ${freq[min]}")

}
