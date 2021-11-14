<#import "parts/common.ftl" as c>

<@c.page "Registration">

<div>
    <form action="/registration" method="post">

        <div class="form-group row mb-3">
            <label class="col-sm-2 col-form-label">Login: </label>
            <div class="col-sm-4">
                <input type="text" name="login" class="form-control" placeholder="login"/>
            </div>
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class ="col-sm-4">
                <input type="password" name="password" class="form-control" placeholder="password" />
            </div>
        </div>

        <div class="form-group row mb-3">
            <label class="col-sm-2 col-form-label">Name:</label>
            <div class ="col-sm-4">
                <input type="text" name="name" class="form-control" placeholder="name" />
            </div>
            <label class="col-sm-2 col-form-label">Phone:</label>
            <div class ="col-sm-4">
                <input type="text" name="phone" class="form-control" placeholder="phone" />
            </div>

        </div>
        <div class="form-group row mb-3">
            <label class="col-sm-2 col-form-label">Birth Date:</label>
            <div class="col-sm-4">
                <input type="date" name="birthday" class="form-control">
            </div>
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-4">
                <input type="text" name="email" class="form-control" placeholder="e_mail">
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary mt-3">Зареєструватися</button>
    </form>
</div>
</@c.page>