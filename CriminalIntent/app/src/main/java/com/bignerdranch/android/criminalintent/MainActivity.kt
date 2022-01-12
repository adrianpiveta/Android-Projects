package com.bignerdranch.android.criminalintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * Agora serão gerenciados os fragmentos
        * Esse currentFragment retorna o ID da View
        * Normalmente se usa um container por fragmento
        * Fragmentos possuem mesmos estados das atividades, então, as funções do ciclo
        *   de vida devem ser correspondentes aos das atividades, porém são chamadas pelo
        *   FragmentManager.
        * Quando um fragmento é adicionado em uma atividade são feitas as etapas para achar
        *   o mesmo passo que a atividade está no momento, de forma contínua, sempre se alinhaando.
        * É errado se utilizar fragmentos para tudo, mas sim para o que é reutilizável
        * Recomendável no máximo de 2 a 3 fragmentos por vez*/
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        /*
        * A transação com fragmentos é feita de forma semelhante ao gerenciamento em banco de Dados
        *    é necessário ter a transação explícita, com o begin() para iniciar, a transação e
        *    o commit(). a transação é o coração de operações com fragmentos.
        * Quando uma atividade é destruída, os fragmentos são mortos, então é necessário recria-los.
        * */
        if (currentFragment == null) {
            val fragment = CrimeFragment()
            supportFragmentManager
                .beginTransaction() //inicio
                .add(R.id.fragment_container, fragment) // destino (id) e o fragmento em si
                .commit() //Efetivação
        }
    }
}