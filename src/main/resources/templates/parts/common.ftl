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
    <h1>Hello ${user}</h1>

    <h2>${header_page}</h2>
    <form action="/${path_page}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <table>
            <tr><td></td><td><input type="submit" value="Add new train"/></td></tr>
            <#nested>
        </table>
    </form>
</#macro>