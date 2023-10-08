
function setLevelsCheckForms(selectedLevelsId, prefix){
    let arrAsStr = document.getElementById(selectedLevelsId).value;
    //оця регулярка, найде і виріже [ на початку і ] в кінці
    arrAsStr = arrAsStr.replace(/^\[|\]$/g, '');

    if (arrAsStr.length != 0){
        let arr = arrAsStr.split(', ');

        for(let i = 0; i < arr.length; i++){
            let checkFormId = prefix+arr[i].toUpperCase();
            document.getElementById(checkFormId).setAttribute('checked', 'checked');
        }
    }
}