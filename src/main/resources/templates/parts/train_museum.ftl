<#macro museum_out trainMuseum button_name button_remove>
    <table>
        <tr>
            <th> Id </th>
            <th> Name </th>
            <th> Description</th>
            <th> Power</th>
            <th> Price</th>
            <th> Speed</th>
            <th> Mass</th>
            <th> CorpsPrice</th>
            <th> Reliability</th>
            <th> Limit_age</th>
            <th> Corps_wear</th>
            <th> ${button_name}</th>
            <#if "${button_remove}" == "button_remove_on">
                <th> Remove</th>
            </#if>
        </tr>
    <#list trainMuseum as museum>
        <tr>
            <form action="/${path_page}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <td><input type="submit" value="${button_name}"/></td>

            <#if "${button_remove}" == "button_remove_on">
                <td>
                    <a href="/trainMuseum_remove/${museum.id}">Видалити</a>
                </td>
            </#if>
            <td><input type="text" name="id" value="${museum.id?c}" readonly/> </td>
            <td><input type="text" name="name" value="${museum.name}"/> </td>
            <td><input type="text" name="description" value="${museum.description}" </td>
            <td><input type="text" name="power" value="${museum.power?c}" </td>
            <td><input type="text" name="speed" value="${museum.speed?c}" </td>
            <td><input type="text" name="price" value="${museum.price?c}" </td>
            <td><input type="text" name="mass" value="${museum.mass?c}" </td>
            <td><input type="text" name="corpsPrice" value="${museum.corpsPrice?c}" </td>
            <td><input type="text" name="reliability" value="${museum.reliability?c}" </td>
            <td><input type="text" name="limitAge" value="${museum.limitAge?c}" </td>
            <td><input type="text" name="corpsWear" value="${museum.corpsWear?c}" </td>
            <#nested>
            </form>
        </tr>
    </#list>
    </table>
</#macro>