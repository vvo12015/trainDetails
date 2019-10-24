<#macro page title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <style type="text/css">
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            text-align: center;
            width: 150;
        }

        input[type=text] {
            width: 70px;
        }
        .error{
            color:red;
        }
    </style>
</head>
<body>
<#nested>
</body>
</html>
</#macro>

<#macro save_form>
    <h2>${header_page}</h2>
    <form action="/${path_page}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <table>
            <tr><td></td><td><input type="submit" value="Add"/></td></tr>
            <input type="hidden" name="id"/>
                <#list fields as field>
                    <#if field != "id">
                        <#if field?item_parity_cap = "Odd">
                            <tr>
                            <td>
                                <#if field?starts_with("list.")>
                                    ${field?substring(5, field?length)?capitalize}
                                <#else> ${field?capitalize}
                                </#if>
                            </td>
                            <td>
                                <input list="${field}List" type="text" name="${field}"/>
                                <#if listMap["${field}"]??>
                                    <datalist id="${field}List">
                                        <#list listMap["${field}"] as item>
                                            <option value=${item["name"]}>${item["id"]}</option>
                                        </#list>
                                    </datalist>
                                </#if>
                            </td>
                        <#else>
                            <td>
                                <#if field?starts_with("list.")>
                                    ${field?substring(5, field?length)?capitalize}
                                <#else> ${field?capitalize}
                                </#if>
                            </td>
                            <td>
                                <input list="${field}List" type="text" name="${field}"/>
                                <#if listMap["${field}"]??>
                                    <datalist id="${field}List">
                                        <#list listMap["${field}"] as item>
                                            <option value=${item["name"]}>${item["id"]}</option>
                                        </#list>
                                    </datalist>
                                </#if>
                            </td>
                            </tr>
                        </#if>
                    </#if>
                </#list>
            <#nested>
        </table>
    </form>
</#macro>

<#macro auth>
    <#if user??>
        <h1>Hello ${user.username}</h1>
        <@l.logout />
    <#else>
        <h1>Hello guest</h1>
        <center><@l.login_form /></center>
        <center><@l.registration /></center>
    </#if>
    <a href="/">Home</a>
</#macro>