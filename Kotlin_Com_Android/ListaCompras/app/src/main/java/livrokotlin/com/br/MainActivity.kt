package livrokotlin.com.br

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.toast
import java.nio.file.Files.delete
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    val COD_IMAGE = 404
    var imageBitMap: Bitmap? = null //nullavel

    override fun onResume() {
        super.onResume()

        /*
        val adapter =
            list_view_produtos.adapter as ProdutoAdapter // puxa adaptador da lista como ProdutoAdapter
        adapter.clear()//limpa a lista de produtos existentes
        adapter.addAll(produtosGlobal)// popula o adapter com a lista


        database.use {
            select("produtos").exec {
                val parser = rowParser {
                    id: Int
                    nome, String,
                    quantidade: Int,
                    valor: Double,
                    foto: ByteArray? ->
                    //colunas do BD

                    // montagem do produto
                    Produto(id, nome, quantidade, valor, foto?.toBitmap())
                }
                var listaProdutos = parseList(parser)

                adapter.clear()
                adapter.addAll(listaProdutos)



            val soma = listaProdutos.sumByDouble{
                it.valor * it.quantidade}
            val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            txt_total.text = "Total: ${f.format(soma)}"
        }
    }*/

        val adapter =
            list_view_produtos.adapter as ProdutoAdapter
        database.use {
            select("produtos").exec {
                //Criando	o	parser	que	montará	o	objeto	produto
                val parser = rowParser { id: Int, nome: String,
                                         quantidade: Int,
                                         valor: Double,
                                         foto: ByteArray? ->
                    //Colunas	do	banco	de	dados
                    //Montagem	do	objeto	Produto	com	as	colunas	do	banco
                    Produto(id, nome, quantidade, valor, foto?.toBitmap())
                }
                //criando	a	lista	de	produtos	com	dados	do	banco
                var listaProdutos = parseList(parser)
                //limpando	os	dados	da	lista	e	carregando	as	novas	informações
                adapter.clear()
                adapter.addAll(listaProdutos)
                //efetuando	a	multiplicação	e	soma	da	quantidade	e	valor
                val soma = listaProdutos.sumByDouble { it.valor * it.quantidade }
                //formatando	em	formato	moeda
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                txt_total.text = "TOTAL:	${f.format(soma)}"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // classe de visualização de conteudo


        //Implementação	do	adaptador
        val produtosAdapter = ProdutoAdapter(this)


        //definindo	o	adaptador	na	lista
        list_view_produtos.adapter = produtosAdapter



        list_view_produtos.isClickable = true

        list_view_produtos.setOnItemLongClickListener { adapter: AdapterView<*>, view1: View,
                                                        i: Int, l: Long ->

            val item = produtosAdapter.getItem(i)

            produtosAdapter.remove(item)

            if (item != null) {
                deletarProduto(item.id)
            }
            toast("Produto deletado com sucesso")

            true

        }


/*
        list_view_produtos.setOnLongClickListener {

            //buscando	o	item	clicado
            val item = list_view_produtos.selectedItem.toString()
            //removendo	o	item	clicado	da	lista
            produtosAdapter.remove(item)

            Toast.makeText(this, "Clicou", Toast.LENGTH_SHORT).show()
            //retorno	indicando	que	o	click	foi	realizado	com	suc
            true
        }*/
        /*
        list_view_produtos.onItemLongClickListener{adapterView:	AdapterView<*>,	view:	View,	position:	Int,	id:	Long	->
            //buscando	o	item	clicado
            val	item	=	produtosAdapter.getItem(position)
            //removendo	o	item	clicado	da	lista
            produtosAdapter.remove(item)
            list_view_produtos.adapter=produtosAdapter
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
            //retorno	indica
            true} */

        /*list_view_produtos.setOnItemClickListener { parent, view, position, id ->
            position
            Toast.makeText(this, produtosAdapter.getItem(position).toString(), Toast.LENGTH_SHORT)
                .show()
        }*/
        // Matriz quadrada
        var matriz = ArrayList<List<Int>>()

        btn_adicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)

            startActivity(intent)
        }


        // matriz quadrada
        /*
        var i=5
        var j=5
        for (x in 1..i) {
            val matrizaux = ArrayList<Int>()
            for (y in 1..j) { //i é linha
                matrizaux.add(0)
            }
            matriz.add(matrizaux)
        }
        */

        /*
        var i=5
        var j=5
        for (x in 1..i) {
            val matrizaux = ArrayList<Int>()
            for (y in 1..j) { //i é linha
                if( x <=y ){
                    matrizaux.add(Random.nextInt(1,9))
                }
                else{
                    matrizaux.add(0)
                }
            }
            matriz.add(matrizaux)
        }

        txt_total.setText(escreveBonitinho(matriz))

    }

    private fun escreveBonitinho(matriz: ArrayList<List<Int>>): String {
        var retorno=""
        for(x in matriz){
            for (y in x){
                retorno+=(y.toString() + " ")
            }
            retorno+="\n"
        }
        return retorno
    }
    */

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                //aqui podemos acessar a imagem na variável data
                val inputStream = contentResolver.openInputStream(data.getData()!!)

                imageBitMap = BitmapFactory.decodeStream(inputStream)

                img_foto_produto.setImageBitmap(imageBitMap)
            }
        }
    }
    fun deletarProduto(idProduto: Int) {
        database.use {
            delete("produto", "id = {id}", "id" to idProduto)
        }
    }

}


/*
public class MainActivity extends Activity {
 ListView listview; String[] subjects = new String[] { "Android", "PHP", "Blogger", "WordPress", "SEO" };
  List<String> subject_list;
   ArrayAdapter<String> arrayadapter;
    @Override protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
     listview = (ListView)findViewById(R.id.listView1);
     subject_list = new ArrayList<String>(Arrays.asList(subjects));
     arrayadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, subject_list);
     listview.setAdapter(arrayadapter);

     listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override public boolean onItemLongClick(AdapterView<?> parent,
       View view, int position, long id) {
            subject_list.remove(position);
            arrayadapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_LONG).show();
            return true;
                                                                                                                                                                                                                                                                                                                                               } });
                                                                                                                                                                                                                                                                                                                                                } }
* */
