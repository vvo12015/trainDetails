<#macro company_info>

    <div>Company name = ${company.name}</div>
    <div>Cash = ${company.cash}</div>

    <div>Train count = ${company.trains?size}</div>

    <div><a href="/my_trains">My trains</a><div>
    <div><a href="/order">Orders</a><div>
    <div><a href="/train_market">Train market</a><div>
</#macro>