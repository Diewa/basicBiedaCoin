package chain

import chain.security.HashGenerator
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Date

class BiedaBlock(private val data: String, private val previousHash: String) {
    private var hash: String = ""
    private val creationDate: Long = Date().time
    private var nonce: Int = 0 //an arbitrary number used in cryptography

    init {
        calculateHash()
    }

    fun getHash(): String {
        return this.hash
    }

    fun getPreviousHash(): String {
        return previousHash
    }

    fun increaseNonce() {
        nonce++
    }

    fun calculateHash(): String {
        val dataToHash: String = this.previousHash + creationDate.toString() + nonce.toString() + data

        hash = HashGenerator.generateHash(dataToHash)
        return hash
    }
}