<#import "parts/common.ftl" as c>
<#import "parts/company.ftl" as comp>
<#import "parts/login.ftl" as l>
<#import "parts/order.ftl" as o>

<@c.page "My orders">
    <@c.auth/>
    <@comp.company_info "${company.name}", "${company.cash}", "${company.trainCount}"/>
     <@o.my_orders orders>
     </@o.my_orders>
</@c.page>
