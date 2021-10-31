<!--оголошення змінних в freemarker - директива <#assign>-->
<!--значення фрімаркеру передасть SpringSecurity-->
<#assign
<!--перевіримо чи існує в конексті необхідний об'єкт-->
exists = Session.SPRING_SECURITY_CONTEXT??
>
<!--якщо об'єкт визначений в контексті - ми можемо працювати із сесією користувача-->
<#if exists> <!--якщо сесія існує-->
<#assign
<!--    тут буде наш користувач з бд, описаний нами класом User-->
user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
name = user.getUsername()
isAdmin = user.isAdmin()
isTeacher = user.isTeacher()
>
<#else>
<#assign
name = "unknown"
isAdmin = false
isTeacher = false
>
</#if>