<#import "parts/common.ftl" as c>

<@c.page "Dictionary">
<table class="table">
    <thead class="table-dark">
    <tr><th>Слово</th><th>Переклад</th><th>Рівень</th></tr>
    </thead>
    <tbody class="table-light">
    <#list words as word>
    <tr><td>${word.word}</td><td>${word.translation}</td><td>${word.level}</td></tr>
    </#list>
    </tbody>

</table>
</@c.page>