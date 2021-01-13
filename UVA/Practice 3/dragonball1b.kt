import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = FastScanner()

	val n = f.nextInt()
	val m = f.nextInt()

	val adj = Array(n+1){mutableListOf<Edge>()}
	for(k in 0 until m){
		val a = f.nextInt()
		val b = f.nextInt()
		val t = f.nextInt().toLong()
		adj[a].add(Edge(b,t))
		adj[b].add(Edge(a,t))
	}

	val pokes = mutableListOf<Int>()
	val seenpokes = HashSet<Int>()

	for(k in 0 until 7){
		val i = f.nextInt()
		if(!seenpokes.contains(i)){
			pokes.add(i)
			seenpokes.add(i)
		}
	}

	val nump = pokes.size

	val diststart = LongArray(n+1){Long.MAX_VALUE}
	diststart[1] = 0L
	var pq = PriorityQueue<State>(compareBy{it.dist})
	pq.add(State(1,0L))

	while(!pq.isEmpty()){
		val s = pq.poll()
		if(s.dist != diststart[s.v]) continue

		for(e in adj[s.v]){
			if(diststart[e.to] <= s.dist + e.t) continue
			diststart[e.to] = s.dist + e.t
			pq.add(State(e.to,s.dist+e.t))
		}
	}

	val dists = Array(nump){LongArray(n+1){Long.MAX_VALUE}}
	for(k in 0 until nump){

		dists[k][pokes[k]] = 0L
		pq = PriorityQueue<State>(compareBy{it.dist})
		pq.add(State(pokes[k],0L))

		while(!pq.isEmpty()){
			val s = pq.poll()
			if(s.dist != dists[k][s.v]) continue

			for(e in adj[s.v]){
				if(dists[k][e.to] <= s.dist + e.t) continue
				dists[k][e.to] = s.dist + e.t
				pq.add(State(e.to,s.dist+e.t))
			}
		}
	}

	val perms = mutableListOf<MutableList<Int>>()

	val taken = BooleanArray(nump){false}
	fun getperm(curperm : MutableList<Int>){
		if(curperm.size == nump) perms.add(curperm.toMutableList())
		else{
			for(k in 0 until nump){
				if(!taken[k]){
					taken[k] = true
					curperm.add(k)
					getperm(curperm)
					curperm.removeAt(curperm.size-1)
					taken[k] = false
				}
			}
		}
	}

	getperm(mutableListOf<Int>())

	var answer = Long.MAX_VALUE
	for(list in perms){
		var cur = diststart[pokes[list[0]]]
		for(k in 1 until nump){
			cur += dists[list[k-1]][pokes[list[k]]]
		}
		answer = min(answer,cur)
	}

	println(answer)





}
data class State(val v : Int, val dist : Long)
data class Edge(val to : Int, val t : Long)


class FastScanner {
    private val BS = 1 shl 16
    private val NC = 0.toChar()
    private val buf = ByteArray(BS)
    private var bId = 0
    private var size = 0
    private var c = NC
    private var `in`: BufferedInputStream? = null

    constructor() {
        `in` = BufferedInputStream(System.`in`, BS)
    }

    private val char: Char
        private get() {
            while (bId == size) {
                size = try {
                    `in`!!.read(buf)
                } catch (e: Exception) {
                    return NC
                }
                if (size == -1) return NC
                bId = 0
            }
            return buf[bId++].toChar()
        }

    fun nextInt(): Int {
        var neg = false
        if (c == NC) c = char
        while (c < '0' || c > '9') {
            if (c == '-') neg = true
            c = char
        }
        var res = 0
        while (c >= '0' && c <= '9') {
            res = (res shl 3) + (res shl 1) + (c - '0')
            c = char
        }
        return if (neg) -res else res
    }
}
