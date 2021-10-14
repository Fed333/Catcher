<#macro login>
<form action="/login" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Login:</label>
        <div class="col-sm-5">
            <input type="text" name="username" class="form-control" placeholder="login"/>
        </div>

    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class ="col-sm-5">
            <input type="password" name="password" class="form-control" placeholder="password" />
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Увійти</button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Вихід</button>
</form>
</#macro>