//Приклад відправки запроса JSON по натисненні на клавішу
function sendFormTest(){

    let token = $('input[name="_csrf"]').attr('value')

    console.log("_csrf: " + token);

    let formData = JSON.stringify($("#answersFormId").serializeArray());

    console.log(formData);

    $.ajax({
        url: "/test",
        data : {
            '_csrf'             : token,
            'task1[0].question' : 'остерігатися',
            'task1[0].answer'   : 'watch out',
            'task1[1].question' : 'прибережний',
            'task1[1].answer'   : 'coastal',
            'task2[0].question' : 'детально',
            'task2[0].answer'   : 'thorough',
            'task2[1].question' : 'гостра критика',
            'task2[1].answer'   : 'diatribe',

        },
        type : 'post',
        dataType : "json",
        success : function(data){
            alert("Список параметрів колекції успішно пройдений");
        }
    });
}

