<#macro login path>
    <form action="${path}" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Sign In"/></div>
    </form>
</#macro>

<#macro login_form>
    <form action="/login" >
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Login"/></div>
    </form>
</#macro>

<#macro registration>
    <form action="/registration" >
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Registration"/></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Sign Out"/></div>
    </form>
</#macro>