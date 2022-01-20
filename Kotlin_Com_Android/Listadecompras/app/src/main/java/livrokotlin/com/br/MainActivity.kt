package livrokotlin.com.br

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //define xml que refere-se a essa classe
        //val list_view_produtos = findViewById<View>(R.id.list_view_produto)
        //foi eliminado

        val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        list_view_produtos.adapter = produtosAdapter


    }
}