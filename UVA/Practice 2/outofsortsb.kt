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

	var answer = 0
	fun bs(l : Int, r : Int, al : Long, ar : Long){
		val mid = (l+r)/2
		if(l > r) return
		if(array[mid] >= al && array[mid] <= ar) answer++
		if(l == r) return
		if(array[mid] > ar){
			bs(l,mid-1,al,ar)
		} else if(array[mid] < al){
			bs(mid+1,r,al,ar)
		} else {
			bs(l,mid-1,al,array[mid]-1)
			bs(mid+1,r,array[mid]+1,ar)
		}
	}

	bs(1,n,0L,m-1L)

	println(answer)
	//println(left.joinToString(" "))
	//println(right.joinToString(" "))


}
