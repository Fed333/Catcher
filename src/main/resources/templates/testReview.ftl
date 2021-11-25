<#import "parts/common.ftl" as c>
<#include "parts/references.ftl">

<@c.page "Test">

<div class="col d-flex justify-content-center">
    <h2>Результати Тесту</h2>
</div>

<div class="row mb-2 mt-2">
    <h5>Завдання 1. Перекладіть слова з української на англійську.</h5>
</div>

<div class="row bt-2">
    <div class="col-3">
        <label>Запитання</label>
    </div>
    <div class="col-3 d-flex justify-content-center">
        <label>Відповідь</label>
    </div>
    <div class="col-3 d-flex justify-content-center">
        <label>Вірна відповідь</label>
    </div>
    <div class="col-1 d-flex justify-content-center">
        <label>Схожість</label>
    </div>
    <div class="col-1 d-flex justify-content-center">
        <label>Очки</label>
    </div>
</div>

    <#if task1Review??>
    <#assign i = 1>
    <#list task1Review as r>
    <div class="row mt-2">
        <div class="col-3">
            <label class="col-form-label">${i}. ${r.question}</label>
        </div>
        <div class="col-3 rounded-rec">
            ${r.answer}
        </div>
        <div class="col-3 rounded-rec ms-2">
            ${r.rightAnswer}
        </div>
        <div class="col-1 rounded-rec ms-2 d-flex justify-content-center">
            ${r.similarity}%
        </div>
        <div class="col-1 rounded-rec ms-2 d-flex justify-content-center">
            ${r.points}
        </div>
    </div>
    <#assign i = (i + 1)>
</#list>
<hr>
<h4>
   Підсумки:
</h4>
<div class="row mt-3">
    <label class="col-2 col-form-label">Сума очок: </label>
    <div class="col-1 rounded-rec d-flex justify-content-center">
        ${totalScore}
    </div>
</div>


<div class="row mt-2">
    <label class="col-2 col-form-label">Точність: </label>
    <div class="col-1 rounded-rec d-flex justify-content-center">
        ${accuracy}%
    </div>
</div>

<div class="row mt-2">
    <div class="col d-flex justify-content-center">
        <a class="btn btn-outline-success" href="${refProfile}" style="width: 100px;">Ок</a>
    </div>
</div>

</#if>


</@c.page>