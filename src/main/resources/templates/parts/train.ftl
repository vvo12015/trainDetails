<#macro my_trains trains>
    <table>
        <tr>
            <th> Orders</th>
            <th> Details</th>
            <th> Id </th>
            <th> Name </th>
            <th> Creation date</th>
            <th> State</th>
            <th> City</th>
            <th> Status </th>
        </tr>
    <#list trains as train>
        <tr>
            <td>
                <a href="/order/train${train["id"]}">Orders</a>
            </td>
            <td>
                <a href="/detail/train${train["id"]}">Details</a>
            </td>
            <td><input type="text" name="id" value="${train["id"]}" readonly/> </td>
            <td><input type="text" name="name" value="${train["name"]}" readonly/> </td>
            <td><input type="text" name="creationDate" value="${train["creationDate"]}"  readonly</td>
            <td><input type="text" name="power" value="${train["corpsState"]}"  readonly</td>
            <td>
                <#if train["city"]??>
                    <#assign city = train["city"]>
                    <#else>
                    city = "-";
                </#if>
                <input type="text" name="city" value="${city}"  readonly>
            </td>
            <td><input type="text" name="status" value="${train["status"]}"  </td>
        </tr>
    </#list>
    </table>
</#macro>
<#macro train_museum_info trainMuseum trainMuseumFields>
    <table>
        <tr>
            <#list trainMuseumFields as field>
                <th> ${field}</th>
            </#list>
        </tr>
        <tr>
            <#list trainMuseumFields as field>
                <td>${trainMuseum["${field}"]}</td>
            </#list>
        </tr>
    </table>
</#macro>