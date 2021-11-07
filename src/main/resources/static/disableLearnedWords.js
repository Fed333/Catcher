//дістаємо масив у вигляді рядка
let data = document.getElementById('data_id').getAttribute('value');
//парсимо рядок в масив
let arr = JSON.parse(data);
for(let i = 0; i < arr.length; i++){
//вимикаємо посилання на слова котрі користувач вже собі додав
    let link = document.getElementById("link"+arr[i]);
    //перевірка на присутність в моделі
    if (typeof(link) != 'undefined' && link != null){
        link.setAttribute("class", "disabled-link");
    }

}

