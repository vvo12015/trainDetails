<#import "parts/common.ftl" as c>
<#import "parts/company.ftl" as comp>
<#import "parts/login.ftl" as l>
<#import "parts/train.ftl" as t_mu>

<@c.page "${header_page}">
    <@c.auth/>
    <@comp.company_info "${company.name}", "${company.cash}", "${company.trainCount}"/>
     <@t_mu.my_trains trains>
     </@t_mu.my_trains>
</@c.page>
