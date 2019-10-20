<#import "common.ftl" as c>

<#macro table_out listValue fields button_name button_remove>
    <#if !save_not??>
        <@c.save_form>
        </@c.save_form>
    </#if>
    <#if refreshTrain??>
        <a href="/order">Refresh</a>
    </#if>
    <table>
        <tr>
            <#if listValue[0]?? >
                <#if listValue[0]["buttons"]??>
                    <#list listValue[0]["buttons"] as button>
                    <#assign head_button_name = button>
                        <#assign indexSymbol = head_button_name?index_of("_")>
                        <#if (indexSymbol > 0)>
                            <#assign head_button_name = head_button_name?substring(0,indexSymbol)>
                        </#if>
                        <th>${head_button_name}</th>
                    </#list>
                </#if>
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
            <#list value["buttons"] as button>
                <#assign first_button_name = button>
                <form action="/${path_page}_${first_button_name}/${value["id"]}">
                    <td>
                        <#assign indexSymbol = first_button_name?index_of("_")>
                        <#if (indexSymbol > 0)>
                            <#assign first_button_name = first_button_name?substring(0,indexSymbol)>
                        </#if>
                        <input type="submit" value="${first_button_name}"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <#if trainMuseum??>
                            <input type="hidden" name="trainMuseumId" value="${trainMuseum["id"]}"/>
                        </#if>
                    </td>
                </form>
            </#list>
            <form action="/${path_page}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <td><input type="submit" value="${button_name}"/></td>
            <#if "${button_remove}" == "button_remove_on">
                 <td>
                        <a href="/${path_page}_remove/${value["id"]}">Видалити</a>
                 </td>
            </#if>

            <#list fields as field>
                <td><input type="text" name="${field}" value="${value["${field}"]}"/> </td>
            </#list>
            <#nested>
            </form>
        </tr>
    </#list>
    </table>
</#macro>