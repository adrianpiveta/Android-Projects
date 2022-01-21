package livrokotlin.com.br

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementação	do	adaptador
        val produtosAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1
        )
        //definindo	o	adaptador	na	lista
        list_view_produtos.adapter = produtosAdapter

        btn_inserir.setOnClickListener {
            val produto = txt_produto.text.toString()
            if (produto.isNotEmpty()) {
                produtosAdapter.add(produto)
            } else {
                txt_produto.error = "Digite um produto!"//Mostra erro na caixa de escrita
            }
            txt_produto.text.clear()
        }

        list_view_produtos.setOnLongClickListener{
            adapterview: AdapterView<*>, view: View,
                position: Int, id: Long ->

            //val item
            val item = produtosAdapter.getItem(position)

            //remoção
            produtosAdapter.remove(item)

            true
        }
    }
}