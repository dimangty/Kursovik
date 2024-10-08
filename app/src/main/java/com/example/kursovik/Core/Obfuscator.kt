package com.example.kursovik
import android.util.Log
import kotlin.experimental.xor

fun String.characters(): List<Char> {
    return this.toList()
}

class Obfuscator {

    // MARK: - Variables

    companion object {
        val vkAPIkey: ByteArray = byteArrayOf(81, 89, 82, 87, 83, 86, 90) // Зашифрованный ключ
    }


    /// The salt used to obfuscate and reveal the string.
    private var salt: String

    // MARK: - Initialization
    constructor() : this("") {
        val a1: Char = 'a'
        val a2: Char = 'b'
        this.salt = String("00".characters().mapIndexed { i, _ ->
            if (i == 0) a2 else a1
        }.toCharArray())
    }

    constructor(salt: String) {
        this.salt = salt
    }

    // MARK: - Instance Methods
    fun bytesByObfuscatingString(string: String): ByteArray {
        val text = string.toByteArray(Charsets.UTF_8)
        val cipher = this.salt.toByteArray(Charsets.UTF_8)
        val length = cipher.size

        val encrypted = ByteArray(text.size)

        for ((index, t) in text.withIndex()) {
            encrypted[index] = t xor cipher[index % length]
        }

        Log.d("Obfuscator", "Salt $salt")
        Log.d("Obfuscator", "Code")
        Log.d("Obfuscator", "Original $string")
        var result = ""
        for (t in encrypted) {
            result += "[$t] "
        }

        Log.d("Obfuscator", "$result")

        return encrypted
    }

    fun reveal(key: ByteArray): String {
        val cipher = this.salt.toByteArray(Charsets.UTF_8)
        val length = cipher.size

        val decrypted = ByteArray(key.size)

        for ((index, k) in key.withIndex()) {
            decrypted[index] = k xor cipher[index % length]
        }

        val result = String(decrypted, Charsets.UTF_8)
        Log.d("Obfuscator", "decrypted $result")
        return result

    }
}

