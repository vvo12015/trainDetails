<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/company.ftl" as comp>

<@c.page "Company page">
    <@c.auth/>
    <h3>Company information.</h3>
    <@comp.company_info "${company.name}", "${company.cash}", "${company.trains?size}"/>
</@c.page>