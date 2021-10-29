package chain.security

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashGenerator {

    companion object {
        fun generateHash(dataToHash: String): String {
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

            return buffer.toString()
        }
    }
}