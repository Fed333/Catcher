let showAddForm = showAddFormFlag.getAttribute("value") === "true"? true: false;


if (showAddForm){
    addWordForm.setAttribute("class", "show");
}
else{
    addWordForm.setAttribute("class", "collapse");
}

addWordForm.setAttribute("aria-expanded", showAddForm);

function switchCollapseAdd(){
    //натиснення на кнопку при видимій панелі призведе до її згортання
    showAddFormFlag.setAttribute("value", (!showAddForm).toString());
    showAddForm = !showAddForm;

}

collapseAddWordButton.onclick = switchCollapseAdd;