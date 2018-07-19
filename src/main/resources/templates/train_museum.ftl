<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@l.logout />
<@c.page "${header_page}">
    <@c.save_form>
        <input type="hidden" name="id"/>
        <tr><td> Name </td><td><input type="text" name="name"/> </td></tr>
        <tr><td> Description: </td><td><input type="text" name="description"/> </td></tr>
        <tr><td> Power: </td><td><input type="text" name="power"/> </td></tr>
        <tr><td> Price: </td><td><input type="text" name="price"/> </td></tr>
        <tr><td> Mass: </td><td><input type="text" name="mass"/> </td></tr>
        <tr><td> CorpsPrice: </td><td><input type="text" name="corpsPrice"/> </td></tr>
        <tr><td> Reliability: </td><td><input type="text" name="reliability"/> </td></tr>
        <tr><td> Limit age: </td><td><input type="text" name="limitAge"/> </td></tr>
        <tr><td> Corps wear: </td><td><input type="text" name="corpsWear"/> </td></tr>
    </@c.save_form>
<table>
    <tr>
        <th> Id </th>
        <th> Name </th>
        <th> Description</th>
        <th> Power</th>
        <th> Price</th>
        <th> Mass</th>
        <th> CorpsPrice</th>
        <th> Reliability</th>
        <th> Limit_age</th>
        <th> Corps_wear</th>
        <th> Update</th>
        <th> Remove</th>
    </tr>
    <#list trainMuseum as museum>
        <@c.update_form>
            <td>${museum.id}</td>
            <td><input type="text" name="name" value="${museum.name}"/> </td>
            <td><input type="text" name="description" value="${museum.description}" </td>
            <td><input type="text" name="power" value="${museum.power}" </td>
            <td><input type="text" name="price" value="${museum.price}" </td>
            <td><input type="text" name="mass" value="${museum.mass}" </td>
            <td><input type="text" name="corpsPrice" value="${museum.corpsPrice}" </td>
            <td><input type="text" name="reliability" value="${museum.reliability}" </td>
            <td><input type="text" name="limitAge" value="${museum.limitAge}" </td>
            <td><input type="text" name="corpsWear" value="${museum.corpsWear}" </td>
            <td><a href="/trainMuseum_remove/${museum.id}">Видалити</a></td>
        </@c.update_form>
    </#list>
</table>
</@c.page>
