QuestionNaireListPageHome = function () {

    document.getElementsByName("CurrentPageNum").item(0).value = 0;

}

QuestionNaireListPageEnd = function () {

    document.getElementsByName("CurrentPageNum").item(0).value = 16383;

}

QuestionNaireListPageUp = function () {

    let CurrentPageNum = parseInt(document.getElementsByName("CurrentPageNum").item(0).value);

    document.getElementsByName("CurrentPageNum").item(0).value = CurrentPageNum - 1;

}

QuestionNaireListPageDn = function () {

    let CurrentPageNum = parseInt(document.getElementsByName("CurrentPageNum").item(0).value);

    document.getElementsByName("CurrentPageNum").item(0).value = CurrentPageNum + 1;

}