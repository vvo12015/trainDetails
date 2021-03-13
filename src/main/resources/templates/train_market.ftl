<#import "parts/common.ftl" as c>
<#import "parts/company.ftl" as comp>
<#import "parts/login.ftl" as l>
<#import "parts/train.ftl" as t_mu>

<@c.page "${header_page}">
    <@c.auth/>
    <@comp.company_info "${company.name}", "${company.cash}", "${company.getTrainCount}"/>
     <@t_mu.museum_out trainMuseum, "Buy", "button_remove_off">
     </@t_mu.museum_out>
</@c.page>
