<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@l.logout />
<@c.page "${header_page}">
    <@c.save_form>
        <tr><div><td> Name </td><td><input type="text" name="name"/> </td></tr>
        <tr><div><td> Description: </td><td><input type="text" name="description"/> </td></tr>
        <tr><div><td> Power: </td><td><input type="text" name="power"/> </td></tr>
        <tr><div><td> Price: </td><td><input type="text" name="price"/> </td></tr>
        <tr><div><td> Mass: </td><td><input type="text" name="mass"/> </td></tr>
        <tr><div><td> CorpsPrice: </td><td><input type="text" name="corpsPrice"/> </td></tr>
        <tr><div><td> Reliability: </td><td><input type="text" name="reliability"/> </td></tr>
        <tr><div><td> Limit age: </td><td><input type="text" name="limitAge"/> </td></tr>
        <tr><div><td> Corps wear: </td><td><input type="text" name="corpsWear"/> </td></tr>
    </@c.save_form>
<table>
    <tr>
        <th> Name </th>
        <th> Description</th>
        <th> Power</th>
        <th> Price</th>
        <th> Mass</th>
        <th> CorpsPrice</th>
        <th> Reliability</th>
        <th> Limit_age</th>
        <th> Corps_wear</th>    
    </tr>
    <#list trainMuseum as museum>
        <tr>
            <td>${museum.name}</td>
            <td>${museum.description}</td>
            <td>${museum.power}</td>
            <td>${museum.price}</td>
            <td>${museum.mass}</td>
            <td>${museum.corpsPrice}</td>
            <td>${museum.reliability}</td>
            <td>${museum.limitAge}</td>
            <td>${museum.corpsWear}</td>
        </tr>
    </#list>

</table>
</@c.page>
