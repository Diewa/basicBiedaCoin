package chain

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Date

class BiedaBlock(private val data: String, private val previousHash: String) {
    private var hash: String = ""
    private val creationDate: Long = Date().time
    private var nonce: Int = 0

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
        val hashBytes: ByteArray = try {
            MessageDigest.getInstance("SHA-256")
                .digest(dataToHash.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            when (ex) {
                is NoSuchAlgorithmException, is UnsupportedEncodingException -> {
                    print("cannot create hash")
                    byteArrayOf()
                }
                else -> throw ex
            }
        }
        val buffer = StringBuffer()
        hashBytes.forEach { byte -> buffer.append(String.format("%02x", byte)) }

        hash = buffer.toString()
        return hash
    }
}