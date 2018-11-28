<#import "parts/common.ftl" as c>
<#import "parts/company.ftl" as comp>
<#import "parts/login.ftl" as l>
<#import "parts/admin.ftl" as adm>
<#import "parts/train.ftl" as t_mu>

<@c.page "${header_page}">
     <@c.auth/>
    <#if errors??>
        <#list errors as error>
            <p class=error>
                ${error}
            </p>
        </#list>
     </#if>
    <#if trainMuseum??>
        <@t_mu.train_museum_info trainMuseum, trainMuseumFields/>
    </#if>
    <#if company??>
        <@comp.company_info "${company.name}", "${company.cash}", "${company.trainCount}"/>
    </#if>
    <@adm.table_out listValue, "Update", "button_remove_on"/>
</@c.page>
