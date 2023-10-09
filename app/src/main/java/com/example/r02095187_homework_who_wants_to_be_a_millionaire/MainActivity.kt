package com.example.r02095187_homework_who_wants_to_be_a_millionaire

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), QuestionAdapter.OnOptionSelectedListener {

    private var currentQuestionIndex = 0
    private var totalEarnings = 0
    private lateinit var questionsList: List<QuestionModel>

    private lateinit var totalEarningsTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var confirmButton: Button
    private var selectedOptionIndex: Int = -1 // Variable to store the selected answer index


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        totalEarningsTextView = findViewById(R.id.totalEarningsTextView)
        questionTextView = findViewById(R.id.questionTextView)
        option1Button = findViewById(R.id.option1Button)
        option2Button = findViewById(R.id.option2Button)
        option3Button = findViewById(R.id.option3Button)
        option4Button = findViewById(R.id.option4Button)
        confirmButton = findViewById(R.id.confirmButton)

        // Load questions
        questionsList = loadQuestions()

        // Show splash screen for 2 seconds
        showSplashScreen()

        // Delay for a few seconds and then load the first question
        Handler(Looper.getMainLooper()).postDelayed({
            loadQuestion()
        }, SPLASH_DELAY_MS)
    }

    private fun showSplashScreen() {
        // Set visibility of UI components for splash screen
        totalEarningsTextView.visibility = View.GONE
        questionTextView.visibility = View.GONE
        option1Button.visibility = View.GONE
        option2Button.visibility = View.GONE
        option3Button.visibility = View.GONE
        option4Button.visibility = View.GONE
        confirmButton.visibility = View.GONE
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < questionsList.size) {
            val question = questionsList[currentQuestionIndex]

            // Set question and options to UI components
            totalEarningsTextView.text = "Total Earnings: $$totalEarnings"
            questionTextView.text = question.question
            option1Button.text = question.options[0]
            option2Button.text = question.options[1]
            option3Button.text = question.options[2]
            option4Button.text = question.options[3]

            // Set visibility of UI components for the question
            totalEarningsTextView.visibility = View.VISIBLE
            questionTextView.visibility = View.VISIBLE
            option1Button.visibility = View.VISIBLE
            option2Button.visibility = View.VISIBLE
            option3Button.visibility = View.VISIBLE
            option4Button.visibility = View.VISIBLE
            confirmButton.visibility = View.VISIBLE

            // Reset background colors of all option buttons
            option1Button.setBackgroundResource(android.R.drawable.btn_default)
            option2Button.setBackgroundResource(android.R.drawable.btn_default)
            option3Button.setBackgroundResource(android.R.drawable.btn_default)
            option4Button.setBackgroundResource(android.R.drawable.btn_default)

            // Handler for user click on the selected answer
            option1Button.setOnClickListener {
                selectOption(0)
            }
            option2Button.setOnClickListener {
                selectOption(1)
            }
            option3Button.setOnClickListener {
                selectOption(2)
            }
            option4Button.setOnClickListener {
                selectOption(3)
            }

            // Handler for user click on the Confirm button
            confirmButton.setOnClickListener {
                if (selectedOptionIndex != -1) {
                    onOptionSelected(selectedOptionIndex)
                } else {
                    showToast("Please select an answer.")
                }
            }
        } else {
            // All questions answered, show statistics
            navigateToStatsActivity()
        }
    }

    override fun onOptionSelected(optionIndex: Int) {
        val question = questionsList[currentQuestionIndex]

        if (optionIndex == question.correctAnswerIndex) {
            totalEarnings += question.rewardAmount
            showToast("Correct! You won $${question.rewardAmount}")
        } else {
            showToast("Incorrect! You earned $0")
        }

        currentQuestionIndex++
        loadQuestion() // Load the next question
    }

    private fun selectOption(optionIndex: Int) {
        // Reset background color of previously selected option (if any)
        when (selectedOptionIndex) {
            0 -> option1Button.setBackgroundResource(android.R.drawable.btn_default)
            1 -> option2Button.setBackgroundResource(android.R.drawable.btn_default)
            2 -> option3Button.setBackgroundResource(android.R.drawable.btn_default)
            3 -> option4Button.setBackgroundResource(android.R.drawable.btn_default)
        }

        // Set background color of the selected option
        when (optionIndex) {
            0 -> option1Button.setBackgroundColor(Color.GREEN)
            1 -> option2Button.setBackgroundColor(Color.GREEN)
            2 -> option3Button.setBackgroundColor(Color.GREEN)
            3 -> option4Button.setBackgroundColor(Color.GREEN)
        }

        // Update the selected option index
        selectedOptionIndex = optionIndex
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToStatsActivity() {
        val intent = Intent(this, StatsActivity::class.java)
        intent.putExtra("TOTAL_EARNINGS", totalEarnings)
        startActivity(intent)
        finish() // Prevent user from coming back to this activity
    }

    private fun loadQuestions(): List<QuestionModel> {
        return listOf(
            QuestionModel(
                "What was Ally McBeal's profession?",
                listOf("Doctor", "Lawyer", "Accountant", "Social Worker"),
                correctAnswerIndex = 1,
                rewardAmount = 500
            ),
            QuestionModel(
                "What word can be put in front of the words 'track', 'way' and 'horse' to make three other words?",
                listOf("Road", "Sound", "Cross", "Race"),
                correctAnswerIndex = 3,
                rewardAmount = 1000
            ),
            QuestionModel(
                "Which of these cartoon characters is a rodent?",
                listOf("Road Runner", "Tasmanian Devil", "Speedy Gonzalez", "Wile E. Coyote"),
                correctAnswerIndex = 2,
                rewardAmount = 2000
            ),
            QuestionModel(
                "Which planet is known as the Red Planet?",
                listOf("Earth", "Mars", "Venus", "Jupiter"),
                correctAnswerIndex = 1,
                rewardAmount = 3000
            ),
            QuestionModel(
                "What were the first two initials of the character played by Larry Hagman in 'Dallas'?",
                listOf("JD", "JC", "JG", "JR"),
                correctAnswerIndex = 3,
                rewardAmount = 4000
            ),
            QuestionModel(
                "In the Old Testament, who, with God's help, parted the Red Sea?",
                listOf("Jonah", "Moses", "Noah", "Joseph"),
                correctAnswerIndex = 1,
                rewardAmount = 5000
            ),
            QuestionModel(
                "Which month of the year was named after Julius Caesar?",
                listOf("October", "June", "July", "August"),
                correctAnswerIndex = 2,
                rewardAmount = 7000
            ),
        )
    }


    companion object {
        private const val SPLASH_DELAY_MS: Long = 2000 // 2 seconds delay for the splash screen
    }
}


