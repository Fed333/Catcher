
function handleChangeActions(){
    let select = document.getElementById("sortCriterionSelect")
    let criterion = select.options[select.selectedIndex].value

    if (criterion !== "None"){

        document.getElementById("editViewForm").submit()
    }
}

//призначаємо обробнику відповідну функцію
sortCriterionSelect.onchange = handleChangeActions