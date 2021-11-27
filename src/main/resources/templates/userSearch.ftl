<#import "parts/common.ftl" as c>
<#include "parts/references.ftl">

<@c.page "UserSearch">

<div class="row">
    <div class="col d-flex justify-content-center">
        <h2>Пошук користувачів</h2>
    </div>
</div>

<div class="form-group mt-3">

    <form action="${refUserSearch}" method="get" >
        <div class="form-group row">
            <div class="col-4">
                <input type="text" name="login" class="form-control" placeholder="Login">
            </div>
            <div class="col-2">
                <button type="submit" class="btn btn-outline-success">Пошук</button>
            </div>
        </div>
    </form>

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