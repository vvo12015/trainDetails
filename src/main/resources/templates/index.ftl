<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Hello page">
    <@c.auth/><br>
    <a href="/new_game">Name game</a>
    <center><a href="/company_page">Resume</a></center>
    <center><a href="/train_museum">Train museum</a></center>
    <center><a href="/detailsMuseum">Details museum</a></center>
    <center><a href="/cargo">Cargo</a></center>
    <center><a href="/city">Cities</a></center>
    <center><a href="/route">Routes</a></center>
    <center><a href="/order">Orders</a></center>
</@c.page>