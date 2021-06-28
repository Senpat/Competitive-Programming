import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val chars = charArrayOf('A','N','T','O')
	val hmap = HashMap<Char,Int>()
	hmap.put('A',0)
	hmap.put('N',1)
	hmap.put('T',2)
	hmap.put('O',3)



	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().toCharArray()
		val n = array.size

		val indeces = Array<MutableList<Int>>(4){mutableListOf<Int>()}

		for(k in 0 until n){
			indeces[hmap[array[k]]!!].add(k+1)
		}

		var (maxa,maxb,maxc,maxd) = listOf(0,0,0,0)
		var maxinversions = -1L

		var bits = LongArray(n+1)

		fun update(ii : Int, x : Long){
			var i = ii
			while(i <= n){
				bits[i] += x
				i += (i and -i)
			}
		}

		fun psum(ii : Int): Long{
			var i = ii
			var sum : Long = 0L
			while(i > 0){
				sum += bits[i]
				i -= (i and -i)
			}
			return sum
		}


		for(a in 0 until 4){
			for(b in 0 until 4){
				if(a == b) continue
				for(c in 0 until 4){
					if(c == a || c == b) continue
					for(d in 0 until 4){
						if(d == a || d == b || d == c) continue

						//make array
						val cur = mutableListOf<Int>()
						cur.add(0) 				//1-indexed
						for(i in indeces[a]) cur.add(i)
						for(i in indeces[b]) cur.add(i)
						for(i in indeces[c]) cur.add(i)
						for(i in indeces[d]) cur.add(i)

						//get inversion count
						for(k in 0..n) bits[k] = 0

						var inversions = 0L
						for(k in 1..n){
							inversions += k.toLong()-1L-psum(cur[k])
							update(cur[k],1)
						}

						if(inversions > maxinversions){
							maxinversions = inversions
							maxa = a
							maxb = b
							maxc = c
							maxd = d
						}

					}
				}
			}
		}

		for(i in 1..indeces[maxa].size) print(chars[maxa])
		for(i in 1..indeces[maxb].size) print(chars[maxb])
		for(i in 1..indeces[maxc].size) print(chars[maxc])
		for(i in 1..indeces[maxd].size) print(chars[maxd])
		println()
	}
}
