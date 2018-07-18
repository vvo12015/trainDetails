<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@l.logout />
<@c.page "Hello page">
    <div>Hello user</div>
    <a href="/train_museum">Train museum</a>
</@c.page>