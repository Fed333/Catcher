<#import "parts/common.ftl" as c>

<@c.page "Test">

<div class="col d-flex justify-content-center">
    <h2>Тест</h2>
</div>

<div class="row mb-2">
    <h5>Завдання 1. Перекладіть слова з української на англійську.</h5>
</div>

<form method="post" action="/test" id="answersFormId">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <#if task1??>
    <#assign
        i = 1
        index = 0
    >
        <#list task1 as w>
        <div class="row mt-2">
            <div class="col-3">
                <label class="col-form-label">${i}. ${w.translation}</label>
            </div>
            <input type="hidden" name="task1[${index}].question" value="${w.translation}">
            <div class="col-3">
                <input type="text" class="form-control" name="task1[${index}].answer">
            </div>
        </div>
        <#assign
            i = (i + 1)
            index = (index + 1)
        >
        </#list>


        <hr>
        <button class="btn btn-outline-success mt-4">Надіслати</button>
</#if>
</form>
<!--<script src="/static/js/sendFormTest.js"></script>-->
<!--<button class="btn btn-outline-success mt-4" onclick="sendFormTest()">Test JSON</button>-->

</@c.page>