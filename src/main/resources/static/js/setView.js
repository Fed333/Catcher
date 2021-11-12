let viewType = document.getElementById('viewTypeId').getAttribute('value');

if (viewType == "cards"){
    cardsLinkId.setAttribute("class", "nav-link active");
}
else if (viewType == "table"){
    tableLinkId.setAttribute("class", "nav-link active");
}
else if (viewType == "statistic"){
    statisticLinkId.setAttribute("class", "nav-link active");
}

