import java.io.*
import java.util.*
import kotlin.math.*
//works on first tc
fun main(roger : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

		fun getlen(x : Long) : Int{
			var i = x
			var ret = 0
			while(i > 0){
				ret++
				i = i shr 1
			}
			return ret
		}

		val lens = IntArray(n)
		for(k in 0 until n){
			var i = array[k]
			while(i > 0){
				lens[k]++
				i = i shr 1
			}
		}

		//2nd biggest bit
		val bit2 = IntArray(n)
		for(k in 0 until n){
			var i = array[k]
			var curbit = 1
			var lastbit = -1
			while(i > 1){
				if(i and 1L == 1L) lastbit = curbit
				curbit++
				i = i shr 1
			}

			bit2[k] = lastbit
		}

		fun solve() : Long{

			var answer = 0L

			for(k in 63 downTo 0){
				//most significant bit is 1 shl k

				val firsts = Array(64){mutableListOf<Long>()}
				val seconds = Array(64){mutableListOf<Long>()}

				for(j in 0 until n){
					//check if it can be x
					if(bit2[j] == -1){
						firsts[0].add(array[j])
					} else {
						firsts[bit2[j]].add(array[j])
					}

					//check if it can be y
					seconds[lens[j]].add(array[j])

				}



				for(j in 0 until k){
					if(firsts[j].size == 0 || seconds[k-j].size == 0) continue
					//take max first and min second
					val maxfirst = firsts[j].max()!!
					val minsecond = seconds[k-j].min()!!

					val xy = (maxfirst shl getlen(minsecond)) + minsecond
					val yx = (minsecond shl getlen(maxfirst)) + maxfirst


					//println("$k $maxfirst $minsecond ${xy-yx}")
					answer = max(answer,abs(xy-yx))
				}


			}
			return answer

		}


		println(solve())
	}
}
