<#import "parts/common.ftl" as c>

<@c.page "Word Editor">
<div class="form-group">
<form action="/dictionary/edit" method="post" enctype="multipart/form-data" id="addForm">

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="hidden" name="wordId" value="${word.id}">

    <input type="file" name="wordImage" id="wordImageFile"  hidden>

    <div class="form-group row">
            <div class="col-3">
                <label for="wordImageFile" id="wordImageLabel">
                <#if word.imgName??>
                    <img id="imageWord" width="225" height="225" src="/word_img/${word.imgName}" class="img-thumbnail" alt="word picture">
                <#else>
                    <img id="imageWord" width="225" height="225" src="/basic/no_img.png" class="img-thumbnail" alt="word picture">
                </#if>
                </label>
            </div>
            <div class="col-2">
                <label class="col-1 col-form-label" id="wordLabel">Слово</label>
                <br>
                <label class="col-1 col-form-label" id="translationLabel">Переклад</label>
                <br>
                <label class="col-1 col-form-label" id="levelLabel">Рівень</label>
            </div>
            <div class="col-6">
                <input type="text" name="word" id="wordInput" class="form-control" value="${word.word!""}" placeholder="Слово">
                <input type="text" name="translation" id="translationInput" class="form-control" value="${word.translation!""}" placeholder="Переклад">

                <select class="form-control" title="Рівень" name="level" id="editWordLevelSelect">
                    <option value="A1" id="levelA1option">A1</option>
                    <option value="A2" id="levelA2option">A2</option>
                    <option value="B1" id="levelB1option">B1</option>
                    <option value="B2" id="levelB2option">B2</option>
                </select>
                <#if word.level == "A1">
                    <script>levelA1option.setAttribute('selected', 'selected');</script>
                <#elseif word.level == "A2">
                    <script>levelA2option.setAttribute('selected', 'selected');</script>
                <#elseif word.level == "B1">
                    <script>levelB1option.setAttribute('selected', 'selected');</script>
                <#elseif word.level == "B2">
                    <script>levelB2option.setAttribute('selected', 'selected');</script>
                </#if>
            </div>

        </div>
        <button type="submit" class="btn btn-outline-success mt-3">Змінити</button>

</form>
</div>

<script>
//Preview & Update an image before it is uploaded
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#imageWord').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#wordImageFile").change(function () {
        readURL(this);
    });
</script>

</@c.page>