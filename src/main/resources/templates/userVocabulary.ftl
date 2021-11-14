<#import "parts/common.ftl" as c>
<#import "parts/forms.ftl" as f>
<#include "parts/references.ftl">
<@c.page "My Vocabulary">

<div class="col d-flex justify-content-center">
    <h2>Мій словник</h2>
</div>

<div class="form-group mt-5">
    <form action="${refUserVoc}" method="get" id="editViewForm">
        <input type="hidden" name="view" id="viewTypeId" value="${view!"table"}">
        <@f.search/>
    </form>
</div>

<h4>Подання</h4>
<ul class="nav nav-tabs" style="background-color: #f9f9f9;">
    <li class="nav-item">
        <a class="nav-link" aria-current="page" href="${refUserVoc}&view=cards" id="cardsLinkId">Cards</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" aria-current="page" href="${refUserVoc}&view=table" id="tableLinkId">Table</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" aria-current="page" href="${refUserVoc}&view=statistic" id="statisticLinkId">Statistic</a>
    </li>
<!--    вибере активне посилання-->
    <script src="/static/js/setView.js"></script>
</ul>

<#if view??>
    <#if view = "table">
    <table id="vocabularyTable" class="table table-bordered table-striped">
        <thead class="table-light">
        <tr>
            <th rowspan="2" class="align-centring">Слово</th>
            <th rowspan="2" class="align-centring">Переклад</th>
            <th rowspan="2" class="align-centring" style="vertical-align: middle;">Рівень</th>
            <th colspan="2" class="align-centring">Спроби</th>
            <th rowspan="2" class="align-centring">Вивчено</th>
        </tr>
        <tr>
            <th class="align-centring">Успішні</th>
            <th class="align-centring">Всі</th>
        </tr>
        </thead>
            <tbody class="table-light">
            <#list vocabulary as progress>
            <tr>
                <td>${progress.word.word}</td>
                <td>${progress.word.translation}</td>
                <td>${progress.word.level}</td>
                <td>${progress.guessingCount}</td>
                <td>${progress.revisionCount}</td>
                <td><#if progress.studied>Так<#else>Ні</#if></td>
            </tr>
            </#list>
            </tbody>
    </table>
    <#elseif view = "cards">

        <script src="static/js/arrangeCardsVocabulary.js"></script>
        <div class="row" data-masonry='{"percentPosition": true }'>

            <#list vocabulary as pw>
                <div class="col-4">
                    <div class="card mt-3">
                            <div id="cardContent${pw.word.id}Id">
                                <#if pw.word.imgName??>
                                <div id="divImage${pw.word.id}Id">
                                    <img class="card-img-top" src="/word_img/${pw.word.imgName}" alt="word image" id="image${pw.word.id}Id">
                                </div>
                                </#if>

                                <div id="divContent${pw.word.id}Id">
                                    <div class="card-body">
                                        <h5 class="card-title">${pw.word.word}</h5>
                                        <h6>${pw.word.translation}</h6>
                                        <p class="card-text">Рівень: ${pw.word.level}</p>
                                        <p class="card-text">Дата вивчення: ${pw.learnedDate?date}</p>
                                        <p class="card-text"><small class="text-muted">Останнє повторення: ${pw.lastRevisionDate!pw.learnedDate?date}</small></p>
                                    </div>
                                </div>
                            </div>
                    </div>
                <#if pw.word.imgName??>
                    <script>arrangeCard(${pw.word.id});</script>
                </#if>

            </div>
            </#list>
        </div>
    </#if>
</#if>

</@c.page>