//Lost Root
import java.io.*
import java.util.*
import kotlin.math.*
import java.util.Random
//works most of the time without tle trick
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val random = Random()

	val depths = mutableListOf<Int>()
	depths.add(n-1)

	var i = 0
	while(depths[i] > 0){
		depths.add(depths[i]/m-1)
		i++
	}

	//	println(depths.joinToString(" "))

	val ntod = HashMap<Int,Int>()
	for(k in 0 until depths.size){
		ntod[depths[k]] = k
		ntod[depths[k]*(m-1)/m+n-depths[k]-1] = k

		//println("$k ${depths[k]} ${depths[k]*(m-1)/m+n-depths[k]-1}")
	}


	var numq = 0
	fun query(a : Int, b : Int, c : Int) : String{
		if(numq >= 60*n){
			 	while(true){}
		}
		numq++

		println("? $a $b $c")
		System.out.flush()

		return f.readLine()
	}

	val searched = HashSet<Int>()

	fun getdepth(x : Int) : Int{
		searched.add(x)
		var a = random.nextInt(n)+1
		while(a == x) a = random.nextInt(n)+1

		var count = 0
		for(k in 1..n){
			if(k == a || k == x) continue
			val s = query(k,x,a)
			if(s.equals("Yes")){
				count++
			}
		}

		return ntod[count]!!
	}

	outer@while(true){
		var a = 0
		var b = 0
		while(a == b){
			a = random.nextInt(n)+1
			b = random.nextInt(n)+1
		}

		for(k in 1..n){
			if(k == a || k == b || searched.contains(k)) continue
			val s = query(a,k,b)
			if(s.equals("Yes")){
				//println("$k ${getdepth(k)}")
				if(getdepth(k) == 0){
					println("! $k")
					break@outer
				}
			}
		}
	}
}
