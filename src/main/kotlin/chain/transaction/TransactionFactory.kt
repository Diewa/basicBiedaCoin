package chain.transaction

import chain.security.HashGenerator
import chain.security.KeyGenerator
import java.security.PrivateKey
import java.security.PublicKey

class TransactionFactory {
    companion object {
        private var sequence: Int = 0

        fun createTransaction(
            senderPrivateKey: PrivateKey,
            senderPublicKey: PublicKey,
            recipientPublicKey: PublicKey,
            value: Int
        ): Transaction {
            val transaction = Transaction(recipientPublicKey, senderPublicKey, value)
            transaction.setTransactionId(calculateTransactionHash(transaction))
            transaction.createSignature(senderPrivateKey)

            return transaction
        }

        private fun calculateTransactionHash(transaction: Transaction): String {
            sequence++
            return HashGenerator.generateHash(
                KeyGenerator.getStringFromKey(transaction.sender) +
                        KeyGenerator.getStringFromKey(transaction.recipient) +
                        transaction.value + sequence
            )
        }
    }
}