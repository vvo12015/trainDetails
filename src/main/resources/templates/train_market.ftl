<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/company.ftl" as comp>
<#import "parts/train_museum.ftl" as t_mu>


<@c.page "${header_page}">
    <@comp.company_info "${company.name}", "${company.cash}", "${company.trainCount}"/>
    <@l.logout />
     <@t_mu.museum_out trainMuseum, "Buy", "button_remove_off">
     </@t_mu.museum_out>
</@c.page>
