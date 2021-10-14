<#import "parts/common.ftl" as c>

<@c.page "Catcher">
<div>
    Hello user, it's a simple learning web application which will help you catch more English words easier!
    <div class="form-row">
        <div class="form-group col-md-5">
            <form method="get" action="main" class="form-inline">
                <input type="text" name="filter" value = "${filter?ifExists}" placeholder="Search">
                <button type="submit"class="btn btn-primary ml-2">Знайти</button>
            </form>
        </div>
    </div>
    <ul>
    <#list users as user>
    <li>
        <span>Login: ${user.login}</span>
        <#if user.name??>
        <span>Name: ${user.name}</span>
        </#if>
    </li>
    </#list>
    </ul>
</div>
</@c.page>