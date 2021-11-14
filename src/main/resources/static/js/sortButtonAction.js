let select = sortCriterionSelect
let value = select.options[select.selectedIndex].value

//показувати true - так, false - ні
let show = select.options[select.selectedIndex].value !== "None"

if (show){
    sortSettings.setAttribute("class", "show")  //контент буде видимий одразу
}
else{
    sortSettings.setAttribute("class", "collapse") //контент буде спершу прихований
}

//визначить наступну дію (show=true згортати чи show=false розгортати)
collapseSortButton.setAttribute("aria-expanded", show)

function switchCollapseSort(){
    //натиснення на кнопку при видимій панелі призведе до її згортання
    if (show == true){
        noneOption.setAttribute("selected", "selected") //так відмічаємо що панель сортування не відображатиметься далі
    }

}
collapseSortButton.onclick = switchCollapseSort