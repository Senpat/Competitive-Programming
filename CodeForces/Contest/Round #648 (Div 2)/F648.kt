import java.io.*
import java.util.*
import kotlin.math.*
//upsolve
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun makepair(a : Int, b : Int): Pair<Int,Int>{
		return Pair(min(a,b),max(a,b))
	}

	fun check1(a : IntArray, b : IntArray) : Boolean{
		val hmapa = HashMap<Int,Int>()
		val hmapb = HashMap<Int,Int>()

		for(k in 0 until a.size){
			hmapa[a[k]] = hmapa.getOrDefault(a[k],0) + 1
			hmapb[b[k]] = hmapb.getOrDefault(b[k],0) + 1
		}

		for(i in hmapa.keys){
			if(!hmapb.containsKey(i) || hmapb[i] != hmapa[i]) return false
		}


		if(a.size % 2 == 1 && a[a.size/2] != b[b.size/2]) return false
		return true
	}

	fun check2(a : IntArray, b : IntArray) : Boolean{
		val n = a.size

		val hmapa = HashMap<Pair<Int,Int>,Int>()
		val hmapb = HashMap<Pair<Int,Int>,Int>()

		for(k in 0 until n/2){
			hmapa[makepair(a[k],a[n-k-1])] = hmapa.getOrDefault(makepair(a[k],a[n-k-1]),0) + 1
			hmapb[makepair(b[k],b[n-k-1])] = hmapb.getOrDefault(makepair(b[k],b[n-k-1]),0) + 1
		}

		for(i in hmapa.keys){
			if(!hmapb.containsKey(i) || hmapb[i] != hmapa[i]) return false
		}

		return true

	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()



		val a = f.readLine().split(" ").map{it.toInt()}.toIntArray()
		val b = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		//println("${check1(a,b)} ${check2(a,b)}")
		if(!check1(a,b) || !check2(a,b)){
			println("No")
		} else {
			println("Yes")
		}


	}
}
