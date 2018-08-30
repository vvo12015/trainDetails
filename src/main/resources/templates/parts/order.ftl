<#macro my_orders orders>
    <table>
        <tr>
            <th> Id </th>
            <th> Route </th>
            <th> Train</th>
            <th> Cargo</th>
            <th> State</th>
            <th> Car count</th>
            <th> Full wear</th>
            <th> Profit</th>
            <th> Creation date</th>
            <th> Waiting deadline</th>
            <th> Deadline1</th>
            <th> Deadline2</th>
        </tr>
    <#list orders as order>
        <tr>
            <td> ${order.id} </td>
            <td> ${order.route} </td>
            <td> ${order.train.name}</td>
            <td> ${order.cargo.name}</td>
            <td> ${order.state.name}</td>
            <td> ${order.carCount}</td>
            <td> ${order.fullWear}</td>
            <td> ${order.profit}</td>
            <td> ${order.creationDate?date}</td>
            <td> ${order.waitingDeadline?date}</td>
            <td> ${order.deadline1?date}</td>
            <td> ${order.deadline2?date}</td>
        </tr>
    </#list>
    </table>
</#macro>