package chain

fun main() {
    val complexity = 4
    val blockMiner = BiedaBlockMiner(complexity)
    val chainValidator = BiedaChainValidator(complexity)


    val listOfBlocks: MutableList<BiedaBlock> = mutableListOf()

    val firstBiedaBlock = BiedaBlock("some data first one", "0")
    blockMiner.mineBlock(firstBiedaBlock)

    val secondBiedaBlock = BiedaBlock("some data first one", firstBiedaBlock.getHash())
    blockMiner.mineBlock(secondBiedaBlock)

    val thirdBiedaBlock = BiedaBlock("some data first one", secondBiedaBlock.getHash())
    blockMiner.mineBlock(thirdBiedaBlock)

    println("first biedaBlock: " + firstBiedaBlock.getHash())
    println("second biedaBlock: " + secondBiedaBlock.getHash())
    println("third biedaBlock: " + thirdBiedaBlock.getHash())

    listOfBlocks.add(firstBiedaBlock)
    listOfBlocks.add(secondBiedaBlock)
    listOfBlocks.add(thirdBiedaBlock)

    if (chainValidator.validateBiedaChain(listOfBlocks)) {
        print("chain is valid")
    } else {
        print("chain is not valid")
    }
}
