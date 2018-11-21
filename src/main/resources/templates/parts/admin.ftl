<#import "common.ftl" as c>

<#macro table_out listValue button_name button_remove>
    <@c.save_form>
    </@c.save_form>
    <#if listMap["refreshTrain"]??>
        <a href="/order/train${listMap["refreshTrain"]}">Refresh</a>
    </#if>
    <table>
        <tr>
            <#if path_page == "order">
                <th>Action</th>
            </#if>
            <th> ${button_name}</th>
            <#if "${button_remove}" == "button_remove_on">
                <th> Remove</th>
            </#if>
            <#list fields as field>
                <th>${field}</th>
             </#list>
        </tr>
    <#list listValue as value>
        <tr>
            <#if value["button"]??>
                <form action="/${path_page}_${value["button"]}/${value["id"]}">
                    <td>
                        <input type="submit" value="${value["button"]}"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </td>
                </form>
            </#if>
            <form action="/${path_page}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <td><input type="submit" value="${button_name}"/></td>
            <#if "${button_remove}" == "button_remove_on">
                 <td>
                        <a href="/${path_page}_remove/${value["id"]}">Видалити</a>
                 </td>
            </#if>

            <#list fields as field>
                <td><input type="text" name="name" value="${value["${field}"]}"/> </td>
            </#list>
            <#nested>
            </form>
        </tr>
    </#list>
    </table>
</#macro>