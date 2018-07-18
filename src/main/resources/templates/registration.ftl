<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Registration page">
    <h2>Add new user</h2>
    <@l.login path="/registration" />
</@c.page>