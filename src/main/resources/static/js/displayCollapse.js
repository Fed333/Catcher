
function displayCollapse(collapseButtonId, collapseContentId, showCollapseId){


    let collapseButton = document.getElementById(collapseButtonId);
    let collapseContent = document.getElementById(collapseContentId);
    let show = document.getElementById(showCollapseId).value === "true";

    if (show){
        collapseContent.setAttribute("class", "show");
    }
    else {
        collapseContent.setAttribute("class", "collapse");
    }

    //визначить наступну дію (show=true згортати чи show=false розгортати)
    collapseButton.setAttribute("aria-expanded", show);
}

function switchCollapse(showCollapseId){

    let showCollapse = document.getElementById(showCollapseId);
    let show = showCollapse.value === "true";
    showCollapse.setAttribute("value", (!show).toString());

    show = showCollapse.getAttribute("value");
}