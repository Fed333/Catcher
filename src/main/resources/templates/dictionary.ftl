<#import "parts/common.ftl" as c>
<#import "parts/forms.ftl" as f>
<#include "parts/security.ftl">
<@c.page "Dictionary">

<div class="col d-flex justify-content-center">
    <h2>Словник</h2>
</div>

<div class="form-group">
    <#if isTeacher>
    <div class="form-group row">
        <div class="col-2">
            <button class="btn btn-primary" name="collapseAddWordButton" type="button" data-bs-toggle="collapse" data-bs-target="#addWordForm" aria-controls="addWordForm" aria-expanded="false" id="collapseAddWordButton">
                Додати слово
            </button>
        </div>
    </div>
    <form action="/dictionary" method="post" enctype="multipart/form-data" id="addForm">
        <div id="addWordForm" class="collapse">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <@f.addWord/>
            <label class="col-form label">${message!""}</label>
            <button type="submit" class="btn btn-outline-success mt-3">Додати</button>
        </div>
    </form>

    </#if>
    <form action="/dictionary" method="get" id="editViewForm">
        <input type="hidden" name="data" id="data_id" value="${data_id!"[]"}">
        <input type="hidden" name="displayAddForm" id="showAddFormFlag" value="${showAddForm!"false"}">
        <script src="/static/js/displayAddWordCollapse.js"></script>

<!--        вставляємо макрос, форму пошуку-->
        <@f.search/>

        <div class="form-group row mb-3">
            <div class="col-2">
                <button class="btn btn-primary mt-3"  name="collapseSortSettingsButton" value="on" type="button" data-bs-toggle="collapse" data-bs-target="#sortSettings" aria-controls="sortSettings" id="collapseSortButton">
                    Сортування
                </button>
            </div>
        </div>
        <div id="sortSettings">
            <div class="form-group row mb-2" >

                <label class="col-sm-1 col-form-label">Критерій</label>
                <div class="col-4">
                    <select class="form-select" name="sortCriterion" id="sortCriterionSelect">
                        <option value="None" id="noneOption">Відсутній</option>
                        <option value="Word" id="wordOption">Слово</option>
                        <option value="Translation" id="translationOption">Переклад</option>
                        <option value="Level" id="levelOption">Рівень</option>
                    </select>

                    <#if sortCriterion=="Word">
                        <script>wordOption.setAttribute('selected', 'selected')</script>
                    <#elseif sortCriterion=="Translation">
                        <script>translationOption.setAttribute('selected', 'selected')</script>
                    <#elseif sortCriterion=="Level">
                        <script>levelOption.setAttribute('selected', 'selected')</script>
                    </#if>
                <script src="/static/js/sortButtonAction.js"></script>
                <script src="/static/js/sortSelectAction.js"></script>
                </div>
                <label class=" col-sm-1 col-form-label">Порядок</label>
                <div class="col-4">
                    <select class="form-select" name="sortOrder" id="sortOrderSelect" onchange="document.getElementById('editViewForm').submit()">
                        <option value="asc" id="ascOption" >По зростанню</option>
                        <option value="desc" id="descOption">По спаданню</option>
                    </select>
                    <#if sortCriterion!="None">
                        <#if sortOrder="asc">
                            <script> ascOption.setAttribute('selected', 'selected');</script>
                        <#elseif sortOrder="desc">
                            <script>descOption.setAttribute('selected', 'selected');</script>
                        </#if>
                    <#else>
                        <script>sortOrderSelect.setAttribute('disabled', 'disabled')</script>
                    </#if>
                </div>
            </div>
        </div>
    </form>
</div>

<table class="table table-striped">
    <thead class="table-dark">
    <tr>
        <#if isTeacher>
        <th>ID</th>
        </#if>
        <th>Слово</th>
        <th>Переклад</th>
        <th>Рівень</th>
        <th></th>
    </tr>
    </thead>
    <tbody class="table-light">

    <#list words as word>
    <tr>
        <#if word??>
        <#if isTeacher>
        <td><a href="/dictionary/${word.id}">${word.id}</a></td>
        </#if>
        <#if languageFilter == "English">
            <td>${word.word}</td>
            <td>${word.translation}</td>
        <#else>
            <td>${word.translation}</td>
            <td>${word.word}</td>
        </#if>
        <td>${word.level}</td>
        <td>
            <form method="post" action="/dictionary/add_to_user_vocabulary" id="form${word.id}">
                <input type="hidden" name="wordId" id="wordId" value="${word.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <a id="link${word.id}" href="#" onclick="document.getElementById('form${word.id}').submit()">Додати</a>
            </form>

        </td>
    </tr>
        </#if>
    </#list>
    </tbody>

</table>
<script src="/static/js/disableLearnedWords.js"></script>
</@c.page>