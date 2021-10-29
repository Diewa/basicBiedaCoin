package chain.transaction

import chain.security.KeyGenerator
import chain.security.SignatureManager
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.spec.ECGenParameterSpec

/**
 * There are a few assumptions:
 * - In order to sign transaction we use private key
 * - Public key is used as sender/receiver address
 */
class Transaction(
    val recipient: PublicKey,
    val sender: PublicKey,
    val value: Int,
) {
    private var transactionId: String = ""
    private var signature: ByteArray = byteArrayOf()


    fun setTransactionId(transactionId: String) {
        this.transactionId = transactionId
    }

    fun createSignature(senderPrivateKey: PrivateKey) {
        val data = KeyGenerator.getStringFromKey(sender) + KeyGenerator.getStringFromKey(recipient) + value
        this.signature = SignatureManager.createECDSASignature(senderPrivateKey, data)
    }

    fun verifySignature(): Boolean {
        val data = KeyGenerator.getStringFromKey(sender) + KeyGenerator.getStringFromKey(recipient) + value
        return SignatureManager.verifySignature(sender, data, signature)
    }
}