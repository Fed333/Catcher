<#import "parts/common.ftl" as c>


<@c.page "Profile">

<div class="row row-centered" >
    <div class="col d-flex justify-content-center">
        <h1>Профіль</h1>
    </div>
</div>
<div class="row mt-3">
    <div class="col-3">
        <#if user.avatarName??>
            <img src="/img/${user.avatarName}" class="img-thumbnail" alt="avatar">
        <#else>
            <img src="/basic/avatar.png" class="img-thumbnail" alt="avatar">
        </#if>
    </div>
    <div class="col-4">
        <ul class="list-group">
            <li class="list-group-item">
                <label>Логін: ${user.login}</label>
            </li>
            <li class="list-group-item">
                <label>Email: ${user.email!"-"}</label>
            </li>
            <li class="list-group-item">
                <#if user.birthday??>
                <label>Дата народження: ${user.birthday?date}</label>
                <#else>
                <label>Дата народження: -</label>
                </#if>
            </li>
        </ul>

    </div>
    <div class="col-4">
        <ul class="list-group">
            <li class="list-group-item">
                <label>Ім'я: ${user.name!"-"}</label>
            </li>
            <li class="list-group-item">
                <label>Телефон: ${user.phone!"-"}</label>
            </li>
        </ul>
    </div>

</div>

<div class="row">
    <div class="col-2 mt-2">
        <a class="btn btn-primary mt-3" data-toggle="collapse" href="#collapseChange" role="button" aria-expanded="false" aria-controls="collapseExample">
            Change
        </a>
    </div>

</div>


<div class="collapse" id="collapseChange">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="avatarFile">
                    <label class="custom-file-label" for="avatarFile">Виберіть фото</label>
                </div>
            </div>

            <div class="form-group row">

                <label class="col-sm-1 col-form-label">Логін:</label>
                <div class="col-3 ml-0">
                    <input type="text" name="login" class="form-control" placeholder="New login" value="${user.login}">
                </div>

                <label class="col-sm-1 col-form-label">Ім'я</label>
                <div class="col-3 ml-0">
                    <input type="text" name="name" class="form-control" placeholder="New name" value="${user.name!""}">
                </div>

                <label class="col-sm-1 col-form-label">Email:</label>
                <div class="col-3 ml-0">
                    <input type="text" name="email" class="form-control" placeholder="New email" value="${user.email!""}">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label">Телефон:</label>
                <div class="col-3 ml-0">
                    <input type="text" name="phone" class="form-control" placeholder="New phone" value="${user.phone!""}">
                </div>

                <label class="col-sm-2 col-form-label d-flex justify-content-center">Дата народження:</label>
                <div class="col-3 ml-0">
                    <input type="date" name="birthday" class="form-control" placeholder="New birthday" value="${user.getBirthdayString()!""}">
                </div>
            </div>
            <button type="submit" class="btn btn-outline-success">Apply</button>
        </form>

    </div>
</div>

<div class="row-mt-4">
    <div class="col-2 mt-4">
        <h4>Ролі: </h4>
    </div>

</div>

<div class="row mt-2">

    <div class="col-md-2 offset-md-0 ">
        <ul class="list-group list-group-flush">
            <#list user.roles as role>
            <li class="list-group-item">
                ${role}
            </li>
            </#list>
        </ul>
    </div>
</div>
</@c.page>