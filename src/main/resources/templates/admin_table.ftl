<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/admin.ftl" as adm>
<#import "parts/train.ftl" as t_mu>

<@l.logout />
<@c.page "${header_page}">
    <@adm.table_out listValue, "Update", "button_remove_on"/>
</@c.page>
