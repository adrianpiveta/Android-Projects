package livrokotlin.com.br.calculoaposentadoria

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : Activity() {

    val list_idades : MutableList<Int> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spn_sexo = findViewById<Spinner>(R.id.spn_sexo)
        val txt_idade = findViewById<EditText>(R.id.txt_idade)
        val btn_calcular = findViewById<Button>(R.id.btn_calcular)
        val txt_resultado = findViewById<TextView>(R.id.txt_resultado)


        //setando os valores do spinner
        spn_sexo.adapter = ArrayAdapter<String>(this, android.R.layout.
        simple_spinner_dropdown_item, listOf("Masculino","Feminino")
        )

        for(i in 1..65){
            list_idades.add(i)
        }

        Log.d("lista",list_idades.toString())

        btn_calcular.setOnClickListener {
            val sexo = spn_sexo.selectedItem as String
            var idade=0
            idade = txt_idade.text.toString().toInt()
            if(idade != null){
                Log.d("batata",txt_idade.text.toString().toInt().toString())
                var resultado=0
                if(sexo=="Masculino"){
                    resultado=(65-idade)
                }
                else{
                    resultado=(60-idade) //setText não suporta concatenação
                }
                if(resultado>0){
                    txt_resultado.setText("Faltam $resultado anos para você se aposentar")
                }
                else{
                    //mesma coisa do que setText
                    txt_resultado.text=("Você já possui idade para ser aposentado!")
                }
            }
        }
    }
}