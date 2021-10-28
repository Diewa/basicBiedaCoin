package chain

class BiedaBlockMiner(private val complexity: Int) {

    fun mineBlock(block: BiedaBlock) {
        val prefix = "0".repeat(complexity)

        while (block.calculateHash().substring(0, complexity) != prefix) {
            block.increaseNonce()
            block.calculateHash()
        }

        println("mining finished with result: " + block.getHash())
    }
}