<#import "parts/common.ftl" as c>

<@c.page "Test">

<div class="col d-flex justify-content-center">
    <h2>Тест</h2>
</div>

<div class="row mb-2">
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
        <div class="col-3 rounded-rec">
            ${r.rightAnswer}
        </div>
        <div class="col-1 rounded-rec ms-1 d-flex justify-content-center">
            ${r.similarity}%
        </div>
        <div class="col-1 rounded-rec ms-1 d-flex justify-content-center">
            ${r.points}
        </div>
    </div>
    <#assign i = (i + 1)>
</#list>
<hr>
</#if>


</@c.page>