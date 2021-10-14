<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Catcher</a>

    <!--        кнопка згортання розгортання при відображенні меню на малих екранах-->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/main">Головна </a>
            </li>
            <#if exists>
            <li class="nav-item active">
                <a class="nav-link" href="/profile">Профіль</a>
            </li>
            </#if>
            <li class="nav-item active">
                <a class="nav-link" href="/dictionary">Словник</a>
            </li>
        </ul>
        <div class="navbar-text mr-4"><a class="navbar-text" href="/profile" style="color: white;">${name}</a></div>

        <#if exists>
            <div class="mt-2"><@l.logout /></div>
        <#else>
            <div class="mt-2">
                <a class="btn btn-primary" href="/login">Вхід</a>
                <a class="btn btn-primary" href="/registration">Реєстрація</a>
            </div>
        </#if>


    </div>

</nav>
