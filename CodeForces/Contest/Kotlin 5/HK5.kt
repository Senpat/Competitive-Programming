import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,xi) = f.readLine().split(" ").map{it.toInt()}
	val x = xi.toLong()

	val a = f.readLine().split(" ").map{it.toLong()}
	val b = f.readLine().split(" ").map{it.toLong()}

	val c = Array<LongArray>(n){
		f.readLine().split(" ").map{it.toLong()}.toLongArray()
	}

	val pq = PriorityQueue<Long>()
	pq.add(c[0][0])

	var ai = 0
	var bi = 0

	while(ai+1 < n && a[ai+1] == 0){
		ai++
		for(k in 0..bi){
			pq.add(c[ai][k])
		}
	}

	while(bi+1 < m && b[bi+1] == 0){
		bi++
		for(k in 0..ai){
			pq.add(c[k][bi])
		}
	}

	var answer = 0
	while(ai < n-1 || bi < m-1){
		answer++

	}

}
