<#import "parts/common.ftl" as c>

<@c.page "Test">

<div class="col d-flex justify-content-center">
    <h2>Тест</h2>
</div>

<div class="row mb-2">
    <h5>Завдання 1. Перекладіть слова з української на англійську.</h5>
</div>

<form method="post" action="/test" id="answersFormId">
    <#if task1??>
    <#assign i = 1>
        <#list task1 as w>
        <div class="row mt-2">
            <div class="col-3">
                <label class="col-form-label">${i}. ${w.translation}</label>
            </div>
            <input type="hidden" name="q${i}" value="${w.word}">
            <div class="col-3">
                <input type="text" class="form-control" name="a${i}">
            </div>
        </div>
        <#assign i = (i + 1)>
        </#list>
        <hr>
        <button class="btn btn-outline-success mt-4" type="submit">Надіслати</button>
</#if>
</form>

</@c.page>