package livrokotlin.com.br

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.jetbrains.anko.db.RowParser
import java.io.ByteArrayOutputStream

val produtosGlobal = mutableListOf<Produto>()

//essas são funções de extensão, poderiam estar em qualquer arquivo

fun Bitmap.toByteArray(): ByteArray{
    // quando utilizamos o this, é o bitmap que chamou a função
    val stream = ByteArrayOutputStream()

    //aqui irá comprimir o bitmap
    this.compress(android.graphics.Bitmap.CompressFormat.PNG,
        0, stream)

    //aqui irá retornar o bitmap em ByteArray
    return stream.toByteArray()
}

//cria o metodo em ByteArray que permite retornar em bitmap
fun ByteArray.toBitmap(): Bitmap{
    return BitmapFactory.decodeByteArray(this,
    0, this.size)
}

