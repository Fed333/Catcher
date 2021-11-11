<#include "security.ftl">
<#include "references.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid d-flex flex-grow-1 mt-2 mb-2">
        <a class="navbar-brand" href="/">Catcher</a>
        <!--        кнопка згортання розгортання при відображенні меню на малих екранах-->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${refMain}">Головна </a>
                </li>
                <#if exists>
                <li class="nav-item active">
                    <a class="nav-link" href="${refProfile}">Профіль</a>
                </li>
            </#if>
            <li class="nav-item active">
                <a class="nav-link" href="${refDictionary}">Словник</a>
            </li>
            </ul>

            <a class="navbar-text me-3" href="${refProfile}" style="color: white;">${name}</a>

            <#if exists>
            <div class="me-2"><@l.logout /></div>
            <#else>
            <div class="me-2">
                <a class="btn btn-primary me-2" href="${refLogin}">Вхід</a>
                <a class="btn btn-primary" href="${refRegistration}">Реєстрація</a>
            </div>

        </#if>
        </div>
    </div>


</nav>