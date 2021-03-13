<#import "parts/common.ftl" as c>
<#import "parts/company.ftl" as comp>
<#import "parts/login.ftl" as l>
<#import "parts/admin.ftl" as adm>
<#import "parts/train.ftl" as t_mu>

<@c.page "${header_page}">
     <@c.auth/>
    <#if company??>
        <@comp.company_info "${company.name}", "${company.cash}", "${company.trains?size}"/>
    </#if>
    <@adm.table_out listValue, fields, "Update", "button_remove_on"/>
    <@adm.table_out detailMuseumList, detailMuseumFields, "Update", "button_remove_on"/>
</@c.page>
