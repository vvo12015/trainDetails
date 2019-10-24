<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Hello page">
    <@c.auth/><br>
    <#if user??>
        <#if user.roles?seq_contains("ADMIN")?string("yes", "no") == "yes">
            <#if gameConfig??>
                <#list gameConfig as key, val>
                    ${key} - ${val["valueStr"]} <br>
                </#list>
            <#else> gameConfig - empty
            </#if>
            <center><a href="/new_game">Name game</a></center>
            <center><a href="/company_page">Resume</a></center>
            <center><a href="/train_museum">Train museum</a></center>
            <center><a href="/detail_museum">Details museum</a></center>
            <#if gameConfig["adminPanel.cargo"]["valueInt"]==1><center><a href="/cargo">Cargo</a></center></#if>
            <center><a href="/city">Cities</a></center>
            <center><a href="/route">Routes</a></center>
            <center><a href="/order">Orders</a></center>
            <center><a href="/users">Users</a></center>
            <center><a href="/gameConfig">GameConfig</a></center>
        <#else>
            <center><a href="/new_game">Name game</a></center>
            <center><a href="/company_page">Resume</a></center>
            <center><a href="/train_museum">Train museum</a></center>
            <center><a href="/detail_museum">Details museum</a></center>
            <center><a href="/cargo">Cargo</a></center>
            <center><a href="/city">Cities</a></center>
            <center><a href="/route">Routes</a></center>
            <center><a href="/order">Orders</a></center>
            <center><a href="/users">Users</a></center>
            <center><a href="/gameConfig">GameConfig</a></center>
        </#if>
    </#if>
</@c.page>