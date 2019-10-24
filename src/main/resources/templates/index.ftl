<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "Hello page">
    <@c.auth/><br>
    <#if user??>
        <#if user.roles?seq_contains("ADMIN")?string("yes", "no") == "yes">
            <center><a href="/new_game">Name game</a></center>
            <center><a href="/company_page">Resume</a></center>

            <#if gameConfig??>
                <#list gameConfig as val>
                    <#if val["name"] == "adminPanel.trainMuseum" && val["valueInt"] == 1>
                        <center><a href="/train_museum">Train museum</a></center>
                    </#if>
                    <#if val["name"] == "adminPanel.detail_museum" && val["valueInt"] == 1>
                        <center><a href="/detail_museum">Details museum</a></center>
                    </#if>
                    <#if val["name"] == "adminPanel.cargo" && val["valueInt"] == 1>
                        <center><a href="/cargo">Cargo</a></center>
                    </#if>
                    <#if val["name"] == "adminPanel.cities" && val["valueInt"] == 1>
                        <center><a href="/city">Cities</a></center>
                    </#if>
                    <#if val["name"] == "adminPanel.routers" && val["valueInt"] == 1>
                        <center><a href="/route">Routes</a></center>
                    </#if>
                    <#if val["name"] == "adminPanel.orders" && val["valueInt"] == 1>
                        <center><a href="/order">Orders</a></center>
                    </#if>
                    <#if val["name"] == "adminPanel.users" && val["valueInt"] == 1>
                        <center><a href="/users">Users</a></center>
                    </#if>
                    <#if val["name"] == "adminPanel.gameConfig" && val["valueInt"] == 1>
                        <center><a href="/gameConfig">GameConfig</a></center>
                    </#if>
                </#list>
            <#else> gameConfig - empty
            </#if>
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