package ru.skillbranch.devintensive.models



class Bender(var status: Status=Status.NORMAL, var question: Question =Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val rez = question.CheckAnsver(answer)

        return if (rez.length > 0)
            "${rez}\n${question.question}" to status.color
        else {
            if (question.answers.contains(answer.toLowerCase())) {
                if (question == Question.IDLE) "Отлично - ты справился\nНа этом все, вопросов больше нет"
                else {
                    if (rez.length == 0) question = question.nextQuestion()
                    "Отлично - ты справился\n${question.question}"
                } to status.color
            } else {
                if (question == Question.IDLE) "На этом все, вопросов больше нет"
                else {
                    status = status.nextStatus()
                    "Это неправильный ответ${if (status == Status.NORMAL) {question=Question.NAME; ". Давай все по новой"} 
                    else ""}\n${question.question}"
                } to status.color

            }
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }

        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun CheckAnsver(answer: String): String =
                if (answer.length > 0 && answer[0] == answer[0].toUpperCase()) ""
                else "Имя должно начинаться с заглавной буквы"


            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun CheckAnsver(answer: String): String =
                if (answer.length > 0 && answer[0] == answer[0].toLowerCase()) ""
                else "Профессия должна начинаться со строчной буквы"
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun CheckAnsver(answer: String): String =
                if (answer.length > 0 && !answer.matches(Regex(".*[0-9].*"))) ""
                else "Материал не должен содержать цифр"
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun CheckAnsver(answer: String): String =
                if (answer.length > 0 && !answer.matches(Regex(".*[^0-9].*")))
                    ""
                else
                    "Год моего рождения должен содержать только цифры"
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun CheckAnsver(answer: String): String =
                if (answer.length == 7 && !answer.matches(Regex(".*[^0-9].*"))) ""
                else "Серийный номер содержит только цифры, и их 7"
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun CheckAnsver(answer: String): String = ""

        };

        abstract fun nextQuestion(): Question
        abstract fun CheckAnsver(answer: String): String

    }
}

/*    Question.NAME -> "Имя должно начинаться с заглавной буквы"

    Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"

    Question.MATERIAL -> "Материал не должен содержать цифр"

    Question.BDAY -> "Год моего рождения должен содержать только цифры"

    Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"

    Question.IDLE -> //игнорировать валидацию*/

