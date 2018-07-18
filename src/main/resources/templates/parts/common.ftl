<#macro page title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<#nested>
</body>
</html>
</#macro>

<#macro save_form>
    <h1>Hello ${user}</h1>

    <h2>${header_page}</h2>
    <form action="/${path_page}" method="post">
            <input type="hidden" name="id"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <table>
            <#nested>
            <tr><td></td><td><input type="submit" value="Add new train"/></td></tr>
        </table>
    </form>
</#macro>