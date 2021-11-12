let wordId;

function arrangeCard(id){
    let image = document.getElementById("image"+id+"Id");
    let cardContent = document.getElementById("cardContent"+id+"Id");
    let divImage = document.getElementById("divImage"+id+"Id");
    let divContent = document.getElementById("divContent"+id+"Id");

//    natural - для розмірів зображення в файлі
//    client - для розмірів зображення в браузері
    let width = image.naturalWidth;
    let height = image.naturalHeight;
    console.log("id:" + id + ", width:"+width + ", height:" + height);
    if (height >= width){
        //horizontal
        cardContent.setAttribute("class", "row g-0");
        divImage.setAttribute("class", "col-md-4");
        image.setAttribute("class", "img-fluid rounded-start");
        divContent.setAttribute("class", "col-md-8");
    }
    else{
            cardContent.setAttribute("class", "");
            divImage.setAttribute("class", "");
            image.setAttribute("class", "card-img-top");
            divContent.setAttribute("class", "");

    }
    console.log("imageId:" + id + ", width=" + width + ", height=" + height);
}
