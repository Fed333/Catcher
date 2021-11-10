<#import "parts/common.ftl" as c>

<@c.page "Dictionary">

<div class="form-group">
    <form action="/dictionary" method="get" id="editViewForm">
        <div class="form-group row">
            <div class="col-2">
                <select class="form-control" name="languageFilter" id="languageSelect" onchange="document.getElementById('editViewForm').submit()">
                    <option value="English" id="EnglishOption" selected>English</option>
                    <option value="Ukrainian" id="UkrainianOption" selected>Ukrainian</option>
                    <#if languageFilter == "English">
<!--                   removing of redundant attribute from DOM using JS-->
                    <script>UkrainianOption.removeAttribute('selected');</script>
                    <#elseif languageFilter == "Ukrainian">
                    <script> EnglishOption.removeAttribute('selected');</script>
                    </#if>
                </select>
            </div>
            <div class="col-3">
                <input type="text" name="wordFilter" class="form-control" placeholder="Введіть слово" value="${wordFilter!""}">
            </div>
            <div class="col-1">
                <button type="submit" class="btn btn-outline-success">Пошук</button>
            </div>

        </div>
        <div class="form-group row">
            <div class="form-check">
                <div class="col-4">
                    <input type="checkbox" class="form-check-input" name="a1" id="checkBoxA1" checked>
                    <label class="form-check-label" for="checkBoxA1">A1</label>
                </div>
            </div>
            <div class="form-check">
                <div class="col-4">
                    <input type="checkbox" class="form-check-input" name="a2" id="checkBoxA2" checked>
                    <label class="form-check-label" for="checkBoxA2">A2</label>
                </div>
            </div>
            <div class="form-check">
                <div class="col-4">
                    <input type="checkbox" class="form-check-input" name="b1" id="checkBoxB1" checked>
                    <label class="form-check-label" for="checkBoxB1">B1</label>
                </div>
            </div>
            <div class="form-check">
                <div class="col-4">
                    <input type="checkbox" class="form-check-input" name="b2" id="checkBoxB2" checked>
                    <label class="form-check-label" for="checkBoxB2">B2</label>
                </div>
            </div>
            <#if a1 != "on">
            <script> checkBoxA1.removeAttribute('checked');</script>
            </#if>

            <#if a2 != "on">
            <script> checkBoxA2.removeAttribute('checked');</script>
            </#if>

            <#if b1 != "on">
            <script> checkBoxB1.removeAttribute('checked');</script>
            </#if>

            <#if b2 != "on">
            <script> checkBoxB2.removeAttribute('checked');</script>
            </#if>
        </div>

        <div class="form-group row">
            <div class="col-2">
            <button class="btn btn-primary" disabled>
                Сортування
            </button>
            </div>
        </div>

            <div class="form-group row" >

                <label class="col-sm-1 col-form-label">Критерій</label>
                <div class="col-4">
                    <select class="form-control" name="sortCriterion" id="sortCriterionSelect" onchange="document.getElementById('editViewForm').submit()">
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
                </div>
                <label class=" col-sm-1 col-form-label">Порядок</label>
                <div class="col-4">
                    <select class="form-control" name="sortOrder" id="sortOrderSelect" onchange="document.getElementById('editViewForm').submit()">
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
    </form>
</div>

<table class="table">
    <thead class="table-dark">
    <tr><th>Слово</th><th>Переклад</th><th>Рівень</th></tr>
    </thead>
    <tbody class="table-light">

    <#list words as word>
    <tr>
        <#if word??>
        <#if languageFilter == "English">
            <td>${word.word}</td>
            <td>${word.translation}</td>
        <#else>
            <td>${word.translation}</td>
            <td>${word.word}</td>
        </#if>
        <td>${word.level}</td></tr>
        </#if>
    </#list>
    </tbody>

</table>
</@c.page>