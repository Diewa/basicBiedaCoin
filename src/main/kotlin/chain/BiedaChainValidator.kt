package chain

class BiedaChainValidator(private val complexity: Int) {
    fun validateBiedaChain(chain: List<BiedaBlock>): Boolean {
        if (chain.size <= 1) {
            return true
        }

        for (i in 1 until chain.size) {
            if (chain[i].getPreviousHash() != chain[i - 1].getHash()) {
                return false
            }
            if (chain[i].getHash().substring(0, complexity) != "0".repeat(complexity)) {
                return false
            }
        }
        return true
    }
}