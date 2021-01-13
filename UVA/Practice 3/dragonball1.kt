import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = FastScanner()

	val n = f.nextInt()
	val m = f.nextInt()

	val MAXSIZE = 200005
	val adj = Array(MAXSIZE){mutableListOf<Edge>()}
	for(k in 0 until m){
		val a = f.nextInt()
		val b = f.nextInt()
		val t = f.nextInt()
		adj[a].add(Edge(b,t))
		adj[b].add(Edge(a,t))
	}

	val pokes = IntArray(MAXSIZE){0}
	for(k in 0 until 7){
		pokes[f.nextInt()] += 1 shl k
	}


	val dists = Array(MAXSIZE){IntArray(128){Int.MAX_VALUE}}
	val seen = Array(MAXSIZE){BooleanArray(128){false}}

	dists[1][pokes[1]] = 0
	val pq = PriorityQueue<State>(compareBy{it.dist})
	pq.add(State(1,pokes[1],0))

	var answer = -1

	while(!pq.isEmpty()){
		val s = pq.poll()
		if(s.dist != dists[s.v][s.p] || seen[s.v][s.p]) continue
		seen[s.v][s.p] = true

		if(s.p == 127){
			answer = s.dist
			break
		}

		for(e in adj[s.v]){
			if(dists[e.to][s.p or pokes[e.to]] <= s.dist + e.t) continue
			dists[e.to][s.p or pokes[e.to]] = s.dist + e.t
			pq.add(State(e.to,s.p or pokes[e.to],s.dist+e.t))
		}
	}

	println(answer)





}
data class State(val v : Int, val p : Int, val dist : Int)
data class Edge(val to : Int, val t : Int)


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
