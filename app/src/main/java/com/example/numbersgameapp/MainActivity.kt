package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //    private lateinit var clRootNumberGame: ConstraintLayout
//    private lateinit var guessField: EditText
//    private lateinit var guessButton: Button
    var message = ArrayList<String>()

    private var answer = 0
    private var guess = 3
    private var newGame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clRootNumberGame = findViewById<ConstraintLayout>(R.id.clMain)
        val guessField = findViewById<EditText>(R.id.et_Text)
        val guessButton = findViewById<Button>(R.id.btn_Gusses)
        val myRV = findViewById<RecyclerView>(R.id.rvMain)

        answer = Random.nextInt(10)

        val addAlert = AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage(
                "You loss..\n" +
                        "the correct anser was $answer \n" +
                        "\n" +
                        "Play again?"
            )

            .setPositiveButton("Yes") { _, _ ->
                this.recreate()
            }.setNegativeButton("No") { _, _ ->
                print("Work!!!")
            }.create()

        val addAlertWiner = AlertDialog.Builder(this)
            .setTitle("Winner")
            .setMessage(
                "Right Answer  \n" +
                        "\n" +
                        "Play again?"
            )

            .setPositiveButton("Yes") { _, _ ->
                this.recreate()
            }.setNegativeButton("No") { _, _ ->
                print("Work!!!")
            }.create()

        val myAdapter = RVAdapter(message)

        guessButton.setOnClickListener {
            print("$answer !!!!!!!!!!!!!!!!!!!!!!!!!")
            var check = guessField.text.toString()
            if (guess > 0 && answer != check.toInt()) {
                guess--
                var text = "\"Your guessed $check  \n" +
                        "\n" +
                        " you have $guess guess left"
                message.add(text)
                guessField.text.clear()
                myAdapter.notifyDataSetChanged()


            } else if (guess > 0 && answer == check.toInt()) {
                guess--
                guessField.text.clear()

                addAlertWiner.show()
            } else {

                addAlert.show()
            }

        }

        myRV.layoutManager = LinearLayoutManager(this)

        myRV.adapter = myAdapter




    }



}