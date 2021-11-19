<#import "login.ftl" as l>
<#include "references.ftl">
<#macro offcanvas>

<div class="navbar navbar-light">
    <button class="navbar-toggler bg-light" type="button" data-bs-toggle="offcanvas" data-bs-target="#sidebar" aria-controls="sidebar">
        <span class="navbar-toggler-icon"></span>
    </button>
</div>

<!--offcanvas-->
<div class="offcanvas offcanvas offcanvas-end" tabindex="-1" id="sidebar"
     aria-labelledby="sidebar-label">
    <div class="offcanvas-header">
        <h5>Меню</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
            <li class="nav-item">
                <a class="nav-link active text-black" aria-current="page" href="${refMain}">Головна</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-black" aria-current="page" href="#">Повідомлення</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-black" id="offcanvasWordsDropdown" aria-current="page" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Слова</a>
                <ul class="dropdown-menu" aria-labelledby="offcanvasWordsDropdown">
                    <li>
                       <a class="dropdown-item" href="${refDictionary}">Словник</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${refUserVoc}">Мої слова</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link text-black" aria-current="page" href="${refTest}" id="testsId">Завдання</a>
            </li>
            <li><hr></li>
            <li class="nav-item">
                <@l.logout/>
            </li>

        </ul>
    </div>
</div>

</#macro>