package chain.transaction

import chain.security.KeyGenerator
import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey

// Wallet acts as a user
class Wallet{
    var publicKey: PublicKey // used as address, necessary to receive transfer
    var privateKey: PrivateKey // used to create signature (message + privKey)

    init {
        val keyPair: KeyPair = KeyGenerator.generateKeyPair();
        publicKey = keyPair.public
        privateKey = keyPair.private
    }

}