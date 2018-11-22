<#macro my_trains trains>
    <table>
        <tr>
            <th> Orders</th>
            <th> Id </th>
            <th> Name </th>
            <th> Creation date</th>
            <th> State</th>
            <th> City</th>
        </tr>
    <#list trains as train>
        <tr>
            <td>
                <a href="/order/train${train.id}">Orders</a>
            </td>
            <td><input type="text" name="id" value="${train.id?c}" readonly/> </td>
            <td><input type="text" name="name" value="${train.name}" readonly/> </td>
            <td><input type="text" name="creationDate" value="${train.creationDate}"  readonly</td>
            <td><input type="text" name="power" value="${train.corpsState?c}"  readonly</td>
            <td>
                <#if train.city??>
                    <#assign city = train.city>
                    <#else>
                    city = "-";
                </#if>
                <input type="text" name="city" value="${city}"  readonly>
            </td>
        </tr>
    </#list>
    </table>
</#macro>
