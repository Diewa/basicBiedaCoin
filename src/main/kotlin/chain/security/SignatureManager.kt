package chain.security

import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature

class SignatureManager {
    companion object {

        // ECDSA - Elliptic Curve Digital Signature Algorithm
        // DSA - Curve Digital Signature Algorithm
        fun createECDSASignature(privateKey: PrivateKey, input: String): ByteArray {
            return try {
                val ecdsa = Signature.getInstance("ECDSA", "BC")
                ecdsa.initSign(privateKey)
                ecdsa.update(input.toByteArray())

                ecdsa.sign()
            } catch (ex: Exception) {
                print("Cannot generate signature: $ex")
                byteArrayOf()
            }
        }

        fun verifySignature(publicKey: PublicKey, data: String, signature: ByteArray): Boolean {
            return try {
                val ecdsa = Signature.getInstance("ECDSA", "BC")
                ecdsa.initVerify(publicKey)
                ecdsa.update(data.toByteArray())

                return ecdsa.verify(signature)
            } catch (ex: Exception) {
                println("cannot verify signature: $ex")
                false
            }
        }
    }
}