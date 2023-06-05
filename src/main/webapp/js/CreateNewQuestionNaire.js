DeleteQuestion = function (Index) {

    document.getElementById("Question_List").removeChild($("#Question_List li")[Index]);

    ReIndexQuestion();

}

DeleteOption = function (QuestionIndex, OptionIndex) {

    let question = $("#Question_List li")[QuestionIndex];

    question.querySelector(".QuestionNaire-Question-Radio-Options").removeChild(question.querySelectorAll(".QuestionNaire-Question-Radio-Options li")[OptionIndex]);

    ReIndexQuestion();

}

InsertQuestion_Enter = function () {

    let li = document.createElement("li");
    li.innerHTML =
        "<div class='QuestionNaire-Question'>" +
        "   <input class='QuestionNaire-Question-Kind' type='hidden' value='Enter'>" +
        "   <label>" +
        "       <input name='QuestionNaire-Question-Title' class='QuestionNaire-Question-Title-Input' type='text' placeholder='问题内容' required>" +
        "   </label>" +
        "   <input class='QuestionNaire-Question-Delete' type='button' value='删除'>" +
        "</div>";

    document.getElementById("Question_List").appendChild(li);

    ReIndexQuestion();

}

InsertQuestion_Single = function () {

    let li = document.createElement("li");
    li.innerHTML =
        "<div class='QuestionNaire-Question'>" +
        "   <input class='QuestionNaire-Question-Kind' type='hidden' value='Single'>" +
        "   <label>" +
        "       <input name='QuestionNaire-Question-Title' class='QuestionNaire-Question-Title-Input' type='text' placeholder='问题内容' required>" +
        "   </label>" +
        "   <input class='QuestionNaire-Question-Delete' type='button' value='删除'>" +
        "   <ul class='QuestionNaire-Question-Radio-Options'>" +
        "   </ul>" +
        "   <input class='QuestionNaire-Question-Radio-OptionInsert-Button' type='button' value='添加新单选项'>" +
        "</div>";

    document.getElementById("Question_List").appendChild(li);

    ReIndexQuestion();

}

InsertQuestion_Multiple = function () {

    let li = document.createElement("li");
    li.innerHTML =
        "<div class='QuestionNaire-Question'>" +
        "   <input class='QuestionNaire-Question-Kind' type='hidden' value='Multiple'>" +
        "   <label>" +
        "       <input name='QuestionNaire-Question-Title' class='QuestionNaire-Question-Title-Input' type='text' placeholder='问题内容' required>" +
        "   </label>" +
        "   <input class='QuestionNaire-Question-Delete' type='button' value='删除'>" +
        "   <ul class='QuestionNaire-Question-Check-Options'>" +
        "   </ul>" +
        "   <input class='QuestionNaire-Question-Multiple-OptionInsert-Button' type='button' value='添加新多选项'>" +
        "</div>";

    document.getElementById("Question_List").appendChild(li);

    ReIndexQuestion();

}

InsertOption_Radio = function (question_Index) {

    let li = document.createElement("li");

    li.classList.add("QuestionNaire-Question-Radio-Option");

    li.innerHTML =
        "<label class='QuestionNaire-Question-Radio-Label'>" +
        "   <input class='QuestionNaire-Question-Delete-Option' type='button' value='删除'>" +
        "   <input name='QuestionNaire-OptionCont' class='QuestionNaire-Question-RadioCont-Input' type='text' placeholder='单选项' required>" +
        "</label>";

    $("#Question_List li .QuestionNaire-Question")[question_Index].querySelector(".QuestionNaire-Question-Radio-Options").appendChild(li);

    ReIndexQuestion();

}

InsertOption_Multiple = function (question_Index) {

    let li = document.createElement("li");

    li.classList.add("QuestionNaire-Question-Check-Option");

    li.innerHTML =
        "<label class='QuestionNaire-Question-Check-Label'>" +
        "   <input class='QuestionNaire-Question-Delete-Option' type='button' value='删除'>" +
        "   <input name='QuestionNaire-OptionCont' class='QuestionNaire-Question-CheckCont-Input' type='text' placeholder='多选项' required>" +
        "</label>";

    $("#Question_List li .QuestionNaire-Question")[question_Index].querySelector(".QuestionNaire-Question-Check-Options").appendChild(li);

    ReIndexQuestion();

}

ReIndexQuestion = function () {

    let list = $("#Question_List li .QuestionNaire-Question");

    let count = list.length;

    for (let i = 0; i < count; ++i) {

        list[i].querySelector("label .QuestionNaire-Question-Title-Input").name = "QuestionNaire-Question-Title-" + i;

        let kind = list[i].querySelector(".QuestionNaire-Question-Kind").value;

        list[i].querySelector(".QuestionNaire-Question-Kind").name = "QuestionNaire-Question-Kind-" + i;

        list[i].querySelector(".QuestionNaire-Question-Delete").onclick = function () {
            DeleteQuestion(i);
        };

        switch (kind) {

            case "Single": {

                let optionList = list[i].querySelectorAll(".QuestionNaire-Question-Radio-Options .QuestionNaire-Question-Radio-Option");

                for (let j = 0; j < optionList.length; ++j) {

                    let option = optionList[j].querySelector(".QuestionNaire-Question-Radio-Label .QuestionNaire-Question-RadioCont-Input");

                    option.name = "QuestionNaire-OptionCont-" + i;

                    option.placeholder = "单选项" + j;

                    optionList[j].querySelector(".QuestionNaire-Question-Radio-Label .QuestionNaire-Question-Delete-Option").onclick = function () {
                        DeleteOption(i, j);
                    }

                }

                list[i].querySelector(".QuestionNaire-Question-Radio-OptionInsert-Button").onclick = function () {
                    InsertOption_Radio(i)
                };

                break;

            }
            case "Multiple": {

                let optionList = list[i].querySelectorAll(".QuestionNaire-Question-Check-Options .QuestionNaire-Question-Check-Option");

                for (let j = 0; j < optionList.length; ++j) {

                    let option = optionList[j].querySelector(".QuestionNaire-Question-Check-Label .QuestionNaire-Question-CheckCont-Input");

                    option.name = "QuestionNaire-OptionCont-" + i;

                    option.placeholder = "多选项" + j;

                    optionList[j].querySelector(".QuestionNaire-Question-Check-Label .QuestionNaire-Question-Delete-Option").onclick = function () {
                        DeleteOption(i, j);
                    }

                }

                list[i].querySelector(".QuestionNaire-Question-Multiple-OptionInsert-Button").onclick = function () {
                    InsertOption_Multiple(i)
                };

                break;

            }

        }

    }

    $("#QuestionNum").attr("value", count);

}