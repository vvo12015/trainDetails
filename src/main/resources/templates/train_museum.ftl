<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/train_museum.ftl" as t_mu>

<@l.logout />
<@c.page "${header_page}">
    <@c.save_form>
        <input type="hidden" name="id"/>
        <tr><td> Name </td><td><input type="text" name="name"/> </td></tr>
        <tr><td> Description: </td><td><input type="text" name="description"/> </td></tr>
        <tr><td> Power: </td><td><input type="text" name="power"/> </td></tr>
        <tr><td> Price: </td><td><input type="text" name="price"/> </td></tr>
        <tr><td> Speed: </td><td><input type="text" name="speed"/> </td></tr>
        <tr><td> Mass: </td><td><input type="text" name="mass"/> </td></tr>
        <tr><td> CorpsPrice: </td><td><input type="text" name="corpsPrice"/> </td></tr>
        <tr><td> Reliability: </td><td><input type="text" name="reliability"/> </td></tr>
        <tr><td> Limit age: </td><td><input type="text" name="limitAge"/> </td></tr>
        <tr><td> Corps wear: </td><td><input type="text" name="corpsWear"/> </td></tr>
    </@c.save_form>

    <@t_mu.museum_out trainMuseum, "Update", "button_remove_on">
    </@t_mu.museum_out>
</@c.page>
