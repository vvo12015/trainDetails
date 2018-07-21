<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/company.ftl" as comp>

<@c.page "Company page">
    <h3>Company information.</h3>
    <@comp.company_info "${company.name}", "${company.cash}", "${company.trains?size}"/>

    <div><a href="/my_trains">My trains</a><div>
    <div><a href="/orders">Orders</a><div>
    <div><a href="/train_market">Train market</a><div>
    <@l.logout />
</@c.page>