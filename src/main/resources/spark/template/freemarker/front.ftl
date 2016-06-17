<!DOCTYPE html>
<html>
<head>
    <title>Registro de Estudiantes</title>
</head>
<body>
<h1>Listado de estudiantes</h1>
<table id="Table" border="1">
    <thead>
    <tr>
        <th><strong>Matricula</strong></th>
        <th><strong>Nombre</strong></th>
        <th><strong>Apellidos</strong></th>
        <th><strong>Telefono</strong></th>
    </tr>
    </thead>
    <tbody>
    <#list lista as estudiante>
    <tr>
        <td name="matricula">${estudiante.getMatricula()}</td>
        <td name="nombre">${estudiante.getNombre()}</td>
        <td name="apellido">${estudiante.getApellido()}</td>
        <td name="telefono">${estudiante.getTelefono()}</td>
    </tr>
    </#list>
    </tbody>
</table>

<form  name="f1" action="http://localhost:4567/edit" method="GET" >
    <input id="Modificar" type="submit" name="Modificar" value="Modificar" />
    <input type="text" name="matricula" required />
</form>
<form name="f2" action="http://localhost:4567/delete" method="POST">
    <input id="Eliminar" type="submit" name="Eliminar" value="Eliminar"/>
    <input type="text" name="matricula" required />
</form>




<form name="f3" action="http://localhost:4567/create" method="get">
    <input type="submit" name="Crear" value="Crear"/>
</form>



</body>
</html>