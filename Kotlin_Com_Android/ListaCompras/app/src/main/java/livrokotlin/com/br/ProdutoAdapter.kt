package livrokotlin.com.br

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

// O Layout será passado mais adiante, por isso ficou definido como 0, para dizer que não há layout (por agora)
class ProdutoAdapter (contexto: Context) : ArrayAdapter<Produto>(contexto, 0){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View
        if(convertView!=null){
            v=convertView
        } else{
            v= LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
                                                    // (XML, elemento na qual será inserido, será anexado ao view raíz
        }

        val item = getItem(position)
        val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txt_valor = v.findViewById<TextView>(R.id.txt_item_valor)
        val imagem_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        if (item != null) {
            txt_produto.text=item.nome
            txt_qtd.text=item.quantidade.toString()
            val f = NumberFormat.getCurrencyInstance(Locale("pr","br"))
            txt_valor.text=f.format(item.valor)
        }

        if(item?.foto !=null){
            imagem_produto.setImageBitmap(item.foto)
        }
        return v
    }
}