package livrokotlin.com.br

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.list_view_item.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener {
            val produto = txt_produto.text.toString()
            val qtd = txt_item_qtd.text.toString()
            val valor = txt_item_valor.text.toString()
            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                val prod = Produto(produto, qtd.toInt(), valor.toDouble())
                produtosGlobal.add(prod)

                Log.d("prod", produtosGlobal.toString())
                txt_produto.text.clear() //limpeza após inserir produto
                txt_qtd.text.clear()
                txt_valor.text.clear()
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
}