<#import "parts/common.ftl" as c>
<#include "parts/references.ftl">

<@c.page "UserSearch">

<div class="row">
    <div class="col d-flex justify-content-center">
        <h2>Пошук користувачів</h2>
    </div>
</div>

<script src="/static/js/displayCollapse.js"></script>

<div class="form-group mt-3">

    <form action="${refUserSearch}" method="get" id="formUserSearchId">
        <input type="hidden" name="showCollapse" value="${showCollapse!"false"}" id="showCollapseId">
        <div class="form-group row">
            <div class="col-3">
                <input type="text" name="login" class="form-control" placeholder="Login" value="${login!""}">
            </div>
            <div class="col-2">
                <button type="submit" onclick="printLogLevels" class="btn btn-outline-success">Пошук</button>
            </div>
        </div>
        <div class="form-group row mb-3">
            <div class="col-2">
                <button class="btn btn-primary mt-3"  name="collapseUserFilterButton" value="on" type="button" data-bs-toggle="collapse" data-bs-target="#userFilter" aria-controls="userFilter" id="collapseUserFilterButton" onclick="switchCollapse('showCollapseId')">
                    Фільтр
                </button>
            </div>
        </div>
        <div id="userFilter">
            <div class="form-group row mt-2">
                <label class="col-1 col-form-label">Ім'я</label>
                <div class="col-2">
                    <input type="text" name="name" class="form-control" placeholder="Ім'я" value="${name!""}">
                </div>

                <label class="col-1 col-form-label">Телефон</label>
                <div class="col-2">
                    <input type="text" name="phone" class="form-control" placeholder="Телефон" maxlength="13" value="${phone!""}">
                </div>

                <label class="col-1 col-form-label">Пошта</label>
                <div class="col-3">
                    <input type="email" name="email" class="form-control" placeholder="adress@email.com" value="${email!""}">
                </div>
            </div>

            <div class="form-group row mt-4">
                <div class="col-1">
                    <label class="form-check-inline">Рівень</label>
                </div>
                <div class="col">
                    <div class="form-check form-check-inline">
                        <input type="checkbox" value="a1" class="form-check-input" name="levels" id="checkBoxA1">
                        <label class="form-check-label" for="checkBoxA1">A1</label>
                    </div>

                    <div class="form-check form-check-inline">
                        <input type="checkbox" value="a2" class="form-check-input" name="levels" id="checkBoxA2">
                        <label class="form-check-label" for="checkBoxA2">A2</label>
                    </div>

                    <div class="form-check form-check-inline">
                        <input type="checkbox" value="b1" class="form-check-input" name="levels" id="checkBoxB1">
                        <label class="form-check-label" for="checkBoxB1">B1</label>
                    </div>

                    <div class="form-check form-check-inline">
                        <input type="checkbox" value="b2" class="form-check-input" name="levels" id="checkBoxB2">
                        <label class="form-check-label" for="checkBoxB2">B2</label>
                    </div>

                </div>
            </div>

        </div>
    </form>
    <script>displayCollapse("collapseUserFilterButton", "userFilter", "showCollapseId");</script>

</div>

<div class="row">
    <div class="col d-flex justify-content-center">
        <h3>Знайдені користувачі</h3>
    </div>
</div>

<#if users??>

<table id="foundUsersTable" class="table table-bordered table-striped mt-3">
    <thead class="table-dark">
        <tr>
            <th class="align-centring">Логін</th>
            <th class="align-centring">Ім'я</th>
            <th class="align-centring">Телефон</th>
            <th class="align-centring">Пошта</th>
            <th class="align-centring">Рівень</th>
        </tr>
    </thead>
    <tbody class="table-light">
        <#list users as user>
            <tr>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td class="d-flex justify-content-center">${user.level}</td>
            </tr>
        </#list>
    </tbody>
</table>

<#else>

<div class="row">
    <h5>Користувачів не знайдено.</h5>
</div>

</#if>


</@c.page>