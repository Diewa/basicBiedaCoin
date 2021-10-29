package chain.security

import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.spec.ECGenParameterSpec
import java.util.Base64

class KeyGenerator {
    companion object {
        fun generateKeyPair(): KeyPair {
            try {
                val keyGen = KeyPairGenerator.getInstance("ECDSA", "BC")
                val random: SecureRandom = SecureRandom.getInstance("SHA1PRNG")
                val ecSpec = ECGenParameterSpec("prime192v1")

                // Initialize the key generator and generate a KeyPair
                keyGen.initialize(ecSpec, random) //256 bytes provides an acceptable security level

                return keyGen.generateKeyPair()
            } catch (e: Exception) {
                print("Cannot generate pair of keys")
                throw RuntimeException(e)
            }
        }

        fun getStringFromKey(key: Key): String {
            return Base64.getEncoder().encodeToString(key.encoded)
        }
    }
}