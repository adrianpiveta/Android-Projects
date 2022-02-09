package livrokotlin.com.br

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast


class CadastroActivity : AppCompatActivity() {
    val COD_IMAGE = 404
    var imageBitMap: Bitmap? = null //nullavel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        img_foto_produto.setOnClickListener{
            abrirGaleria()
        }
        btn_inserir.setOnClickListener {

            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()

            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                database.use {
                    val idProduto = insert("Produtos",
                        "nome" to produto,
                        "quantidade" to qtd,
                        "valor" to valor.toDouble(),
                        "foto" to imageBitMap?.toByteArray()//"?" é nullavel
                    )
                    if (idProduto != -1L) {
                        toast("produto Inserido com sucesso")
                        txt_produto.text.clear() //limpeza após inserir produto
                        txt_qtd.text.clear()
                        txt_valor.text.clear()  //Log.d("prod", produtosGlobal.toString())
                    } else {
                        toast("produto nao inserido")
                    }
                }
            } else {
                if(produto.isEmpty())txt_produto.error="Preencha o nome" else null

                if(qtd.isEmpty()){
                    txt_qtd.error="Insira a quantidade"
                } else{
                   txt_qtd.error=null
                }

                if(valor.isEmpty()){
                    txt_valor.error="Insira o valor"
                } else{
                    txt_valor.error=null
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==COD_IMAGE && resultCode== Activity.RESULT_OK){
            if(data!=null){
                //aqui podemos acessar a imagem na variável data
                val inputStream = contentResolver.openInputStream(data.getData()!!)

                imageBitMap = BitmapFactory.decodeStream(inputStream)

                img_foto_produto.setImageBitmap(imageBitMap)
            }
        }
    }

    private fun abrirGaleria() {

        //define a ação de conteúdo
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        //define que o tipo será de imagens, sem filtros de tipo
        intent.type="image/*"

        startActivityForResult(Intent.createChooser(intent, "" +
                "Selecione uma imagem"),COD_IMAGE)
    }
}