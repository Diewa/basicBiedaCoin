package chain

import chain.transaction.TransactionFactory
import chain.transaction.Wallet
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider

fun main() {
    Security.addProvider(BouncyCastleProvider())

    val complexity = 1
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
        println("chain is valid")
    } else {
        println("chain is not valid")
    }


    println("lets test transactions")
    val walletA = Wallet() // sender
    val walletB = Wallet() // recipient

    val transaction = TransactionFactory.createTransaction(walletA.privateKey, walletA.publicKey, walletB.publicKey, 3)
    println("signature check pass?:" + transaction.verifySignature())
}
