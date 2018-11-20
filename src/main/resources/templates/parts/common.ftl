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
            width: 50;
        }

        input {
            width: 100;
        }
    </style>
</head>
<body>
<#nested>
</body>
</html>
</#macro>

<#macro save_form>
    <h1>Hello <#if user??>${user.username}</#if></h1>

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
                            <td>${field?capitalize}</td>
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
                            <td> ${field?capitalize}</td>
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