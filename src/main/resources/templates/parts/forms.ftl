<#macro search>
<div class="form-group row">
    <div class="col-2">
        <select class="form-select" name="languageFilter" id="languageSelect" onchange="document.getElementById('editViewForm').submit()">
            <option value="English" id="EnglishOption" selected>English</option>
            <option value="Ukrainian" id="UkrainianOption" selected>Ukrainian</option>
            <#if languageFilter == "English">
            <!--                   removing of redundant attribute from DOM using JS-->
            <script>UkrainianOption.removeAttribute('selected');</script>
            <#elseif languageFilter == "Ukrainian">
            <script>EnglishOption.removeAttribute('selected');</script>
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
<div class="form-group row mt-3">
    <div class="col">
        <div class="form-check form-check-inline">
            <input type="checkbox" class="form-check-input" name="a1" id="checkBoxA1" checked>
            <label class="form-check-label" for="checkBoxA1">A1</label>
        </div>

        <div class="form-check form-check-inline">
            <input type="checkbox" class="form-check-input" name="a2" id="checkBoxA2" checked>
            <label class="form-check-label" for="checkBoxA2">A2</label>
        </div>

        <div class="form-check form-check-inline">
            <input type="checkbox" class="form-check-input" name="b1" id="checkBoxB1" checked>
            <label class="form-check-label" for="checkBoxB1">B1</label>
        </div>

        <div class="form-check form-check-inline">
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
</#macro>

<#macro addWord>
<div class="form-group row mt-3 mb-3">
    <label class="col-1 col-form-label">Слово</label>
    <div class="col-3">
        <input type="text" name="word" class="form-control" placeholder="Введіть слово">
    </div>
    <label class="col-1 col-form-label">Переклад</label>
    <div class="col-3">
        <input type="text" name="translation" class="form-control" placeholder="Введіть переклад">
    </div>
    <label class="col-1 col-form-label">Рівень</label>
    <div class="col-2">
        <select class="form-select" name="level" id="wordLevelSelect">
            <option value="A1" id="levelA1option">A1</option>
            <option value="A2" id="levelA2option">A2</option>
            <option value="B1" id="levelB1option">B1</option>
            <option value="B2" id="levelB2option">B2</option>
        </select>
    </div>

</div>

<label class="form-label" for="wordImageFile">Зображення</label>
<input class="form-control" type="file" name="wordImage" id="wordImageFile">

</#macro>