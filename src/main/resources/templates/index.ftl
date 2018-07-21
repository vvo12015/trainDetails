<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Hello page">
    <center><div><#if user??>Hello ${user}</#if></div></center>
    <center><a href="/login">Login</a></center>
    <center><a href="/registration">Registration</a></center>
    <center><a href="/company_page">Resume</a></center>
    <center><a href="/new_game">Name game</a></center>
    <center><a href="/train_museum">Train museum</a></center>
    <center><@l.logout /></center>
</@c.page>