import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (nl,m,a,c,x0) = f.readLine().split(" ").map{it.toLong()}
	val n = nl.toInt()
	val array = LongArray(n+1)

	array[0] = x0
	for(k in 1..n){
		array[k] = (a*array[k-1]+c+m)%m
	}

	val left = IntArray(n+1){-1}
	val right = IntArray(n+1){-1}

	val start = (1+n)/2

	fun bs(l : Int, r : Int) : Int{
		if(l>=r) return l
		val mid = (l+r)/2
		left[mid] = bs(l,mid-1)
		right[mid] = bs(mid+1,r)
		return mid
	}

	bs(1,n)
	var answer = 0

	fun dfs(x : Int){
		answer++
		//check left
		if(left[x] != -1){
			if(array[left[x]] < array[x]) dfs(left[x])
		}
		//check right
		if(right[x] != -1){
			if(array[right[x]] > array[x]) dfs(right[x])
		}
	}

	dfs(start)

	println(answer)
	//println(left.joinToString(" "))
	//println(right.joinToString(" "))


}
